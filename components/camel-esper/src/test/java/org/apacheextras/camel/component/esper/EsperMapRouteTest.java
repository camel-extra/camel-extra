/**
 * ************************************************************************************
 * https://camel-extra.github.io
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 * http://www.gnu.org/licenses/gpl-2.0-standalone.html
 **************************************************************************************
 */
package org.apacheextras.camel.component.esper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

public class EsperMapRouteTest extends CamelTestSupport {

    private Map<String, Object> firstEvent;
    private Map<String, Object> secondEvent;
    private List<Map<String, Object>> expectedBodies = new ArrayList<Map<String, Object>>();

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Setup first event
        firstEvent = new HashMap<String, Object>();
        firstEvent.put("id", 1);
        firstEvent.put("evt_value", 10);
        firstEvent.put("event", new MyEvent("evt_1", 10));
        expectedBodies.add(firstEvent);
        // Setup second event
        secondEvent = new HashMap<String, Object>();
        secondEvent.put("id", 2);
        secondEvent.put("evt_value", 75);
        secondEvent.put("event", new MyEvent("evt_2", 75));
        expectedBodies.add(secondEvent);
    }

    @Test
    public void testSendMapEvents() {
        final MockEndpoint endpoint = getMockEndpoint("mock:results");
        template.sendBody("direct://start", firstEvent);
        template.sendBody("direct://start", secondEvent);
        endpoint.expectedBodiesReceived(expectedBodies);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Route to publish map events to
                from("direct://start").to("log://map-test-1?level=INFO").to("esper://map-test");
                // Esper consumer filtering map events
                from("esper://map-test?pattern=every event=java.util.HashMap").to("log://map-test-2?level=INFO").to("mock://results");
            }
        };
    }
}
