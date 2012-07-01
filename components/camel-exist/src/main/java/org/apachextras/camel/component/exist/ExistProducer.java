/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.exist;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.w3c.dom.Node;
import org.xmldb.api.base.Collection;
import org.xmldb.api.modules.XMLResource;

public class ExistProducer extends DefaultProducer {
    private ExistEndpoint endpoint;

    public ExistProducer(ExistEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        Collection collection = endpoint.getCollection();
        XMLResource document = (XMLResource) collection.createResource(null, "XMLResource");
        Object body = exchange.getIn().getBody();
        if (body instanceof Node) {
            document.setContentAsDOM((Node) body);
        } else {
            document.setContent(body);
        }
        collection.storeResource(document);
    }
}
