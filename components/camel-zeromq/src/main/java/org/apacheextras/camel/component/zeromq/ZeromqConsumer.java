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

import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
import org.apache.camel.util.AsyncProcessorConverterHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;

public class ZeromqConsumer extends DefaultConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZeromqConsumer.class);

    private final Processor processor;
    private final ZeromqEndpoint endpoint;
    private final ZMQ.Context context;
    private final SocketFactory socketFactory;
    private ExecutorService executor;
    private Listener listener;
    private final String controlPipeAddress;
    private final ZMQ.Socket emitter;

    public ZeromqConsumer(ZeromqEndpoint endpoint, Processor processor, ContextFactory contextFactory, SocketFactory socketFactory) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        // use same context within single consumer
        // to enable inproc:// for control pipe
        this.context = contextFactory.createContext(1);
        this.socketFactory = socketFactory;
        this.processor = AsyncProcessorConverterHelper.convert(processor);
        this.controlPipeAddress =  "inproc://pipe" + endpoint.hashCode();
        this.emitter = contextFactory.createContext(1).socket(ZMQ.PAIR);
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        executor = endpoint.getCamelContext().getExecutorServiceManager().newFixedThreadPool(this, endpoint.getEndpointUri(), 1);
        listener = new Listener(endpoint, processor, socketFactory, i -> context, controlPipeAddress);
        executor.submit(listener);
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
        if (listener != null) {
            emitter.connect(controlPipeAddress);
            emitter.send("close".getBytes(Charset.defaultCharset()), 0);
        }
        if (executor != null) {
            LOGGER.debug("Shutdown of executor");

            executor.shutdown();
            try {
                executor.awaitTermination(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
            }

            if (!executor.isShutdown()) {
                executor.shutdownNow();
            }

            LOGGER.debug("Executor is now shutdown");
            executor = null;
        }
    }

    public ContextFactory getContextFactory() {
        return i -> context;
    }

    public SocketFactory getSocketFactory() {
        return socketFactory;
    }

    @Override
    public void resume() throws Exception {
        super.resume();
        doStart();
    }

    @Override
    public void suspend() throws Exception {
        super.suspend();
        // currently do not support resume and suspend of listener, right
        // now this delegates to just stopping and
        // starting the consumer.
        doStop();
    }
}
