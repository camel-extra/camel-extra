/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.


 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/gpl-2.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.jgroups;

import org.apache.camel.AsyncCallback;
import org.apache.camel.AsyncProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.AsyncProcessorConverterHelper;
import org.apache.camel.util.ObjectHelper;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of JGroups message receiver ({@code org.jgroups.Receiver}) wrapping incoming messages into Camel
 * exchanges. Used by {@link JGroupsConsumer}.
 */
public class CamelJGroupsReceiver extends ReceiverAdapter {

    private static final transient Logger LOG = LoggerFactory.getLogger(CamelJGroupsReceiver.class);

    private final JGroupsEndpoint endpoint;
    private final AsyncProcessor processor;

    public CamelJGroupsReceiver(JGroupsEndpoint endpoint, Processor processor) {
        ObjectHelper.notNull(endpoint, "endpoint");
        ObjectHelper.notNull(processor, "processor");

        this.endpoint = endpoint;
        this.processor = AsyncProcessorConverterHelper.convert(processor);
    }

    @Override
    public void viewAccepted(View view) {
        if (endpoint.isResolvedEnableViewMessages()) {
            Exchange exchange = endpoint.createExchange(view);
            try {
                LOG.debug("Processing view: {}", view);
                processor.process(exchange, new AsyncCallback() {
                    @Override
                    public void done(boolean doneSync) {
                        // noop
                    }
                });
            } catch (Exception e) {
                throw new JGroupsException("Error in consumer while dispatching exchange containing view " + view, e);
            }
        } else {
            LOG.debug("Option enableViewMessages is set to false. Skipping processing of the view: {}", view);
        }
    }

    @Override
    public void receive(Message message) {
        Exchange exchange = endpoint.createExchange(message);
        try {
            LOG.debug("Processing message: {}", message);
            processor.process(exchange, new AsyncCallback() {
                @Override
                public void done(boolean doneSync) {
                    // noop
                }
            });
        } catch (Exception e) {
            throw new JGroupsException("Error in consumer while dispatching exchange containing message " + message, e);
        }
    }

}