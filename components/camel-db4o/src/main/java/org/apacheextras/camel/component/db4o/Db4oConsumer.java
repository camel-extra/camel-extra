/**************************************************************************************
 https://camel-extra.github.io

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.


 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/gpl-2.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.db4o;

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
