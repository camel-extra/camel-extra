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

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.junit.Test;

import static org.apacheextras.camel.component.couchbase.CouchbaseConstants.DEFAULT_COUCHBASE_PORT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CouchbaseEndpointTest {

    @Test
    public void assertSingleton() throws Exception {
        CouchbaseEndpoint endpoint = new CouchbaseEndpoint("couchbase:http://localhost/bucket", "http://localhost/bucket", new CouchbaseComponent());
        assertTrue(endpoint.isSingleton());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketRequired() throws Exception {
        new CouchbaseEndpoint("couchbase:http://localhost:80", "http://localhost:80", new CouchbaseComponent());
    }

    @Test
    public void testDefaultPortIsSet() throws Exception {
        CouchbaseEndpoint endpoint = new CouchbaseEndpoint("couchbase:http://localhost/bucket", "http://localhost/bucket", new CouchbaseComponent());
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

    @Test
    public void testCouchbaseEndpoint() {
        new CouchbaseEndpoint();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCouchbaseEndpointWithoutProtocol() throws Exception {
        new CouchbaseEndpoint("localhost:80/bucket", "localhost:80/bucket", new CouchbaseComponent());
    }

    @Test
    public void testCouchbaseEndpointUri() {
        new CouchbaseEndpoint("couchbase:localhost:80/bucket");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCouchbaseEndpointCreateProducer() throws Exception {
        new CouchbaseEndpoint("couchbase:localhost:80/bucket").createProducer();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCouchbaseEndpointCreateConsumer() throws Exception {
        new CouchbaseEndpoint("couchbase:localhost:80/bucket").createConsumer(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                // Nothing to do
            }
        });
    }

    @Test
    public void testCouchbaseEndpontSettersAndGetters() {
        CouchbaseEndpoint endpoint = new CouchbaseEndpoint();

        endpoint.setProtocol("couchbase");
        assertTrue(endpoint.getProtocol().equals("couchbase"));

        endpoint.setBucket("bucket");
        assertTrue(endpoint.getBucket().equals("bucket"));

        endpoint.setHostname("localhost");
        assertTrue(endpoint.getHostname().equals("localhost"));

        endpoint.setPort(80);
        assertTrue(endpoint.getPort() == 80);

        endpoint.setOperation("PUT");
        assertTrue(endpoint.getOperation().equals("PUT"));

        endpoint.setStartingIdForInsertsFrom(1L);
        assertTrue(endpoint.getStartingIdForInsertsFrom() == (1L));

        endpoint.setProducerRetryAttempts(5);
        assertTrue(endpoint.getProducerRetryAttempts() == 5);

        endpoint.setProducerRetryPause(1);
        assertTrue(endpoint.getProducerRetryPause() == 1);

        endpoint.setDesignDocumentName("beer");
        assertTrue(endpoint.getDesignDocumentName().equals("beer"));

        endpoint.setViewName("brewery_beers");
        assertTrue(endpoint.getViewName().equals("brewery_beers"));

        endpoint.setLimit(1);
        assertTrue(endpoint.getLimit() == 1);

        endpoint.setSkip(1);
        assertTrue(endpoint.getSkip() == 1);

        endpoint.setRangeStartKey("");
        assertTrue(endpoint.getRangeStartKey().equals(""));

        endpoint.setRangeEndKey("");
        assertTrue(endpoint.getRangeEndKey().equals(""));

        endpoint.setConsumerProcessedStrategy("delete");
        assertTrue(endpoint.getConsumerProcessedStrategy().equals("delete"));

        endpoint.setOpTimeOut(1L);
        assertTrue(endpoint.getOpTimeOut() == 1L);

        endpoint.setTimeoutExceptionThreshold(1);
        assertTrue(endpoint.getTimeoutExceptionThreshold() == 1);

        endpoint.setReadBufferSize(1);
        assertTrue(endpoint.getReadBufferSize() == 1);

        endpoint.setShouldOptimize(true);
        assertTrue(endpoint.isShouldOptimize());

        endpoint.setMaxReconnectDelay(1L);
        assertTrue(endpoint.getMaxReconnectDelay() == 1L);

        endpoint.setOpQueueMaxBlockTime(1L);
        assertTrue(endpoint.getOpQueueMaxBlockTime() == 1L);

        endpoint.setObsPollInterval(1L);
        assertTrue(endpoint.getObsPollInterval() == 1L);

        endpoint.setObsTimeout(1L);
        assertTrue(endpoint.getObsTimeout() == 1L);

        endpoint.setDescending(false);
    }
}
