package org.apacheextras.camel.component.wmq;

import com.ibm.mq.MQDestination;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;

public class WMQUtilities {
	
	public WMQUtilities() {
		
	}

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
