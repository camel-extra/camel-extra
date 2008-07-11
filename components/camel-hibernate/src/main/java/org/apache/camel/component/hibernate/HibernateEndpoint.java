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

import org.apache.camel.*;
import org.apache.camel.builder.ExpressionBuilder;
import org.apache.camel.impl.ScheduledPollEndpoint;
import org.apache.camel.util.ObjectHelper;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.hibernate.SessionFactory;
import org.hibernate.impl.SessionFactoryImpl;

/**
 * A Hibernate endpoint
 * 
 * @version $Revision: 655516 $
 */
public class HibernateEndpoint extends ScheduledPollEndpoint<Exchange> {
    private HibernateTemplate template;
    private Expression<Exchange> producerExpression;
    private int maximumResults = -1;
    private Class<?> entityType;
    private boolean consumeDelete = true;
    private boolean consumeLockEntity = true;
    private boolean flushOnSend = true;
    private boolean deleteFirstOnConsume = true;

    public HibernateEndpoint(String uri, HibernateComponent component) {
        super(uri, component);
        template = component.getTemplate();
    }

    public HibernateEndpoint(String uri, HibernateTemplate template) {
        super(uri);
        this.template = template;
    }

    public HibernateEndpoint(String endpointUri) {
        super(endpointUri);
    }

    public Producer<Exchange> createProducer() throws Exception {
        validate();
        return new HibernateProducer(this, getProducerExpression());
    }

    public Consumer<Exchange> createConsumer(Processor processor) throws Exception {
        validate();
        HibernateConsumer consumer = new HibernateConsumer(this, processor);
        configureConsumer(consumer);
        return consumer;
    }

    public boolean isSingleton() {
        return true;
    }

    // Properties
    // -------------------------------------------------------------------------
    public HibernateTemplate getTemplate() {
        if (template == null) {
            Component component = getComponent();
            if (component instanceof HibernateComponent) {
                HibernateComponent hibernateComponent = (HibernateComponent) component;
                template = hibernateComponent.getTemplate();

            }
        }
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public Expression<Exchange> getProducerExpression() {
        if (producerExpression == null) {
            producerExpression = createProducerExpression();
        }
        return producerExpression;
    }

    public void setProducerExpression(Expression<Exchange> producerExpression) {
        this.producerExpression = producerExpression;
    }

    public int getMaximumResults() {
        return maximumResults;
    }

    public void setMaximumResults(int maximumResults) {
        this.maximumResults = maximumResults;
    }

    public Class<?> getEntityType() {
        return entityType;
    }

    public void setEntityType(Class<?> entityType) {
        this.entityType = entityType;
    }

    public boolean isConsumeDelete() {
        return consumeDelete;
    }

    public void setConsumeDelete(boolean consumeDelete) {
        this.consumeDelete = consumeDelete;
    }

    public boolean isConsumeLockEntity() {
        return consumeLockEntity;
    }

    public void setConsumeLockEntity(boolean consumeLockEntity) {
        this.consumeLockEntity = consumeLockEntity;
    }

    public boolean isFlushOnSend() {
        return flushOnSend;
    }

    public void setFlushOnSend(boolean flushOnSend) {
        this.flushOnSend = flushOnSend;
    }

    public boolean isDeleteFirstOnConsume() {
        return deleteFirstOnConsume;
    }

    public void setDeleteFirstOnConsume(boolean deleteFirstOnConsume) {
        this.deleteFirstOnConsume = deleteFirstOnConsume;
    }

    // Implementation methods
    // -------------------------------------------------------------------------
    protected void validate() {
        ObjectHelper.notNull(getTemplate(), "template property");
    }

    protected TransactionStrategy createTransactionStrategy() {
        return DefaultTransactionStrategy.newInstance(getTemplate());
    }

    protected Expression<Exchange> createProducerExpression() {
        final Class<?> type = getEntityType();
        if (type == null) {
            return ExpressionBuilder.bodyExpression();
        } else {
            return new Expression<Exchange>() {
                public Object evaluate(Exchange exchange) {
                    Object answer = exchange.getIn().getBody(type);
                    if (answer == null) {
                        Object defaultValue = exchange.getIn().getBody();
                        if (defaultValue != null) {
                            throw new NoTypeConversionAvailableException(defaultValue, type);
                        }

                        // if we don't have a body then
                        // lets instantiate and inject a new instance
                        answer = exchange.getContext().getInjector().newInstance(type);
                    }
                    return answer;
                }
            };
        }
    }
}
