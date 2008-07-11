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


import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @version $Revision: $
 */
public class OpenTypeSupport {


    public static OpenTypeFactory getFactory(Class clazz) throws OpenDataException {
        return (OpenTypeFactory) openTypeFactories.get(clazz);
    }

    public static CompositeData convert(Object message) throws OpenDataException {
        OpenTypeFactory f = getFactory(message.getClass());
        if( f == null )
            throw new OpenDataException("Cannot create a CompositeData for type: "+message.getClass().getName());
        CompositeType ct = f.getCompositeType();
        Map fields = f.getFields(message);
        return new CompositeDataSupport(ct, fields);
    }
    interface OpenTypeFactory {
        CompositeType getCompositeType() throws OpenDataException;
        Map getFields( Object o ) throws OpenDataException;
    }

    private static final HashMap openTypeFactories = new HashMap();

    abstract static class AbstractOpenTypeFactory implements OpenTypeFactory {

        private CompositeType compositeType;
        ArrayList itemNamesList = new ArrayList();
        ArrayList itemDescriptionsList = new ArrayList();
        ArrayList itemTypesList = new ArrayList();

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

        public Map getFields(Object o) throws OpenDataException {
            HashMap rc = new HashMap();
            return rc;
        }
    }

    /*
    static class MessageOpenTypeFactory extends AbstractOpenTypeFactory {

        protected String getTypeName() {
            return ActiveMQMessage.class.getName();
        }

        protected void init() throws OpenDataException {
            super.init();
            addItem("JMSCorrelationID", "JMSCorrelationID", SimpleType.STRING);
            addItem("JMSDestination", "JMSDestination", SimpleType.STRING);
            addItem("JMSMessageID", "JMSMessageID", SimpleType.STRING);
            addItem("JMSReplyTo", "JMSReplyTo", SimpleType.STRING);
            addItem("JMSType", "JMSType", SimpleType.STRING);
            addItem("JMSDeliveryMode", "JMSDeliveryMode", SimpleType.STRING);
            addItem("JMSExpiration", "JMSExpiration", SimpleType.LONG);
            addItem("JMSPriority", "JMSPriority", SimpleType.INTEGER);
            addItem("JMSRedelivered", "JMSRedelivered", SimpleType.BOOLEAN);
            addItem("JMSTimestamp", "JMSTimestamp", SimpleType.DATE);
            addItem("Properties", "Properties", SimpleType.STRING);
        }

        public Map getFields(Object o) throws OpenDataException {
            ActiveMQMessage m = (ActiveMQMessage) o;
            Map rc = super.getFields(o);
            rc.put("JMSCorrelationID", m.getJMSCorrelationID());
            rc.put("JMSDestination", ""+m.getJMSDestination());
            rc.put("JMSMessageID", m.getJMSMessageID());
            rc.put("JMSReplyTo", ""+m.getJMSReplyTo());
            rc.put("JMSType", m.getJMSType());
            rc.put("JMSDeliveryMode", m.getJMSDeliveryMode()==DeliveryMode.PERSISTENT ? "PERSISTENT" : "NON-PERSISTENT");
            rc.put("JMSExpiration", Long.valueOf(m.getJMSExpiration()));
            rc.put("JMSPriority", Integer.valueOf(m.getJMSPriority()));
            rc.put("JMSRedelivered", Boolean.valueOf(m.getJMSRedelivered()));
            rc.put("JMSTimestamp", new Date(m.getJMSTimestamp()));
            try {
                rc.put("Properties", ""+m.getProperties());
            } catch (IOException e) {
                rc.put("Properties", "");
            }
            return rc;
        }
    }
    */


}
