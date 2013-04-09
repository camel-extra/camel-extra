/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

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
package org.apacheextras.camel.component.jgroups;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.junit.Test;

public class JGroupsConsumerTest extends CamelTestSupport {

    // Fixtures

    String clusterName = "clusterName";

    String mockEndpoint = "mock:test";

    String message = "message";

    JChannel channel;

    // Routes fixture

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("jgroups:" + clusterName).to(mockEndpoint);
            }
        };
    }

    // Fixture setup

    @Override
    protected void doPreSetup() throws Exception {
        super.doPreSetup();
        channel = new JChannel();
        channel.connect(clusterName);
    }

    @Override
    public void tearDown() throws Exception {
        channel.close();
        super.tearDown();
    }

    @Test
    public void shouldConsumeMulticastedMessage() throws Exception {
        // When
        channel.send(new Message(null, null, message));

        // Then
        MockEndpoint mock = getMockEndpoint(mockEndpoint);
        mock.setExpectedMessageCount(1);
        mock.expectedBodiesReceived(message);
        mock.assertIsSatisfied();
    }

}
