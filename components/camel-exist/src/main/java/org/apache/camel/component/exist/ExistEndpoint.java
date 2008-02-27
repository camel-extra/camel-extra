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
package org.apache.camel.component.exist;

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

/**
 * @version $Revision: 1.1 $
 */
public class ExistEndpoint extends DefaultPollingEndpoint<Exchange> {
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
    public PollingConsumer<Exchange> createPollingConsumer() throws Exception {
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
        }
        else {
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
