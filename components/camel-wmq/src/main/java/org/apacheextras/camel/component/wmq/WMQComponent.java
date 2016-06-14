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

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class WMQComponent extends UriEndpointComponent {

    private final static Logger LOGGER = LoggerFactory.getLogger(WMQComponent.class);

    public WMQComponent() {
        super(WMQEndpoint.class);
    }

    public WMQComponent(CamelContext camelContext) {
        super(camelContext, WMQEndpoint.class);
    }
    
    private String connectionMode;
    private String queueManagerName;
    private String queueManagerHostname;
    private String queueManagerPort;
    private String queueManagerChannel;
    private String queueManagerUserID;
    private String queueManagerPassword;
    private String queueManagerCCSID;
    
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

    public WMQConfig createConfig() {
    	WMQConfig conf = new WMQConfig();
    	conf.setConnectionMode(getConnectionMode());
    	conf.setQueueManagerHostname(getQueueManagerHostname());
    	conf.setQueueManagerName(getQueueManagerName());
    	conf.setQueueManagerChannel(getQueueManagerChannel());
    	conf.setQueueManagerPort(getQueueManagerPort());
    	conf.setQueueUsername(getQueueManagerUserID());
    	conf.setQueuePassword(getQueueManagerPassword());
    	return conf;
    }
    
    @Override
    public Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
       WMQEndpoint endpoint = new WMQEndpoint(uri, this, remaining);
       endpoint.setWmqConfig(createConfig());
       return endpoint;
    }

}
