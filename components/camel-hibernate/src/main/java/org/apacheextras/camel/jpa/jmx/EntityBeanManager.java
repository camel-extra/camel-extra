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
package org.apacheextras.camel.jpa.jmx;

import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.OpenDataException;

import org.hibernate.SessionFactory;

public class EntityBeanManager {
    private SessionFactory sessionFactory;
    private Class entityType;
    private int maximumRows = 1000;

    public int getSize() {
        List answer = getEntities();
        return answer.size();
    }

    public List getEntities() {
        // TODO: Not sure what this was trying to do...
        // return sessionFactory.getCurrentSession().find("select x from " +
        // entityType.getName() + " ");
        return sessionFactory.getCurrentSession().createQuery("from " + entityType.getName()).list();
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

    public void setEntityType(Class entityType) {
        this.entityType = entityType;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
