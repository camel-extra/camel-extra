/**************************************************************************************
 * Copyright (C) 2008 Camel Extra Team. All rights reserved.                          *
 * http://code.google.com/p/camel-extra/                                              *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apache.camel.component.esper;

import java.util.Iterator;

import net.esper.client.EPStatement;
import net.esper.event.EventBean;
import org.apache.camel.Exchange;
import org.apache.camel.impl.PollingConsumerSupport;

/**
 * @version $Revision: 1.1 $
 */
public class EsperPollingConsumer extends PollingConsumerSupport<Exchange> {
    private EsperEndpoint endpoint;
    private EPStatement statement;

    public EsperPollingConsumer(EsperEndpoint endpoint, EPStatement statement) {
        super(endpoint);
        this.endpoint = endpoint;
        this.statement = statement;
    }

    protected void doStart() throws Exception {
        statement.start();
    }

    protected void doStop() throws Exception {
        statement.stop();
        statement.destroy();
    }

    public Exchange receive() {
        Iterator<EventBean> iterator = statement.iterator();
        if (iterator.hasNext()) {
            EventBean eventBean = iterator.next();
            return endpoint.createExchange(eventBean, statement);
        }
        else {
            return null;
        }
    }

    public Exchange receiveNoWait() {
        return receive();
    }

    public Exchange receive(long timeout) {
        return receive();
    }
}
