package org.apacheextras.camel.component.wmq;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
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
	
	private Map<String, MQQueueManager> queueManagers;
	
	public WMQConfig() {
		queueManagers = new HashMap<String, MQQueueManager>();
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
	
	/**
     * Create a MQQueueMananger for this Endpoint
     * @return
     * @throws MQException
     */
    public MQQueueManager createMQQueueManager() throws MQException {
    	
		LOGGER.debug("Creating MQQueueManager");
    	Hashtable<String,Object> properties = new Hashtable<String,Object>();
    	//properties.put("hostname", get);
         //connectionProperties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES_BINDINGS);
    	if (getConnectionMode().equals("binding")) {
    		properties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES_BINDINGS);
    	} else {
    		LOGGER.debug("ELSEBLOCK: Client connection being used");
    		properties.put("hostname",getQueueManagerHostname());
    		properties.put("port", Integer.parseInt(getQueueManagerPort()));
    		properties.put("channel", getQueueManagerChannel());
    		properties.put(MQConstants.USER_ID_PROPERTY, getQueueManagerUsername());
    		properties.put(MQConstants.USE_MQCSP_AUTHENTICATION_PROPERTY, true);
    		properties.put(MQConstants.PASSWORD_PROPERTY, getQueueManagerPassword());
    	}
    	LOGGER.debug("Attempting to create MQQueueManager with queue name: " + getQueueManagerName());
    	MQQueueManager manager = new MQQueueManager(getQueueManagerName(),properties);
    	
    	LOGGER.debug("Manager successfully created");
    	return manager;
    }
    /*	if (instance == null) {
    		instance = "default";
    	}
    	
    	MQQueueManager manager = queueManagers.get(instance);
    	
    	if (manager == null) {
    		LOGGER.debug("No queue mananger with instance name " + instance + " setup");
    		LOGGER.debug("Creating MQQueueManager");
        	Hashtable<String,Object> properties = new Hashtable<String,Object>();
        	//properties.put("hostname", get);
             //connectionProperties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES_BINDINGS);
        	if (getConnectionMode().equals("binding")) {
        		properties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES_BINDINGS);
        	} else {
        		LOGGER.debug("ELSEBLOCK: Client connection being used");
        		properties.put("hostname",getQueueManagerHostname());
        		properties.put("port", Integer.parseInt(getQueueManagerPort()));
        		properties.put("channel", getQueueManagerChannel());
        		properties.put(MQConstants.USER_ID_PROPERTY, getQueueManagerUsername());
        		properties.put(MQConstants.USE_MQCSP_AUTHENTICATION_PROPERTY, true);
        		properties.put(MQConstants.PASSWORD_PROPERTY, getQueueManagerPassword());
        	}
        	LOGGER.debug("Attempting to create MQQueueManager with queue name: " + getQueueManagerName());
        	manager = new MQQueueManager(getQueueManagerName(),properties);
        	queueManagers.put(instance, manager);
        	LOGGER.debug("Manager successfully created");
    	}
    	return manager;
    }*/
}
