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

import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
import org.jgroups.Channel;

public class JGroupsConsumer extends DefaultConsumer {

    private final Channel channel;
    private final String clusterName;

    private final CamelJGroupsReceiver receiver;

    public JGroupsConsumer(JGroupsEndpoint endpoint, Processor processor, Channel channel, String clusterName) {
        super(endpoint, processor);
        this.channel = channel;
        this.clusterName = clusterName;

        this.receiver = new CamelJGroupsReceiver(endpoint, processor);
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        log.debug("Connecting receiver: {} to the cluster: {}.", receiver, clusterName);
        channel.setReceiver(receiver);
        channel.connect(clusterName);
    }

    @Override
    protected void doStop() throws Exception {
        log.debug("Closing connection to cluster: {} from receiver: {}.", clusterName, receiver);
        channel.disconnect();
        super.doStop();
    }
}
