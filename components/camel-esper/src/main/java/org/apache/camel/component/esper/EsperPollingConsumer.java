/**************************************************************************************
 * Copyright (C) 2008 Camel Extra Team. All rights reserved.                          *
 * http://code.google.com/p/camel-extra/                                              *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apache.camel.component.esper;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import net.esper.client.EPStatement;
import net.esper.client.UpdateListener;
import net.esper.event.EventBean;
import org.apache.camel.Exchange;
import org.apache.camel.impl.PollingConsumerSupport;

/**
 * @version $Revision: 1.1 $
 */
public class EsperPollingConsumer extends PollingConsumerSupport<Exchange> implements UpdateListener {
    private EsperEndpoint endpoint;
    private EPStatement statement;
    private LinkedBlockingQueue<EventBean> beanForwardQueue = new LinkedBlockingQueue<EventBean>();

    public EsperPollingConsumer(EsperEndpoint endpoint, EPStatement statement) {
        super(endpoint);
        this.endpoint = endpoint;
        this.statement = statement;
    }

    protected void doStart() throws Exception {
        statement.addListener(this);
    }

    protected void doStop() throws Exception {
        statement.removeListener(this);
        endpoint.removeConsumer();
    }

    public void update(EventBean[] arg0, EventBean[] arg1) {
        for (EventBean bean : arg0) {
            try {
                // put the new events to the forwarding queue
                beanForwardQueue.put(bean);
            } catch (InterruptedException e) {
                return;
            }
        }
    }


    public Exchange receive() {
        EventBean bean;
        try {
            bean = beanForwardQueue.take();
        } catch (InterruptedException e) {
            return null;
        }
        if (bean == null) {
            return null;
        }
        return endpoint.createExchange(bean, statement);
    }

    public Exchange receiveNoWait() {
        EventBean bean = beanForwardQueue.poll();
        if (bean == null) {
            return null;
        }
        return endpoint.createExchange(bean, statement);
    }

    public Exchange receive(long timeout) {
        EventBean bean = null;
        try {
            bean = beanForwardQueue.poll(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return null;
        }
        if (bean == null) {
            return null;
        }
        return endpoint.createExchange(bean, statement);
    }
    
    /*
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
    */
}
