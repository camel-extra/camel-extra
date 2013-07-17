package org.apacheextras.camel.component.jcifs;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test to test both consumer.moveNamePrefix and consumer.moveNamePostfix options.
 */
public class FromSmbMoveFileIntegrationTest extends BaseSmbIntegrationTestSupport {
	private String getSmbUrl() {
		return "smb://"+getDomain()+";"+getUsername()+"@localhost/"+getShare()+"/camel/"
			+getClass().getSimpleName()+"?password="+getPassword()
			+"&move=done/sub2/${file:name}.old&consumer.delay=5000";
    }

    @Test
    public void testPollFileAndShouldBeMoved() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived("Hello World this file will be moved");
        mock.expectedFileExists(getLocalSharePath() + "/camel/"
    			+getClass().getSimpleName()+"/done/sub2/hello.txt.old");

        mock.assertIsSatisfied();
    }
	
	
	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		// prepares the FTP Server by creating a file on the server that we want to unit
        // test that we can pool and store as a local file
        Endpoint endpoint = context.getEndpoint(getSmbUrl());
        Exchange exchange = endpoint.createExchange();
        exchange.getIn().setBody("Hello World this file will be moved");
        exchange.getIn().setHeader(Exchange.FILE_NAME, "hello.txt");
        Producer producer = endpoint.createProducer();
        producer.start();
        producer.process(exchange);
        producer.stop();
	}
	
	 protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from(getSmbUrl()).to("mock:result");
            }
        };
    }
}
