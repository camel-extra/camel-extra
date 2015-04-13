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

import java.util.List;

import com.espertech.esper.client.EventBean;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class EsperPojoRouteTest extends CamelTestSupport {

  @Test
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
      EventBean newEvent = exchange.getIn(EsperMessage.class).getNewEvent();
      assertNotNull(newEvent);
      EventBean oldEvent = exchange.getIn(EsperMessage.class).getOldEvent();
      assertNull(oldEvent);

      Object value = exchange.getIn().getBody();
      EventBean eventBean = assertIsInstanceOf(EventBean.class, value);
      Object event = eventBean.get("event");
      log.info("Received " + event);
      MyEvent myEvent = assertIsInstanceOf(MyEvent.class, event);

      assertEquals("foo[" + counter + "]", expectedFoos[counter++], myEvent.getFoo());
    }
  }
  
  @Override
  protected RouteBuilder createRouteBuilder() throws Exception {
    return new RouteBuilder() {
      public void configure() throws Exception {
        from("direct:start").to("esper://cheese");

        from("esper://cheese?pattern=every event=org.apacheextras.camel.component.esper.MyEvent(bar=5)")
                .to("mock:results");
      }
    };
  }
}
