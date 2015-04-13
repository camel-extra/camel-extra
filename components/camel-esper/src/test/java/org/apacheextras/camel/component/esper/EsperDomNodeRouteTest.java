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

import com.espertech.esper.event.map.MapEventBean;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EsperDomNodeRouteTest extends CamelTestSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(EsperDomNodeRouteTest.class);

  private Document document;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    document = buildDom();
  }

  private Document buildDom() {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    Document doc = null;
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      doc = builder.newDocument();
      Element root = doc.createElement("root");
      doc.appendChild(root);
      root.appendChild(doc.createTextNode("Hello"));
      root.appendChild(doc.createTextNode(" "));
      root.appendChild(doc.createTextNode("World!"));
    } catch (ParserConfigurationException ex) {
      LOGGER.error("Could not instantiate document builder: {}", ex.getMessage());
    }
    return doc;
  }

  @Test
  public void testSendDoDmNodeEvents() throws InterruptedException {
    MockEndpoint endpoint = getMockEndpoint("mock:results");
    template.sendBody("direct://start", document);
    endpoint.expectedMessageCount(1);
    endpoint.assertIsSatisfied();
    List<Exchange> exchanges = endpoint.getExchanges();
    for (Exchange exchange : exchanges) {
      MapEventBean eventBean = exchange.getIn().getBody(MapEventBean.class);
      Document doc = (Document) eventBean.get("childNodes");
      Element element = doc.getDocumentElement();
      assertTrue(element.toString().contains("root"));
      assertEquals("root", element.getNodeName());
      assertEquals("Hello World!", element.getTextContent());
    }
  }

  @Override
  protected RouteBuilder createRouteBuilder() throws Exception {
    return new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        // Start route
        from("direct://start")
                .to("log://esper-dom?level=INFO")
                .to("esper://esper-dom");
        // Esper stream route
        from("esper://esper-dom?eql=insert into DomStream select * from org.w3c.dom.Document")
                .to("log://esper-dom?level=INFO");
        // Esper selection route
        from("esper://esper-dom?eql=select childNodes from DomStream")
                .to("mock:results");
      }
    };
  }
}
