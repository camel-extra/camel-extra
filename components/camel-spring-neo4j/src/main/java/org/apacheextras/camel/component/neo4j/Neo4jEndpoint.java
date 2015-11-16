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

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

@UriEndpoint(scheme = "spring-neo4j", title = "Neo4j", syntax = "spring-neo4j:http://hostname[:port]/database[?options]")
public class Neo4jEndpoint extends DefaultEndpoint {

    public static final String HEADER_OPERATION = "Neo4jOperation";
    public static final String HEADER_NODE_ID = "Neo4jNodeId";
    public static final String HEADER_RELATIONSHIP_ID = "Neo4jRelationshipId";

    private final GraphDatabaseService graphDatabase;

    public Neo4jEndpoint(String endpointUri, String remaining, Neo4jComponent component) {
        super(endpointUri, component);
        graphDatabase = new SpringRestGraphDatabase(remaining);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public Producer createProducer() throws Exception {
        return new Neo4jProducer(this, (SpringRestGraphDatabase)graphDatabase);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
