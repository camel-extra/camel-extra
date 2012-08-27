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
package org.apache.camel.component.jgroups;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.jgroups.Address;
import org.jgroups.Channel;
import org.jgroups.Message;

public class JGroupsProducer extends DefaultProducer {

    private final Channel channel;

    private final String clusterName;

    public JGroupsProducer(Endpoint endpoint, Channel channel, String clusterName) {
        super(endpoint);
        this.channel = channel;
        this.clusterName = clusterName;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        channel.connect(clusterName);
    }

    @Override
    protected void doStop() throws Exception {
        channel.disconnect();
        super.doStop();
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Object body = exchange.getIn().getBody();
        if (body != null) {
            Address destinationAddress = exchange.getIn().getHeader(JGroupsEndpoint.HEADER_JGROUPS_DEST, Address.class);
            Address sourceAddress = exchange.getIn().getHeader(JGroupsEndpoint.HEADER_JGROUPS_SRC, Address.class);

            log.debug("Posting: {} to cluster: {}", body, clusterName);
            if (destinationAddress != null) {
               log.debug("Posting to custom destination address: {}", destinationAddress);
            }
            if (sourceAddress != null) {
                log.debug("Posting from custom source address: {}", sourceAddress);
            }

            channel.send(new Message(destinationAddress, sourceAddress, body));
        } else {
            log.debug("Body is null, cannot post to channel.");
        }
    }

}
