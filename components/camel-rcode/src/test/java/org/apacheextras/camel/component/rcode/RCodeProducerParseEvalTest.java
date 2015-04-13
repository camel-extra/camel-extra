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
package org.apacheextras.camel.component.rcode;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.rosuda.REngine.REXPDouble;

import static org.mockito.Mockito.when;

public class RCodeProducerParseEvalTest extends RCodeProducerTest {

    @Test
    public void sendParseAndEvalMatrixTest() throws Exception {

        final String command = "seq <- seq(1:9);\n"
                + "mat <- matrix(seq, 3);\n"
                + "mat3 <- mat * 3;";

        final double[] expected = {3.0, 6.0, 9.0, 12.0, 15.0, 18.0, 21.0, 24.0, 27.0};

        final REXPDouble rexpd = new REXPDouble(expected);

        when(rConnection.isConnected()).thenReturn(Boolean.TRUE);
        when(rConnection.parseAndEval(command)).thenReturn(rexpd);


        // Initialize a mock endpoint that receives at least one message
        final MockEndpoint mockEndpoint = getMockEndpoint("mock:rcode");
        mockEndpoint.whenAnyExchangeReceived(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                try {
                    assertTrue(expected == ((REXPDouble) exchange.getIn().getBody()).asDoubles());
                } catch (Exception ex) {
                    fail("Did not receive the expected result " + ex.getMessage());
                }
            }
        });

        template.sendBody("direct:rcode", command);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Handle exceptions by sending the exceptions to the mock endpoint
                onException(Exception.class)
                        .handled(true)
                        .to("mock:error");
                // Send commands to the RCode endpoint, operation is 'parse_and_eval'
                from("direct:rcode")
                        .to("rcode:localhost:6311/parse_and_eval?user=" + user + "&password=" + password + "&bufferSize=4194304")
                        .to("mock:rcode");
            }
        };
    }
}
