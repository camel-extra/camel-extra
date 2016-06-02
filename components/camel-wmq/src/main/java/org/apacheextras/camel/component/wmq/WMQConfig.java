package org.apacheextras.camel.component.wmq;

public class WMQConfig {
	
	private String connectionMode;
	private String queueManagerName;
	private String queueManagerHostname;
	
	public String getConnectionMode() {
		return connectionMode;
	}
	public void setConnectionMode(String connectionMode) {
		this.connectionMode = connectionMode;
	}
	public String getQueueManagerName() {
		return queueManagerName;
	}
	public void setQueueManagerName(String queueManagerName) {
		this.queueManagerName = queueManagerName;
	}
	public String getQueueManagerHostname() {
		return queueManagerHostname;
	}
	public void setQueueManagerHostname(String queueManagerHostname) {
		this.queueManagerHostname = queueManagerHostname;
	}
	
	

}
