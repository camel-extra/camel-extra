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
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apacheextras.camel.component.jgroups.JGroupsEndpoint;
import org.junit.Test;

public class JGroupsEndpointTest extends CamelTestSupport {

    // Constants

    static final String CLUSTER_NAME = "CLUSTER_NAME";

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

    // Tests

    @Test
    public void shouldSetClusterName() throws Exception {
        // When
        JGroupsEndpoint endpoint = getMandatoryEndpoint("jgroups:" + CLUSTER_NAME, JGroupsEndpoint.class);

        // Then
        assertEquals(CLUSTER_NAME, endpoint.getClusterName());
    }

    @Test
    public void shouldSetChannel() throws Exception {
        // When
        JGroupsEndpoint endpoint = getMandatoryEndpoint("jgroups:" + CLUSTER_NAME, JGroupsEndpoint.class);

        // Then
        assertNotNull(endpoint.getChannel());
    }

}
