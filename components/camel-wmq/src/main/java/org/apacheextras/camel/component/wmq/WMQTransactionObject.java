package org.apacheextras.camel.component.wmq;

import java.util.UUID;

import com.ibm.mq.MQQueueManager;

public class WMQTransactionObject {
	
	public WMQTransactionObject() {
		id = UUID.randomUUID().toString();
	}
	
	private String id;
	private MQQueueManager manager;

	public MQQueueManager getManager() {
		return manager;
	}

	public void setManager(MQQueueManager manager) {
		this.manager = manager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
