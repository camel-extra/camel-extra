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
package org.apacheextras.camel.component.wmq;

import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQHeaderList;
import com.ibm.mq.headers.MQRFH2;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.SuspendableService;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WMQConsumer extends ScheduledPollConsumer implements SuspendableService {

    private final static Logger LOGGER = LoggerFactory.getLogger(WMQConsumer.class);

    public WMQConsumer(WMQEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
    }

    @Override
    protected int poll() throws Exception {
        Exchange exchange = getEndpoint().createExchange();

        Message in = exchange.getIn();

        WMQComponent component = (WMQComponent) getEndpoint().getComponent();

        MQQueueManager queueManager = component.getQueueManager(getEndpoint().getQueueManagerName(),
                getEndpoint().getQueueManagerHostname(),
                getEndpoint().getQueueManagerPort(),
                getEndpoint().getQueueManagerChannel());

        MQDestination destination = null;
        try {
            LOGGER.debug("Consuming from {}", getEndpoint().getDestinationName());

            if (getEndpoint().getDestinationName().startsWith("topic:")) {
                String destinationName = getEndpoint().getDestinationName().substring("topic:".length());
                int options = CMQC.MQSO_CREATE | CMQC.MQSO_RESUME | CMQC.MQSO_DURABLE | CMQC.MQSO_FAIL_IF_QUIESCING;
                destination = queueManager.accessTopic(destinationName, null, options, null, null);
            } else {
                String destinationName = getEndpoint().getDestinationName();
                if (destinationName.startsWith("queue:")) {
                    destinationName = destinationName.substring("queue:".length());
                }
                destination = queueManager.accessQueue(destinationName, MQConstants.MQOO_INPUT_AS_Q_DEF, null, null, null);
            }

            MQMessage message = new MQMessage();
            MQGetMessageOptions options = new MQGetMessageOptions();
            options.options = MQConstants.MQGMO_WAIT + MQConstants.MQGMO_PROPERTIES_COMPATIBILITY + MQConstants.MQGMO_ALL_SEGMENTS_AVAILABLE + MQConstants.MQGMO_COMPLETE_MSG + MQConstants.MQGMO_ALL_MSGS_AVAILABLE;
            options.waitInterval = MQConstants.MQWI_UNLIMITED;
            LOGGER.info("Waiting for message ...");
            destination.get(message, options);

            LOGGER.info("Message consumed");

            LOGGER.info("Dealing with MQMD headers");
            LOGGER.info("\tmq.mqmd.format: {}", message.format);
            in.setHeader("mq.mqmd.format", message.format);
            LOGGER.info("\tmq.mqmd.charset: {}", message.characterSet);
            in.setHeader("mq.mqmd.charset", message.characterSet);
            LOGGER.info("\tmq.mqmd.expiry: {}", message.expiry);
            in.setHeader("mq.mqmd.expiry", message.expiry);
            LOGGER.info("\tmq.mqmd.put.appl.name: {}", message.putApplicationName);
            in.setHeader("mq.mqmd.put.appl.name", message.putApplicationName);
            LOGGER.info("\tmq.mqmd.group.id: {}", message.groupId);
            in.setHeader("mq.mqmd.group.id", message.groupId);
            LOGGER.info("\tmq.mqmd.msg.seq.number: {}", message.messageSequenceNumber);
            in.setHeader("mq.mqmd.msg.seq.number", message.messageSequenceNumber);
            LOGGER.info("\tmq.mqmd.msg.accounting.token: {}", message.accountingToken);
            in.setHeader("mq.mqmd.msg.accounting.token", message.accountingToken);
            LOGGER.info("\tmq.mqmd.correl.id: {}", message.correlationId);
            in.setHeader("mq.mqmd.correl.id", message.correlationId);
            LOGGER.info("\tmq.mqmd.replyto.q: {}", message.replyToQueueName);
            in.setHeader("mq.mqmd.replyto.q", message.replyToQueueName);
            LOGGER.info("\tmq.mqmd.replyto.q.mgr: {}", message.replyToQueueManagerName);
            in.setHeader("mq.mqmd.replyto.q.mgr", message.replyToQueueManagerName);

            MQHeaderList headerList = new MQHeaderList(message);
            // TODO MQRFH, MQCIH, MQDLH, MQIIH, MQRMH, MQSAPH, MQWIH, MQXQH, MQDH, MQEPH headers support
            int index = headerList.indexOf("MQRFH2");
            if (index >= 0) {
                LOGGER.info("MQRFH2 header detected (index " + index + ")");
                MQRFH2 rfh = (MQRFH2) headerList.get(index);
                LOGGER.info("\tmq.rfh2.format: " + rfh.getFormat());
                in.setHeader("mq.rfh2.format", rfh.getFormat());
                LOGGER.info("\tmq.rfh2.struct.id: " + rfh.getStrucId());
                in.setHeader("mq.rfh2.struct.id", rfh.getStrucId());
                LOGGER.info("\tmq.rfh2.encoding: " + rfh.getEncoding());
                in.setHeader("mq.rfh2.encoding", rfh.getEncoding());
                LOGGER.info("\tmq.rfh2.coded.charset.id: " + rfh.getCodedCharSetId());
                in.setHeader("mq.rfh2.coded.charset.id", rfh.getCodedCharSetId());
                LOGGER.info("\tmq.rfh2.flags: " + rfh.getFlags());
                in.setHeader("mq.rfh2.flags", rfh.getFlags());
                LOGGER.info("\tmq.rfh2.version: " + rfh.getVersion());
                in.setHeader("mq.rfh2.version", rfh.getVersion());
                MQRFH2.Element[] folders = rfh.getFolders();
                for (MQRFH2.Element folder : folders) {
                    LOGGER.info("mq.rfh2.folder " + folder.getName() + ": " + folder.toXML());
                    in.setHeader("mq.rfh2.folder." + folder.getName(), folder.toXML());
                }
            }


            LOGGER.info("Reading body");
            byte[] buffer = new byte[message.getDataLength()];
            message.readFully(buffer);
            String body = new String(buffer, "UTF-8");

            in.setBody(body, String.class);
            getProcessor().process(exchange);
        } catch (Exception e) {
            exchange.setException(e);
        } finally {
            if (destination != null)
                destination.close();
        }

        if (exchange.getException() != null) {
            getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
        }

        return 1;
    }

    @Override
    public WMQEndpoint getEndpoint() {
        return (WMQEndpoint) super.getEndpoint();
    }

}
