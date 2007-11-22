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
package org.apache.camel.components.exist;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.w3c.dom.Node;
import org.xmldb.api.base.Collection;
import org.xmldb.api.modules.XMLResource;

/**
 * @version $Revision: 1.1 $
 */
public class ExistProducer extends DefaultProducer<Exchange> {
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
        }
        else {
            document.setContent(body);
        }
        collection.storeResource(document);
    }
}
