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
import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

public class WMQComponent extends UriEndpointComponent {

    private final static Logger LOGGER = LoggerFactory.getLogger(WMQComponent.class);

    private WMQConfig config;
    private WMQTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public WMQComponent() {
        super(WMQEndpoint.class);
        LOGGER.debug("CALLED!");
    }

   public WMQComponent(CamelContext camelContext) {
        super(camelContext, WMQEndpoint.class);
        LOGGER.debug("CALLED!!!!!");
    }    
    
    public WMQConfig getConfig() {
		return config;
	}

	public void setConfig(WMQConfig config) {
		this.config = config;
	}
	
	public WMQTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(WMQTransactionManager transactionMananger) {
		this.transactionManager = transactionMananger;
	}
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
    
    @Override
    public Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
    	LOGGER.debug("Creating endpoint on WMQ component");
    	WMQEndpoint endpoint = new WMQEndpoint(uri, this, remaining);
    	
    	// need to refactor this, ugly to check it works before designing how we get the queue mananger to transaction mananger
    	//getTransactionManager().setQueueManager(getConfig().createMQQueueManager());
    	
    	
    	
        endpoint.setWmqConfig(getConfig());
       // endpoint.setTransactionTemplate(new TransactionTemplate(getTransactionManager()));
        endpoint.setTransactionTemplate(getTransactionTemplate());
    	return endpoint;
    }

}
