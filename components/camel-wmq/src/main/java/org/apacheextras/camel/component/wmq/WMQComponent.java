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

import com.ibm.mq.MQQueueManager;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

public class WMQComponent extends UriEndpointComponent {

    private final static Logger LOGGER = LoggerFactory.getLogger(WMQComponent.class);

    private Map<String, MQQueueManager> queueManagers = new HashMap<String, MQQueueManager>();

    public WMQComponent() {
        super(WMQEndpoint.class);
    }

    public WMQComponent(CamelContext camelContext) {
        super(camelContext, WMQEndpoint.class);
    }

    public MQQueueManager getQueueManager() {
        return getQueueManager("default", null, null, null);
    }

    public MQQueueManager getQueueManager(String name, String hostname, String port, String channel) {
        if (name == null) {
            LOGGER.warn("QueueManager name not defined, fallback to default");
            name = "default";
        }
        if (queueManagers.get(name) == null) {
            LOGGER.debug("Connecting to MQQueueManager {} ...", name);
            Properties qmProperties = new Properties();
            if (hostname != null && port != null && channel != null) {
                qmProperties.put(name + ".hostname", hostname);
                qmProperties.put(name + ".port", port);
                qmProperties.put(name + ".channel", channel);
            } else {
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mq.properties");
                try {
                    LOGGER.debug("Loading mq.properties from the classloader ...");
                    qmProperties.load(inputStream);
                } catch (Exception e) {
                    LOGGER.debug("mq.properties not found in the classloader, trying from etc folder");
                    try {
                        FileInputStream fileInputStream = new FileInputStream(new File(new File(new File(System.getProperty("karaf.home")), "etc"), "mq.properties"));
                        qmProperties.load(fileInputStream);
                        LOGGER.debug("mq.properties loaded from etc/mq.properties");
                    } catch (Exception e1) {
                        LOGGER.debug("mq.properties not found from etc folder, falling to default");
                        qmProperties.put(name + ".hostname", "localhost");
                        qmProperties.put(name + ".port", "7777");
                        qmProperties.put(name + ".channel", "QM_TEST.SVRCONN");
                    }
                }
            }
            if (qmProperties.get(name + ".hostname") == null) {
                throw new IllegalArgumentException(name + ".hostname property is missing");
            }
            if (qmProperties.get(name + ".port") == null) {
                throw new IllegalArgumentException(name + ".port property is missing");
            }
            if (qmProperties.get(name + ".channel") == null) {
                throw new IllegalArgumentException(name + ".channel property is missing");
            }
            Hashtable connectionProperties = new Hashtable();
            connectionProperties.put("hostname", (String) qmProperties.get(name + ".hostname"));
            connectionProperties.put("port", Integer.parseInt((String) qmProperties.get(name + ".port")));
            connectionProperties.put("channel", (String) qmProperties.get(name + ".channel"));
            try {
                LOGGER.info("Connecting to MQQueueManager {} on {}:{} (channel {})", new String[]{name,
                        (String) qmProperties.get(name + ".hostname"),
                        (String) qmProperties.get(name + ".port"),
                        (String) qmProperties.get(name + ".channel")});
                queueManagers.put(name, new MQQueueManager(name, connectionProperties));
            } catch (Exception e) {
                throw new IllegalStateException("Can't create MQQueueManager", e);
            }
        }

        return queueManagers.get(name);
    }

    @Override
    public Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        return new WMQEndpoint(uri, this, remaining);
    }

}
