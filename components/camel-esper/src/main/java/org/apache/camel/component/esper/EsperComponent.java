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

import java.util.Map;

import net.esper.client.EPRuntime;
import net.esper.client.EPServiceProvider;
import net.esper.client.EPServiceProviderManager;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultComponent;

/**
 * A component for working with <a href="http//esper.codehaus.org/">Esper</a>
 *
 * @version $Revision: 1.1 $
 */
public class EsperComponent extends DefaultComponent<Exchange> {
    private EPServiceProvider esperService;
    private EPRuntime esperRuntime;

    protected Endpoint<Exchange> createEndpoint(String uri, String remaining, Map parameters) throws Exception {
        return new EsperEndpoint(uri, this, remaining);
    }

    public EPServiceProvider getEsperService() {
        if (esperService == null) {
            esperService = EPServiceProviderManager.getDefaultProvider();
        }
        return esperService;
    }

    public void setEsperService(EPServiceProvider esperService) {
        this.esperService = esperService;
    }

    public EPRuntime getEsperRuntime() {
        if (esperRuntime == null) {
            esperRuntime = getEsperService().getEPRuntime();
        }
        return esperRuntime;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();

        // lets force lazy creation
        getEsperRuntime();
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();

        // TODO anything we can do here?
    }
}
