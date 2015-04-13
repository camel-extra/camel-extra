/**************************************************************************************
 https://camel-extra.github.io

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
 ***************************************************************************************/
package org.apacheextras.camel.component.neo4j;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apacheextras.camel.component.neo4j.Neo4jEndpoint;
import org.apacheextras.camel.component.neo4j.Neo4jOperation;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.graphdb.Node;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

@Ignore("This test need to start the neo4j server first")
public class RestNeo4jProducerCreateNodeIntegrationTest extends CamelTestSupport {
   
    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    private final String neo4jEndpoint = "spring-neo4j:http://localhost:7474/db/data/";

    private final SpringRestGraphDatabase db = new SpringRestGraphDatabase("http://localhost:7474/db/data/");

   
    @EndpointInject(uri = "mock:end")
    private MockEndpoint end;

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("direct:start").to(neo4jEndpoint).process(new Processor() {

                    @Override
                    public void process(Exchange arg0) throws Exception {
                        Long id = (Long)arg0.getIn().getHeader(Neo4jEndpoint.HEADER_NODE_ID);
                        assertNotNull(id);
                        Node node = db.getNodeById(id);
                        assertNotNull(node);
                    }
                }).to(end);
            }
        };
    }

    @Test
    public void testCreateNodes() throws InterruptedException {

        final int messageCount = 100;
        end.expectedMessageCount(messageCount);

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int k = 0; k < messageCount; k++) {
                    template.sendBodyAndHeader(null, Neo4jEndpoint.HEADER_OPERATION,
                                               Neo4jOperation.CREATE_NODE);
                }
            }
        });
        t.start();
        t.join();
        end.assertIsSatisfied();
    }

}
