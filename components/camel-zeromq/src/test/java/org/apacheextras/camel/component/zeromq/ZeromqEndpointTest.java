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

import java.net.URISyntaxException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ZeromqEndpointTest {

    private ZeromqEndpoint endpoint;

    @Test
    public void assertSingleton() throws URISyntaxException {
        assertTrue(endpoint.isSingleton());
    }

    @Before
    public void before() throws URISyntaxException {
        endpoint = new ZeromqEndpoint("zeromq:tcp://localhost:1234?socketType=PUBLISH", "tcp://localhost:1234?socketType=PUBLISH",
                new ZeromqComponent());
    }

    @Test
    public void createConsumer() throws Exception {
        ZeromqEndpoint endpoint = new ZeromqEndpoint("zeromq:tcp://localhost:80?socketType=PUSH", "tcp://localhost:80?socketType=PUSH",
                new ZeromqComponent());
        endpoint.setSocketType(ZeromqSocketType.PUSH);
        endpoint.createConsumer(new Processor() {

            @Override
            public void process(Exchange exchange) throws Exception {
            }
        });
    }

    @Test
    public void createExchangeSetsHeaders() throws URISyntaxException {

        endpoint.setSocketType(ZeromqSocketType.PUBLISH);
        endpoint.setMessageIdEnabled(true);

        Exchange exchange = endpoint.createZeromqExchange("my message".getBytes());
        assertNotNull(exchange.getIn().getHeader(ZeromqConstants.HEADER_MSG_ID));
        assertEquals("tcp://localhost:1234", exchange.getIn().getHeader(ZeromqConstants.HEADER_SOURCE));
        assertEquals(ZeromqSocketType.PUBLISH, exchange.getIn().getHeader(ZeromqConstants.HEADER_SOCKET_TYPE));

    }

    @Test
    public void createProducer() throws Exception {
        ZeromqEndpoint endpoint = new ZeromqEndpoint("zeromq:tcp://localhost:80?socketType=PUSH", "tcp://localhost:80?socketType=PUSH",
                new ZeromqComponent());
        endpoint.setSocketType(ZeromqSocketType.PUSH);
        endpoint.createProducer();
    }

    @Test
    public void messageIdEnabledFlagIsHonored() throws URISyntaxException {
        endpoint.setSocketType(ZeromqSocketType.PUBLISH);
        endpoint.setMessageIdEnabled(true);
        Exchange exchange = endpoint.createZeromqExchange("my message".getBytes());
        assertNotNull(exchange.getIn().getHeader(ZeromqConstants.HEADER_MSG_ID));

        endpoint.setSocketType(ZeromqSocketType.PUBLISH);
        endpoint.setMessageIdEnabled(false);
        exchange = endpoint.createZeromqExchange("my message".getBytes());
        assertNull(exchange.getIn().getHeader(ZeromqConstants.HEADER_MSG_ID));
    }

    @Test(expected = ZeromqException.class)
    public void topicsErrorWithoutSubscribeType() throws Exception {
        ZeromqEndpoint endpoint = new ZeromqEndpoint("zeromq:tcp://localhost:80?socketType=PUSH&topics=coldplay",
                "tcp://localhost:80?socketType=PUSH&topics=coldplay", new ZeromqComponent());
        endpoint.createConsumer(new Processor() {

            @Override
            public void process(Exchange exchange) throws Exception {
            }
        });
    }

    @Test(expected = ZeromqException.class)
    public void uriHostnameRequired() throws URISyntaxException {
        new ZeromqEndpoint("zeromq:tcp://?socketType=PUBLISH", "tcp://?socketType=PUBLISH", new ZeromqComponent());
    }

    @Test(expected = ZeromqException.class)
    public void uriInvalidProtocolNotAccepted() throws URISyntaxException {
        new ZeromqEndpoint("zeromq:http://localhost:80?socketType=PUBLISH", "localhost:80?socketType=PUBLISH", new ZeromqComponent());
    }

    @Test(expected = ZeromqException.class)
    public void uriPortRequired() throws URISyntaxException {
        new ZeromqEndpoint("zeromq://tcp:localhost?socketType=PUBLISH", "tcp:localhost?socketType=PUBLISH", new ZeromqComponent());
    }

    @Test(expected = ZeromqException.class)
    public void uriPortValid() throws URISyntaxException {
        new ZeromqEndpoint("zeromq://tcp:localhost:-44?socketType=PUBLISH", "tcp:localhost:-44?socketType=PUBLISH",
                new ZeromqComponent());
    }

    @Test
    public void uriProtocolAccepted() throws URISyntaxException {
        new ZeromqEndpoint("zeromq:tcp://localhost:80?socketType=PUBLISH", "tcp://localhost:80?socketType=PUBLISH",
                new ZeromqComponent());
        new ZeromqEndpoint("zeromq:ipc://localhost:80?socketType=PUBLISH", "ipc://localhost:80?socketType=PUBLISH",
                new ZeromqComponent());
    }

    @Test(expected = ZeromqException.class)
    public void uriProtocolRequired() throws URISyntaxException {
        new ZeromqEndpoint("zeromq?socketType=PUBLISH", "?socketType=PUBLISH", new ZeromqComponent());
    }

    @Test(expected = ZeromqException.class)
    public void whenCreatingProducerAndSocketTypeIsNullThenThrowException() throws Exception {
        ZeromqEndpoint endpoint = new ZeromqEndpoint("zeromq:tcp://localhost:80?socketType=PUSH", "tcp://localhost:80?socketType=PUSH",
                new ZeromqComponent());
        endpoint.createProducer();
    }

    @Test
    public void whenCustomContextFactorySetThenIsUsed() throws Exception {
        ContextFactory cf = mock(ContextFactory.class);
        endpoint.setContextFactory(cf);
        endpoint.setSocketType(ZeromqSocketType.PUBLISH);
        ZeromqProducer p = endpoint.createProducer();
        assertEquals(cf, p.getContextFactory());
        ZeromqConsumer c = endpoint.createConsumer(null);
        assertEquals(cf, c.getContextFactory());
    }

    @Test
    public void whenCustomSocketFactorySetThenIsUsed() throws Exception {
        SocketFactory sf = mock(SocketFactory.class);
        endpoint.setSocketFactory(sf);
        endpoint.setSocketType(ZeromqSocketType.PUBLISH);
        ZeromqProducer p = endpoint.createProducer();
        assertEquals(sf, p.getSocketFactory());
        ZeromqConsumer c = endpoint.createConsumer(null);
        assertEquals(sf, c.getSocketFactory());
    }
}
