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

import net.esper.client.EPAdministrator;
import net.esper.client.EPRuntime;
import net.esper.client.EPServiceProvider;
import net.esper.client.EPStatement;
import net.esper.event.EventBean;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.PollingConsumer;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.util.ObjectHelper;

/**
 * An endpoint for working with <a href="http//esper.codehaus.org/">Esper</a>
 *
 * @version $Revision: 1.1 $
 */
public class EsperEndpoint extends DefaultEndpoint<Exchange> {
    private EsperComponent component;
    private String name;
    private boolean mapEvents;
    private String pattern;
    private String eql;

    public EsperEndpoint(String uri, EsperComponent component, String name) {
        super(uri, component);
        this.component = component;
        this.name = name;
    }

    public boolean isSingleton() {
        return true;
    }

    public EsperProducer createProducer() throws Exception {
        return new EsperProducer(this);
    }

    public EsperConsumer createConsumer(Processor processor) throws Exception {
        EPStatement statement = createStatement();
        return new EsperConsumer(this, statement, processor);
    }

    @Override
    public PollingConsumer<Exchange> createPollingConsumer() throws Exception {
        EPStatement statement = createStatement();
        return new EsperPollingConsumer(this, statement);
    }

    public EPStatement createStatement() {
        if (pattern != null) {
            return getEsperAdministrator().createPattern(pattern);
        }
        else {
            ObjectHelper.notNull(eql, "eql or pattern");
            return getEsperAdministrator().createEQL(eql);
        }
    }

    /**
     * Creates a Camel {@link Exchange} from an Esper {@link EventBean} instance
     */
    public Exchange createExchange(EventBean eventBean, EPStatement statement) {
        Exchange exchange = createExchange(ExchangePattern.InOnly);
        Message in = exchange.getIn();
        in.setHeader("CamelEsperName", name);
        in.setHeader("CamelEsperStatement", statement);
        if (pattern != null) {
            in.setHeader("CamelEsperPattern", pattern);
        }
        if (eql != null) {
            in.setHeader("CamelEsperEql", eql);
        }
        in.setBody(eventBean);
        return exchange;
    }

    // Properties
    //-------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public EPRuntime getEsperRuntime() {
        return component.getEsperRuntime();
    }

    public EPServiceProvider getEsperService() {
        return component.getEsperService();
    }

    public EPAdministrator getEsperAdministrator() {
        return getEsperService().getEPAdministrator();
    }

    public boolean isMapEvents() {
        return mapEvents;
    }

    /**
     * Should we use Map events (the default approach) containing all the message headers
     * and the message body in the "body" entry, or should we just send the body of the message
     * as the event.
     *
     * @param mapEvents whether or not we should send map events.
     */
    public void setMapEvents(boolean mapEvents) {
        this.mapEvents = mapEvents;
    }

    public String getEql() {
        return eql;
    }

    /**
     * Sets the EQL statement used for consumers
     */
    public void setEql(String eql) {
        this.eql = eql;
    }

    public String getPattern() {
        return pattern;
    }

    /**
     * Sets the Esper pattern used for consumers
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
