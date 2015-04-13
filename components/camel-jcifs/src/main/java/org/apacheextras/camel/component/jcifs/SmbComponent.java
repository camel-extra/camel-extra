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

import java.net.URI;
import java.util.Map;

import jcifs.smb.SmbFile;

import org.apache.camel.CamelContext;
import org.apache.camel.component.file.GenericFileComponent;
import org.apache.camel.component.file.GenericFileEndpoint;

public class SmbComponent extends GenericFileComponent<SmbFile> {
    
    private SmbApiFactory smbApiFactory;

    public SmbComponent() {

    }
    
    public SmbComponent(CamelContext context) {
        super(context);
    }
    
    
    public void setSmbApiFactoryClass(SmbApiFactory smbApiFactory) {
        this.smbApiFactory = smbApiFactory;
    }

    @Override
    protected SmbEndpoint buildFileEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("buildFileEndpoint() uri[" + uri + "] remaining[" + remaining + "] parameters[" + parameters + "]");
        }
        uri = fixSpaces(uri);
        SmbConfiguration config = new SmbConfiguration(new URI(uri), smbApiFactory);
        SmbEndpoint endpoint = new SmbEndpoint(uri, this, config);
        return endpoint;
    }

    @Override
    protected void afterPropertiesSet(GenericFileEndpoint<SmbFile> endpoint) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("afterPropertiesSet()");
        }
    }

    private String fixSpaces(String input) {
        return input.replace(" ", "%20");
    }


}
