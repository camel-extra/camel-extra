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
package org.apacheextras.camel.component.jcifs;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;

/**
 * Unit test to verify that we can pool an ASCII file from a local file path and store it on the SMB Server 
 */
public class FromFileToSmbIntegrationTest extends BaseSmbIntegrationTestSupport {
    private String getSmbUrl() {
        return "smb://" + getDomain() + ";" + getUsername() + "@localhost/" + getShare() + "/camel/" + getClass().getSimpleName() + "?password=" + getPassword() + "&fileExist=Override";
    }
    
    @Test
    public void testFromFileToSmb() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(2);

        assertMockEndpointsSatisfied();
    }
    
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from(getSmbUrl()).to("mock:result");
                from("file:src/test/data?noop=true&consumer.delay=3000").to(getSmbUrl());
            }
        };
    }
}
