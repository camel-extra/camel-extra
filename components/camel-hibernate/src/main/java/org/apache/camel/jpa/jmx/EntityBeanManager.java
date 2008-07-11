/*
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
package org.apache.camel.jpa.jmx;

import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.OpenDataException;
import java.util.ArrayList;
import java.util.List;


/**
 * @version $Revision: $
 */
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


}
