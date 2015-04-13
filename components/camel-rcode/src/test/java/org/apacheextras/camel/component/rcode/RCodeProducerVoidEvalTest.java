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

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class RCodeProducerVoidEvalTest extends RCodeProducerTest {

    // Path where the output file should be generated
    private final String path = RCodeProducerVoidEvalTest.class.getProtectionDomain()
            .getCodeSource().getLocation().getPath() + "/german_map.pdf";
    // RCode to generate a german map as pdf
    private final String command = "library(maps);\n"
            + "library(mapdata);\n"
            + "pdf(\"" + path + "\");\n"
            + "map('worldHires', 'germany');\n"
            + "dev.off()";

    @Test
    public void sendVoidEvalCmdMapTest() throws Exception {
        // Configure mock endpoint for assertions
        final Object obj = null;
        final MockEndpoint mockEndpoint = getMockEndpoint("mock:rcode");
        mockEndpoint.expectedBodiesReceived(obj); // void_eval returns a body with null value

        when(rConnection.isConnected()).thenReturn(Boolean.TRUE);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                return null;
            }
        }).when(rConnection).voidEval(command);

        // Send the command to the RCode component
        template.sendBody("direct:rcode", command);
        // Assert if the return value does not contain any body
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void sendVoidEvalCmdHeaderTest() throws Exception {
        // Configure mock endpoint for assertions
        final MockEndpoint mockEndpoint = getMockEndpoint("mock:rcode");
        final Object obj = null;
        mockEndpoint.expectedBodiesReceived(obj); // void_eval returns a body with null value

        when(rConnection.isConnected()).thenReturn(Boolean.TRUE);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                return null;
            }
        }).when(rConnection).voidEval(command);

        template.sendBodyAndHeader("direct:rcode", command,
                RCodeConstants.RSERVE_OPERATION, RCodeOperation.VOID_EVAL);
        mockEndpoint.assertIsSatisfied();
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
                // Send commands to the RCode endpoint, operation is 'void_eval'
                from("direct:rcode")
                        .to("rcode:localhost:6311/void_eval?user=test&password=test123")
                        .to("mock:rcode");
            }
        };
    }
}