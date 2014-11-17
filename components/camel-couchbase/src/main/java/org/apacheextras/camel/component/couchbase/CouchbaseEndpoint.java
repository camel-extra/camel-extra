/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

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
import com.couchbase.client.CouchbaseConnectionFactoryBuilder;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.ScheduledPollEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.apacheextras.camel.component.couchbase.CouchbaseConstants.*;

/**
 * Represents a Couchbase endpoint that can query Views with a Poll strategy and/or produce various type of operations.
 */

@UriEndpoint(scheme = "couchbase", consumerClass = CouchbaseConsumer.class)
public class CouchbaseEndpoint extends ScheduledPollEndpoint {

    // URL stuff
    private String protocol;
    private String bucket;
    private String hostname;
    private int port;

    // Couchbase key
    @UriParam
    private String key;

    // Authentication
    @UriParam
    private String username = "";
    @UriParam
    private String password = "";

    // Additional hosts
    @UriParam
    private String additionalHosts = "";

    // Persistence and replication parameters
    @UriParam
    private int persistTo = 0;

    @UriParam
    private int replicateTo = 0;

    // Producer parameters
    @UriParam
    private String operation = COUCHBASE_PUT;
    @UriParam
    private boolean autoStartIdForInserts = false;
    @UriParam
    private int producerRetryAttempts = DEFAULT_PRODUCER_RETRIES;
    @UriParam
    private int producerRetryPause = DEFAULT_PAUSE_BETWEEN_RETRIES;

    @UriParam
    private long startingIdForInsertsFrom = 0;
    // View control
    @UriParam
    private String designDocumentName = DEFAULT_DESIGN_DOCUMENT_NAME;
    @UriParam
    private String viewName = DEFAULT_VIEWNAME;
    @UriParam
    private int limit = -1;
    @UriParam
    private boolean descending = false;
    @UriParam
    private int skip = -1;
    @UriParam
    private String rangeStartKey = "";

    @UriParam
    private String rangeEndKey = "";

    // Consumer strategy
    @UriParam
    private String consumerProcessedStrategy = DEFAULT_CONSUME_PROCESSED_STRATEGY;

    // Connection fine tuning parameters
    @UriParam
    private long opTimeOut = DEFAULT_OP_TIMEOUT;
    @UriParam
    private int timeoutExceptionThreshold = DEFAULT_TIMEOUT_EXCEPTION_THRESHOLD;
    @UriParam
    private int readBufferSize = DEFAULT_READ_BUFFER_SIZE;
    @UriParam
    private boolean shouldOptimize;
    @UriParam
    private long maxReconnectDelay = DEFAULT_MAX_RECONNECT_DELAY;
    @UriParam
    private long opQueueMaxBlockTime = DEFAULT_OP_QUEUE_MAX_BLOCK_TIME;
    @UriParam
    private long obsPollInterval = DEFAULT_OBS_POLL_INTERVAL;
    @UriParam
    private long obsTimeout = DEFAULT_OBS_TIMEOUT;

    public CouchbaseEndpoint() {
    }

    public CouchbaseEndpoint(String uri, String remaining, CouchbaseComponent component)
            throws URISyntaxException {
        super(uri, component);
        URI remainingUri = new URI(remaining);

        protocol = remainingUri.getScheme();
        if (protocol == null) {
            throw new IllegalArgumentException(COUCHBASE_URI_ERROR);
        }

        port = remainingUri.getPort() == -1 ? DEFAULT_COUCHBASE_PORT : remainingUri.getPort();

        if (remainingUri.getPath() == null || remainingUri.getPath().trim().length() == 0) {
            throw new IllegalArgumentException(COUCHBASE_URI_ERROR);
        }
        bucket = remainingUri.getPath().substring(1);

        hostname = remainingUri.getHost();
        if (hostname == null) {
            throw new IllegalArgumentException(COUCHBASE_URI_ERROR);
        }
    }


    public CouchbaseEndpoint(String endpointUri) {
        super(endpointUri);
    }

    @Override
    public Producer createProducer() throws Exception {
        return new CouchbaseProducer(this, createClient(), persistTo, replicateTo);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        return new CouchbaseConsumer(this, createClient(), processor);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }


    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdditionalHosts() {
        return additionalHosts;
    }

    public void setAdditionalHosts(String additionalHosts) {
        this.additionalHosts = additionalHosts;
    }

    public int getPersistTo() {
        return persistTo;
    }

    public void setPersistTo(int persistTo) {
        this.persistTo = persistTo;
    }

    public int getReplicateTo() {
        return replicateTo;
    }

