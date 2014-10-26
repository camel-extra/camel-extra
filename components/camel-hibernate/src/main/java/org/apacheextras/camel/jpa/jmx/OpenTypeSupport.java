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
package org.apacheextras.camel.jpa.jmx;


import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OpenTypeSupport {


    public static OpenTypeFactory getFactory(Class clazz) throws OpenDataException {
        return (OpenTypeFactory) OPEN_TYPE_FACTORIES.get(clazz);
    }

    public static CompositeData convert(Object message) throws OpenDataException {
        OpenTypeFactory f = getFactory(message.getClass());
        if( f == null ) {
          throw new OpenDataException("Cannot create a CompositeData for type: " + message.getClass().getName());
        }
        CompositeType ct = f.getCompositeType();
        Map fields = f.getFields(message);
        return new CompositeDataSupport(ct, fields);
    }

    interface OpenTypeFactory {
        CompositeType getCompositeType() throws OpenDataException;
        Map getFields( Object o ) throws OpenDataException;
    }

    private static final HashMap OPEN_TYPE_FACTORIES = new HashMap();

    abstract static class AbstractOpenTypeFactory implements OpenTypeFactory {

        private CompositeType compositeType;
        ArrayList itemNamesList = new ArrayList();
        ArrayList itemDescriptionsList = new ArrayList();
        ArrayList itemTypesList = new ArrayList();

        @Override
        public CompositeType getCompositeType() throws OpenDataException {
            if( compositeType == null ) {
                init();
                compositeType = createCompositeType();
            }
            return compositeType;
        }

        protected void init() throws OpenDataException {
        }

        protected CompositeType createCompositeType() throws OpenDataException {
            String[] itemNames = (String[]) itemNamesList.toArray(new String[itemNamesList.size()]);
            String[] itemDescriptions = (String[]) itemDescriptionsList.toArray(new String[itemDescriptionsList.size()]);
            OpenType[] itemTypes = (OpenType[]) itemTypesList.toArray(new OpenType[itemTypesList.size()]);
            return new CompositeType(getTypeName(), getDescription(), itemNames, itemDescriptions, itemTypes);
        }

        abstract protected String getTypeName();

        protected void addItem(String name, String description, OpenType type) {
            itemNamesList.add(name);
            itemDescriptionsList.add(description);
            itemTypesList.add(type);
        }


        protected String getDescription() {
            return getTypeName();
        }

        @Override
        public Map getFields(Object o) throws OpenDataException {
            HashMap rc = new HashMap();
            return rc;
        }
    }
}
