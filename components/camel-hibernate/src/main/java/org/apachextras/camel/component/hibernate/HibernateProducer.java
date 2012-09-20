/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

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
package org.apachextras.camel.component.hibernate;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.converter.ObjectConverter;
import org.apache.camel.impl.DefaultProducer;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Iterator;

public class HibernateProducer extends DefaultProducer {
    private final TransactionStrategy template;
    private final HibernateEndpoint endpoint;
    private final Expression expression;

    public HibernateProducer(HibernateEndpoint endpoint, Expression expression) {
        super(endpoint);
        this.endpoint = endpoint;
        this.expression = expression;
        this.template = endpoint.getTransactionStrategy();
    }

    public void process(Exchange exchange) {
        final Object values = expression.evaluate(exchange, Object.class);
        if (values != null) {
            template.execute(new TransactionCallback<Object>() {
                public Object doInTransaction(Session session) {
                    Iterator iter = ObjectConverter.iterator(values);
                    while (iter.hasNext()) {
                        Object value = iter.next();
                        session.persist(value);
                    }
                    if (endpoint.isFlushOnSend()) {
                        session.flush();
                    }
                    return null;
                }
            });
        }
        exchange.setProperty("CamelHibernateValue", values);
    }
}
