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

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.apache.camel.util.ObjectHelper;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * A Hibernate Component
 *
 * @version $Revision: 630591 $
 */
public class HibernateComponent extends DefaultComponent {
    private HibernateTemplate template;
    private SessionFactory sessionFactory;

    // Properties
    //-------------------------------------------------------------------------
    public HibernateTemplate getTemplate() {
        if (template == null) {
            SessionFactory factory = getSessionFactory();
            ObjectHelper.notNull(factory, "sessionFactory");
            template = new HibernateTemplate(factory);
        }
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Implementation methods
    //-------------------------------------------------------------------------

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
