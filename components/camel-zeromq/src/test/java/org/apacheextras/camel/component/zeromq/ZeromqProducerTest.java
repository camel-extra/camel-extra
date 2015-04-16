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

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ZeromqProducerTest {

    static class TestConvertor implements MessageConverter {

        @Override
        public byte[] convert(Exchange arg0) {
            return ("coldplay" + arg0.getIn().getBody().toString()).getBytes();
        }
    }

    @Mock
    private ZeromqEndpoint endpoint;

    @Mock
    private Exchange exchange;

    @Mock
    private Message msg;

    private ZeromqProducer producer;

    @Mock
    private AkkaSocketFactory akkaSocketFactory;

    @Mock
    private Socket socket;

    @Mock
    private AkkaContextFactory akkaContextFactory;

    @Mock
    private Context context;

    @Before
    public void before() throws Exception {
        initMocks(this);

        when(msg.getBody()).thenReturn("mymsg");

        when(endpoint.getSocketType()).thenReturn(ZeromqSocketType.PUBLISH);
        when(endpoint.getMessageConvertor()).thenReturn(DefaultMessageConvertor.class);
        when(endpoint.getSocketAddress()).thenReturn("tcp://localhost:5555");

        when(exchange.getIn()).thenReturn(msg);
        when(akkaContextFactory.createContext(anyInt())).thenReturn(context);
        when(akkaSocketFactory.createProducerSocket(context, ZeromqSocketType.PUBLISH)).thenReturn(socket);

        producer = new ZeromqProducer(endpoint, akkaSocketFactory, akkaContextFactory);
    }

    @Test
    public void customConvertor() throws Exception {
        when(endpoint.getMessageConvertor()).thenReturn(TestConvertor.class);
        producer = new ZeromqProducer(endpoint, akkaSocketFactory, akkaContextFactory);
        producer.start();
        producer.process(exchange);
        verify(socket).send("coldplaymymsg".getBytes(), 0);
    }

    @Test
    public void headersAreSet() throws Exception {

        producer.start();
        producer.process(exchange);
        verify(msg).setHeader(ZeromqConstants.HEADER_SOURCE, "tcp://localhost:5555");
        verify(msg).setHeader(ZeromqConstants.HEADER_SOCKET_TYPE, ZeromqSocketType.PUBLISH);
    }

    @Test
    public void isSingleton() {
        assertTrue(producer.isSingleton());
    }

    @Test
    public void publisherBroadcaststoAllTopics() throws Exception {
        when(endpoint.getTopics()).thenReturn("coldplay,elton,jethrotull");
        producer.start();
        producer.process(exchange);
        verify(socket).send("coldplaymymsg".getBytes(), 0);
        verify(socket).send("eltonmymsg".getBytes(), 0);
        verify(socket).send("jethrotullmymsg".getBytes(), 0);
    }

    @Test
    public void stopInterruptsBlockedSender() throws Exception {
        producer.start();
        when(socket.send((byte[])any(), anyInt())).then(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                try {
                    new ArrayBlockingQueue(10).take(); // anything just to
                } catch (InterruptedException e) {
                    // ignore
                    return null;
                }
                // block
                return null;
            }
        });
        final Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    producer.process(exchange);
                } catch (Exception e) {
                    // ignore
                }
            }
        });
        doAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                t.interrupt();
                return null;
            }
        }).when(context).term();
        producer.setShutdownWait(1);
        t.start();
        producer.stop();
        t.join();
    }
}
