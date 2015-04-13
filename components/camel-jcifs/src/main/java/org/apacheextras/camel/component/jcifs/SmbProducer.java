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

import java.io.File;

import jcifs.smb.SmbFile;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.ServicePoolAware;
import org.apache.camel.component.file.GenericFileEndpoint;
import org.apache.camel.component.file.GenericFileExist;
import org.apache.camel.component.file.GenericFileOperationFailedException;
import org.apache.camel.component.file.GenericFileOperations;
import org.apache.camel.component.file.GenericFileProducer;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.spi.Language;
import org.apache.camel.util.ExchangeHelper;
import org.apache.camel.util.FileUtil;
import org.apache.camel.util.ObjectHelper;
import org.apache.camel.util.StringHelper;

public class SmbProducer extends GenericFileProducer<SmbFile> implements ServicePoolAware {

    private String endpointPath;

    protected SmbProducer(GenericFileEndpoint<SmbFile> endpoint, GenericFileOperations<SmbFile> operations) {
        super(endpoint, operations);

    }

    @Override
    public String getFileSeparator() {
        return File.separator;
    }

    @Override
    public String normalizePath(String name) {
        if (log.isDebugEnabled()) {
            log.debug("normalizePath() name[" + name + "]");
            log.debug("normalizePath() returning [" + name.replace('\\', '/') + "]");
        }
        return name.replace('\\', '/');
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        Exchange smbExchange = getEndpoint().createExchange(exchange);
        setEndpointPath(getEndpoint().getEndpointUri());
        processExchange(smbExchange);
        ExchangeHelper.copyResults(exchange, smbExchange);
    }

    public void setEndpointPath(String endpointPath) {
        this.endpointPath = endpointPath;
    }

    public String getEndpointPath() {
        return endpointPath;
    }

    @Override
    public void postWriteCheck() {
        //not at this time
    }

    protected void processExchange(Exchange exchange) throws Exception {
        if (log.isDebugEnabled()) {
            log.trace("Processing " + exchange);
        }

        try {
            String target = createFileName(exchange);
            if (log.isDebugEnabled()) {
                log.debug("processExchange() target[" + target + "]");
            }

            preWriteCheck();

            // should we write to a temporary name and then afterwards rename to real target
            boolean writeAsTempAndRename = ObjectHelper.isNotEmpty(endpoint.getTempFileName());
            String tempTarget = null;
            if (writeAsTempAndRename) {
                // compute temporary name with the temp prefix
                tempTarget = createTempFileName(exchange, target);
                if (log.isDebugEnabled()) {
                    log.debug("Writing using tempNameFile: " + tempTarget);
                }

                // delete any pre existing temp file
                if (operations.existsFile(tempTarget)) {
                    if (log.isDebugEnabled()) {
                        log.debug("Deleting existing temp file: " + tempTarget);
                    }
                    if (!operations.deleteFile(tempTarget)) {
                        throw new GenericFileOperationFailedException("Cannot delete file: " + tempTarget);
                    }
                }
            }

            // write/upload the file
            writeFile(exchange, tempTarget != null ? tempTarget : target);

            // if we did write to a temporary name then rename it to the real
            // name after we have written the file
            if (tempTarget != null) {

                // if we should not eager delete the target file then do it now just before renaming
                if (!endpoint.isEagerDeleteTargetFile() && operations.existsFile(target)
                        && endpoint.getFileExist() == GenericFileExist.Override) {
                    // we override the target so we do this by deleting it so the temp file can be renamed later
                    // with success as the existing target file have been deleted
                    if (log.isDebugEnabled()) {
                        log.debug("Deleting existing file: " + target);
                    }
                    if (!operations.deleteFile(target)) {
                        throw new GenericFileOperationFailedException("Cannot delete file: " + target);
                    }
                }

                // now we are ready to rename the temp file to the target file
                if (log.isDebugEnabled()) {
                    log.debug("Renaming file: [" + tempTarget + "] to: [" + target + "]");
                }
                boolean renamed = operations.renameFile(tempTarget, target);
                if (!renamed) {
                    throw new GenericFileOperationFailedException("Cannot rename file from: " + tempTarget + " to: " + target);
                }
            }

            // any done file to write?
            if (endpoint.getDoneFileName() != null) {
                String doneFileName = getEndpoint().createDoneFileName(target);
                ObjectHelper.notEmpty(doneFileName, "doneFileName", endpoint);

                // create empty exchange with empty body to write as the done file
                Exchange empty = new DefaultExchange(exchange);
                empty.getIn().setBody("");

                log.trace("Writing done file: [{}]", doneFileName);
                // delete any existing done file
                if (operations.existsFile(doneFileName) && !operations.deleteFile(doneFileName)) {
                    throw new GenericFileOperationFailedException("Cannot delete existing done file: " + doneFileName);
                }
                writeFile(empty, doneFileName);
            }

            // lets store the name we really used in the header, so end-users
            // can retrieve it
            exchange.getIn().setHeader(Exchange.FILE_NAME_PRODUCED, target);
        } catch (Exception e) {
            handleFailedWrite(exchange, e);
        }

        postWriteCheck();
    }

