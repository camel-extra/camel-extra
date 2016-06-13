package org.apacheextras.camel.component.wmq;

public class WMQConfig {
	
	private String connectionMode;
	private String queueManagerName;
	private String queueManagerHostname;
	private String queueManagerPort;
	private String queueManagerChannel;
	private String queueUsername;
	private String queuePassword;
	
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
	public String getQueueManagerPort() {
		return queueManagerPort;
	}
	public void setQueueManagerPort(String queueManagerPort) {
		this.queueManagerPort = queueManagerPort;
	}
	public String getQueueManagerChannel() {
		return queueManagerChannel;
	}
	public void setQueueManagerChannel(String queueManagerChannel) {
		this.queueManagerChannel = queueManagerChannel;
	}
	public String getQueueUsername() {
		return queueUsername;
	}
	public void setQueueUsername(String queueUsername) {
		this.queueUsername = queueUsername;
	}
	public String getQueuePassword() {
		return queuePassword;
	}
	public void setQueuePassword(String queuePassword) {
		this.queuePassword = queuePassword;
	}
	
	
	

}
