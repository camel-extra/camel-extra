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
package org.apacheextras.camel.component.exist;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.exist.xmldb.DatabaseImpl;
import org.junit.Test;
import org.xmldb.api.DatabaseManager;

import java.util.List;

public class ExistRouteTest extends CamelTestSupport {
    private DatabaseImpl database;

    @Test
    public void testSendMessagesIntoEsper() throws Exception {
        MockEndpoint endpoint = getMockEndpoint("mock:results");
        endpoint.expectedMessageCount(2);

        template.sendBody("direct:start", "<order product='beer' amount='5' id='a'/>");
        template.sendBody("direct:start", "<order product='pizza' amount='2' id='b'/>");
        template.sendBody("direct:start", "<order product='beer' amount='3' id='c'/>");

        assertMockEndpointsSatisfied();

        List<Exchange> list = endpoint.getReceivedExchanges();
        for (Exchange exchange : list) {
            Object value = exchange.getIn().getBody();
            log.info("Received {}", value);
        }
    }

    @Override
    public void setUp() throws Exception {
        database = new DatabaseImpl();
        database.setProperty("create-database", "true");
        // database.setProperty("configuration", "src/test/resources/conf.xml");
        database.setProperty("database-id", "test");
        DatabaseManager.registerDatabase(database);
        super.setUp();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("direct:start").to("xmldb:test:///db");

                from("xmldb:test:///db?xpath=/order/@product = 'beer'").to("mock:results");
            }
        };
    }
}
