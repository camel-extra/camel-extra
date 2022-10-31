package org.apache.camel.component;

import java.util.Map;

import org.apache.camel.Endpoint;

import org.apache.camel.support.DefaultComponent;

@org.apache.camel.spi.annotations.Component("SapJCoServer")
public class SapJCoServerComponent extends DefaultComponent {
    
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
    	SapJCoServerEndpoint endpoint = new SapJCoServerEndpoint(uri, this);
    	endpoint.setSystemName(remaining);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
