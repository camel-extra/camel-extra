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

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.jgroups.Channel;

/**
 * Component providing support for messages multicasted from- or to JGroups channels ({@code org.jgroups.Channel}).
 */
public class JGroupsComponent extends DefaultComponent {

    private Channel channel;

    private String channelProperties;

    private Boolean enableViewMessages;

    @Override
    protected Endpoint createEndpoint(String uri, String clusterName, Map<String, Object> parameters) throws Exception {
        return new JGroupsEndpoint(uri, this, channel, clusterName, channelProperties, enableViewMessages);
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getChannelProperties() {
        return channelProperties;
    }

    public void setChannelProperties(String channelProperties) {
        this.channelProperties = channelProperties;
    }

    public Boolean getEnableViewMessages() {
        return enableViewMessages;
    }

    public void setEnableViewMessages(Boolean enableViewMessages) {
        this.enableViewMessages = enableViewMessages;
    }

}
