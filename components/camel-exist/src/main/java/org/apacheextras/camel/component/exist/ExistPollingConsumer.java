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

    @Override
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
        } catch (XMLDBException e) {
            throw new RuntimeExistException(endpoint, e);
        }
    }

    @Override
    public Exchange receiveNoWait() {
        return receive();
    }

    @Override
    public Exchange receive(long l) {
        return receive();
    }

    @Override
    protected void doStart() throws Exception {
    }

    @Override
    protected void doStop() throws Exception {
    }
}
