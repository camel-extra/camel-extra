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

import com.couchbase.client.CouchbaseClient;

import net.spy.memcached.internal.OperationFuture;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;

import java.util.Map;

import static org.apacheextras.camel.component.couchbase.CouchbaseConstants.*;

/**
 * Couchbase producer generates various type of operations. PUT, GET, and DELETE are currently supported
 */

public class CouchbaseProducer extends DefaultProducer {

  private CouchbaseEndpoint endpoint;
  private CouchbaseClient client;
  private long startId;

  public CouchbaseProducer(CouchbaseEndpoint endpoint, CouchbaseClient client) {
    super(endpoint);
    this.endpoint = endpoint;
    this.client = client;
    if (endpoint.isAutoStartIdForInserts()) {
      this.startId = endpoint.getStartingIdForInsertsFrom();
    }

  }

  public void process(Exchange exchange) throws Exception {

    Map<String, Object> headers = exchange.getIn().getHeaders();

    String id = (headers.containsKey(HEADER_ID))
        ? exchange.getIn().getHeader(HEADER_ID, String.class)
        : endpoint.getId();

    if (endpoint.isAutoStartIdForInserts()) {
      id = Long.toString(startId);
      startId++;
    } else if (id == null) {
      throw new CouchbaseException(HEADER_ID + " is not specified in message header or endpoint URL.",
          exchange);
    }

    if (endpoint.getOperation().equals(COUCHBASE_PUT)) {
      log.info("Type of operation: PUT");
      Object obj = exchange.getIn().getBody();
      OperationFuture<Boolean> result = client.set(id, obj);
      exchange.getOut().setBody(result.get());
    } else if (endpoint.getOperation().equals(COUCHBASE_GET)) {
      log.info("Type of operation: GET");
      Object result = client.get(id);
      exchange.getOut().setBody(result);
    } else if (endpoint.getOperation().equals(COUCHBASE_DELETE)) {
      log.info("Type of operation: DELETE");
      OperationFuture<Boolean> result = client.delete(id);
      exchange.getOut().setBody(result.get());
    }

    //cleanup the cache headers
    exchange.getIn().removeHeader(HEADER_ID);

  }

}
