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

import org.apache.camel.impl.ServiceSupport;
import org.apache.camel.util.ObjectHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

/**
 * Delegates the strategy to the {@link HibernateTemplate} and {@link TransactionTemplate} for transaction handling
 *
 */
public class DefaultTransactionStrategy extends ServiceSupport implements TransactionStrategy {
    private final HibernateTemplate hibernateTemplate;
    private final TransactionTemplate transactionTemplate;

    public DefaultTransactionStrategy(HibernateTemplate hibernateTemplate, TransactionTemplate transactionTemplate) {
        this.hibernateTemplate = hibernateTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * Creates a new implementation from the given the {@link SessionFactory}
     */
    public static DefaultTransactionStrategy newInstance(SessionFactory sessionFactory) {
        ObjectHelper.notNull(sessionFactory, "sessionFactory");
        HibernateTemplate template = new HibernateTemplate(sessionFactory);
        return newInstance(sessionFactory, template);
    }


    public static TransactionStrategy newInstance(HibernateTemplate template) {
        ObjectHelper.notNull(template, "template");
        return newInstance(template.getSessionFactory(), template);
    }

    public static DefaultTransactionStrategy newInstance(SessionFactory sessionFactory, HibernateTemplate template) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        transactionManager.afterPropertiesSet();

        TransactionTemplate tranasctionTemplate = new TransactionTemplate(transactionManager);
        tranasctionTemplate.afterPropertiesSet();

        return new DefaultTransactionStrategy(template, tranasctionTemplate);
    }

    public Object execute(final HibernateCallback callback) {
        return transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                return hibernateTemplate.execute(new HibernateCallback() {
                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
                        return callback.doInHibernate(session);
                    }
                });
            }
        });
    }

    protected void doStart() throws Exception {
    }

    protected void doStop() throws Exception {
    }

}
