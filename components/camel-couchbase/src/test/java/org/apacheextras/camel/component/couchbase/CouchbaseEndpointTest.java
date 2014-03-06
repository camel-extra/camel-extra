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

import org.junit.Test;

import static org.apacheextras.camel.component.couchbase.CouchbaseConstants.DEFAULT_COUCHBASE_PORT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CouchbaseEndpointTest {

    @Test
    public void assertSingleton() throws Exception {
        CouchbaseEndpoint endpoint = new CouchbaseEndpoint("couchbase:http://localhost/bucket",
                "http://localhost/bucket", new CouchbaseComponent());
        assertTrue(endpoint.isSingleton());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketRequired() throws Exception {
        new CouchbaseEndpoint("couchbase:http://localhost:80", "http://localhost:80", new CouchbaseComponent());
    }

    @Test
    public void testDefaultPortIsSet() throws Exception {
        CouchbaseEndpoint endpoint = new CouchbaseEndpoint("couchbase:http://localhost/bucket",
                "http://localhost/bucket", new CouchbaseComponent());
        assertEquals(DEFAULT_COUCHBASE_PORT, endpoint.getPort());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHostnameRequired() throws Exception {
        new CouchbaseEndpoint("couchbase:http://:80/bucket", "couchbase://:80/bucket", new CouchbaseComponent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSchemeRequired() throws Exception {
        new CouchbaseEndpoint("couchbase:localhost:80/bucket", "localhost:80/bucket", new CouchbaseComponent());
    }

}
