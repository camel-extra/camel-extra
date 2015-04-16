/**
 * ************************************************************************************
 * https://camel-extra.github.io
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 * http://www.gnu.org/licenses/gpl-2.0-standalone.html
 **************************************************************************************
 */
package org.apacheextras.camel.component.neo4j;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultComponent;

public class Neo4jComponent extends DefaultComponent {

    public Neo4jComponent() {
    }

    public Neo4jComponent(CamelContext context) {
        super(context);
    }

    /**
     * {@inheritDoc} Creates a Neo4J Endpoint
     * 
     * @param uri instantiates the neo4j endpoint
     * @param remaining defines the transport protocol, e.g. http
     * @param params optional parameters
     * @return Neo4jEndpoint
     * @throws java.lang.Exception
     */
    @Override
    protected Neo4jEndpoint createEndpoint(String uri, String remaining, Map<String, Object> params) throws Exception {
        Neo4jEndpoint endpoint = new Neo4jEndpoint(uri, remaining, this);
        setProperties(endpoint, params);
        return endpoint;
    }
}
