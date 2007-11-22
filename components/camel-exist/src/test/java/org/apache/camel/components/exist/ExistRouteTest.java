/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.components.exist;

import java.util.List;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.exist.xmldb.DatabaseImpl;
import org.xmldb.api.DatabaseManager;

/**
 * @version $Revision: 1.1 $
 */
public class ExistRouteTest extends ContextTestSupport {
    private DatabaseImpl database;

    public void testSendMessagesIntoEsper() throws Exception {
        MockEndpoint endpoint = getMockEndpoint("mock:results");
        endpoint.expectedMessageCount(2);

        template.sendBody("direct:start", "<order product='beer' amount='5' id='a'/>");
        template.sendBody("direct:start", "<order product='pizza' amount='2' id='b'/>");
        template.sendBody("direct:start", "<order product='beer' amount='3' id='c'/>");

        String[] expectedFoos = {"b", "d"};

        assertMockEndpointsSatisifed();
        List<Exchange> list = endpoint.getReceivedExchanges();
        int counter = 0;
        for (Exchange exchange : list) {
            Object value = exchange.getIn().getBody();
            System.out.println("Received: " + value);
        }
    }

    @Override
    protected void setUp() throws Exception {
        database = new DatabaseImpl();
        database.setProperty("create-database", "true");
        //database.setProperty("configuration", "src/test/resources/conf.xml");
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