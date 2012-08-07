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

import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.NoTypeConversionAvailableException;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.builder.ExpressionBuilder;
import org.apache.camel.impl.ScheduledPollEndpoint;
import org.apache.camel.util.ObjectHelper;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * A Hibernate endpoint
 * 
 */
public class HibernateEndpoint extends ScheduledPollEndpoint {
    private HibernateTemplate template;
    private Expression producerExpression;
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

    public Producer createProducer() throws Exception {
        validate();
        return new HibernateProducer(this, getProducerExpression());
    }

    public Consumer createConsumer(Processor processor) throws Exception {
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

    public Expression getProducerExpression() {
        if (producerExpression == null) {
            producerExpression = createProducerExpression();
        }
        return producerExpression;
    }

    public void setProducerExpression(Expression producerExpression) {
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

    protected Expression createProducerExpression() {
        final Class<?> type = getEntityType();
        if (type == null) {
            return ExpressionBuilder.bodyExpression();
        } else {
            return new Expression() {
                public Object evaluate(Exchange exchange, Class asType) {
                    Object answer = exchange.getIn().getBody(type);
                    if (answer == null) {
                        Object defaultValue = exchange.getIn().getBody();
                        if (defaultValue != null) {
                            throw ObjectHelper.wrapRuntimeCamelException(new NoTypeConversionAvailableException(defaultValue, type));
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
