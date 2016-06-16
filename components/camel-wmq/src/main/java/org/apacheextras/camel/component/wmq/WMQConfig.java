package org.apacheextras.camel.component.wmq;

import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.MQSimpleConnectionManager;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;

public class WMQConfig {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(WMQComponent.class);

	private String connectionMode;
	private String queueManagerName;
	private String queueManagerHostname;
	private String queueManagerPort;
	private String queueManagerChannel;
	private String queueManagerUsername;
	private String queueManagerPassword;
	
	private MQSimpleConnectionManager connectionManager;

	public WMQConfig() {
		
	}
	
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
	
	public String getQueueManagerUsername() {
		return queueManagerUsername;
	}
	public void setQueueManagerUsername(String queueManagerUsername) {
		this.queueManagerUsername = queueManagerUsername;
	}
	public String getQueueManagerPassword() {
		return queueManagerPassword;
	}
	public void setQueueManagerPassword(String queueManagerPassword) {
		this.queueManagerPassword = queueManagerPassword;
	}
	
	public MQSimpleConnectionManager getConnectionManager() {
		return connectionManager;
	}

	public void setConnectionManager(MQSimpleConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	/**
	 * Creates a MQQueueManager based on a connection manager passed into this class or a default pool if no pool is passed in.
	 * 
	 * The type of connection is controlled by the connection mode of either "binding" or "client".
	 * 
	 * If binding mode is specified only the queue manager name is needed.
	 * If connection mode is specified then the host, port, channel, username and password are needed.
	 * 
	 * @return A MQQueueManager instance
	 * @throws MQException - thrown if the creation of the MQQueueManager fails. Check the MQQueueManager error code for details.
	 *                       
	 */
    public MQQueueManager createMQQueueManager() throws MQException {
    	
    	LOGGER.trace("If no connection manager setup, then create a default one");
    	if(connectionManager == null) {
    		connectionManager = new MQSimpleConnectionManager();
    	} 
    	
    	LOGGER.trace("If not already, set the connection manager to active");
    	if(connectionManager.getActive() != MQSimpleConnectionManager.MODE_ACTIVE) {
    		connectionManager.setActive(MQSimpleConnectionManager.MODE_ACTIVE);
    	}
    	
		LOGGER.debug("Creating MQQueueManager based on connection mode: " + getConnectionMode());
    	Hashtable<String,Object> properties = new Hashtable<String,Object>();
    	if (getConnectionMode().equals("binding")) {
    		properties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES_BINDINGS);
    	} else {
    		properties.put("hostname",getQueueManagerHostname());
    		properties.put("port", Integer.parseInt(getQueueManagerPort()));
    		properties.put("channel", getQueueManagerChannel());
    		properties.put(MQConstants.USER_ID_PROPERTY, getQueueManagerUsername());
    		properties.put(MQConstants.USE_MQCSP_AUTHENTICATION_PROPERTY, true);
    		properties.put(MQConstants.PASSWORD_PROPERTY, getQueueManagerPassword());
    	}
    	LOGGER.debug("Attempting to create MQQueueManager with queue name: " + getQueueManagerName());
    	MQQueueManager manager = new MQQueueManager(getQueueManagerName(), properties, connectionManager); 
    	LOGGER.debug("MQQueueManager successfully created");
    	
    	return manager;
    }
}
