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
package org.apacheextras.camel.component.rcode;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RCodeComponentTest {

    RCodeComponent component;

    @Before
    public void setUp() {
        CamelContext context = new DefaultCamelContext();
        component = new RCodeComponent();
        component.setCamelContext(context);
    }

    private static Map<String, Object> createParameters() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", "test");
        params.put("password", "test123");
        params.put("bufferSize", new Long(4194304));
        return params;
    }

    @Test
    public void testRCodeComponentCreateEndpoint() throws Exception {
        Map<String, Object> params = createParameters();
        String uri = "rcode://localhost:1234";
        Endpoint endpoint = component.createEndpoint(uri, "/eval", params);
        assertNotNull(endpoint);
        assertEquals(uri, endpoint.getEndpointUri());
        assertEquals(params.get("user"), endpoint.getEndpointConfiguration().getParameter("user"));
        assertEquals(params.get("password"), endpoint.getEndpointConfiguration().getParameter("password"));
        assertEquals(params.get("bufferSize"), endpoint.getEndpointConfiguration().getParameter("bufferSize"));
    }
}
