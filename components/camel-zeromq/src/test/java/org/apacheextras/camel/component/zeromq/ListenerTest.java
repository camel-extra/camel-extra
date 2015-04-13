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

import org.apache.camel.AsyncCallback;
import org.apache.camel.AsyncProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ListenerTest {

    @Mock
    private AkkaContextFactory akkaContextFactory;

    @Mock
    private AkkaSocketFactory akkaSocketFactory;

    @Mock
    private Processor processor;

    @Mock
    private ZeromqEndpoint endpoint;

    private Listener listener;

    @Mock
    private Socket socket;

    @Mock
    private Context context;

    private final ArrayBlockingQueue<byte[]> q = new ArrayBlockingQueue(10);

    @Mock
    private AsyncCallback callback;

    @Mock
    private AsyncProcessor asyncProcessor;

    @Test
    public void asyncProcessorInvoked() throws InterruptedException {
        when(endpoint.getSocketType()).thenReturn(ZeromqSocketType.PULL);
        when(endpoint.isAsyncConsumer()).thenReturn(true);
        listener = new Listener(endpoint, asyncProcessor, akkaSocketFactory, akkaContextFactory);
        listener.setCallback(callback);
        listener.connect();
        Thread t = new Thread(listener);
        t.start();
        Thread.sleep(100);
        listener.stop();
        t.join();
        verify(asyncProcessor, atLeast(1)).process(any(Exchange.class), eq(callback));
    }

    @Before
    public void before() {
        initMocks(this);

        when(socket.recv(0)).then(new Answer<byte[]>() {

            @Override
            public byte[] answer(InvocationOnMock invocation) throws Throwable {
                return "mymsg".getBytes();
            }
        });

        when(endpoint.getSocketType()).thenReturn(ZeromqSocketType.SUBSCRIBE);
        when(endpoint.getSocketAddress()).thenReturn("tcp://localhost:5555");
        when(akkaContextFactory.createContext(anyInt())).thenReturn(context);
        when(akkaSocketFactory.createConsumerSocket(eq(context), any(ZeromqSocketType.class))).thenReturn(socket);
        listener = new Listener(endpoint, processor, akkaSocketFactory, akkaContextFactory);
    }

    @Test
    public void exchangeCreatedWithMessageContents() throws InterruptedException {
        when(endpoint.isAsyncConsumer()).thenReturn(true);
        listener.connect();
        Thread t = new Thread(listener);
        t.start();
        Thread.sleep(100);
        listener.stop();
        t.join();
        verify(endpoint, atLeast(1)).createZeromqExchange("mymsg".getBytes());
    }

    @Test
    public void nullMessageSkipped() throws InterruptedException {
        when(socket.recv(0)).then(new Answer<byte[]>() {

            @Override
            public byte[] answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        });
        listener.connect();
        Thread t = new Thread(listener);
        t.start();
        // pause to allow thread to start
        Thread.sleep(100);
        // stop will shutdown the thread
        listener.stop();
        // wait for thread to die
        t.join();
    }

    @Test
    public void socketConnectedWithAddress() {
        listener.connect();
        verify(socket).connect("tcp://localhost:5555");
    }

    @Test
    public void socketCreatedWithCorrectContext() {
        listener.connect();
        verify(akkaSocketFactory).createConsumerSocket(context, ZeromqSocketType.SUBSCRIBE);
    }

    @Test
    public void stopIgnoresNullContext() {
        listener.stop();
    }

    @Test
    public void stopInterruptsRunningThread() throws InterruptedException {
        listener.connect();
        Thread t = new Thread(listener);
        t.start();
        // pause to allow thread to start
        Thread.sleep(100);
        // stop will shutdown the thread
        listener.stop();
        // wait for thread to die
        t.join();
        // socket should have been closed up
        verify(socket).close();
    }

    @Test
    public void stopInterruptsWhenBlockedOnRecv() throws InterruptedException {
        when(socket.recv(0)).then(new Answer<byte[]>() {

            @Override
            public byte[] answer(InvocationOnMock invocation) throws Throwable {
                return q.take();
            }
        });
        Mockito.doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                q.add("".getBytes());
                return null;
            }
        }).when(context).term();
        listener.connect();
        Thread t = new Thread(listener);
        t.start();
        // pause to allow thread to start
        Thread.sleep(100);
        // stop will shutdown the thread
        listener.stop();
        // wait for thread to die
        t.join();
        // socket should have been closed up
        verify(socket).close();
    }

    @Test
    public void stopTermsContext() {
        listener.connect();
        listener.stop();
        verify(context).term();
    }

    @Test
    public void subscribeAllOnNullTopics() {
        listener.connect();
        verify(socket).subscribe("".getBytes());
    }

    @Test
    public void subscribeMultipleTopics() {
        when(endpoint.getTopics()).thenReturn("coldplay,cream");
        listener.connect();
        verify(socket).subscribe("coldplay".getBytes());
        verify(socket).subscribe("cream".getBytes());
    }

    @Test
    public void subscribeTopic() {
        when(endpoint.getTopics()).thenReturn("coldplay");
        listener.connect();
        verify(socket).subscribe("coldplay".getBytes());
    }

    @Test
    public void syncProcessorInvoked() throws Exception {
        when(endpoint.isAsyncConsumer()).thenReturn(false);
        listener.connect();
        Thread t = new Thread(listener);
        t.start();
        Thread.sleep(100);
        listener.stop();
        t.join();
        verify(processor, atLeast(1)).process(any(Exchange.class));
    }
}
