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

import org.apache.camel.AsyncCallback;
import org.apache.camel.AsyncProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.AsyncProcessorConverterHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Poller;
import org.zeromq.ZMQ.Socket;

class Listener implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    private Context context;
    private Poller poller;
    private final ZeromqEndpoint endpoint;
    private final String controlPipeAddress;
    private final Processor processor;
    private final SocketFactory akkaSocketFactory;
    private final ContextFactory akkaContextFactory;
    private AsyncCallback callback = new AsyncCallback() {

        @Override
        public void done(boolean doneSync) {
            // no-op
        }
    };

    public Listener(ZeromqEndpoint endpoint,
                    Processor processor,
                    SocketFactory akkaSocketFactory,
                    ContextFactory akkaContextFactory,
                    String controlPipeAddress) {
        this.endpoint = endpoint;
        this.akkaSocketFactory = akkaSocketFactory;
        this.akkaContextFactory = akkaContextFactory;
        if (endpoint.isAsyncConsumer()) {
            this.processor = AsyncProcessorConverterHelper.convert(processor);
        } else {
            this.processor = processor;
        }
        this.controlPipeAddress = controlPipeAddress;
    }

    void connect() {
        context = akkaContextFactory.createContext(1);
        poller = context.poller(2);

        Socket socket = akkaSocketFactory.createConsumerSocket(context, endpoint.getSocketType());

        String addr = endpoint.getSocketAddress();
        if (endpoint.getMode() == null || endpoint.getMode().equals("CONNECT")) {
            LOGGER.info("Connecting to server [{}]", addr);
            socket.connect(addr);
            LOGGER.info("Connected OK");
        } else {
            LOGGER.info("Binding to server [{}]", addr);
            socket.bind(addr);
            LOGGER.info("Bound OK");
        }

        if (endpoint.getSocketType() == ZeromqSocketType.SUBSCRIBE) {
            subscribe(socket);
        }
        poller.register(socket, Poller.POLLIN);

        Socket receiver = context.socket(ZMQ.PAIR);
        receiver.bind(controlPipeAddress);
        poller.register(receiver, ZMQ.Poller.POLLIN);
    }

    @Override
    public void run() {
        connect();
        while (true) {
            poller.poll();

            if (poller.pollin(0)) {
                Socket socket = poller.getSocket(0);
                byte[] msg = socket.recv(0);
                if (msg == null) {
                    continue;
                }
                handleMessage(msg);
            } else if (poller.pollin(1)) {
                LOGGER.debug("Requesting shutdown of consumer thread");
                break;
            }
        }

        try {
            LOGGER.info("Closing sockets");

            for (int i = 0; i < poller.getSize(); i++) {
                poller.getSocket(i).close();
            }
        } catch (Exception e) {
            LOGGER.error("Could not close socket during run() [{}]", e);
        }
        try {
            LOGGER.info("Terminating context");
            context.term();
        } catch (Exception e) {
            LOGGER.error("Could not terminate context during run() [{}]", e);
        }
    }

    private void handleMessage(byte[] msg) {
        LOGGER.trace("Received message [length=" + msg.length + "]");
        Exchange exchange = endpoint.createZeromqExchange(msg);
        LOGGER.trace("Created exchange [exchange={}]", new Object[]{exchange});
        try {
            if (processor instanceof AsyncProcessor) {
                ((AsyncProcessor) processor).process(exchange, callback);
            } else {
                processor.process(exchange);
            }
        } catch (Exception e) {
            LOGGER.error("Exception processing exchange [{}]", e);
        }
    }

    public void setCallback(AsyncCallback callback) {
        this.callback = callback;
    }

    void subscribe(Socket socket) {
        if (endpoint.getTopics() == null) {
            // subscribe all by using
            // empty filter
            LOGGER.debug("Subscribing to all messages (topics option was not specified)", endpoint.getTopics());
            socket.subscribe("".getBytes());
        } else {
            LOGGER.debug("Subscribing to topics: {}", endpoint.getTopics());
            for (String topic : endpoint.getTopics().split(",")) {
                socket.subscribe(topic.getBytes());
            }
        }
    }
}
