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

import java.io.File;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test to verify that we can pool an ASCII file from the SMB Server and store it on a local file path
 */
public class FromSmbToAsciiFileIntegrationTest extends BaseSmbIntegrationTestSupport {

    private String getSmbUrl() {
        return "smb://" + getDomain() + ";" + getUsername() + "@localhost/" + getShare() + "/camel/" + getClass().getSimpleName() + "?password=" + getPassword() + "&fileExist=Override";
    }
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        // prepares the FTP Server by creating a file on the server that we want to unit
        // test that we can pool and store as a local file
        Endpoint endpoint = context.getEndpoint(getSmbUrl());
        Exchange exchange = endpoint.createExchange();
        exchange.getIn().setBody("Hello World from SMBServer");
        exchange.getIn().setHeader(Exchange.FILE_NAME, "hello.txt");
        Producer producer = endpoint.createProducer();
        producer.start();
        producer.process(exchange);
        producer.stop();
    }
    
    @Test
    public void testFromSmbToFile() throws Exception {
        MockEndpoint resultEndpoint = getMockEndpoint("mock:result");
        resultEndpoint.expectedMinimumMessageCount(1);
        resultEndpoint.expectedBodiesReceived("Hello World from SMBServer");
        
        resultEndpoint.assertIsSatisfied();

        // assert the file
        File file = new File("target/smbtest/" + getClass().getSimpleName() + "/deleteme.txt");
        assertTrue("The ASCII file should exists", file.exists());
        assertTrue("File size wrong", file.length() > 10);
        file.delete();
    }
    
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                String fileUrl = "file:target/smbtest/" + FromSmbToAsciiFileIntegrationTest.class.getSimpleName() + "/?fileExist=Override&noop=true";
                from(getSmbUrl()).setHeader(Exchange.FILE_NAME, constant("deleteme.txt")).
                        convertBodyTo(String.class).to(fileUrl).to("mock:result");
            }
        };
    }
}
