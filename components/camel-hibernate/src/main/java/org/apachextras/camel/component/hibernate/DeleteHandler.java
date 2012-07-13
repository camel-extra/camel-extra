/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.hibernate;

import org.hibernate.Session;

/**
 * A strategy for deleting entity beans which have been processed; either by a real delete or by an update of some
 * application specific properties so that the entity bean will not be found in future polling queries.
 *
 */
public interface DeleteHandler<T> {

    /**
     * Deletes the entity bean after it has been processed either by actually
     * deleting the object or updating it in a way so that future queries do not return this object again.
     *
     * @param session       the session
     * @param entityBean    the entity bean that has been processed and should be deleted
     */
    void deleteObject(Session session, Object entityBean);
}
