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
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPString;

import static org.mockito.Mockito.when;

public class RCodeProducerEvalTest extends RCodeProducerTest {

    @Test
    public void sendEvalCmdVersionTest() throws Exception {

        // R command to retrieve the version string from the server
        final String command = "R.version.string";
        // Prefix of the REXP version result
        final String expected = "R version 2.15.2 (2012-10-26)";
        // Create a REXP that contains the expected version string
        final REXP rexp = new REXPString(expected);
        // Setup the rConnection mock to meet the expectations of the test case
        when(rConnection.eval(command)).thenReturn(rexp);
        when(rConnection.isConnected()).thenReturn(Boolean.TRUE);

        // Initialize a mock endpoint that receives at least one message
        final MockEndpoint mockEndpoint = getMockEndpoint("mock:rcode");
        mockEndpoint.expectedMinimumMessageCount(1);
        mockEndpoint.expectedMessageCount(1);
        mockEndpoint.whenAnyExchangeReceived(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                final REXP receivedRexp = exchange.getIn().getBody(REXP.class);
                // Evaluate of the String contains the "R version" expression
                try {
                    assertTrue(receivedRexp.asString().contains(expected));
                } catch (Exception ex) {
                    fail("Did not receive the expected version String " + ex.getMessage());
                }
            }
        });

        // Send out the RCode version command
        template.sendBody("direct:rcode", command);

        // Check if at least one result could be retrieved
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void sendEvalCmdPythagorasTest() throws Exception {
        // R command to calculate a pythagoras
        final String command = "c <- sqrt(2^2 + 2^2);";
        // Result for the pythagoras calculation
        final double expected = 2.8284271247461903;
        // R expression containing the result
        final REXP rexp = new REXPDouble(expected);
        // Setup the rConnection mock to meet the expectations of the test case
        when(rConnection.isConnected()).thenReturn(Boolean.TRUE);
        when(rConnection.eval(command)).thenReturn(rexp);
        // Mock endpoint to evaluate the caluculated result
        final MockEndpoint mockEndpoint = getMockEndpoint("mock:rcode");
        mockEndpoint.whenAnyExchangeReceived(new Processor() {
            @Override
            public void process(Exchange exchng) throws Exception {
                try {
                    assertTrue(expected == ((REXPDouble)exchng.getIn().getBody()).asDouble());
                } catch (Exception ex) {
                    fail("Did not receive the expected result " + ex.getMessage());
                }
            }
        });
        // Send the R command to calculate "c <- sqrt(2^2 + 2^2);"
        template.sendBody("direct:rcode", command);
        mockEndpoint.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Handle exceptions by sending the exceptions to the mock
                // endpoint
                onException(Exception.class).handled(true).to("mock:error");
                // Send commands to the RCode endpoint, operation is
                // 'parse_and_eval'
                from("direct:rcode").to("rcode:localhost:6311/eval?user=test&password=test123&bufferSize=4194304").to("mock:rcode");
            }
        };
    }

}
