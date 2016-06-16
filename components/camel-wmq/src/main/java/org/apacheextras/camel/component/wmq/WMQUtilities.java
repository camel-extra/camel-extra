package org.apacheextras.camel.component.wmq;

import com.ibm.mq.MQDestination;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;

public class WMQUtilities {
	
	public WMQUtilities() {
		
	}
	
	/**
	 * Utility method for creating a connection to a MQDestination, shared by both the consumer and producer. 
	 * @param destinationName The destination (queue:name or topic:name)
	 * @param MQOO The destination connectivity options
	 * @param queueMananger The queue mananger to use for the connection
	 * @return MQDestination object which can be used to operate on the object
	 * @throws MQException If a error occurs connecting to the destination, see the error code for details.
	 */
	public MQDestination accessDestination(String destinationName, int MQOO, MQQueueManager queueMananger) throws MQException {
    	MQDestination destination;
        if (destinationName.startsWith("topic:")) {
            destinationName = destinationName.substring("topic:".length());
            destination = queueMananger.accessTopic(destinationName, null, MQOO, null, null);
        } else {
            if (destinationName.startsWith("queue:")) {
                destinationName = destinationName.substring("queue:".length());
            }
            destination = queueMananger.accessQueue(destinationName, MQOO, null, null, null);
        }
        return destination;
    }
	
}
