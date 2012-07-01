/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * A Strategy to create a query to search for objects in a database
 *
 */
public interface QueryFactory {
    /**
     * Creates a new query to find objects to be processed
     *
     * @param entityManager the entity manager
     * @return the query configured with any parameters etc
     */
    Query createQuery(Session entityManager);
}
