package org.apacheextras.camel.component.wmq;

import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;
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
        MQQueueManager queueManager = component.getQueueManager();

        MQQueue queue = null;
        try {
            LOGGER.debug("Consuming from queue {}", getEndpoint().getDestinationName());
            queue = queueManager.accessQueue(getEndpoint().getDestinationName(), MQConstants.MQOO_INPUT_AS_Q_DEF, null, null, null);
            MQMessage message = new MQMessage();
            MQGetMessageOptions options = new MQGetMessageOptions();
            options.options = MQConstants.MQGMO_WAIT + MQConstants.MQGMO_PROPERTIES_COMPATIBILITY + MQConstants.MQGMO_ALL_SEGMENTS_AVAILABLE + MQConstants.MQGMO_COMPLETE_MSG + MQConstants.MQGMO_ALL_MSGS_AVAILABLE;
            options.waitInterval = MQConstants.MQWI_UNLIMITED;
            LOGGER.debug("Waiting for message ...");
            queue.get(message, options);

            LOGGER.debug("Get a message with a length of {}", message.getMessageLength());
            String msgText = message.readStringOfByteLength(message.getMessageLength());
            message.seek(4);
            int version = message.readInt();
            in.setHeader("MQRFH_VERSION", version);
            int length = message.readInt();
            in.setHeader("MQLENGTH", length);
            int encoding = message.readInt();
            in.setHeader("MQENC", encoding);
            int codedCharSetId = message.readInt();
            in.setHeader("MQCCSI", codedCharSetId);
            String format = message.readStringOfByteLength(8);
            in.setHeader("MQFMT", format);
            int flags = message.readInt();
            in.setHeader("MQFLAGS", flags);
            int nameValueCodedCharSetId = message.readInt();
            in.setHeader("MQNVCCSI", nameValueCodedCharSetId);
            int STRUC_LENGTH = 36;
            int cnt = 1;

            int ln = 0;
            int remainLength = length - STRUC_LENGTH;
            while (remainLength > 0) {

                int areaLen = message.readInt();
                // byte[] b = new byte[areaLen];
                byte[] b = new byte[message.getMessageLength()];
                message.readFully(b);

                String areaAsString = new String(b, "UTF-8");

                if (areaAsString.contains("<mcd>") || areaAsString.contains("<MCD>")) {
                    LOGGER.debug("MCD V2 FOLDER detected");
                    in.setHeader("MQ.V2FLDR.MCD", areaAsString);
                } else if (areaAsString.contains("<psc>") || areaAsString.contains("<PSC>")) {
                    LOGGER.debug("PSC/PUB V2 FOLDER detected");
                    in.setHeader("MQ.V2FLDR.PUB", areaAsString);
                } else if (areaAsString.contains("<usr>") || areaAsString.contains("<USR>")) {
                    LOGGER.debug("USR V2 FOLDER detected");
                    in.setHeader("MQ.V2FLDR.USR", areaAsString);
                } else if (areaAsString.contains("<pscr>") || areaAsString.contains("<PSCR>")) {
                    LOGGER.debug("PSCR V2 FOLDER detected");
                    in.setHeader("MQ.V2FLDR.PSCR", areaAsString);
                } else if (areaAsString.contains("<other>") || areaAsString.contains("<OTHER>")) {
                    LOGGER.debug("OTHER V2 FOLDER detected");
                    in.setHeader("MQ.V2FLDR.OTHER", areaAsString);
                }

                remainLength = remainLength - areaLen - 4;
                cnt = cnt + 1;

                ln = ln + areaLen;

            }

            ln = ln + 36 + 12;

            msgText = msgText.substring(ln);

            in.setBody(msgText, String.class);
            getProcessor().process(exchange);
        } catch (Exception e) {
            exchange.setException(e);
        } finally {
            if (queue != null)
                queue.close();
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
