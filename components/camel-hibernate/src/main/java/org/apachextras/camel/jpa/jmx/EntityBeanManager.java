/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.jpa.jmx;

import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.OpenDataException;
import java.util.ArrayList;
import java.util.List;

public class EntityBeanManager {
    private HibernateTemplate template;
    private Class entityType;
    private int maximumRows = 1000;

    public int getSize() {
        List answer = getEntities();
        return answer.size();
    }

    public List getEntities() {
        return template.find("select x from " + entityType.getName() + " ");
    }

    public CompositeData[] browse(String selector) throws OpenDataException {
        List entities = getEntities();
        List<CompositeData> compositeRows = new ArrayList<CompositeData>();
        int row = 0;
        for (Object entity : entities) {
            if (++row > maximumRows) {
                break;
            }
            compositeRows.add(OpenTypeSupport.convert(entity));
        }

        CompositeData rc[] = new CompositeData[compositeRows.size()];
        compositeRows.toArray(rc);
        return rc;
    }


    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public void setEntityType(Class entityType) {
        this.entityType = entityType;
    }

}
