package org.apacheextras.camel.component.wmq;

import java.util.UUID;

public class WMQTransactionObject {

	
	
	public WMQTransactionObject() {
		id = UUID.randomUUID().toString();
	}
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
