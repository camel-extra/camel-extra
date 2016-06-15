package org.apacheextras.camel.component.wmq;

public class WMQQueueManager {

	private String connectionMode;
    private String queueManagerName;
    private String queueManagerHostname;
    private String queueManagerPort;
    private String queueManagerChannel;
    private String queueManagerUserID;
    private String queueManagerPassword;
    private String queueManagerCCSID;
    
    public WMQQueueManager() {
    	
    }
    
    /**
     * Gets hostname
     * @return
     */
    public String getQueueManagerHostname() {
		return queueManagerHostname;
	}
    
    /**
     * Sets hostname
     * @param hostname
     */
    public void setQueueManagerHostname(String hostname) {
		this.queueManagerHostname = hostname;
	}
    
    /**
     * Returns whether we are in binding mode or client mode 
     * @return
     */
    public String getConnectionMode() {
		return connectionMode;
	}
    
    /**
     * Sets the binding mode
     * @param connectionMode
     */
    public void setConnectionMode(String connectionMode) {
		this.connectionMode = connectionMode;
	}
    
    /**
     * Get the queue manager name
     * @return
     */
    public String getQueueManagerName() {
		return queueManagerName;
	}
    
    /**
     * Set the queue mananger name
     * @param queueManagerName
     */
    public void setQueueManagerName(String queueManagerName) {
		this.queueManagerName = queueManagerName;
	}

    /**
     * Get queue manager port
     * @return
     */
    public String getQueueManagerPort() {
        return queueManagerPort;
    }

    /**
     * Set queue mananger port
     * @param queueManagerPort
     */
    public void setQueueManagerPort(String queueManagerPort) {
        this.queueManagerPort = queueManagerPort;
    }

    /**
     * Get queue manager channel
     * @return
     */
    public String getQueueManagerChannel() {
        return queueManagerChannel;
    }

    /**
     * Set queue mananger channel
     * @param queueManagerChannel
     */
    public void setQueueManagerChannel(String queueManagerChannel) {
        this.queueManagerChannel = queueManagerChannel;
    }

    /**
     * Get queue manager user id
     * @return
     */
    public String getQueueManagerUserID() {
        return queueManagerUserID;
    }

    /**
     * Set queue mananger user id
     * @param queueManagerUserID
     */
    public void setQueueManagerUserID(String queueManagerUserID) {
        this.queueManagerUserID = queueManagerUserID;
    }

    /**
     * Get queue mananger password
     * @return
     */
    public String getQueueManagerPassword() {
        return queueManagerPassword;
    }
    
    /**
     * Set queue mananger password
     * @param queueManagerPassword
     */
    public void setQueueManagerPassword(String queueManagerPassword) {
        this.queueManagerPassword = queueManagerPassword;
    }

    /**
     * Get queue manager CCSID
     * @return
     */
    public String getQueueManagerCCSID() {
        return queueManagerCCSID;
    }

    /**
     * Set Queue mananger CCSID
     * @param queueManagerCCSID
     */
    public void setQueueManagerCCSID(String queueManagerCCSID) {
        this.queueManagerCCSID = queueManagerCCSID;
    }

	
}
