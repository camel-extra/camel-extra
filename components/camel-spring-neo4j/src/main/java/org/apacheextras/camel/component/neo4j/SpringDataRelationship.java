/**************************************************************************************
 https://camel-extra.github.io

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.


 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/gpl-2.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.neo4j;

public class SpringDataRelationship<R> {

    private final Object start;
    private final Object end;
    private final Class<R> relationshipEntityClass;
    private final String relationshipType;
    private final boolean allowDuplicates;

    public SpringDataRelationship(Object start, Object end, Class<R> relationshipEntityClass,
                                  String relationshipType, boolean allowDuplicates) {
        this.start = start;
        this.end = end;
        this.relationshipEntityClass = relationshipEntityClass;
        this.relationshipType = relationshipType;
        this.allowDuplicates = allowDuplicates;
    }

    public Object getEnd() {
        return end;
    }

    public Class<R> getRelationshipEntityClass() {
        return relationshipEntityClass;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public Object getStart() {
        return start;
    }

    public boolean isAllowDuplicates() {
        return allowDuplicates;
    }
}
