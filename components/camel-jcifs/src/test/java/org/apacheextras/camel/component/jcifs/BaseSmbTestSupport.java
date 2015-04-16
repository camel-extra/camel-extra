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

import org.apache.camel.CamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apacheextras.camel.component.jcifs.test.StubFileSmbApiFactory;
import org.junit.Before;

public abstract class BaseSmbTestSupport extends CamelTestSupport {

    protected StubFileSmbApiFactory smbApiFactory;

    protected abstract void setUpFileSystem() throws Exception;

    @Before
    public void setUp() throws Exception {
        smbApiFactory = new StubFileSmbApiFactory();
        setUpFileSystem();
        super.setUp();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();
        SmbComponent component = (SmbComponent)context.getComponent("smb");
        component.setSmbApiFactoryClass(smbApiFactory);
        return context;
    }

    public String getUsername() {
        return "joed";
    }

    public String getDomain() {
        return "WORKGROUP";
    }

    public String getPassword() {
        return "secret123";
    }

    public String getShare() {
        return "share";
    }

    public String getLocalSharePath() {
        return "src/test/data";
    }
}
