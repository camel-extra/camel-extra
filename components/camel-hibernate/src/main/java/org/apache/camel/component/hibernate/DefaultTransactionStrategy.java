/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.hibernate;

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
 * @version $Revision: 630591 $
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
