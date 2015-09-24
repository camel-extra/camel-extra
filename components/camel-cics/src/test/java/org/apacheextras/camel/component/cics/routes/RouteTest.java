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
package org.apacheextras.camel.component.cics.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apacheextras.camel.component.cics.commareas.EC01Impl;
import org.junit.Test;

/**
 * It is needed to change the properties CTG_XXX to test successfully
 * 
 * @author Sergio Gutierrez (sgutierr@redhat.com)
 * @author Jose Roman Martin Gil (rmarting@redhat.com)
 */
public class RouteTest extends CamelTestSupport {

	/** CTG Host */
	private static final String CTG_HOST = "ctghost";
	
	/** CTG Port */
	private static final String CTG_PORT = "2006";

	/** CTG Server */
	private static final String CTG_SERVER = "ctgserver";

	@Test(expected = Exception.class)	
	public void testRoute() throws Exception {
		MockEndpoint mock = getMockEndpoint("mock:result");
		mock.expectedMinimumMessageCount(1);

		// template.setDefaultEndpointUri("direct:start");
		template.sendBody("direct:test", "cics");

		assertMockEndpointsSatisfied();
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			public void configure() {
				from("direct:test")
						.log("CTG Endpoing: cics://" + CTG_HOST + ":" + CTG_PORT + "/" + CTG_SERVER)
						.setHeader("programName", constant("EC01"))
						.setHeader("commAreaSize", constant("18"))
						.setBody(method(EC01Impl.class, "getData"))
						.log("Calling EC01 Program")
						.to("cics://" + CTG_HOST + ":" + CTG_PORT + "/" + CTG_SERVER)
						.log("Called EC01 Program").to("mock:result");
			}
		};
	}

}