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
package org.apache.camel.component.esper;

import java.util.List;

import net.esper.event.EventBean;
import net.esper.event.EventType;
import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.util.ObjectHelper;

/**
 * @version $Revision: 1.1 $
 */
public class EsperRouteTest extends ContextTestSupport {
    public void testSendMessagesIntoEsper() throws Exception {
        MockEndpoint endpoint = getMockEndpoint("mock:results");
        endpoint.expectedMessageCount(2);

        template.sendBody("direct:start", new MyEvent("a",1));
        template.sendBody("direct:start", new MyEvent("b",5));
        template.sendBody("direct:start", new MyEvent("c",1));
        template.sendBody("direct:start", new MyEvent("d",5));

        String[] expectedFoos = {"b", "d"};

        assertMockEndpointsSatisifed();
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

                from("esper://cheese?pattern=every event=org.apache.camel.component.esper.MyEvent(bar=5)").to("mock:results");
            }
        };
    }
}
