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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.spi.annotations.Component;
import org.apache.camel.support.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.mq.MQQueueManager;
import com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation;
import com.ibm.msg.client.commonservices.workqueue.WorkQueueManager;

@Component("wmq")
public class WMQComponent extends DefaultComponent {

    private final static Logger LOGGER = LoggerFactory.getLogger(WMQComponent.class);

    @Override
    public WMQEndpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        String destinationName = remaining;
        WMQEndpoint endpoint = new WMQEndpoint(uri, this);
        setProperties(endpoint, parameters);
        endpoint.setDestinationName(destinationName);
        return endpoint;
    }

    public MQQueueManager getQueueManager() {
        return getQueueManager("default", null, null, null, null, null, null);
    }

    public MQQueueManager getQueueManager(String name, String hostname, String port, String channel, String userID, String password, String CCSID) {
        if (name == null) {
            LOGGER.warn("QueueManager name not defined, fallback to default");
            name = "default";
        }
        LOGGER.debug("Connecting to MQQueueManager {} ...", name);
        Properties qmProperties = new Properties();
        if (hostname != null && port != null && channel != null) {
            qmProperties.put(name + ".hostname", hostname);
            qmProperties.put(name + ".port", port);
            qmProperties.put(name + ".channel", channel);
            if (userID != null) {
                qmProperties.put(name + ".userID", userID);
            }
            if (password != null) {
                qmProperties.put(name + ".password", password);
            }
            if (CCSID != null) {
                qmProperties.put(name + ".CCSID", CCSID);
            }
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
        if (qmProperties.get(name + ".userID") != null) {
            connectionProperties.put("userID", (String) qmProperties.get(name + ".userID"));
        }
        if (qmProperties.get(name + ".password") != null) {
            connectionProperties.put("password", (String) qmProperties.get(name + ".password"));
        }
        if (qmProperties.get(name + ".CCSID") != null) {
            connectionProperties.put("CCSID", (String) qmProperties.get(name + ".CCSID"));
        }
        try {
            LOGGER.info("Connecting to MQQueueManager {} on {}:{} (channel {})", new String[]{name,
                    (String) qmProperties.get(name + ".hostname"),
                    (String) qmProperties.get(name + ".port"),
                    (String) qmProperties.get(name + ".channel")});
            return new MQQueueManager(name, connectionProperties);
        } catch (Exception e) {
            throw new IllegalStateException("Can't create MQQueueManager", e);
        }
    }

    @Override
    public void stop()  {
        try {
            WorkQueueManager.close();
            interruptMangerThread();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            super.stop();
        }
    }

    /***
     * Code of {@link WorkQueueManager.close()} is not implemented correctly and still leaves
     * hanging thread behind the only way to really kill it is to access it through reflection and
     * call interrupt()
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void interruptMangerThread() throws NoSuchFieldException, IllegalAccessException {
        Field managerImplField = WorkQueueManager.class.getDeclaredField("manager");
        managerImplField.setAccessible(true);
        Object managerImpl = managerImplField.get(null);
        if (managerImpl != null && WorkQueueManagerImplementation.class.isAssignableFrom(managerImpl.getClass())){
            WorkQueueManagerImplementation managerImplementation = (WorkQueueManagerImplementation) managerImpl;
            managerImplementation.close();
            Field managerThreadFld = WorkQueueManagerImplementation.class.getDeclaredField("workManagerThread");
            managerThreadFld.setAccessible(true);
            Object actualManagerThread = managerThreadFld.get(managerImplementation);
            if (actualManagerThread != null && Thread.class.isAssignableFrom(actualManagerThread.getClass())){
                Thread managerThread = (Thread) actualManagerThread;
                managerThread.interrupt();
            }
        }
    }
}
