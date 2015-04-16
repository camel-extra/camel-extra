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

import com.couchbase.client.CouchbaseClientIF;
import net.spy.memcached.PersistTo;
import net.spy.memcached.ReplicateTo;
import net.spy.memcached.internal.OperationFuture;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;

import java.util.Map;
import java.util.concurrent.Future;

import static org.apacheextras.camel.component.couchbase.CouchbaseConstants.*;

/**
 * Couchbase producer generates various type of operations. PUT, GET, and DELETE
 * are currently supported
 */

public class CouchbaseProducer extends DefaultProducer {

    private CouchbaseEndpoint endpoint;
    private CouchbaseClientIF client;
    private long startId;
    private PersistTo persistTo;
    private ReplicateTo replicateTo;
    private int producerRetryAttempts;
    private int producerRetryPause;

    public CouchbaseProducer(CouchbaseEndpoint endpoint, CouchbaseClientIF client, int persistTo, int replicateTo) throws Exception {
        super(endpoint);
        this.endpoint = endpoint;
        this.client = client;
        if (endpoint.isAutoStartIdForInserts()) {
            this.startId = endpoint.getStartingIdForInsertsFrom();
        }
        this.producerRetryAttempts = endpoint.getProducerRetryAttempts();
        this.producerRetryPause = endpoint.getProducerRetryPause();

        switch (persistTo) {
        case 0:
            this.persistTo = PersistTo.ZERO;
            break;
        case 1:
            this.persistTo = PersistTo.MASTER;
            break;
        case 2:
            this.persistTo = PersistTo.TWO;
            break;
        case 3:
            this.persistTo = PersistTo.THREE;
            break;
        case 4:
            this.persistTo = PersistTo.FOUR;
            break;
        default:
            throw new IllegalArgumentException("Unsupported persistTo parameter. Supported values are 0 to 4. Currently provided: " + persistTo);
        }

        switch (replicateTo) {
        case 0:
            this.replicateTo = ReplicateTo.ZERO;
            break;
        case 1:
            this.replicateTo = ReplicateTo.ONE;
            break;
        case 2:
            this.replicateTo = ReplicateTo.TWO;
            break;
        case 3:
            this.replicateTo = ReplicateTo.THREE;
            break;
        default:
            throw new IllegalArgumentException("Unsupported replicateTo parameter. Supported values are 0 to 3. Currently provided: " + replicateTo);
        }

    }

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String, Object> headers = exchange.getIn().getHeaders();

        String id = (headers.containsKey(HEADER_ID)) ? exchange.getIn().getHeader(HEADER_ID, String.class) : endpoint.getId();

        int ttl = (headers.containsKey(HEADER_TTL)) ? Integer.parseInt(exchange.getIn().getHeader(HEADER_TTL, String.class)) : DEFAULT_TTL;

        if (endpoint.isAutoStartIdForInserts()) {
            id = Long.toString(startId);
            startId++;
        } else if (id == null) {
            throw new CouchbaseException(HEADER_ID + " is not specified in message header or endpoint URL.", exchange);
        }

        if (endpoint.getOperation().equals(COUCHBASE_PUT)) {
            log.info("Type of operation: PUT");
            Object obj = exchange.getIn().getBody();
            exchange.getOut().setBody(setDocument(id, ttl, obj, persistTo, replicateTo));
        } else if (endpoint.getOperation().equals(COUCHBASE_GET)) {
            log.info("Type of operation: GET");
            Object result = client.get(id);
            exchange.getOut().setBody(result);
        } else if (endpoint.getOperation().equals(COUCHBASE_DELETE)) {
            log.info("Type of operation: DELETE");
            Future<Boolean> result = client.delete(id);
            exchange.getOut().setBody(result.get());
        }

        // cleanup the cache headers
        exchange.getIn().removeHeader(HEADER_ID);

    }

    private Boolean setDocument(String id, int expiry, Object obj, PersistTo persistTo, ReplicateTo replicateTo) throws Exception {
        return setDocument(id, expiry, obj, producerRetryAttempts, persistTo, replicateTo);
    }

    private Boolean setDocument(String id, int expiry, Object obj, int retryAttempts, PersistTo persistTo, ReplicateTo replicateTo) throws Exception {

        OperationFuture<Boolean> result = client.set(id, expiry, obj, persistTo, replicateTo);
        try {
            if (!result.get()) {
                throw new Exception("Unable to save Document. " + id);
            }
            return true;
        } catch (Exception e) {
            if (retryAttempts <= 0) {
                throw e;
            } else {
                log.info("Unable to save Document, retrying in " + producerRetryPause + "ms (" + retryAttempts + ")");
                Thread.sleep(producerRetryPause);
                return setDocument(id, expiry, obj, (retryAttempts - 1), persistTo, replicateTo);
            }
        }
    }

}
