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
package org.apache.camel.component.jgroups;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.junit.Test;

public class JGroupsComponentTest extends CamelTestSupport {

    // Constants

    static final String CLUSTER_NAME = "CLUSTER_NAME";

    static final String MESSAGE = "MESSAGE";

    static final String SAMPLE_CHANNEL_PROPERTIES = "UDP(discard_incompatible_packets=\"true\")";

    // Fixtures

    JChannel clientChannel;

    JChannel defaultComponentChannel;

    // Routes fixture

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        JGroupsComponent defaultComponent = new JGroupsComponent();
        defaultComponent.setChannel(defaultComponentChannel);
        context().addComponent("my-default-jgroups", defaultComponent);

        JGroupsComponent configuredComponent = new JGroupsComponent();
        configuredComponent.setChannelProperties(SAMPLE_CHANNEL_PROPERTIES);
        context().addComponent("my-configured-jgroups", configuredComponent);

        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("my-default-jgroups:" + CLUSTER_NAME).to("mock:default");
                from("my-configured-jgroups:" + CLUSTER_NAME).to("mock:configured");
            }
        };
    }

    // Fixture setup

    @Override
    protected void doPreSetup() throws Exception {
        super.doPreSetup();
        clientChannel = new JChannel();
        clientChannel.connect(CLUSTER_NAME);

        defaultComponentChannel = new JChannel();
    }

    @Override
    public void tearDown() throws Exception {
        clientChannel.close();
        super.tearDown();
    }

    @Test
    public void shouldConsumeMulticastedMessage() throws Exception {
        // When
        clientChannel.send(new Message(null, null, MESSAGE));

        // Then
        MockEndpoint mockEndpoint = getMockEndpoint("mock:default");
        mockEndpoint.setExpectedMessageCount(1);
        mockEndpoint.expectedBodiesReceived(MESSAGE);
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void shouldConfigureChannelWithProperties() throws Exception {
        // When
        JGroupsEndpoint endpoint = getMandatoryEndpoint("my-configured-jgroups:" + CLUSTER_NAME, JGroupsEndpoint.class);
        JGroupsComponent component = (JGroupsComponent)endpoint.getComponent();

        // Then
        assertEquals(SAMPLE_CHANNEL_PROPERTIES, component.getChannelProperties());
    }

    @Test
    public void shouldCreateChannel() throws Exception {
        // When
        JGroupsEndpoint endpoint = getMandatoryEndpoint("my-default-jgroups:" + CLUSTER_NAME, JGroupsEndpoint.class);
        JGroupsComponent component = (JGroupsComponent)endpoint.getComponent();

        // Then
        assertNotNull(component.getChannel());
    }

}
