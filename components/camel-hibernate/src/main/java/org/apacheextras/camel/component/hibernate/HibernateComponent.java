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
package org.apacheextras.camel.component.hibernate;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.apache.camel.util.ObjectHelper;
import org.hibernate.SessionFactory;

/**
 * A Hibernate Component
 */
public class HibernateComponent extends DefaultComponent {
    private SessionFactory sessionFactory;
    private TransactionStrategy transactionStrategy;

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        if (transactionStrategy == null) {
            transactionStrategy = resolveTransactionStrategy();
        }
    }

    // Properties
    // -------------------------------------------------------------------------

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public TransactionStrategy getTransactionStrategy() {
        return transactionStrategy;
    }

    public void setTransactionStrategy(TransactionStrategy transactionStrategy) {
        this.transactionStrategy = transactionStrategy;
    }

    // Property resolvers

    private TransactionStrategy resolveTransactionStrategy() {
        return new DefaultTransactionStrategy(sessionFactory);
    }

    // Implementation methods
    // -------------------------------------------------------------------------

    @Override
    protected Endpoint createEndpoint(String uri, String path, Map options) throws Exception {
        HibernateEndpoint endpoint = new HibernateEndpoint(uri, this);

        // lets interpret the next string as a class
        if (path != null) {
            Class<?> type = ObjectHelper.loadClass(path);
            if (type != null) {
                endpoint.setEntityType(type);
            }
        }
        return endpoint;
    }

}
