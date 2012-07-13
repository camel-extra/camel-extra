/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.db4o;

import java.io.File;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;

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
