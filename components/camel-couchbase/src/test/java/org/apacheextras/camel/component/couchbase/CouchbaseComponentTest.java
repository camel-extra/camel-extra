/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

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

package org.apacheextras.camel.component.couchbase;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    String uri = "couchdb:http://localhost:91234/bucket";
    String remaining = "http://localhost:91234/bucket";

    CouchbaseEndpoint endpoint = new CouchbaseComponent(context).createEndpoint(uri, remaining, params);

    assertEquals("http", endpoint.getProtocol());
    assertEquals("localhost", endpoint.getHostname());
    assertEquals("bucket", endpoint.getBucket());
    assertEquals(91234, endpoint.getPort());
    assertEquals("ugol", endpoint.getUsername());
    assertEquals("pwd", endpoint.getPassword());

  }

  @Test
  public void testCouchbaseURI() throws Exception {

    Map<String, Object> params = new HashMap<String, Object>();
    String uri = "couchbase:http://localhost/bucket?param=true";
    String remaining = "http://localhost/bucket?param=true";

    CouchbaseEndpoint endpoint = new CouchbaseComponent(context).createEndpoint(uri, remaining, params);
    assertEquals("http://localhost:8091/pools", endpoint.makeBootstrapURI());

  }
}
