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
package org.apacheextras.camel.component.jcifs.test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import org.apacheextras.camel.component.jcifs.SmbApiFactory;

public class StubFileSmbApiFactory implements SmbApiFactory {

    Map<String, SmbFile> smbFiles = new HashMap<String, SmbFile>();
    Map<String, SmbFileOutputStream> smbFileOutputStream = new HashMap<String, SmbFileOutputStream>();

    public SmbFile createSmbFile(String urlString, NtlmPasswordAuthentication authentication) throws MalformedURLException, SmbException {
        try {
            URI uri = new URI(urlString);
            return smbFiles.get(uri.getPath());
        } catch (URISyntaxException e) {
            throw new MalformedURLException(e.getLocalizedMessage());
        }
    }

    public SmbFileOutputStream createSmbFileOutputStream(SmbFile smbFile, boolean b) throws SmbException, MalformedURLException, UnknownHostException {
        return smbFileOutputStream.get(smbFile.getName());

    }

    public void putSmbFiles(String urlString, SmbFile file) throws URISyntaxException {
        URI uri = new URI(urlString);
        this.smbFiles.put(uri.getPath(), file);

    }

    public void putSmbFileOutputStream(String string, SmbFileOutputStream mockOutputStream) {
        this.smbFileOutputStream.put(string, mockOutputStream);

    }

}
