/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.exist;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;

public class ExistComponent extends DefaultComponent {
    private Database database;
    private String username;
    private String password;

    protected Endpoint createEndpoint(String uri, String remaining, Map parameters) throws Exception {
        Collection collection;
        if (database != null) {
            collection = database.getCollection(remaining, username, password);
        } else {
            collection = DatabaseManager.getCollection(uri);
        }
        return new ExistEndpoint(uri, this, collection);
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
