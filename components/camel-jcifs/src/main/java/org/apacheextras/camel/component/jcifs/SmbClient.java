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

    boolean storeFile(String url, InputStream inputStream, boolean append, Long lastModified)
            throws IOException;

    List<SmbFile> listFiles(String url) throws IOException;

    boolean isExist(String url) throws Exception;

    boolean delete(String url) throws Exception;

    boolean rename(String fromUrl, String toUrl) throws Exception;
}
