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
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

class Listener implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    private volatile boolean running = true;
    private Socket socket;
    private Context context;
    private final ZeromqEndpoint endpoint;
    private final Processor processor;
    private final SocketFactory akkaSocketFactory;
    private final ContextFactory akkaContextFactory;
    private AsyncCallback callback = new AsyncCallback() {

        @Override
        public void done(boolean doneSync) {
            // no-op
        }
    };

    public Listener(ZeromqEndpoint endpoint, Processor processor, SocketFactory akkaSocketFactory, ContextFactory akkaContextFactory) {
        this.endpoint = endpoint;
        this.akkaSocketFactory = akkaSocketFactory;
        this.akkaContextFactory = akkaContextFactory;
        if (endpoint.isAsyncConsumer()) {
            this.processor = AsyncProcessorConverterHelper.convert(processor);
        } else {
            this.processor = processor;
        }
    }

    void connect() {
        context = akkaContextFactory.createContext(1);
        socket = akkaSocketFactory.createConsumerSocket(context, endpoint.getSocketType());

        String addr = endpoint.getSocketAddress();
        LOGGER.info("Connecting to server [{}]", addr);
        socket.connect(addr);
        LOGGER.info("Connected OK");

        if (endpoint.getSocketType() == ZeromqSocketType.SUBSCRIBE) {
            subscribe();
        }
    }

    @Override
    public void run() {
        connect();
        while (running) {
            byte[] msg = socket.recv(0);
            if (msg == null) {
                continue;
            }
            LOGGER.trace("Received message [length=" + msg.length + "]");
            Exchange exchange = endpoint.createZeromqExchange(msg);
            LOGGER.trace("Created exchange [exchange={}]", new Object[] {exchange});
            try {
                if (processor instanceof AsyncProcessor) {
                    ((AsyncProcessor)processor).process(exchange, callback);
                } else {
                    processor.process(exchange);
                }
            } catch (Exception e) {
                LOGGER.error("Exception processing exchange [{}]", e);
            }
        }

        try {
            LOGGER.info("Closing socket");
            socket.close();
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

    public void setCallback(AsyncCallback callback) {
        this.callback = callback;
    }

    void stop() {
        LOGGER.debug("Requesting shutdown of consumer thread");
        running = false;
        // we have to term the context to interrupt the recv call
        if (context != null) {
            try {
                context.term();
            } catch (Exception e) {
                LOGGER.error("Could not terminate context during stop() [{}]", e);
            }
        }
    }

    void subscribe() {
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
