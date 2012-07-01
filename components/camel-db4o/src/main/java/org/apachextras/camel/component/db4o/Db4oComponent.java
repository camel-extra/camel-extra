/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.db4o;

import java.util.Map;

import com.db4o.ObjectContainer;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.apache.camel.util.ObjectHelper;

/**
 * @version $Revision$
 */
public class Db4oComponent extends DefaultComponent {

    private ObjectContainer objectContainer;

    @Override
    protected Endpoint createEndpoint(String uri, String path, Map options) throws Exception {
        Db4oEndpoint endpoint = new Db4oEndpoint(uri, ObjectHelper.loadClass(path), this);
        return endpoint;
    }

    public ObjectContainer getObjectContainer() {
        return objectContainer;
    }

    public void setObjectContainer(ObjectContainer objectContainer) {
        this.objectContainer = objectContainer;
    }

}
