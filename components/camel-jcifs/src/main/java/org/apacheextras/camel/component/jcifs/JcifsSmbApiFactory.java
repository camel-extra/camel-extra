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

import jcifs.context.SingletonContext;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

/**
 * Default implementation of the the {@link SmbApiFactory} uses the JCIFS API.
 */
public class JcifsSmbApiFactory implements SmbApiFactory {

    @Override
    public SmbFile createSmbFile(String url, NtlmPasswordAuthenticator authenticator) throws IOException {
        return new SmbFile(url, SingletonContext.getInstance().withCredentials(authenticator));
    }

    @Override
    public SmbFileOutputStream createSmbFileOutputStream(SmbFile smbFile, boolean b) throws IOException {
        return new SmbFileOutputStream(smbFile, b);
    }

}
