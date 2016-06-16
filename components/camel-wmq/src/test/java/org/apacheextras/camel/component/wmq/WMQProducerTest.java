package org.apacheextras.camel.component.wmq;


import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

public class WMQProducerTest extends CamelTestSupport {

	/*
	protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();
        WMQComponent c = new WMQComponent(camelContext);
        c.setConnectionMode("connection");
        c.setQueueManagerHostname("192.168.33.10");
        c.setQueueManagerPort("1414");
        c.setQueueManagerUserID("mqm");
        c.setQueueManagerPassword("4pple4pple123");
        c.setQueueManagerChannel("test2.channel");
        c.setQueueManagerName("venus.queue.manager");
        camelContext.addComponent("wmq", c);
        return camelContext;
    }
	
	@Produce(uri = "direct:start")
    ProducerTemplate template;
	
	@Test
    public void testSendNotMatchingMessage() throws Exception {
        template.sendBody("Sending message");
    }
	
	@Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
            	from("direct:start").to("wmq:queue:REDHAT.TMP.QUEUE");
            }
        };
    }*/
}
