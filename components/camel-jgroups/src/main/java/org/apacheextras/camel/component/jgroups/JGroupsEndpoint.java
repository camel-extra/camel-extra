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
package org.apacheextras.camel.component.jgroups;

import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.jgroups.Channel;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.View;

public class JGroupsEndpoint extends DefaultEndpoint {

    public static final String HEADER_JGROUPS_SRC = "JGROUPS_SRC";

    public static final String HEADER_JGROUPS_DEST = "JGROUPS_DEST";

    public static final String HEADER_JGROUPS_CHANNEL_ADDRESS = "JGROUPS_CHANNEL_ADDRESS";

    private Channel channel;

    private Channel resolvedChannel;

    private String clusterName;

    private String channelProperties;

    private Boolean enableViewMessages;

    private boolean resolvedEnableViewMessages = false;

    public JGroupsEndpoint(String endpointUri, Component component, Channel channel, String clusterName, String channelProperties, Boolean enableViewMessages) {
        super(endpointUri, component);
        this.channel = channel;
        this.clusterName = clusterName;
        this.channelProperties = channelProperties;
        this.enableViewMessages = enableViewMessages;
    }

    @Override
    public Producer createProducer() throws Exception {
        return new JGroupsProducer(this, resolvedChannel, clusterName);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        return new JGroupsConsumer(this, processor, resolvedChannel, clusterName);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Exchange createExchange(Message message) {
        Exchange exchange = createExchange();
        exchange.getIn().setHeader(HEADER_JGROUPS_SRC, message.getSrc());
        exchange.getIn().setHeader(HEADER_JGROUPS_DEST, message.getDest());
        exchange.getIn().setHeader(HEADER_JGROUPS_CHANNEL_ADDRESS, resolvedChannel.getAddress());
        exchange.getIn().setBody(message.getObject());
        return exchange;
    }

    public Exchange createExchange(View view) {
        Exchange exchange = createExchange();
        exchange.getIn().setHeader(HEADER_JGROUPS_CHANNEL_ADDRESS, resolvedChannel.getAddress());
        exchange.getIn().setBody(view);
        return exchange;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        resolvedChannel = resolveChannel();
        resolvedEnableViewMessages = resolveEnableViewMessages();
    }

    @Override
    protected void doStop() throws Exception {
        resolvedChannel.close();
        super.doStop();
    }

    private Channel resolveChannel() throws Exception {
        if (channel != null) {
            return channel;
        }
        if (channelProperties != null && !channelProperties.isEmpty()) {
            return new JChannel(channelProperties);
        }
        return new JChannel();
    }

    private boolean resolveEnableViewMessages() {
        if(enableViewMessages != null) {
            resolvedEnableViewMessages = enableViewMessages;
        }
        return resolvedEnableViewMessages;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getChannelProperties() {
        return channelProperties;
    }

    public void setChannelProperties(String channelProperties) {
        this.channelProperties = channelProperties;
    }

    public Channel getResolvedChannel() {
        return resolvedChannel;
    }

    public void setResolvedChannel(Channel resolvedChannel) {
        this.resolvedChannel = resolvedChannel;
    }

    public Boolean getEnableViewMessages() {
        return enableViewMessages;
    }

    public void setEnableViewMessages(Boolean enableViewMessages) {
        this.enableViewMessages = enableViewMessages;
    }

    public boolean isResolvedEnableViewMessages() {
        return resolvedEnableViewMessages;
    }

    public void setResolvedEnableViewMessages(boolean resolvedEnableViewMessages) {
        this.resolvedEnableViewMessages = resolvedEnableViewMessages;
    }

}