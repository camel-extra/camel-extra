/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.exist;

import java.util.List;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.exist.xmldb.DatabaseImpl;
import org.xmldb.api.DatabaseManager;

public class ExistRouteTest extends ContextTestSupport {
    private DatabaseImpl database;

    public void testSendMessagesIntoEsper() throws Exception {
        MockEndpoint endpoint = getMockEndpoint("mock:results");
        endpoint.expectedMessageCount(2);

        template.sendBody("direct:start", "<order product='beer' amount='5' id='a'/>");
        template.sendBody("direct:start", "<order product='pizza' amount='2' id='b'/>");
        template.sendBody("direct:start", "<order product='beer' amount='3' id='c'/>");

        String[] expectedFoos = {"b", "d"};

        assertMockEndpointsSatisfied();
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
