/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
