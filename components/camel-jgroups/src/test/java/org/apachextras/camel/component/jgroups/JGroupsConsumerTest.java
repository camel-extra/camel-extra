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
package org.apachextras.camel.component.jgroups;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.junit.Test;

public class JGroupsConsumerTest extends CamelTestSupport {

    static final String CLUSTER_NAME = "CLUSTER_NAME";

    static final String MESSAGE = "MESSAGE";

    // Fixtures

    JChannel channel;

    // Routes fixture

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("jgroups:" + CLUSTER_NAME).to("mock:test");
            }
        };
    }

    // Fixture setup

    @Override
    protected void doPreSetup() throws Exception {
        super.doPreSetup();
        channel = new JChannel();
        channel.connect(CLUSTER_NAME);
    }

    @Override
    public void tearDown() throws Exception {
        channel.close();
        super.tearDown();
    }

    @Test
    public void shouldConsumeMulticastedMessage() throws Exception {
        // When
        channel.send(new Message(null, null, MESSAGE));

        // Then
        getMockEndpoint("mock:test").setExpectedMessageCount(1);
        getMockEndpoint("mock:test").expectedBodiesReceived(MESSAGE);
        getMockEndpoint("mock:test").assertIsSatisfied();
    }

}
