/**
 http://code.google.com/a/apache-extras.org/p/camel-extra

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.


 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/gpl-2.0-standalone.html
 */
package org.apacheextras.camel.component.jcifs;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

/**
 * Default implementation of the the {@link SmbApiFactory} uses the JCIFS API.
 *
 */
public class JcifsSmbApiFactory implements SmbApiFactory {

    public SmbFile createSmbFile(String url,
            NtlmPasswordAuthentication authentication)
        throws IOException {
        return new SmbFile(url, authentication);
    }

    public SmbFileOutputStream createSmbFileOutputStream(SmbFile smbFile,
            boolean b) throws IOException {
        return new SmbFileOutputStream(smbFile, b);
    }

}
