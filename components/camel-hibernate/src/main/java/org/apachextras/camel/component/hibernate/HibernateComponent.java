/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.hibernate;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.apache.camel.util.ObjectHelper;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * A Hibernate Component
 *
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
