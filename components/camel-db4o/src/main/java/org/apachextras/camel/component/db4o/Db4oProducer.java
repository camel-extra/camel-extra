/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.db4o;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.apache.camel.util.ObjectHelper;

/**
 * @version $Revision$
 */
public class Db4oProducer extends DefaultProducer {

    private final Db4oEndpoint endpoint;

    public Db4oProducer(Db4oEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) {
        Object msgObject = exchange.getIn().getBody();
        ObjectHelper.isAssignableFrom(msgObject.getClass(), this.endpoint.getStoredClass());
        try {
            endpoint.getObjectContainer().store(msgObject);
            endpoint.getObjectContainer().commit();
        } catch (Exception e) {
            endpoint.getObjectContainer().rollback();
            throw new RuntimeException(e);
        }
    }

}
