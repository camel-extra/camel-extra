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

package org.apacheextras.camel.examples.rcode.converter;

import java.util.List;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apacheextras.camel.examples.rcode.types.ForecastDocument;
import org.junit.Test;

public class ForecastDocumentConverterTest extends CamelTestSupport {

  @EndpointInject(uri="direct:start")
  ProducerTemplate producerTemplate;

  @EndpointInject(uri="mock:beforeConversion")
  MockEndpoint beforeConversion;

  @EndpointInject(uri="mock:afterConversion")
  MockEndpoint afterConversion;

  @Test
  public void convertByteArrayToForecastDocumentTest() throws Exception {
    final byte[] bytes = ("Test bytes for document conversion").getBytes();

    beforeConversion.expectedBodiesReceived(bytes);
    List<Exchange> exchanges = afterConversion.getExchanges();

    producerTemplate.sendBody(bytes);

    for(Exchange exchange : exchanges) {
      assertTrue("Did not receive converted object.", (exchange.getIn().getBody() instanceof ForecastDocument));
    }
    beforeConversion.assertIsSatisfied();;
  }

  @Override
  protected RouteBuilder createRouteBuilder() throws Exception {
    return new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        from("direct:start")
            .to("mock:beforeConversion")
            .convertBodyTo(ForecastDocument.class)
            .to("mock:afterConversion");
      }
    };
  }
}
