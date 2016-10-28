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
package org.apacheextras.camel.component.esper;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class EsperConsumerWithoutListenerTest extends CamelTestSupport {

	@Produce(uri = "direct://feed")
    ProducerTemplate template;

    @EndpointInject(uri = "mock://EsperStockTickerWithoutListenerMock")
    MockEndpoint mockEndpoint;

    @Test
    public void sendStockTickToLogTest() throws InterruptedException {
        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
//                from("direct://feed").to("esper://EsperStockTickerWithoutListenerMock");

				from("esper://EsperStockTickerWithoutListenerMock?listen=false&configured=true&eql=create window TenSecOfTicksWindow.win:time(10 sec) as StockTick").to("mock://EsperWithoutListenerMock");
			}
		};
	}
}
