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
package org.apacheextras.camel.component.couchbase;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultComponent;

import java.util.Map;

/**
 * Couchbase component.
 */

public class CouchbaseComponent extends DefaultComponent {

    public CouchbaseComponent() {

    }

    public CouchbaseComponent(CamelContext context) {
        super(context);
    }

    @Override
    protected CouchbaseEndpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        CouchbaseEndpoint endpoint = new CouchbaseEndpoint(uri, remaining, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
