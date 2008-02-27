/**************************************************************************************
 * Copyright (C) 2008 Camel Extra Team. All rights reserved.                          *
 * http://code.google.com/p/camel-extra/                                              *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apache.camel.component.esper;

import java.util.HashMap;
import java.util.Map;

import net.esper.client.EPRuntime;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultProducer;

/**
 * @version $Revision: 1.1 $
 */
public class EsperProducer extends DefaultProducer<Exchange> {
    private EsperEndpoint endpoint;

    public EsperProducer(EsperEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        Object body = in.getBody();
        if (endpoint.isMapEvents()) {
            Map map = new HashMap(in.getHeaders());
            map.put("body", body);
            getEsperRuntime().sendEvent(map, endpoint.getName());
        }
        else {
            getEsperRuntime().sendEvent(body);
        }
    }

    public EPRuntime getEsperRuntime() {
        return endpoint.getEsperRuntime();
    }
}
