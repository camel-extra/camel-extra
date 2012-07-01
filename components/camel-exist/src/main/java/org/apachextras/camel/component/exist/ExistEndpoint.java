/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.exist;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.PollingConsumer;
import org.apache.camel.impl.DefaultPollingEndpoint;
import org.apache.camel.util.ObjectHelper;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class ExistEndpoint extends DefaultPollingEndpoint {
    private Collection collection;
    private String xpath;

    public ExistEndpoint(String uri, ExistComponent component, Collection collection) {
        super(uri, component);
        this.collection = collection;
    }

    public boolean isSingleton() {
        return true;
    }

    public ExistProducer createProducer() throws Exception {
        return new ExistProducer(this);
    }

    @Override
    public PollingConsumer createPollingConsumer() throws Exception {
        return new ExistPollingConsumer(this);
    }

    public Collection getCollection() {
        return collection;
    }

    public ResourceIterator createResourceIterator() throws XMLDBException {
        if (xpath != null) {
            XPathQueryService service =
                    (XPathQueryService) getCollection().getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query(xpath);
            return result.getIterator();
        } else {
            ObjectHelper.notNull(xpath, "xpath");
            return null;
        }
    }

    public Exchange createExchange(Resource resource) throws XMLDBException {
        Object body = resource.getContent();
        Exchange exchange = createExchange(ExchangePattern.InOnly);
        Message in = exchange.getIn();
        in.setBody(body);
        in.setHeader("CamelExistResourceId", resource.getId());
        in.setHeader("CamelExistResourceType", resource.getResourceType());
        exchange.setProperty("CamelExistResource", resource);
        return exchange;
    }

    // Properties
    //-------------------------------------------------------------------------

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }
}
