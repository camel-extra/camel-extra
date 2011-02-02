/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.db4o;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;

import java.util.Collection;

public class Db4oConsumer extends ScheduledPollConsumer {

    private final Db4oEndpoint endpoint;

    public Db4oConsumer(Db4oEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected int poll() throws Exception {
        try {
            Collection polledInstances = endpoint.getObjectContainer().query(endpoint.getStoredClass());
            for (Object polledInstance : polledInstances) {
                getProcessor().process(createExchange(polledInstance));
                if (endpoint.isConsumeDelete()) {
                    endpoint.getObjectContainer().delete(polledInstance);
                    endpoint.getObjectContainer().commit();
                }
            }
            return polledInstances.size();
        } catch (Exception e) {
            endpoint.getObjectContainer().rollback();
            throw e;
        }
    }

    protected Exchange createExchange(Object polledInstance) {
        Exchange exchange = endpoint.createExchange();
        exchange.getIn().setBody(polledInstance);
        return exchange;
    }

}
