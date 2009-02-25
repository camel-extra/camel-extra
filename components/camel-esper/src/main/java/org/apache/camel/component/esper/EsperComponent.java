/**************************************************************************************
 * Copyright (C) 2008 Camel Extra Team. All rights reserved.                          *
 * http://code.google.com/p/camel-extra/                                              *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apache.camel.component.esper;

import java.util.Map;


import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

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
