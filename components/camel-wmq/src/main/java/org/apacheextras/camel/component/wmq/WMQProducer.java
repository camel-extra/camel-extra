package org.apacheextras.camel.component.wmq;

import com.ibm.mq.MQDestination;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WMQProducer extends DefaultProducer {

    private final static Logger LOGGER = LoggerFactory.getLogger(WMQProducer.class);

    private final WMQEndpoint endpoint;

    public WMQProducer(WMQEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @Override
    public WMQEndpoint getEndpoint() {
        return (WMQEndpoint) super.getEndpoint();
    }

    public void process(Exchange exchange) throws Exception {
        WMQComponent component = (WMQComponent) this.getEndpoint().getComponent();
        MQQueueManager queueManager = component.getQueueManager();

        Message in = exchange.getIn();

        LOGGER.debug("Accessing to MQQueue {}", endpoint.getDestinationName());
        int MQOO = MQConstants.MQOO_OUTPUT;
        if (in.getHeader("MQOO") != null) {
            LOGGER.debug("MQOO defined to {}", in.getHeader("MQOO"));
            MQOO = (Integer) in.getHeader("MQOO");
        }
        MQDestination destination;
        if (endpoint.getDestinationType() == null || endpoint.getDestinationType().equalsIgnoreCase("queue"))
            destination = queueManager.accessQueue(endpoint.getDestinationName(), MQOO, null, null, null);
        else destination = queueManager.accessTopic(endpoint.getDestinationName(), null, MQOO, null, null, null);

        LOGGER.debug("Creating MQMessage");
        MQMessage message = new MQMessage();
        if (in.getHeader("MQFMT") != null) {
            LOGGER.debug("Message format (MQFMT) set to {}", in.getHeader("MQFMT"));
            message.format = (String) in.getHeader("MQFMT");
        } else {
            LOGGER.debug("Message format (MQFMT) set to MQFMT_RF_HEADER_2 ({})", MQConstants.MQFMT_RF_HEADER_2);
            message.format = MQConstants.MQFMT_RF_HEADER_2;
        }
        if (in.getHeader("MQRFH_STRUCT") != null) {
            LOGGER.debug("Message RFH Struct ID (MQRFH_STRUCT) set to {}", in.getHeader("MQRFH_STRUCT"));
            message.writeString((String) in.getHeader("MQRFH_STRUCT"));
        } else {
            LOGGER.debug("Message RFH Struct ID (MQRFH_STRUCT) set to MQRFH_STRUC_ID ({})", MQConstants.MQRFH_STRUC_ID);
            message.writeString(MQConstants.MQRFH_STRUC_ID);
        }
        if (in.getHeader("MQRFH_VERSION") != null) {
            LOGGER.debug("Message RFH Version (MQRFH_VERSION) set to {}", in.getHeader("MQRFH_VERSION"));
            message.writeInt4((Integer) in.getHeader("MQRFH_VERSION"));
        } else {
            LOGGER.debug("Message RFH Version (MQRFH_VERSION) set to MQRFH_VERSION_2 ({})", MQConstants.MQRFH_VERSION_2);
            message.writeInt4(MQConstants.MQRFH_VERSION_2);
        }

        // v2 folders: mcd, jms, usr, PubSub, pscr, other
        LOGGER.debug("Dealing with V2 folders");
        String mcd = (String) in.getHeader("MQ.V2FLDR.MCD");
        String jms = (String) in.getHeader("MQ.V2FLDR.JMS");
        String usr = (String) in.getHeader("MQ.V2FLDR.USR");
        String pub = (String) in.getHeader("MQ.V2FLDR.PUB");
        String pscr = (String) in.getHeader("MQ.V2FLDR.PSCR");
        String other = (String) in.getHeader("MQ.V2FLDR.OTHER");

        if (mcd != null) {
            LOGGER.debug("MCD V2 FOLDER: {}", mcd);
            while (mcd.length() % 4 != 0) {
                mcd = mcd + " ";
            }
        }
        if (jms != null) {
            LOGGER.debug("JMS V2 FOLDER: {}", jms);
            while (jms.length() % 4 != 0) {
                jms = jms + " ";
            }
        }
        if (usr != null) {
            LOGGER.debug("USR V2 FOLDER: {}", usr);
            while (usr.length() % 4 != 0) {
                usr = usr + " ";
            }
        }
        if (pub != null) {
            LOGGER.debug("PUB V2 FOLDER: {}", pub);
            while (pub.length() % 4 != 0) {
                pub = pub + " ";
            }
        }
        if (pscr != null) {
            LOGGER.debug("PSCR V2 FOLDER: {}", pscr);
            while (pscr.length() % 4 != 0) {
                pscr = pscr + " ";
            }
        }
        if (other != null) {
            LOGGER.debug("OTHER V2 FOLDER: {}", other);
            while (other.length() % 4 != 0) {
                other = other + " ";
            }
        }

        int folderSize = 0;
        int overhead = 0;
        if (mcd != null) {
            folderSize = folderSize + mcd.length();
            overhead = overhead + 4;
        }
        if (jms != null) {
            folderSize = folderSize + jms.length();
            overhead = overhead + 4;
        }
        if (usr != null) {
            folderSize = folderSize + usr.length();
            overhead = overhead + 4;
        }
        if (pub != null) {
            folderSize = folderSize + pub.length();
            overhead = overhead + 4;
        }
        if (pscr != null) {
            folderSize = folderSize + pscr.length();
            overhead = overhead + 4;
        }
        if (other != null) {
            folderSize = folderSize + other.length();
            overhead = overhead + 4;
        }

        LOGGER.debug("Set message length: {}", MQConstants.MQRFH_STRUC_LENGTH_FIXED_2 + folderSize + overhead);
        message.writeInt4(MQConstants.MQRFH_STRUC_LENGTH_FIXED_2 + folderSize + overhead);
        if (in.getHeader("MQENC") != null) {
            LOGGER.debug("Message encoding (MQENC) set to {}", in.getHeader("MQENC"));
            message.writeInt4((Integer) in.getHeader("MQENC"));
        } else {
            LOGGER.debug("Message encoding (MQENC) set to MQENC_NATIVE ({})", MQConstants.MQENC_NATIVE);
            message.writeInt4(MQConstants.MQENC_NATIVE);
        }
        if (in.getHeader("MQCCSI") != null) {
            LOGGER.debug("Message coded character set id (MQCCSI) set to {}", in.getHeader("MQCCSI"));
            message.writeInt4((Integer) in.getHeader("MQCCSI"));
        } else {
            LOGGER.debug("Message coded character set id (MQCCSI) set to MQCCSI_DEFAULT ({})", MQConstants.MQCCSI_DEFAULT);
            message.writeInt4(MQConstants.MQCCSI_DEFAULT);
        }
        message.writeString(MQConstants.MQFMT_NONE);
        message.writeInt4(MQConstants.MQRFH_NO_FLAGS);
        message.writeInt4(1208);
        if (mcd != null) {
            message.writeInt4(mcd.length());
            message.writeString(mcd);
        }
        if (jms != null) {
            message.writeInt4(jms.length());
            message.writeString(jms);
        }
        if (usr != null) {
            message.writeInt4(usr.length());
            message.writeString(usr);
        }
        if (pub != null) {
            message.writeInt4(pub.length());
            message.writeString(pub);
        }
        if (pscr != null) {
            message.writeInt4(pscr.length());
            message.writeString(pscr);
        }
        if (other != null) {
            message.writeInt4(other.length());
            message.writeString(other);
        }

        // TODO push all other in.headers in the MQ Message ?

        message.writeString(in.getBody(String.class));

        LOGGER.debug("Putting the message ...");
        destination.put(message);
        destination.close();
    }

}
