/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.exist;

import org.apache.camel.Exchange;
import org.apache.camel.impl.PollingConsumerSupport;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;

public class ExistPollingConsumer extends PollingConsumerSupport {
    private ExistEndpoint endpoint;
    private ResourceIterator iterator;

    public ExistPollingConsumer(ExistEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public Exchange receive() {
        try {
            if (iterator == null) {
                iterator = endpoint.createResourceIterator();
            }
            if (iterator.hasMoreResources()) {
                Resource resource = iterator.nextResource();
                return endpoint.createExchange(resource);
            } else {
                iterator = null;
                return null;
            }
        }
        catch (XMLDBException e) {
            throw new RuntimeExistException(endpoint, e);
        }
    }

    public Exchange receiveNoWait() {
        return receive();
    }

    public Exchange receive(long l) {
        return receive();
    }

    protected void doStart() throws Exception {
    }

    protected void doStop() throws Exception {
    }
}
