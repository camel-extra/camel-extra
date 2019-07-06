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
package org.apacheextras.camel.component.wmq;

import org.apache.camel.Component;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.api.management.ManagedAttribute;
import org.apache.camel.api.management.ManagedResource;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;

@ManagedResource(description = "Managed WMQ Endpoint")
@UriEndpoint(scheme = "wmq", title = "IBM WebSphere MQ", syntax = "wmq:destinationName", consumerClass = WMQConsumer.class)
public class WMQEndpoint extends DefaultEndpoint {

    @UriParam
    private String destinationName;

    @UriParam
    private String queueManagerName;

    @UriParam
    private String queueManagerHostname;

    @UriParam
    private String queueManagerPort;

    @UriParam
    private String queueManagerChannel;

    @UriParam
    private String queueManagerUserID;

    @UriParam
    private String queueManagerPassword;

    @UriParam
    private String queueManagerCCSID;

    @UriParam
    private String bodyType;

    @UriParam
    private int delay = 5;

    public WMQEndpoint() {
    }

    public WMQEndpoint(String uri, Component component, String destinationName) {
        super(uri, component);
        this.destinationName = destinationName;
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

    public Producer createProducer() throws Exception {
        return new WMQProducer(this);
    }

    public WMQConsumer createConsumer(Processor processor) throws Exception {
        WMQConsumer consumer = new WMQConsumer(this, processor);
        consumer.setDelay(delay);
        return consumer;
    }

    @ManagedAttribute
    public boolean isSingleton() {
        return true;
    }
}
