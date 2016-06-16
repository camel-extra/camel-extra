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
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.MQHeaderList;
import com.ibm.mq.headers.MQRFH2;

import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.SuspendableService;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

public class WMQConsumer extends ScheduledPollConsumer implements SuspendableService {

    private final static Logger LOGGER = LoggerFactory.getLogger(WMQConsumer.class);

    private MQQueueManager queueManager;
    private WMQUtilities wmqUtilities;
    private TransactionTemplate transactionTemplate;

    public WMQConsumer(WMQEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
    }
    
    public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setWmqUtilities(WMQUtilities wmqUtilities) {
		this.wmqUtilities = wmqUtilities;
	}
    
    public WMQUtilities getWmqUtilities() {
		return wmqUtilities;
	}
    
    public void setQueueManager(MQQueueManager queueManager) {
		this.queueManager = queueManager;
	}
    
    public MQQueueManager getQueueManager() {
		return queueManager;
	}

    /**
     * Populate headers on the Exchange based on those found on the IBM MQ Message.
     * @param message The IBM message
     * @param in The exchange
     * @throws IOException
     * @throws MQDataException
     */
    public void populateHeaders(MQMessage message, Message in) throws IOException, MQDataException {
    	LOGGER.trace("\tmq.mqmd.format: {}", message.format);
        in.setHeader("mq.mqmd.format", message.format);
        LOGGER.trace("\tmq.mqmd.charset: {}", message.characterSet);
        in.setHeader("mq.mqmd.charset", message.characterSet);
        LOGGER.trace("\tmq.mqmd.expiry: {}", message.expiry);
        in.setHeader("mq.mqmd.expiry", message.expiry);
        LOGGER.trace("\tmq.mqmd.put.appl.name: {}", message.putApplicationName);
        in.setHeader("mq.mqmd.put.appl.name", message.putApplicationName);
        LOGGER.trace("\tmq.mqmd.group.id: {}", message.groupId);
        in.setHeader("mq.mqmd.group.id", message.groupId);
        LOGGER.trace("\tmq.mqmd.msg.seq.number: {}", message.messageSequenceNumber);
        in.setHeader("mq.mqmd.msg.seq.number", message.messageSequenceNumber);
        LOGGER.trace("\tmq.mqmd.msg.accounting.token: {}", message.accountingToken);
        in.setHeader("mq.mqmd.msg.accounting.token", message.accountingToken);
        LOGGER.trace("\tmq.mqmd.correl.id: {}", message.correlationId);
        in.setHeader("mq.mqmd.correl.id", message.correlationId);
        LOGGER.trace("\tmq.mqmd.replyto.q: {}", message.replyToQueueName);
        in.setHeader("mq.mqmd.replyto.q", message.replyToQueueName);
        LOGGER.trace("\tmq.mqmd.replyto.q.mgr: {}", message.replyToQueueManagerName);
        in.setHeader("mq.mqmd.replyto.q.mgr", message.replyToQueueManagerName);

        MQHeaderList headerList = new MQHeaderList(message);
        // TODO MQRFH, MQCIH, MQDLH, MQIIH, MQRMH, MQSAPH, MQWIH, MQXQH, MQDH, MQEPH headers support
        int index = headerList.indexOf("MQRFH2");
        if (index >= 0) {
            LOGGER.trace("MQRFH2 header detected (index " + index + ")");
            MQRFH2 rfh = (MQRFH2) headerList.get(index);
            LOGGER.trace("\tmq.rfh2.format: " + rfh.getFormat());
            in.setHeader("mq.rfh2.format", rfh.getFormat());
            LOGGER.trace("\tmq.rfh2.struct.id: " + rfh.getStrucId());
            in.setHeader("mq.rfh2.struct.id", rfh.getStrucId());
            LOGGER.trace("\tmq.rfh2.encoding: " + rfh.getEncoding());
            in.setHeader("mq.rfh2.encoding", rfh.getEncoding());
            LOGGER.trace("\tmq.rfh2.coded.charset.id: " + rfh.getCodedCharSetId());
            in.setHeader("mq.rfh2.coded.charset.id", rfh.getCodedCharSetId());
            LOGGER.trace("\tmq.rfh2.flags: " + rfh.getFlags());
            in.setHeader("mq.rfh2.flags", rfh.getFlags());
            LOGGER.trace("\tmq.rfh2.version: " + rfh.getVersion());
            in.setHeader("mq.rfh2.version", rfh.getVersion());
            MQRFH2.Element[] folders = rfh.getFolders();
            for (MQRFH2.Element folder : folders) {
                LOGGER.trace("mq.rfh2.folder " + folder.getName() + ": " + folder.toXML());
                in.setHeader("mq.rfh2.folder." + folder.getName(), folder.toXML());
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * 
     * Creates a new TransactionCallback object which will contain our transaction which follows:
     * 
     *  Get the MQQueueManager for this transaction
     *  Open a connection to the destination
     *  Get a message
     *  Process message
     */
    @Override
    protected int poll() throws Exception {
    	LOGGER.debug("Poll invoked on WMQConsumer"); 
    	getTransactionTemplate().execute(new TransactionCallback<Object>() {

			@Override
			public Object doInTransaction(TransactionStatus status) {
				
				LOGGER.trace("Get the MQQueueManager for this transaction");
				MQQueueManager manager = (MQQueueManager)TransactionSynchronizationManager.getResource("queueManager");
				String id = (String)TransactionSynchronizationManager.getResource("id");
				
				LOGGER.debug("Consumer transaction started with id " + id + " and mananger " + manager.toString());
				
				
				Exchange exchange = getEndpoint().createExchange();

		        Message in = exchange.getIn();        

		        MQDestination destination = null;
		        try {
		            LOGGER.trace("Consuming from {}", getEndpoint().getDestinationName());

		            String destinationName = getEndpoint().getDestinationName();
		            int MQOO;
		            
		            if (destinationName.startsWith("topic:")) {             
		                MQOO = CMQC.MQSO_CREATE | CMQC.MQSO_RESUME | CMQC.MQSO_DURABLE | CMQC.MQSO_FAIL_IF_QUIESCING;
		            } else {            
		                if (destinationName.startsWith("queue:")) {
		                    MQOO = MQConstants.MQOO_INPUT_AS_Q_DEF;
		                } else {
		                	MQOO = -1;
		                }
		            }
		            		         
		            LOGGER.trace("Create connection to the destination {}", destinationName);
		        	destination = wmqUtilities.accessDestination(getEndpoint().getDestinationName(), MQOO, manager);
					
		                 
		            MQMessage message = new MQMessage();
		            MQGetMessageOptions options = new MQGetMessageOptions();
		            options.options = MQConstants.MQGMO_WAIT + MQConstants.MQGMO_PROPERTIES_COMPATIBILITY + MQConstants.MQGMO_ALL_SEGMENTS_AVAILABLE + MQConstants.MQGMO_COMPLETE_MSG + MQConstants.MQGMO_ALL_MSGS_AVAILABLE;
		            options.waitInterval = MQConstants.MQWI_UNLIMITED;
		            LOGGER.trace("Waiting for message ...");
		            
		            LOGGER.trace("DESTINATION OPEN? " + destination.isOpen());
		            LOGGER.trace("QUEUE MANAGER OPEN? CONNECTED?" + manager.isConnected() + ", " + manager.isOpen());
		            
		            destination.get(message, options);

		            LOGGER.trace("Message consumed");

		            LOGGER.trace("Dealing with MQMD headers");
		            populateHeaders(message, in);
		            
		            LOGGER.trace("Reading body");
		            byte[] buffer = new byte[message.getDataLength()];
		            message.readFully(buffer);
		            String body = new String(buffer, "UTF-8");

		            in.setBody(body, String.class);
		            
		            getProcessor().process(exchange);
		            LOGGER.debug("Consumer transaction finished with id " + id + " and mananger " + manager.toString());
		        } catch (Exception e) {
		        	exchange.setException(e);
		        }/* finally {
		           /* if (destination != null) {
		                destination.close();
		            }
		        }*/

		        if (exchange.getException() != null) {
		            getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
		        }
		        
		        
		        return 1;
			}
		});
    	
        return 1;
    }

    @Override
    public WMQEndpoint getEndpoint() {
        return (WMQEndpoint) super.getEndpoint();
    }
}
