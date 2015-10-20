package org.apacheextras.camel.component.wmq;

import com.ibm.mq.MQQueueManager;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

public class WMQComponent extends UriEndpointComponent {

    private final static Logger LOGGER = LoggerFactory.getLogger(WMQComponent.class);

    private MQQueueManager queueManager;

    public WMQComponent() {
        super(WMQEndpoint.class);
    }

    public WMQComponent(CamelContext camelContext) {
        super(camelContext, WMQEndpoint.class);
    }

    public MQQueueManager getQueueManager() {
        if (queueManager == null) {
            LOGGER.debug("Connecting to MQQueueManager ...");
            Properties loadedProperties = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mq.properties");
            try {
                LOGGER.debug("Loading mq.properties from the classloader ...");
                loadedProperties.load(inputStream);
            } catch (Exception e) {
                LOGGER.debug("mq.properties not found in the classloader, trying from etc folder");
                try {
                    FileInputStream fileInputStream = new FileInputStream(new File(new File(new File(System.getProperty("karaf.home")), "etc"), "mq.properties"));
                    loadedProperties.load(fileInputStream);
                    LOGGER.debug("mq.properties loaded from etc/mq.properties");
                } catch (Exception e1) {
                    LOGGER.debug("mq.properties not found from etc folder, falling to default");
                    loadedProperties.put("hostname", "localhost");
                    loadedProperties.put("port", "7777");
                    loadedProperties.put("channel", "QM_TEST.SVRCONN");
                    loadedProperties.put("name", "QM_TEST");
                }
            }
            if (loadedProperties.get("hostname") == null) {
                throw new IllegalArgumentException("hostname property is missing");
            }
            if (loadedProperties.get("port") == null) {
                throw new IllegalArgumentException("port property is missing");
            }
            if (loadedProperties.get("channel") == null) {
                throw new IllegalArgumentException("channel property is missing");
            }
            if (loadedProperties.get("name") == null) {
                throw new IllegalArgumentException("name property is missing");
            }
            Hashtable connectionProperties = new Hashtable();
            connectionProperties.put("hostname", (String) loadedProperties.get("hostname"));
            connectionProperties.put("port", Integer.parseInt((String) loadedProperties.get("port")));
            connectionProperties.put("channel", (String) loadedProperties.get("channel"));
            try {
                LOGGER.info("Connecting to MQQueueManager " + loadedProperties.get("name") + " on " + loadedProperties.get("hostname") + ":" + loadedProperties.get("port")
                                + " (channel " + loadedProperties.get("channel") + ")");
                this.queueManager = new MQQueueManager(((String) loadedProperties.get("name")), connectionProperties);
            } catch (Exception e) {
                throw new IllegalStateException("Can't create MQQueueManager", e);
            }
        }
        return queueManager;
    }

    public void setQueueManager(MQQueueManager queueManager) {
        this.queueManager = queueManager;
    }

    @Override
    public Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        return new WMQEndpoint(uri, this, remaining);
    }

}
