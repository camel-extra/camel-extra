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

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.converter.ObjectConverter;
import org.apache.camel.impl.DefaultProducer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.Iterator;

/**
 * @version $Revision: 630591 $
 */
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
