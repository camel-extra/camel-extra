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
package org.apacheextras.camel.component.zeromq;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ZeromqComponentTest {

    @Mock
    private CamelContext context;

    @Test
    public void testEndpointCreated() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();

        String uri = "zeromq:tcp://localhost:5555";
        String remaining = "tcp://localhost:5555";

        ZeromqComponent comp = new ZeromqComponent();
        comp.setCamelContext(context);
        ZeromqEndpoint endpoint = comp.createEndpoint(uri, remaining, params);
        assertNotNull(endpoint);
    }

    @Test
    public void testPropertiesSet() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("asyncConsumer", false);
        params.put("messageIdEnabled", true);
        params.put("topics", "coldplay,cream");
        params.put("messageConvertor", ThreadDeath.class);
        params.put("socketType", ZeromqSocketType.PULL);
        params.put("linger", 100);
        params.put("highWaterMark", 51);

        String uri = "zeromq:tcp://localhost:5555";
        String remaining = "tcp://localhost:5555";

        ZeromqComponent comp = new ZeromqComponent();
        comp.setCamelContext(context);
        ZeromqEndpoint endpoint = comp.createEndpoint(uri, remaining, params);

        assertEquals("tcp", endpoint.getProtocol());
        assertEquals("localhost", endpoint.getHostname());
        assertEquals(5555, endpoint.getPort());
        assertEquals("coldplay,cream", endpoint.getTopics());
        assertEquals(ZeromqSocketType.PULL, endpoint.getSocketType());
        assertEquals(ThreadDeath.class, endpoint.getMessageConvertor());
        assertEquals(100, endpoint.getLinger());
        assertEquals(51, endpoint.getHighWaterMark());
        assertFalse(endpoint.isAsyncConsumer());
        assertTrue(endpoint.isMessageIdEnabled());
    }
}
