/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.esper;

import java.util.List;

import com.espertech.esper.client.EventBean;
import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;

public class EsperRouteTest extends ContextTestSupport {

    @SuppressWarnings("deprecation")
	public void testSendMessagesIntoEsper() throws Exception {
        MockEndpoint endpoint = getMockEndpoint("mock:results");
        endpoint.expectedMessageCount(2);

        template.sendBody("direct:start", new MyEvent("a", 1));
        template.sendBody("direct:start", new MyEvent("b", 5));
        template.sendBody("direct:start", new MyEvent("c", 1));
        template.sendBody("direct:start", new MyEvent("d", 5));

        String[] expectedFoos = {"b", "d"};

        assertMockEndpointsSatisfied();
        List<Exchange> list = endpoint.getReceivedExchanges();
        int counter = 0;
        for (Exchange exchange : list) {
            Object value = exchange.getIn().getBody();
            EventBean eventBean = assertIsInstanceOf(EventBean.class, value);
            Object event = eventBean.get("event");
            System.out.println("Received: " + event);
            MyEvent myEvent = assertIsInstanceOf(MyEvent.class, event);

            assertEquals("foo[" + counter + "]", expectedFoos[counter++], myEvent.getFoo());
        }
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("direct:start").to("esper://cheese");

                from("esper://cheese?pattern=every event=org.apachextras.camel.component.esper.MyEvent(bar=5)").to("mock:results");
            }
        };
    }
}
