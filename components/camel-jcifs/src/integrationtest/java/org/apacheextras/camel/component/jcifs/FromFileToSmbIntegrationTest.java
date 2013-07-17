package org.apacheextras.camel.component.jcifs;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;

/**
 * Unit test to verify that we can pool an ASCII file from a local file path and store it on the SMB Server 
 */
public class FromFileToSmbIntegrationTest extends BaseSmbIntegrationTestSupport {
	private String getSmbUrl() {
		return "smb://"+getDomain()+";"+getUsername()+"@localhost/"+getShare()+"/camel/"+getClass().getSimpleName()+"?password="+getPassword()+"&fileExist=Override";
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
