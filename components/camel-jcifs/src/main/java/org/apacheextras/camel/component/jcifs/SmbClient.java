package org.apacheextras.camel.component.jcifs;

import jcifs.smb.SmbFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface SmbClient {
    void login(String domain, String username, String password);

    boolean retrieveFile(String url, OutputStream out)
        throws IOException;

    boolean createDirs(String url);

    InputStream getInputStream(String url) throws IOException;

    boolean storeFile(String url, InputStream inputStream, boolean append)
            throws IOException;

    List<SmbFile> listFiles(String url) throws IOException;

    boolean isExist(String url) throws Exception;

    boolean delete(String url) throws Exception;

    boolean rename(String fromUrl, String toUrl) throws Exception;
}
