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

import com.ibm.mq.MQDestination;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQDataException;

import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

public class WMQProducer extends DefaultProducer {

    private final static Logger LOGGER = LoggerFactory.getLogger(WMQProducer.class);

    private final WMQEndpoint endpoint;

    private TransactionTemplate transactionTemplate;
    private WMQUtilities wmqUtilities;
    
    public WMQProducer(WMQEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
        LOGGER.trace("WMQ producer created");
        
    }

	@Override
    public WMQEndpoint getEndpoint() {
        return (WMQEndpoint) super.getEndpoint();
    }
    
    public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public WMQUtilities getWmqUtilities() {
		return wmqUtilities;
	}
    
    public void setWmqUtilities(WMQUtilities wmqUtilities) {
		this.wmqUtilities = wmqUtilities;
	}

    /**
     * Populate headers on the IBM MQ Message based on those found on the Exchange.
     * @param in The Exchange
     * @param message The IBM message
     * @throws IOException
     * @throws MQDataException
     */
    public void populateHeaders(Message in, MQMessage message) throws IOException {
    	LOGGER.trace("Populating MQMD headers");
        if (in.getHeader("mq.mqmd.format") != null)
            message.format = (String) in.getHeader("mq.mqmd.format");
        if (in.getHeader("mq.mqmd.charset") != null)
            message.characterSet = (Integer) in.getHeader("mq.mqmd.charset");
        if (in.getHeader("mq.mqmd.expiry") != null)
            message.expiry = (Integer) in.getHeader("mq.mqmd.expiry");
        if (in.getHeader("mq.mqmd.put.appl.name") != null)
            message.putApplicationName = (String) in.getHeader("mq.mqmd.put.appl.name");
        if (in.getHeader("mq.mqmd.group.id") != null)
            message.groupId = (byte[]) in.getHeader("mq.mqmd.group.id");
        if (in.getHeader("mq.mqmd.msg.seq.number") != null)
            message.messageSequenceNumber = (Integer) in.getHeader("mq.mqmd.msg.seq.number");
        if (in.getHeader("mq.mqmd.msg.accounting.token") != null)
            message.accountingToken = (byte[]) in.getHeader("mq.mqmd.msg.accounting.token");
        if (in.getHeader("mq.mqmd.correl.id") != null)
            message.correlationId = (byte[]) in.getHeader("mq.mqmd.correl.id");
        if (in.getHeader("mq.mqmd.replyto.q") != null)
            message.replyToQueueName = (String) in.getHeader("mq.mqmd.replyto.q");
        if (in.getHeader("mq.mqmd.replyto.q.mgr") != null)
            message.replyToQueueManagerName = (String) in.getHeader("mq.mqmd.replyto.q.mgr");
        boolean rfh2 = false;
        if (in.getHeaders().containsKey("mq.rfh2.format")) {
            LOGGER.trace("mq.rfh2.format");
            message.format = MQConstants.MQFMT_RF_HEADER_2;
            rfh2 = true;
        }
        if (in.getHeader("mq.rfh2.struct.id") != null && rfh2) {
            LOGGER.trace("mq.rfh2.struct.id defined: {}", in.getHeader("mq.rfh2.struct.id"));
            message.writeString((String) in.getHeader("mq.rfh2.struct.id"));
        } else if (rfh2){
            LOGGER.trace("mq.rfh2.struct.id not defined, fallback: {}", MQConstants.MQRFH_STRUC_ID);
            message.writeString(MQConstants.MQRFH_STRUC_ID);
        }
        if (in.getHeader("mq.rfh2.version") != null && rfh2) {
            LOGGER.trace("mq.rfh2.version defined: {}", in.getHeader("mq.rfh2.version"));
            message.writeInt4((Integer) in.getHeader("mq.rfh2.version"));
        } else if (rfh2){
            LOGGER.trace("mq.rfh2.version not defined, fallback: {}", MQConstants.MQRFH_VERSION_2);
            message.writeInt4(MQConstants.MQRFH_VERSION_2);
        }

        // TODO iterator on the headers and folders
        // v2 folders: mcd, jms, usr, PubSub, pscr, other
        LOGGER.trace("Dealing with RFH2 folders");
        String mcd = (String) in.getHeader("mq.rfh2.folder.mcd");
        String jms = (String) in.getHeader("mq.rfh2.folder.jms");
        String usr = (String) in.getHeader("mq.rfh2.folder.usr");
        String pub = (String) in.getHeader("mq.rfh2.folder.psc");
        String pscr = (String) in.getHeader("mq.rfh2.folder.pscr");
        String other = (String) in.getHeader("mq.rfh2.folder.other");

        if (mcd != null) {
            LOGGER.trace("MCD V2 FOLDER: {}", mcd);
            while (mcd.length() % 4 != 0) {
                mcd = mcd + " ";
            }
        }
        if (jms != null) {
            LOGGER.trace("JMS V2 FOLDER: {}", jms);
            while (jms.length() % 4 != 0) {
                jms = jms + " ";
            }
        }
        if (usr != null) {
            LOGGER.trace("USR V2 FOLDER: {}", usr);
            while (usr.length() % 4 != 0) {
                usr = usr + " ";
            }
        }
        if (pub != null) {
            LOGGER.trace("PUB V2 FOLDER: {}", pub);
            while (pub.length() % 4 != 0) {
                pub = pub + " ";
            }
        }
        if (pscr != null) {
            LOGGER.trace("PSCR V2 FOLDER: {}", pscr);
            while (pscr.length() % 4 != 0) {
                pscr = pscr + " ";
            }
        }
        if (other != null) {
            LOGGER.trace("OTHER V2 FOLDER: {}", other);
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

        if (rfh2) {
            LOGGER.trace("Set message length: {}", MQConstants.MQRFH_STRUC_LENGTH_FIXED_2 + folderSize + overhead);
            message.writeInt4(MQConstants.MQRFH_STRUC_LENGTH_FIXED_2 + folderSize + overhead);
        }
        if (in.getHeader("mq.rfh2.encoding") != null && rfh2) {
            LOGGER.trace("mq.rfh2.encoding defined: {}", in.getHeader("mq.rfh2.encoding"));
            message.writeInt4((Integer) in.getHeader("mq.rfh2.encoding"));
        } else if (rfh2) {
            LOGGER.trace("mq.rfh2.encoding not defined, fallback: {}", MQConstants.MQENC_NATIVE);
            message.writeInt4(MQConstants.MQENC_NATIVE);
        }
        if (in.getHeader("mq.rfh2.coded.charset.id") != null && rfh2) {
            LOGGER.trace("mq.rfh2.coded.charset.id defined: {}", in.getHeader("mq.rfh2.coded.charset.id"));
            message.writeInt4((Integer) in.getHeader("mq.rfh2.coded.charset.id"));
        } else if (rfh2) {
            LOGGER.trace("mq.rfh2.coded.charset.id not defined, fallback: {}", MQConstants.MQCCSI_DEFAULT);
            message.writeInt4(MQConstants.MQCCSI_DEFAULT);
        }
        if (rfh2) {
            message.writeString(MQConstants.MQFMT_NONE);
            message.writeInt4(MQConstants.MQRFH_NO_FLAGS);
            message.writeInt4(1208);
        }
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
    }
    
    /**
     * Checks whether message segmentation is enabled. Currently always set to true.
     * @return true
     */
    public boolean isSegmented() {
    	return true;
    }
    
    /**
     * Creates put options on the IBM MQ Message based on the header mq.put.options on the exchange
     * @param in
     * @return
     */
    public MQPutMessageOptions createPutMessageOptions(Message in) {
    	MQPutMessageOptions options = new MQPutMessageOptions();
    	// check if header exists, if it does then use values otherwise return null
    	if (in.getHeader("mq.put.options") != null ) {
    		String[] optionsA = ((String) in.getHeader("mq.put.options")).split(",");
    	
    		int optionValue = 0;
    		for(int i = 0; i < optionsA.length; i++) {
    			optionValue += MQConstants.getIntValue(optionsA[i]);
    		}
    		options.options = optionValue;
    		return options;
    	} else {
    		return null;
    	}
    }
    
    /**
     * {@inheritDoc}
     * 
     * Process a message in the following way:
     *   Get the queue manager for this transaction
     *   Create a connection to a destination
     *   Populate headers
     *   Send message
     *   Close connection
     */
    public void process(Exchange exchange) throws Exception {
       
    	LOGGER.trace("Get the MQQueueManager for this transaction");
    	MQQueueManager manager = (MQQueueManager)TransactionSynchronizationManager.getResource("queueManager");
    	String id = (String)TransactionSynchronizationManager.getResource("id");
    	
    	LOGGER.debug("Producer transaction started with id " + id + " and mananger " + manager.toString() + " on thread " + Thread.currentThread().getId());
  	
        Message in = exchange.getIn();

        LOGGER.trace("Accessing to MQQueue {}", endpoint.getDestinationName());
        int MQOO = MQConstants.MQOO_OUTPUT;
        if (in.getHeader("MQOO") != null) {
            LOGGER.trace("MQOO defined to {}", in.getHeader("MQOO"));
            MQOO = (Integer) in.getHeader("MQOO");
        }
        
        MQDestination destination = null;
        try {
	        destination = wmqUtilities.accessDestination(getEndpoint().getDestinationName(), MQOO, manager);
        	
	        LOGGER.trace("Creating MQMessage");
	        MQMessage message = new MQMessage();        
	        populateHeaders(in, message);
	        
	        LOGGER.trace("Set message segmentation to on based on segmentation settings");
	        if (isSegmented()) {        	
	        	message.messageFlags = MQConstants.MQMF_SEGMENTATION_ALLOWED;
	        	message.groupId = null;
	        }
	        
	        message.writeString(in.getBody(String.class));
	        
	        LOGGER.trace("Putting the message ...");        	
	        MQPutMessageOptions putOptions = createPutMessageOptions(in);
	        if (putOptions != null) {
	        	LOGGER.trace("PutOptions are present");
	        	destination.put(message, putOptions);
	        } else {
	        	destination.put(message);
	        }
	        LOGGER.debug("Producer transaction finished with id " + id + " and mananger " + manager.toString() + " on thread " + Thread.currentThread().getId());
        	destination.close();
        } finally {
            if (destination != null) {
               destination.close();
            }
        }
    }
}