    public void setReplicateTo(int replicateTo) {
        this.replicateTo = replicateTo;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isAutoStartIdForInserts() {
        return autoStartIdForInserts;
    }

    public void setAutoStartIdForInserts(boolean autoStartIdForInserts) {
        this.autoStartIdForInserts = autoStartIdForInserts;
    }

    public long getStartingIdForInsertsFrom() {
        return startingIdForInsertsFrom;
    }

    public void setStartingIdForInsertsFrom(long startingIdForInsertsFrom) {
        this.startingIdForInsertsFrom = startingIdForInsertsFrom;
    }

    public int getProducerRetryAttempts() {
        return producerRetryAttempts;
    }

    public void setProducerRetryAttempts(int producerRetryAttempts) {
        this.producerRetryAttempts = producerRetryAttempts;
    }

    public int getProducerRetryPause() {
        return producerRetryPause;
    }

    public void setProducerRetryPause(int producerRetryPause) {
        this.producerRetryPause = producerRetryPause;
    }

    public String getDesignDocumentName() {
        return designDocumentName;
    }

    public void setDesignDocumentName(String designDocumentName) {
        this.designDocumentName = designDocumentName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isDescending() {
        return descending;
    }

    public void setDescending(boolean descending) {
        this.descending = descending;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public String getRangeStartKey() {
        return rangeStartKey;
    }

    public void setRangeStartKey(String rangeStartKey) {
        this.rangeStartKey = rangeStartKey;
    }

    public String getRangeEndKey() {
        return rangeEndKey;
    }

    public void setRangeEndKey(String rangeEndKey) {
        this.rangeEndKey = rangeEndKey;
    }

    public String getConsumerProcessedStrategy() {
        return consumerProcessedStrategy;
    }

    public void setConsumerProcessedStrategy(String consumerProcessedStrategy) {
        this.consumerProcessedStrategy = consumerProcessedStrategy;
    }

    public long getOpTimeOut() {
        return opTimeOut;
    }

    public void setOpTimeOut(long opTimeOut) {
        this.opTimeOut = opTimeOut;
    }

    public int getTimeoutExceptionThreshold() {
        return timeoutExceptionThreshold;
    }

    public void setTimeoutExceptionThreshold(int timeoutExceptionThreshold) {
        this.timeoutExceptionThreshold = timeoutExceptionThreshold;
    }

    public int getReadBufferSize() {
        return readBufferSize;
    }

    public void setReadBufferSize(int readBufferSize) {
        this.readBufferSize = readBufferSize;
    }

    public boolean isShouldOptimize() {
        return shouldOptimize;
    }

    public void setShouldOptimize(boolean shouldOptimize) {
        this.shouldOptimize = shouldOptimize;
    }

    public long getMaxReconnectDelay() {
        return maxReconnectDelay;
    }

    public void setMaxReconnectDelay(long maxReconnectDelay) {
        this.maxReconnectDelay = maxReconnectDelay;
    }

    public long getOpQueueMaxBlockTime() {
        return opQueueMaxBlockTime;
    }

    public void setOpQueueMaxBlockTime(long opQueueMaxBlockTime) {
        this.opQueueMaxBlockTime = opQueueMaxBlockTime;
    }

    public long getObsPollInterval() {
        return obsPollInterval;
    }

    public void setObsPollInterval(long obsPollInterval) {
        this.obsPollInterval = obsPollInterval;
    }

    public long getObsTimeout() {
        return obsTimeout;
    }

    public void setObsTimeout(long obsTimeout) {
        this.obsTimeout = obsTimeout;
    }

    public URI[] makeBootstrapURI() throws URISyntaxException {

        if (additionalHosts == null || "".equals(additionalHosts)) {
            return new URI[]{new URI(protocol + "://" + hostname + ":" + port + "/pools")};
        }
        return getAllUris();

    }

    private URI[] getAllUris() throws URISyntaxException {

        String[] hosts = additionalHosts.split(",");

        for (int i = 0; i < hosts.length; i++) {
            hosts[i] = hosts[i].trim();
        }

        List<String> hostList = new ArrayList<String>();
        hostList.add(hostname);
        hostList.addAll(Arrays.asList(hosts));
        Set<String> hostSet = new LinkedHashSet<String>(hostList);
        hosts = hostSet.toArray(new String[hostSet.size()]);

        URI[] uriArray = new URI[hosts.length];

        for (int i = 0; i < hosts.length; i++) {
            uriArray[i] = new URI(protocol + "://" + hosts[i] + ":" + port + "/pools");
        }

        return uriArray;
    }

    private CouchbaseClient createClient() throws IOException, URISyntaxException {
        List<URI> hosts = Arrays.asList(makeBootstrapURI());

        CouchbaseConnectionFactoryBuilder cfb = new CouchbaseConnectionFactoryBuilder();

        if (opTimeOut != DEFAULT_OP_TIMEOUT) {
            cfb.setOpTimeout(opTimeOut);
        }
        if (timeoutExceptionThreshold != DEFAULT_TIMEOUT_EXCEPTION_THRESHOLD) {
            cfb.setTimeoutExceptionThreshold(timeoutExceptionThreshold);
        }
        if (readBufferSize != DEFAULT_READ_BUFFER_SIZE) {
            cfb.setReadBufferSize(readBufferSize);
        }
        if (shouldOptimize) {
            cfb.setShouldOptimize(true);
        }
        if (maxReconnectDelay != DEFAULT_MAX_RECONNECT_DELAY) {
            cfb.setMaxReconnectDelay(maxReconnectDelay);
        }
        if (opQueueMaxBlockTime != DEFAULT_OP_QUEUE_MAX_BLOCK_TIME) {
            cfb.setOpQueueMaxBlockTime(opQueueMaxBlockTime);
        }
        if (obsPollInterval != DEFAULT_OBS_POLL_INTERVAL) {
            cfb.setObsPollInterval(obsPollInterval);
        }
        if (obsTimeout != DEFAULT_OBS_TIMEOUT) {
            cfb.setObsTimeout(obsTimeout);
        }

        return new CouchbaseClient(cfb.buildCouchbaseConnection(hosts, bucket, username, password));

    }
}
