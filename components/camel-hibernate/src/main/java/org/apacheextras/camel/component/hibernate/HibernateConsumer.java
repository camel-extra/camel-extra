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
package org.apacheextras.camel.component.hibernate;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.apache.camel.util.ObjectHelper;
import org.apache.camel.RuntimeCamelException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;

public class HibernateConsumer extends ScheduledPollConsumer {
    private static final transient Logger LOG = LoggerFactory.getLogger(HibernateConsumer.class);
    private final HibernateEndpoint endpoint;
    private final TransactionStrategy transactionStrategy;
    private QueryFactory queryFactory;
    private DeleteHandler<Object> deleteHandler;
    private String query;
    private String namedQuery;
    private String nativeQuery;

    public HibernateConsumer(HibernateEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.transactionStrategy = endpoint.getTransactionStrategy();
    }

    protected int poll() throws Exception {
        transactionStrategy.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(Session session) {
                Query query = getQueryFactory().createQuery(session);
                configureParameters(query);
                List results = query.list();
                for (Object result : results) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Processing new entity: " + result);
                    }

                    if (endpoint.isDeleteFirstOnConsume()) {
                        getDeleteHandler().deleteObject(session, result);
                        processResult(result);
                    } else if (lockEntity(result, session)) {
                        processResult(result);
                        getDeleteHandler().deleteObject(session, result);
                    }
                }
                session.flush();
                return results.size();
            }
        });
        return 0;
    }

    protected void processResult(Object result) {
        // lets turn the result into an exchange and fire it
        // into the processor
        Exchange exchange = createExchange(result);
        try {
            getProcessor().process(exchange);
            if (exchange.isFailed()) {
                throw new RuntimeCamelException(exchange.getException());
            }
        } catch (Exception e) {
            LOG.error("Failed: " + e, e);
            if (e instanceof RuntimeCamelException) {
                throw (RuntimeCamelException)e;
            } else {
                throw new RuntimeCamelException(e);
            }
        }
    }

    // Properties
    // -------------------------------------------------------------------------
    public HibernateEndpoint getEndpoint() {
        return endpoint;
    }

    public QueryFactory getQueryFactory() {
        if (queryFactory == null) {
            queryFactory = createQueryFactory();
            if (queryFactory == null) {
                throw new IllegalArgumentException("No queryType property configured on this consumer, nor an entityType configured on the endpoint so cannot consume");
            }
        }
        return queryFactory;
    }

    public void setQueryFactory(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public DeleteHandler getDeleteHandler() {
        if (deleteHandler == null) {
            deleteHandler = createDeleteHandler();
        }
        return deleteHandler;
    }

    public void setDeleteHandler(DeleteHandler deleteHandler) {
        this.deleteHandler = deleteHandler;
    }

    public String getNamedQuery() {
        return namedQuery;
    }

    public void setNamedQuery(String namedQuery) {
        this.namedQuery = namedQuery;
    }

    public String getNativeQuery() {
        return nativeQuery;
    }

    public void setNativeQuery(String nativeQuery) {
        this.nativeQuery = nativeQuery;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    // Implementation methods
    // -------------------------------------------------------------------------

    /**
     * A strategy method to lock an object with an exclusive lock so that it can
     * be processed
     *
     * @param entity  the entity to be locked
     * @param session
     * @return true if the entity was locked
     */
    protected boolean lockEntity(Object entity, Session session) {
        if (!getEndpoint().isConsumeDelete() || !getEndpoint().isConsumeLockEntity()) {
            return true;
        }
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Acquiring exclusive lock on entity: " + entity);
            }
            session.buildLockRequest(LockOptions.UPGRADE)
            		.setLockMode(LockMode.PESSIMISTIC_WRITE)
            		.setTimeOut(60000).lock(entity);
            return true;
        } catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Failed to achieve lock on entity: " + entity + ". Reason: " + e, e);
            }
            return false;
        }
    }

    protected QueryFactory createQueryFactory() {
        if (query != null) {
            return QueryBuilder.query(query);
        } else if (namedQuery != null) {
            // TODO
            //return QueryBuilder.namedQuery(namedQuery);
            throw new IllegalArgumentException("named queries are not yet implemented!");
        } else if (nativeQuery != null) {
            return QueryBuilder.nativeQuery(nativeQuery);
        } else {
            Class<?> entityType = endpoint.getEntityType();
            if (entityType == null) {
                return null;
            } else {
                return QueryBuilder.query("select x from " + entityType.getName() + " x");
            }
        }
    }

    protected DeleteHandler<Object> createDeleteHandler() {
        // TODO auto-discover an annotation in the entity bean to indicate the
        // process completed method call?
        Class<?> entityType = getEndpoint().getEntityType();
        if (entityType != null) {
            List<Method> methods = ObjectHelper.findMethodsWithAnnotation(entityType, Consumed.class);
            if (methods.size() > 1) {
                throw new IllegalArgumentException("Only one method can be annotated with the @Consumed annotation but found: " + methods);
            } else if (methods.size() == 1) {
                final Method method = methods.get(0);

                return new DeleteHandler<Object>() {
                    public void deleteObject(Session session, Object entityBean) {
                        ObjectHelper.invokeMethod(method, entityBean);
                    }
                };
            }
        }
        if (getEndpoint().isConsumeDelete()) {
            return new DeleteHandler<Object>() {
                public void deleteObject(Session session, Object entityBean) {
                    session.delete(entityBean);
                }
            };
        } else {
            return new DeleteHandler<Object>() {
                public void deleteObject(Session session, Object entityBean) {
                    // do nothing
                }
            };
        }
    }

    protected void configureParameters(Query query) {
        int maxResults = endpoint.getMaximumResults();
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }
    }

    protected Exchange createExchange(Object result) {
        Exchange exchange = endpoint.createExchange();
        exchange.getIn().setBody(result);
        return exchange;
    }
}
