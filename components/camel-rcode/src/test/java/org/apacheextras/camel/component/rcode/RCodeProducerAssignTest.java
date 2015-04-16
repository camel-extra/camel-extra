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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.doAnswer;

public class RCodeProducerAssignTest extends RCodeProducerTest {

    @Test
    public void sendAssignVariableTest() throws Exception {
        final Map<String, String> assignments = new HashMap<String, String>();
        assignments.put("var1", "3 * 5");

        final Object obj = null;
        final MockEndpoint mockEndpoint = getMockEndpoint("mock:rcode");
        mockEndpoint.expectedBodiesReceived(obj);

        for (Entry<String, String> assignment : assignments.entrySet()) {
            doAnswer(new Answer<Void>() {
                @Override
                public Void answer(InvocationOnMock invocation) {
                    return null;
                }
            }).when(rConnection).assign(assignment.getKey(), assignment.getValue());
            template.sendBody("direct:rcode", assignment);
            mockEndpoint.assertIsSatisfied();
        }
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
                // 'assign_content'
                from("direct:rcode").to("rcode:localhost:6311/assign_content?user=test&password=test123&bufferSize=10").to("mock:rcode");
            }
        };
    }
}
