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

    @Override
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
