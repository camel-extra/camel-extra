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

import java.util.Hashtable;

import org.apache.camel.Component;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.api.management.ManagedAttribute;
import org.apache.camel.api.management.ManagedResource;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;

@ManagedResource(description = "Managed WMQ Endpoint")
@UriEndpoint(scheme = "wmq", title = "IBM WebSphere MQ", syntax = "wmq:destinationName", consumerClass = WMQConsumer.class)
public class WMQEndpoint extends DefaultEndpoint {

	private final static Logger LOGGER = LoggerFactory.getLogger(WMQComponent.class);
	
	private MQQueueManager MQQueueManager;
	
    @UriParam
    private String destinationName;

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public WMQEndpoint() {
    }

    public WMQEndpoint(String uri, Component component, String destinationName) {
        super(uri, component);
        this.destinationName = destinationName;
    }
    
    public void setMQQueueManager(MQQueueManager mQQueueManager) {
		MQQueueManager = mQQueueManager;
	}
    
    public MQQueueManager getMQQueueManager() {
		return MQQueueManager;
	}

    public Producer createProducer() throws Exception {
    	WMQProducer producer = new WMQProducer(this);
    	producer.setQueueManager(createMQQueueManager());
    	producer.setWmqUtilities(new WMQUtilities());
        return producer;
    }

    public WMQConsumer createConsumer(Processor processor) throws Exception {
        WMQConsumer consumer = new WMQConsumer(this, processor);
        consumer.setQueueManager(createMQQueueManager());
        consumer.setWmqUtilities(new WMQUtilities());
        consumer.setDelay(5);
        return consumer;
    }
    
    
    private WMQConfig wmqConfig;
    
    public WMQConfig getWmqConfig() {
		return wmqConfig;
	}
    
    public void setWmqConfig(WMQConfig wmqConfig) {
		this.wmqConfig = wmqConfig;
	}
    
    /**
     * Create a MQQueueMananger for this Endpoint
     * @return
     * @throws MQException
     */
    public MQQueueManager createMQQueueManager() throws MQException {
    	Hashtable<String,Object> properties = new Hashtable<String,Object>();
    	//properties.put("hostname", get);
         //connectionProperties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES_BINDINGS);
    	if (getWmqConfig().getConnectionMode().equals("binding")) {
    		properties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES_BINDINGS);
    	} else {
    		properties.put("hostname",getWmqConfig().getQueueManagerHostname());
    	}
    	LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + getWmqConfig().getQueueManagerName());
    	return new MQQueueManager(getWmqConfig().getQueueManagerName(),properties);
    }

    @ManagedAttribute
    public boolean isSingleton() {
        return true;
    }

}
