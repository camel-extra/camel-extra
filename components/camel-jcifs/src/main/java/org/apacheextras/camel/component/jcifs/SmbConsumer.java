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

import java.io.IOException;
import java.util.List;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileConsumer;
import org.apache.camel.component.file.GenericFileEndpoint;
import org.apache.camel.component.file.GenericFileOperations;
import org.apache.camel.util.FileUtil;
import org.apache.camel.util.ObjectHelper;

public class SmbConsumer extends GenericFileConsumer<SmbFile> {

    private String endpointPath;
    private String currentRelativePath = "";

    public SmbConsumer(GenericFileEndpoint<SmbFile> endpoint, Processor processor, GenericFileOperations<SmbFile> operations) {
        super(endpoint, processor, operations);
        this.endpointPath = endpoint.getConfiguration().getDirectory();
    }

    @Override
    protected boolean pollDirectory(String fileName, List<GenericFile<SmbFile>> fileList, int depth) {

        if (log.isTraceEnabled()) {
            log.trace("pollDirectory() running. My delay is [" + this.getDelay() + "] and my strategy is [" + this.getPollStrategy().getClass().toString() + "]");
            log.trace("pollDirectory() fileName[" + fileName + "]");
        }

        List<SmbFile> smbFiles;
        boolean currentFileIsDir = false;
        smbFiles = operations.listFiles(fileName);
        for (SmbFile smbFile : smbFiles) {
            if (!canPollMoreFiles(fileList)) {
                return false;
            }
            try {
                if (smbFile.isDirectory()) {
                    currentFileIsDir = true;
                } else {
                    currentFileIsDir = false;
                }
            } catch (SmbException e1) {
                throw ObjectHelper.wrapRuntimeCamelException(e1);
            }
            if (currentFileIsDir) {
                if (endpoint.isRecursive()) {
                    currentRelativePath = smbFile.getName().split("/")[0] + "/";
                    int nextDepth = depth++;
                    pollDirectory(fileName + "/" + smbFile.getName(), fileList, nextDepth);
                } else {
                    currentRelativePath = "";
                }
            } else {
                try {
                    GenericFile<SmbFile> genericFile = asGenericFile(fileName, smbFile);
                    if (isValidFile(genericFile, false, smbFiles)) {
                        fileList.add(asGenericFile(fileName, smbFile));
                    }
                } catch (IOException e) {
                    throw ObjectHelper.wrapRuntimeCamelException(e);
                }
            }
        }
        return true;
    }

    @Override
    protected void updateFileHeaders(GenericFile<SmbFile> genericFile, Message message) {
        // TODO
    }

    // TODO: this needs some checking!
    private GenericFile<SmbFile> asGenericFile(String path, SmbFile file) throws IOException {
        SmbGenericFile<SmbFile> answer = new SmbGenericFile<SmbFile>();
        answer.setAbsoluteFilePath(path + answer.getFileSeparator() + file.getName());
        answer.setAbsolute(true);
        answer.setEndpointPath(endpointPath);
        answer.setFileNameOnly(file.getName());
        answer.setFileLength(file.getContentLength());
        answer.setFile(file);
        answer.setLastModified(file.getLastModified());
        answer.setFileName(currentRelativePath + file.getName());
        answer.setRelativeFilePath(file.getName());

        if (log.isTraceEnabled()) {
            log.trace("asGenericFile():");
            log.trace("absoluteFilePath[" + answer.getAbsoluteFilePath() + "] endpointpath[" + answer.getEndpointPath() + "] filenameonly[" + answer.getFileNameOnly()
                      + "] filename[" + answer.getFileName() + "] relativepath[" + answer.getRelativeFilePath() + "]");
        }
        return answer;
    }

    @Override
    protected boolean isMatched(GenericFile<SmbFile> file, String doneFileName, List<SmbFile> files) {
        String onlyName = FileUtil.stripPath(doneFileName);

        for (SmbFile f : files) {
            if (f.getName().equals(onlyName)) {
                return true;
            }
        }

        log.trace("Done file: {} does not exist", doneFileName);
        return false;
    }
    
    @Override
    protected boolean isRetrieveFile() {
        return ((SmbEndpoint)getEndpoint()).isDownload();
    }

}
