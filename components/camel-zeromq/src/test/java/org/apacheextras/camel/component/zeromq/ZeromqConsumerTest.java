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
package org.apacheextras.camel.component.zeromq;

import java.util.concurrent.ExecutorService;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spi.ExecutorServiceManager;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ZeromqConsumerTest extends CamelTestSupport {

    @EndpointInject(uri = "direct:start")
    private Endpoint from;

    @SuppressWarnings("hiding")
    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint to;

    @Mock
    private Processor processor;

    @Mock
    private ZeromqEndpoint endpoint;

    @SuppressWarnings("hiding")
    private ZeromqConsumer consumer;

    @Mock
    private CamelContext ctx;

    @Mock
    private ExecutorServiceManager manager;

    @Mock
    private ExecutorService executor;

    @Before
    public void before() {
        initMocks(this);

        consumer = new ZeromqConsumer(endpoint, processor, new AkkaContextFactory(), new AkkaSocketFactory(-1, -1));
        when(endpoint.getCamelContext()).thenReturn(ctx);
        when(ctx.getExecutorServiceManager()).thenReturn(manager);
        when(manager.newFixedThreadPool(any(ZeromqConsumer.class), anyString(), anyInt())).thenReturn(executor);
    }

    @Test
    public void stopWithoutStartIsOk() throws Exception {
        consumer.stop();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from(from).process(new Processor() {

                    @Override
                    public void process(Exchange exchange) throws Exception {

                    }
                }).to(to);
            }
        };
    }

    @Test
    public void listenerIsSubmittedOnStart() throws Exception {
        consumer.doStart();
        verify(executor).submit(any(Runnable.class));
    }

    @Test
    public void shouldStopExecutor() throws Exception {
        // Given
        consumer.doStart();

        // When
        consumer.doStop();

        // Then
        verify(executor).shutdownNow();
    }

    @Test
    public void test1000VirtualMessages() throws Exception {
        // just test that the messages are passed on into the final endpoint
        to.expectedMessageCount(1000);
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 1000; i++) {
                    template.sendBody("test");
                }
            }
        });

        t.start();
        t.join();
        to.assertIsSatisfied();
    }

    @Test
    public void textExecutorLifecycleForStopStart() throws Exception {
        consumer.doStart();
        verify(executor, never()).shutdownNow();

        consumer.doStop();
        verify(executor).shutdownNow();

        // 2nd invocation should have no effect
        consumer.doStop();
        verify(executor).shutdownNow();
    }
}
