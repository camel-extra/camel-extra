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

import java.io.File;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;

/**
 * @version $Revision$
 */
public class TestObjectContainerFactoryBean implements FactoryBean<ObjectContainer>, DisposableBean {

    private ObjectContainer objectContainer;

    public ObjectContainer getObject() throws Exception {
        File randomDatabase = File.createTempFile(getClass().getName(), "tmp");
        randomDatabase.deleteOnExit();
        objectContainer = Db4oEmbedded.openFile(randomDatabase.getAbsolutePath());
        return objectContainer;
    }

    public Class<?> getObjectType() {
        return ObjectContainer.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void destroy() throws Exception {
        objectContainer.close();
    }

}
