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

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;

@ManagedResource(description = "Managed WMQ Endpoint")
@UriEndpoint(scheme = "wmq", title = "IBM WebSphere MQ", syntax = "wmq:destinationName", consumerClass = WMQConsumer.class)
public class WMQEndpoint extends DefaultEndpoint {

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
    	producer.setQueueManager(getMQQueueManager());    	
        return producer;
    }

    public WMQConsumer createConsumer(Processor processor) throws Exception {
        WMQConsumer consumer = new WMQConsumer(this, processor);
        consumer.setDelay(5);
        return consumer;
    }

    @ManagedAttribute
    public boolean isSingleton() {
        return true;
    }

}
