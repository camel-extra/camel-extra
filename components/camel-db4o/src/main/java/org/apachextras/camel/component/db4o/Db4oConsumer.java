/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.db4o;

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
