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

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.View;
import com.couchbase.client.protocol.views.ViewResponse;
import com.couchbase.client.protocol.views.ViewRow;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultScheduledPollConsumer;

import static org.apacheextras.camel.component.couchbase.CouchbaseConstants.*;

public class CouchbaseConsumer extends DefaultScheduledPollConsumer {

    private final CouchbaseEndpoint endpoint;
    private final CouchbaseClient client;
    private final View view;
    private final Query query;

    public CouchbaseConsumer(CouchbaseEndpoint endpoint, CouchbaseClient client, Processor processor) {

        super(endpoint, processor);
        this.client = client;
        this.endpoint = endpoint;
        this.view = client.getView(endpoint.getDesignDocumentName(), endpoint.getViewName());
        this.query = new Query();
        init();

    }

    private void init() {

        query.setIncludeDocs(true);

        int limit = endpoint.getLimit();
        if (limit > 0) {
            query.setLimit(limit);
        }

        int skip = endpoint.getSkip();
        if (skip > 0) {
            query.setSkip(skip);
        }

        query.setDescending(endpoint.isDescending());

        String rangeStartKey = endpoint.getRangeStartKey();
        String rangeEndKey = endpoint.getRangeEndKey();
        if ("".equals(rangeStartKey) || "".equals(rangeEndKey)) {
            return;
        }
        query.setRange(rangeStartKey, rangeEndKey);

    }

    @Override
    protected void doStart() throws Exception {
        log.info("Starting Couchbase consumer");
        super.doStart();
    }

    @Override
    protected void doStop() throws Exception {
        log.info("Stopping Couchbase consumer");
        super.doStop();
    }

    @Override
    protected synchronized int poll() throws Exception {
        ViewResponse result = client.query(view, query);
        log.info("Received result set from Couchbase");

        if (log.isTraceEnabled()) {
            log.trace("ViewResponse = {}", result);
        }

        String consumerProcessedStrategy = endpoint.getConsumerProcessedStrategy();

        for (ViewRow row : result) {

            String id = row.getId();
            Object doc = row.getDocument();

            String key = row.getKey();
            String designDocumentName = endpoint.getDesignDocumentName();
            String viewName = endpoint.getViewName();

            Exchange exchange = endpoint.createExchange();
            exchange.getIn().setBody(doc);
            exchange.getIn().setHeader(HEADER_ID, id);
            exchange.getIn().setHeader(HEADER_KEY, key);
            exchange.getIn().setHeader(HEADER_DESIGN_DOCUMENT_NAME, designDocumentName);
            exchange.getIn().setHeader(HEADER_VIEWNAME, viewName);

            if ("delete".equalsIgnoreCase(consumerProcessedStrategy)) {
                if (log.isTraceEnabled()) {
                    log.trace("Deleting doc with ID {}", id);
                }
                client.delete(id);
            } else if ("filter".equalsIgnoreCase(consumerProcessedStrategy)) {
                if (log.isTraceEnabled()) {
                    log.trace("Filtering out ID {}", id);
                }
                // add filter for already processed docs
            } else {
                log.trace("No strategy set for already processed docs, beware of duplicates!");
            }

            logDetails(id, doc, key, designDocumentName, viewName, exchange);

            try {
                this.getProcessor().process(exchange);
            } catch (Exception e) {
                this.getExceptionHandler().handleException("Error processing exchange.", exchange, e);
            }
        }

        return result.size();
    }

    private void logDetails(String id, Object doc, String key, String designDocumentName, String viewName, Exchange exchange) {

        if (log.isTraceEnabled()) {
            log.trace("Created exchange = {}", exchange);
            log.trace("Added Document in body = {}", doc);
            log.trace("Adding to Header");
            log.trace("ID = {}", id);
            log.trace("Key = {}", key);
            log.trace("Design Document Name = {}", designDocumentName);
            log.trace("View Name = {}", viewName);
        }

    }
}