    @Override
    public String createFileName(Exchange exchange) {
        String answer;

        String name = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);


        // expression support
        Expression expression = endpoint.getFileName();
        if (name != null && StringHelper.hasStartToken(name, "simple")) {
            // the header name can be an expression too, that should override
            // whatever configured on the endpoint
             if (log.isDebugEnabled()) {
               log.debug(Exchange.FILE_NAME + " contains a Simple expression: " + name);
             }
          Language language = getEndpoint().getCamelContext().resolveLanguage("file");
          expression = language.createExpression(name);
        }
        if (expression != null) {
            if (log.isDebugEnabled()) {
                log.debug("Filename evaluated as expression: " + expression);
            }
            name = expression.evaluate(exchange, String.class);
        }

        // flatten name
        if (name != null && endpoint.isFlatten()) {
            int pos = name.lastIndexOf(getFileSeparator());
            if (pos == -1) {
                pos = name.lastIndexOf('/');
            }
            if (pos != -1) {
                name = name.substring(pos + 1);
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("createFilename() name[" + name + "]");
        }
        // compute path by adding endpoint starting directory
        String endpointPath = endpoint.getConfiguration().getDirectory();
        if (log.isDebugEnabled()) {
            log.debug("createFileName() enpointPath [" + endpointPath + "]");
        }
        // Its a directory so we should use it as a base path for the filename
        // If the path isn't empty, we need to add a trailing / if it isn't already there
        String baseDir = "";
        if (endpointPath.length() > 0) {
            baseDir = endpointPath + (endpointPath.endsWith(getFileSeparator()) ? "" : getFileSeparator());
        }
        if (name != null) {
            answer = baseDir + name;
        } else {
            // use a generated filename if no name provided
            answer = baseDir + endpoint.getGeneratedFileName(exchange.getIn());
        }

        if (endpoint.getConfiguration().needToNormalize()) {
            // must normalize path to cater for Windows and other OS
            answer = normalizePath(answer);
        }

        return answer;
    }

    @Override
    public void writeFile(Exchange exchange, String fileName) throws GenericFileOperationFailedException {

        if (log.isDebugEnabled()) {
            log.debug("writeFile() fileName[" + fileName + "]");
        }
        // build directory if auto create is enabled
        if (endpoint.isAutoCreate()) {
            // we must normalize it (to avoid having both \ and / in the name which confuses java.io.File)
            String name = FileUtil.normalizePath(fileName);

            // use java.io.File to compute the file path
            File file = new File(name);
            String directory = file.getParent();
            boolean absolute = FileUtil.isAbsolute(file);
            if (directory != null && !operations.buildDirectory(directory, absolute)) {
              log.warn("Cannot build directory [" + directory + "] (could be because of denied permissions)");
            }
        }

        // upload
        if (log.isDebugEnabled()) {
            log.debug("About to write [" + fileName + "] to [" + getEndpoint() + "] from exchange [" + exchange + "]");
        }

        boolean success = operations.storeFile(fileName, exchange);
        if (!success) {
            throw new GenericFileOperationFailedException("Error writing file [" + fileName + "]");
        }
        if (log.isDebugEnabled()) {
            log.debug("Wrote [" + fileName + "] to [" + getEndpoint() + "]");
        }
    }

    @Override
    public SmbEndpoint getEndpoint() {
        return (SmbEndpoint) super.getEndpoint();
    }

}
