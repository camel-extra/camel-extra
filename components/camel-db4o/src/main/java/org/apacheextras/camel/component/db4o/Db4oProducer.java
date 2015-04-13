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

    @Override
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
