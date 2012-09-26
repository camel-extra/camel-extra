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
package org.apachextras.camel.component.jgroups;

import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.jgroups.Channel;
import org.jgroups.Message;

public class JGroupsEndpoint extends DefaultEndpoint {

    public static final String HEADER_JGROUPS_SRC = "JGROUPS_SRC";

    public static final String HEADER_JGROUPS_DEST = "JGROUPS_DEST";

    private Channel channel;

    private String clusterName;

    public JGroupsEndpoint(String endpointUri, Component component, Channel channel, String clusterName) {
        super(endpointUri, component);
        this.channel = channel;
        this.clusterName = clusterName;
    }

    @Override
    public Producer createProducer() throws Exception {
        return new JGroupsProducer(this, channel, clusterName);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        return new JGroupsConsumer(this, processor, channel, clusterName);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Exchange createExchange(Message message) {
        Exchange exchange = createExchange();
        exchange.getIn().setBody(message.getObject());
        exchange.getIn().setHeader(HEADER_JGROUPS_SRC, message.getSrc());
        exchange.getIn().setHeader(HEADER_JGROUPS_DEST, message.getDest());
        return exchange;
    }

    @Override
    protected void doStop() throws Exception {
        channel.close();
        super.doStop();
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

}
