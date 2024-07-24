/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apacheextras.camel.component.wmq;

import org.apache.camel.Category;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.api.management.ManagedAttribute;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultEndpoint;

@UriEndpoint(firstVersion="2.25.3", scheme = "wmq", title = "IBM WebSphere MQ", syntax = "wmq:destinationName", category = {Category.MESSAGING})
public class WMQEndpoint extends DefaultEndpoint {

    @UriPath
    @Metadata(required = true, description = "MQ destination name")
    private String destinationName;

    @UriParam
    @Metadata(description = "Queue manager name")
    private String queueManagerName;

    @UriParam
    @Metadata(description = "Queue manager hostname")
    private String queueManagerHostname;

    @UriParam
    @Metadata(description = "Queue manager port number")
    private String queueManagerPort;

    @UriParam
    @Metadata(description = "Queue manager channel name")
    private String queueManagerChannel;

    @UriParam
    @Metadata(description = "Queue manager user ID")
    private String queueManagerUserID;

    @UriParam
    @Metadata(description = "Queue manager password")
    private String queueManagerPassword;

    @UriParam
    @Metadata(description = "Queue manager CCSID")
    private String queueManagerCCSID;

    @UriParam
    @Metadata(description = "Type of the message body")
    private String bodyType;

    @UriParam
    @Metadata(description = "Polling delay (iteration)")
    private int delay = 5;

    @UriParam
    @Metadata(description = "Timeout/delay when exception occur")
    private int delayOnException = 60*1000;

    public WMQEndpoint() {}

    public WMQEndpoint(String uri, WMQComponent component) {
        super(uri, component);
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getQueueManagerName() {
        return queueManagerName;
    }

    public void setQueueManagerName(String queueManagerName) {
        this.queueManagerName = queueManagerName;
    }

    public String getQueueManagerHostname() {
        return queueManagerHostname;
    }

    public void setQueueManagerHostname(String queueManagerHostname) {
        this.queueManagerHostname = queueManagerHostname;
    }

    public String getQueueManagerPort() {
        return queueManagerPort;
    }

    public void setQueueManagerPort(String queueManagerPort) {
        this.queueManagerPort = queueManagerPort;
    }

    public String getQueueManagerChannel() {
        return queueManagerChannel;
    }

    public void setQueueManagerChannel(String queueManagerChannel) {
        this.queueManagerChannel = queueManagerChannel;
    }

    public String getQueueManagerUserID() {
        return queueManagerUserID;
    }

    public void setQueueManagerUserID(String queueManagerUserID) {
        this.queueManagerUserID = queueManagerUserID;
    }

    public String getQueueManagerPassword() {
        return queueManagerPassword;
    }

    public void setQueueManagerPassword(String queueManagerPassword) {
        this.queueManagerPassword = queueManagerPassword;
    }

    public String getQueueManagerCCSID() {
        return queueManagerCCSID;
    }

    public void setQueueManagerCCSID(String queueManagerCCSID) {
        this.queueManagerCCSID = queueManagerCCSID;
    }


    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelayOnException() {
        return delayOnException;
    }

    public void setDelayOnException(int delayOnException) {
        this.delayOnException = delayOnException;
    }

    public Producer createProducer() throws Exception {
        return new WMQProducer(this);
    }

    public WMQConsumer createConsumer(Processor processor) throws Exception {
        WMQConsumer consumer = new WMQConsumer(this, processor);
        consumer.setDelay(delay);
        this.configureConsumer(consumer);
        return consumer;
    }

    @ManagedAttribute
    public boolean isSingleton() {
        return true;
    }
}
