/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.hibernate;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.converter.ObjectConverter;
import org.apache.camel.impl.DefaultProducer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

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
        this.template = endpoint.createTransactionStrategy();
    }

    public void process(Exchange exchange) {
        final Object values = expression.evaluate(exchange, Object.class);
        if (values != null) {
            template.execute(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
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
