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
package org.apache.camel.component.db4o;

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
