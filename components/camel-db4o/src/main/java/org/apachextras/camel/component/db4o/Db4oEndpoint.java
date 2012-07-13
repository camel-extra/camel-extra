/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.db4o;

import com.db4o.ObjectContainer;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.ScheduledPollEndpoint;
import org.apache.camel.util.ObjectHelper;

/**
 * @version $Revision$
 */
public class Db4oEndpoint extends ScheduledPollEndpoint {
    private ObjectContainer objectContainer;
    private Class storedClass;
    private boolean consumeDelete = true;

    public Db4oEndpoint(String uri, Class storedClass, Db4oComponent component) {
        super(uri, component);
        this.setObjectContainer(component.getObjectContainer());
        this.setStoredClass(storedClass);
    }

    public Producer createProducer() throws Exception {
        ObjectHelper.notNull(getObjectContainer(), "object container property");
        return new Db4oProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        ObjectHelper.notNull(getObjectContainer(), "object container property");
        Db4oConsumer consumer = new Db4oConsumer(this, processor);
        configureConsumer(consumer);
        return consumer;
    }

    public boolean isSingleton() {
        return true;
    }

    public ObjectContainer getObjectContainer() {
        return objectContainer;
    }

    public void setObjectContainer(ObjectContainer objectContainer) {
        this.objectContainer = objectContainer;
    }

    public Class getStoredClass() {
        return storedClass;
    }

    public void setStoredClass(Class storedClass) {
        this.storedClass = storedClass;
    }

    public boolean isConsumeDelete() {
        return consumeDelete;
    }

    public void setConsumeDelete(boolean consumeDelete) {
        this.consumeDelete = consumeDelete;
    }

}
