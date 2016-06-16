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
import org.springframework.transaction.support.TransactionTemplate;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;

@ManagedResource(description = "Managed WMQ Endpoint")
@UriEndpoint(scheme = "wmq", title = "IBM WebSphere MQ", syntax = "wmq:destinationName", consumerClass = WMQConsumer.class)
public class WMQEndpoint extends DefaultEndpoint {

	private final static Logger LOGGER = LoggerFactory.getLogger(WMQComponent.class);
	
	private WMQConfig wmqConfig;
	private TransactionTemplate transactionTemplate;

	@UriParam
    private String destinationName;
	
	@UriParam
	private String instance;

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public WMQEndpoint() {
    	LOGGER.debug("Constructor called");
    }

    public WMQEndpoint(String uri, Component component, String destinationName) {
        super(uri, component);
        this.destinationName = destinationName;
        LOGGER.debug("Constructor called #2");
    }

    public Producer createProducer() throws Exception {
    	LOGGER.debug("Creating producer");
    	WMQProducer producer = new WMQProducer(this);
    	//producer.setQueueManager(getWmqConfig().createMQQueueManager(getInstance()));
    	producer.setQueueManagerInstanceName(getInstance());
    	producer.setWmqUtilities(new WMQUtilities());
    	producer.setTransactionTemplate(getTransactionTemplate());
        return producer;
    }

    public WMQConsumer createConsumer(Processor processor) throws Exception {
    	LOGGER.debug("Creating consumer");
        WMQConsumer consumer = new WMQConsumer(this, processor);
     //   consumer.setQueueManager(getWmqConfig().createMQQueueManager());
        consumer.setWmqUtilities(new WMQUtilities());
        consumer.setTransactionTemplate(getTransactionTemplate());
        consumer.setDelay(5);
        return consumer;
    }
    
    public WMQConfig getWmqConfig() {
		return wmqConfig;
	}
    
    public void setWmqConfig(WMQConfig wmqConfig) {
		this.wmqConfig = wmqConfig;
	}
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
    
    @ManagedAttribute
    public boolean isSingleton() {
        return true;
    }

}
