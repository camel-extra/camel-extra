/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.esper;

import net.esper.client.EPStatement;
import net.esper.client.UpdateListener;
import net.esper.event.EventBean;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RuntimeExchangeException;
import org.apache.camel.impl.DefaultConsumer;

/**
 * @version $Revision: 1.1 $
 */
public class EsperConsumer extends DefaultConsumer<Exchange> implements UpdateListener {
    private EsperEndpoint endpoint;
    private EPStatement statement;

    public EsperConsumer(EsperEndpoint endpoint, EPStatement statement, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.statement = statement;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        statement.addListener(this);
        //statement.start();
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
        statement.stop();
        statement.destroy();
    }

    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents != null) {
	        for (EventBean eventBean : newEvents) {
	            Object underlying = eventBean.getUnderlying();
	            Exchange exchange = endpoint.createExchange(eventBean, statement);
	            try {
	                getProcessor().process(exchange);
	            }
	            catch (Exception e) {
	                throw new RuntimeExchangeException(e, exchange);
	            }
	        }
		}
    }
}
