package org.apache.camel.component;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SapJCoServerComponentTest extends CamelTestSupport {

  //  private final EventBusHelper eventBusHelper = EventBusHelper.getInstance();

   // @Test
    public void testSapJCoServer() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(5);

        // Trigger events to subscribers
        simulateEventTrigger();

        mock.await();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("SapJCoServer://foo")
                  .to("SapJCoServer://bar")
                  .to("mock:result");
            }
        };
    }

    private void simulateEventTrigger() {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final Date now = new Date();
                // publish events to the event bus
               // eventBusHelper.publish(now);
            }
        };

        new Timer().scheduleAtFixedRate(task, 1000L, 1000L);
    }
}
