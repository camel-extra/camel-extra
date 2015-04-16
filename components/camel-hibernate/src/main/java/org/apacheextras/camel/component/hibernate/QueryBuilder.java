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

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A builder of query expressions
 */
public abstract class QueryBuilder implements QueryFactory {
    ParameterBuilder parameterBuilder;

    /**
     * Creates a query builder using the JPA query syntax
     * 
     * @param query JPA query language to create
     * @return a query builder
     */
    public static QueryBuilder query(final String query) {
        return new QueryBuilder() {
            protected Query makeQueryObject(Session session) {
                return session.createQuery(query);
            }

            @Override
            public String toString() {
                return "Query: " + query + " params: " + getParameterDescription();
            }
        };
    }

    /**
     * Creates a native SQL query
     */
    public static QueryBuilder nativeQuery(final String nativeQuery) {
        return new QueryBuilder() {
            protected Query makeQueryObject(Session session) {
                return session.createSQLQuery(nativeQuery);
            }

            @Override
            public String toString() {
                return "SQL: " + nativeQuery + getParameterDescription();
            }
        };
    }

    /**
     * Specifies the parameters to the query
     * 
     * @param parameters the parameters to be configured on the query
     * @return this query builder
     */
    public QueryBuilder parameters(Object... parameters) {
        return parameters(Arrays.asList(parameters));
    }

    /**
     * Specifies the parameters to the query as an ordered collection of
     * parameters
     * 
     * @param parameters the parameters to be configured on the query
     * @return this query builder
     */
    public QueryBuilder parameters(final Collection parameters) {
        checkNoParametersConfigured();
        parameterBuilder = new ParameterBuilder() {
            public void populateQuery(Session entityManager, Query query) {
                int counter = 0;
                for (Object parameter : parameters) {
                    query.setParameter(counter++, parameter);
                }
            }

            @Override
            public String toString() {
                return "Parameters: " + parameters;
            }
        };
        return this;
    }

    /**
     * Specifies the parameters to the query as a Map of key/value pairs
     * 
     * @param parameterMap the parameters to be configured on the query
     * @return this query builder
     */
    public QueryBuilder parameters(final Map<String, Object> parameterMap) {
        checkNoParametersConfigured();
        parameterBuilder = new ParameterBuilder() {
            public void populateQuery(Session entityManager, Query query) {
                Set<Map.Entry<String, Object>> entries = parameterMap.entrySet();
                for (Map.Entry<String, Object> entry : entries) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }

            @Override
            public String toString() {
                return "Parameters: " + parameterMap;
            }
        };
        return this;
    }

    protected void checkNoParametersConfigured() {
        if (parameterBuilder != null) {
            throw new IllegalArgumentException("Cannot add parameters to a QueryBuilder which already has parameters configured");
        }
    }

    @Override
    public Query createQuery(Session entityManager) {
        Query query = makeQueryObject(entityManager);
        populateQuery(entityManager, query);
        return query;
    }

    protected String getParameterDescription() {
        if (parameterBuilder == null) {
            return "";
        } else {
            return " " + parameterBuilder.toString();
        }
    }

    protected void populateQuery(Session entityManager, Query query) {
        if (parameterBuilder != null) {
            parameterBuilder.populateQuery(entityManager, query);
        }
    }

    protected abstract Query makeQueryObject(Session entityManager);

    /**
     * A plugin strategy to populate the query with parameters
     */
    protected abstract static class ParameterBuilder {
        public abstract void populateQuery(Session entityManager, Query query);
    }
}
