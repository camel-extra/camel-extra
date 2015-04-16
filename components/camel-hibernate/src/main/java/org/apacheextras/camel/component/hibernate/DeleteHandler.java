/**************************************************************************************
 https://camel-extra.github.io

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation; either version 3
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.


 You should have received a copy of the GNU Lesser General Public
 License along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.hibernate;

import org.hibernate.Session;

/**
 * A strategy for deleting entity beans which have been processed; either by a
 * real delete or by an update of some application specific properties so that
 * the entity bean will not be found in future polling queries.
 */
public interface DeleteHandler<T> {

    /**
     * Deletes the entity bean after it has been processed either by actually
     * deleting the object or updating it in a way so that future queries do not
     * return this object again.
     *
     * @param session the session
     * @param entityBean the entity bean that has been processed and should be
     *            deleted
     */
    void deleteObject(Session session, Object entityBean);
}
