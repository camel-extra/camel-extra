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
import org.apache.camel.Endpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CouchbaseComponentTest {

    @Mock
    private CamelContext context;

    @Test
    public void testEndpointCreated() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();

        String uri = "couchbase:http://localhost:9191/bucket";
        String remaining = "http://localhost:9191/bucket";

        Endpoint endpoint = new CouchbaseComponent(context).createEndpoint(uri, remaining, params);
        assertNotNull(endpoint);
    }

    @Test
    public void testPropertiesSet() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", "ugol");
        params.put("password", "pwd");
        params.put("additionalHosts", "127.0.0.1,example.com,another-host");
        params.put("persistTo", 2);
        params.put("replicateTo", 3);

        String uri = "couchdb:http://localhost:91234/bucket";
        String remaining = "http://localhost:91234/bucket";

        CouchbaseEndpoint endpoint = new CouchbaseComponent(context).createEndpoint(uri, remaining, params);

        assertEquals("http", endpoint.getProtocol());
        assertEquals("localhost", endpoint.getHostname());
        assertEquals("bucket", endpoint.getBucket());
        assertEquals(91234, endpoint.getPort());
        assertEquals("ugol", endpoint.getUsername());
        assertEquals("pwd", endpoint.getPassword());
        assertEquals("127.0.0.1,example.com,another-host", endpoint.getAdditionalHosts());
        assertEquals(2, endpoint.getPersistTo());
        assertEquals(3, endpoint.getReplicateTo());
    }

    @Test
    public void testCouchbaseURI() throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        String uri = "couchbase:http://localhost/bucket?param=true";
        String remaining = "http://localhost/bucket?param=true";

        CouchbaseEndpoint endpoint = new CouchbaseComponent(context).createEndpoint(uri, remaining, params);
        assertEquals(new URI("http://localhost:8091/pools"), endpoint.makeBootstrapURI()[0]);

    }

    @Test
    public void testCouchbaseAdditionalHosts() throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("additionalHosts", "127.0.0.1,example.com,another-host");
        String uri = "couchbase:http://localhost/bucket?param=true";
        String remaining = "http://localhost/bucket?param=true";

        CouchbaseEndpoint endpoint = new CouchbaseComponent(context).createEndpoint(uri, remaining, params);

        //System.out.print(endpoint.makeBootstrapURI()[0].toString() + " " +  endpoint.makeBootstrapURI().length + " ");
        URI[] endpointArray = endpoint.makeBootstrapURI();
        assertEquals(new URI("http://localhost:8091/pools"), endpointArray[0]);
        assertEquals(new URI("http://127.0.0.1:8091/pools"), endpointArray[1]);
        assertEquals(new URI("http://example.com:8091/pools"), endpointArray[2]);
        assertEquals(new URI("http://another-host:8091/pools"), endpointArray[3]);
        assertEquals(4, endpointArray.length);

    }

    @Test
    public void testCouchbaseAdditionalHostsWithSpaces() throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("additionalHosts", " 127.0.0.1, example.com, another-host ");
        String uri = "couchbase:http://localhost/bucket?param=true";
        String remaining = "http://localhost/bucket?param=true";

        CouchbaseEndpoint endpoint = new CouchbaseComponent(context).createEndpoint(uri, remaining, params);

        //System.out.print(endpoint.makeBootstrapURI()[0].toString() + " " +  endpoint.makeBootstrapURI().length + " ");
        URI[] endpointArray = endpoint.makeBootstrapURI();
        assertEquals(new URI("http://localhost:8091/pools"), endpointArray[0]);
        assertEquals(new URI("http://127.0.0.1:8091/pools"), endpointArray[1]);
        assertEquals(new URI("http://example.com:8091/pools"), endpointArray[2]);
        assertEquals(new URI("http://another-host:8091/pools"), endpointArray[3]);
        assertEquals(4, endpointArray.length);

    }

    @Test
    public void testCouchbaseDuplicateAdditionalHosts() throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("additionalHosts", "127.0.0.1,localhost, localhost");
        String uri = "couchbase:http://localhost/bucket?param=true";
        String remaining = "http://localhost/bucket?param=true";

        CouchbaseEndpoint endpoint = new CouchbaseComponent(context).createEndpoint(uri, remaining, params);
        URI[] endpointArray = endpoint.makeBootstrapURI();
        assertEquals(2, endpointArray.length);
        assertEquals(new URI("http://localhost:8091/pools"), endpointArray[0]);
        assertEquals(new URI("http://127.0.0.1:8091/pools"), endpointArray[1]);

    }

    @Test
    public void testCouchbaseNullAdditionalHosts() throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("additionalHosts", null);
        String uri = "couchbase:http://localhost/bucket?param=true";
        String remaining = "http://localhost/bucket?param=true";

        CouchbaseEndpoint endpoint = new CouchbaseComponent(context).createEndpoint(uri, remaining, params);

        //System.out.print(endpoint.makeBootstrapURI()[0].toString() + " " +  endpoint.makeBootstrapURI().length + " ");
        URI[] endpointArray = endpoint.makeBootstrapURI();

        assertEquals(1, endpointArray.length);

    }

    @Test
    public void testCouchbasePersistToAndReplicateToParameters() throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("persistTo", "1");
        params.put("replicateTo", "2");
        String uri = "couchbase:http://localhost/bucket?param=true";
        String remaining = "http://localhost/bucket?param=true";

        CouchbaseEndpoint endpoint = new CouchbaseComponent(context).createEndpoint(uri, remaining, params);

        assertEquals(1, endpoint.getPersistTo());
        assertEquals(2, endpoint.getReplicateTo());
    }
}
