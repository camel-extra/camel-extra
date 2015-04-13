/**************************************************************************************
 https://camel-extra.github.io

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation; either version 3
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.


 You should have received a copy of the GNU Lesser General Public
 License along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.jcifs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.FileComponent;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileEndpoint;
import org.apache.camel.component.file.GenericFileExist;
import org.apache.camel.component.file.GenericFileOperationFailedException;
import org.apache.camel.component.file.GenericFileOperations;
import org.apache.camel.util.ExchangeHelper;
import org.apache.camel.util.FileUtil;
import org.apache.camel.util.IOHelper;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmbOperations<SmbFile> implements GenericFileOperations<SmbFile> {
  private static final Logger LOGGER = LoggerFactory.getLogger(SmbOperations.class);

  private GenericFileEndpoint<SmbFile> endpoint;
  private SmbClient client;

  public SmbOperations(SmbClient smbClient) {
    this.client = smbClient;
  }

  @Override
  public void setEndpoint(GenericFileEndpoint<SmbFile> endpoint) {
    this.endpoint = endpoint;
  }

  @Override
  public boolean deleteFile(String name) {
    try {
      login();
      return client.delete(getPath(name));

    } catch (Exception e) {
      throw new GenericFileOperationFailedException("Could not delete file", e);
    }
  }

  @Override
  public boolean existsFile(String name) {
    try {
      login();
      return client.isExist(getPath(name));
    } catch (Exception e) {
      throw new GenericFileOperationFailedException("Could not determine if file exists", e);
    }
  }

  @Override
  public boolean renameFile(String from, String to) {
    final String fromPath = getPath(from);
    final String toPath = getPath(to);
    try {
      login();
      return client.rename(fromPath, toPath);
    } catch (final Exception e) {
      throw new GenericFileOperationFailedException("Could not rename file", e);
    }
  }

  @Override
  public boolean buildDirectory(String directory, boolean absolute) {
    try {
      login();
      return client.createDirs(getPath(directory));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return false;
    }
  }

  @Override
  public boolean retrieveFile(String name, Exchange exchange) {
    if (ObjectHelper.isNotEmpty(endpoint.getLocalWorkDirectory())) {
      return retrieveFileToFileInLocalWorkDirectory(name, exchange);
    }
    return retrieveFileToStreamInBody(name, exchange);
  }

  @SuppressWarnings("unchecked")
  private boolean retrieveFileToFileInLocalWorkDirectory(String name, Exchange exchange) {
    File temp;

    File local = new File(endpoint.getLocalWorkDirectory());
    local.mkdirs();
    OutputStream os;
    GenericFile<SmbFile> file = (GenericFile<SmbFile>) exchange.getProperty(FileComponent.FILE_EXCHANGE_FILE);
    ObjectHelper.notNull(file, "Exchange should have the " + FileComponent.FILE_EXCHANGE_FILE + " set");

    // use relative filename in local work directory
    String relativeName = file.getRelativeFilePath();
    temp = new File(local, relativeName + ".inprogress");
    local = new File(local, relativeName);

    // delete any existing files
    if (temp.exists() && (!FileUtil.deleteFile(temp))) {
      throw new GenericFileOperationFailedException("Cannot delete existing local work file: " + temp);
    }
    if (local.exists() && (!FileUtil.deleteFile(local))) {
      throw new GenericFileOperationFailedException("Cannot delete existing local work file: " + local);
    }
    // create new temp local work file
    try {
      if (!temp.createNewFile()) {
        throw new GenericFileOperationFailedException("Cannot create new local work file: " + temp);
      }
    } catch (IOException e1) {
      throw new GenericFileOperationFailedException("Cannot create new local work file: " + temp, e1);
    }

    // store content as a file in the local work directory in the temp handle
    try {
      os = new FileOutputStream(temp);
    } catch (FileNotFoundException e1) {
      throw new GenericFileOperationFailedException("File not found: " + temp, e1);
    }

    // set header with the path to the local work file
    exchange.getIn().setHeader(Exchange.FILE_LOCAL_WORK_PATH, local.getPath());

    boolean result;

    try {
      // store the java.io.File handle as the body
      file.setBody(local);
      login();
      result = client.retrieveFile(getPath(name), os);
    } catch (IOException e) {
      throw new GenericFileOperationFailedException("Cannot retrieve file: " + name, e);
    } catch (Exception e) {
      throw new GenericFileOperationFailedException("Cannot retrieve file: " + name, e);
    } finally {
      IOHelper.close(os, "retrieve: " + name);
    }

    try {
      if (!FileUtil.renameFile(temp, local, true)) {
        throw new GenericFileOperationFailedException("Cannot rename local work file from: " + temp + " to: " + local);
      }
    } catch (IOException e) {
      throw new GenericFileOperationFailedException("Cannot rename local work file from: " + temp + " to: " + local, e);
    }

    return result;
  }

  @SuppressWarnings("unchecked")
  private boolean retrieveFileToStreamInBody(String name, Exchange exchange) {
    OutputStream os = null;
    boolean result;
    try {
      os = new ByteArrayOutputStream();
      GenericFile<SmbFile> target = (GenericFile<SmbFile>) exchange.getProperty(FileComponent.FILE_EXCHANGE_FILE);
      ObjectHelper.notNull(target, "Exchange should have the " + FileComponent.FILE_EXCHANGE_FILE + " set");
      target.setBody(os);

      // use input stream which works with Apache SSHD used for testing
      login();
      result = client.retrieveFile(getPath(name), os);

    } catch (IOException e) {
      throw new GenericFileOperationFailedException("Cannot retrieve file: " + name, e);
    } catch (Exception e) {
      throw new GenericFileOperationFailedException("Cannot retrieve file: " + name, e);
    } finally {
      IOHelper.close(os, "retrieve: " + name);
    }

    return result;
  }

    private void doMoveExistingFile(String fileName) throws GenericFileOperationFailedException {
        // need to evaluate using a dummy and simulate the file first, to have access to all the file attributes
        // create a dummy exchange as Exchange is needed for expression evaluation
        // we support only the following 3 tokens.
        Exchange dummy = endpoint.createExchange();
        String parent = FileUtil.onlyPath(fileName);
        String onlyName = FileUtil.stripPath(fileName);
        dummy.getIn().setHeader(Exchange.FILE_NAME, fileName);
        dummy.getIn().setHeader(Exchange.FILE_NAME_ONLY, onlyName);
        dummy.getIn().setHeader(Exchange.FILE_PARENT, parent);

        String to = endpoint.getMoveExisting().evaluate(dummy, String.class);
        // we must normalize it (to avoid having both \ and / in the name which confuses java.io.File)
        to = FileUtil.normalizePath(to);
        if (ObjectHelper.isEmpty(to)) {
            throw new GenericFileOperationFailedException("moveExisting evaluated as empty String, cannot move existing file: " + fileName);
        }

        // ensure any paths is created before we rename as the renamed file may be in a different path (which may be non exiting)
        // use java.io.File to compute the file path
        File toFile = new File(to);
        String directory = toFile.getParent();
        boolean absolute = FileUtil.isAbsolute(toFile);
        if (directory != null) {
            if (!buildDirectory(directory, absolute)) {
                LOGGER.debug("Cannot build directory [{}] (could be because of denied permissions)", directory);
            }
        }

        // deal if there already exists a file
        if (existsFile(to)) {
            if (endpoint.isEagerDeleteTargetFile()) {
                LOGGER.trace("Deleting existing file: {}", to);
                if (!deleteFile(to)) {
                    throw new GenericFileOperationFailedException("Cannot delete file: " + to);
                }
            } else {
                throw new GenericFileOperationFailedException("Cannot moved existing file from: " + fileName + " to: " + to + " as there already exists a file: " + to);
            }
        }

        LOGGER.trace("Moving existing file: {} to: {}", fileName, to);
        if (!renameFile(fileName, to)) {
            throw new GenericFileOperationFailedException("Cannot rename file from: " + fileName + " to: " + to);
        }
    }

  @Override
  public boolean storeFile(String name, Exchange exchange) {
    boolean append = false;
    // if an existing file already exists what should we do?
    if (existsFile(name)) {
      if (endpoint.getFileExist() == GenericFileExist.Ignore) {
        // ignore but indicate that the file was written
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("An existing file already exists: " + name + ". Ignore and do not override it.");
        }
        return false;
      } else if (endpoint.getFileExist() == GenericFileExist.Fail) {
        throw new GenericFileOperationFailedException("File already exist: " + name + ". Cannot write new file.");
      } else if (endpoint.getFileExist() == GenericFileExist.Move) {
          // move any existing file first
          doMoveExistingFile(name);
      } else if (endpoint.isEagerDeleteTargetFile() && endpoint.getFileExist() == GenericFileExist.Override) {
        // we override the target so we do this by deleting it so the temp file can be renamed later
        // with success as the existing target file have been deleted
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("Eagerly deleting existing file: " + name);
        }
        if (!deleteFile(name)) {
          throw new GenericFileOperationFailedException("Cannot delete file: " + name);
        }
      } else if (endpoint.getFileExist() == GenericFileExist.Append) {
        append = true;
      }
    }


    String storeName = getPath(name);

    InputStream is = null;
    try {
      is = ExchangeHelper.getMandatoryInBody(exchange, InputStream.class);

      login();
      client.storeFile(storeName, is, append, lastModifiedDate(exchange));
      return true;
    } catch (Exception e) {
      throw new GenericFileOperationFailedException("Cannot store file " + storeName, e);
    } finally {
      IOHelper.close(is, "store: " + storeName);
    }
  }

  private Long lastModifiedDate(Exchange exchange) {
    Long last = null;
    if (endpoint.isKeepLastModified()) {
        Date date = exchange.getIn().getHeader(Exchange.FILE_LAST_MODIFIED, Date.class);
        if (date != null) {
            last = date.getTime();
        } else {
            // fallback and try a long
            last = exchange.getIn().getHeader(Exchange.FILE_LAST_MODIFIED, Long.class);
        }
    }
    return last;
  }

  @Override
  public String getCurrentDirectory() {
    return null;
  }

  @Override
  public void changeCurrentDirectory(String path) {
  }

  @Override
  public void changeToParentDirectory() {
  }

  @Override
  public List<SmbFile> listFiles() {
    return null;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<SmbFile> listFiles(String path) {
    String listPath = getDirPath(path);
    List<SmbFile> files = new ArrayList<SmbFile>();
    try {
      login();
      for (Object f : client.listFiles(listPath)) {
        files.add((SmbFile) f);
      }
    } catch (Exception e) {
      throw new GenericFileOperationFailedException("Could not get files " + e.getMessage(), e);
    }
    return files;
  }

  public void login() {
    String domain = ((SmbConfiguration) endpoint.getConfiguration()).getDomain();
    String username = ((SmbConfiguration) endpoint.getConfiguration()).getUsername();
    String password = ((SmbConfiguration) endpoint.getConfiguration()).getPassword();

    client.login(domain, username, password);
  }

  private String getPath(String pathEnd) {
    String path = ((SmbConfiguration) endpoint.getConfiguration()).getSmbHostPath() + pathEnd;
    return path.replace('\\', '/');
  }

  private String getDirPath(String pathEnd) {
    String path = ((SmbConfiguration) endpoint.getConfiguration()).getSmbHostPath() + pathEnd;
    if (!path.endsWith("/")) {
      path = path + "/";
    }
    return path.replace('\\', '/');
  }

  @Override
  public void releaseRetreivedFileResources(Exchange exchange) {
    // Right now do nothing
  }
}
