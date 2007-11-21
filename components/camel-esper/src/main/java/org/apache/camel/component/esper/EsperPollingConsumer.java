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

import java.util.Iterator;

import net.esper.client.EPStatement;
import net.esper.event.EventBean;
import org.apache.camel.Exchange;
import org.apache.camel.impl.PollingConsumerSupport;

/**
 * @version $Revision: 1.1 $
 */
public class EsperPollingConsumer extends PollingConsumerSupport<Exchange> {
    private EsperEndpoint endpoint;
    private EPStatement statement;

    public EsperPollingConsumer(EsperEndpoint endpoint, EPStatement statement) {
        super(endpoint);
        this.endpoint = endpoint;
        this.statement = statement;
    }

    protected void doStart() throws Exception {
        statement.start();
    }

    protected void doStop() throws Exception {
        statement.stop();
        statement.destroy();
    }

    public Exchange receive() {
        Iterator<EventBean> iterator = statement.iterator();
        if (iterator.hasNext()) {
            EventBean eventBean = iterator.next();
            return endpoint.createExchange(eventBean, statement);
        }
        else {
            return null;
        }
    }

    public Exchange receiveNoWait() {
        return receive();
    }

    public Exchange receive(long timeout) {
        return receive();
    }
}
