/**************************************************************************************
 https://camel-extra.github.io

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation; either version 3
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.


 You should have received a copy of the GNU Lesser General Public
 License along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.exist;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.PollingConsumer;
import org.apache.camel.impl.DefaultPollingEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.util.ObjectHelper;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

@UriEndpoint(scheme = "xmldb", title = "xmldb exists", syntax = "xmldb:test:///[database]?xpath=[path]", consumerClass = ExistPollingConsumer.class)
public class ExistEndpoint extends DefaultPollingEndpoint {
    private Collection collection;
    private String xpath;

    public ExistEndpoint(String uri, ExistComponent component, Collection collection) {
        super(uri, component);
        this.collection = collection;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
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
            XPathQueryService service = (XPathQueryService)getCollection().getService("XPathQueryService", "1.0");
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
    // -------------------------------------------------------------------------

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }
}
