/**
 * ************************************************************************************
 * https://camel-extra.github.io
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 * http://www.gnu.org/licenses/gpl-2.0-standalone.html
 * *************************************************************************************
 */
package org.apacheextras.camel.component.esper;

import java.util.concurrent.atomic.AtomicInteger;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.PollingConsumer;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.util.ObjectHelper;

/**
 * An endpoint for working with <a href="http//esper.codehaus.org/">Esper</a>
 *
 * @version $Revision: 1.1 $
 */
@UriEndpoint(scheme = "esper", title = "Esper", syntax = "esper:name[?options]", consumerClass = EsperConsumer.class)
public class EsperEndpoint extends DefaultEndpoint {

    private final EsperComponent component;
    private final String name;
    private boolean mapEvents;
    private boolean mapBody = false;
    private boolean configured = false;
    private boolean listen = true;
    private String pattern;
    private String eql;
    private EPStatement statement;
    private final AtomicInteger consumers = new AtomicInteger(0);

    public EsperEndpoint(String uri, EsperComponent component, String name) {
        super(uri, component);
        this.component = component;
        this.name = name;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public EsperProducer createProducer() throws Exception {
        return new EsperProducer(this);
    }

    @Override
    public EsperConsumer createConsumer(Processor processor) throws Exception {
        EPStatement stat = getStatement();
        consumers.incrementAndGet();
        return new EsperConsumer(this, statement, processor);
    }

    @Override
    public PollingConsumer createPollingConsumer() throws Exception {
        EPStatement stat = getStatement();
        consumers.incrementAndGet();
        return new EsperPollingConsumer(this, statement);
    }

    private EPStatement getStatement() {
        if (statement == null) {
            statement = createStatement(null);
            // statement.start();
        }
        return statement;
    }

    protected EPStatement createStatement(String routeId) {
        if (pattern != null) {
            return getEsperAdministrator().createPattern(pattern, routeId);
        } else {
            ObjectHelper.notNull(eql, "eql or pattern");
            return getEsperAdministrator().createEPL(eql, routeId);
        }
    }

    public synchronized void removeConsumer() {
        if (0 == consumers.decrementAndGet()) {
            statement.stop();
            statement.destroy();
        }
    }

    /**
     * Creates a Camel {@link Exchange} from an Esper {@link EventBean} instance
     *
     * @param newEventBean
     * @param oldEventBean
     * @param statement
     * @return Exchange
     */
    public Exchange createExchange(EventBean newEventBean, EventBean oldEventBean, EPStatement statement) {
        Exchange exchange = createExchange(ExchangePattern.InOnly);
        Message in = new EsperMessage(newEventBean, oldEventBean);
        in.setHeader("CamelEsperName", name);
        in.setHeader("CamelEsperStatement", statement);
        if (pattern != null) {
            in.setHeader("CamelEsperPattern", pattern);
        }
        if (eql != null) {
            in.setHeader("CamelEsperEql", eql);
        }
        exchange.setIn(in);
        return exchange;
    }

    // Properties
    // -------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public EPRuntime getEsperRuntime() {
        return component.getEsperRuntime(isConfigured());
    }

    public EPServiceProvider getEsperService() {
        return component.getEsperService(isConfigured());
    }

    public EPAdministrator getEsperAdministrator() {
        return getEsperService().getEPAdministrator();
    }

    public boolean isMapEvents() {
        return mapEvents;
    }

    /**
     * Should we use Map events (the default approach) containing all the
     * message headers and the message body in the "body" entry, or should we
     * just send the body of the message as the event.
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
     *
     * @param eql
     */
    public void setEql(String eql) {
        this.eql = eql;
    }

    public String getPattern() {
        return pattern;
    }

    /**
     * Sets the Esper pattern used for consumers
     *
     * @param pattern
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isConfigured() {
        return configured;
    }

    public void setConfigured(boolean configured) {
        this.configured = configured;
    }

	public boolean isMapBody() {
		return mapBody;
	}

	public void setMapBody(boolean mapBody) {
		this.mapBody = mapBody;
	}
	
	public boolean isListen() {
		return listen;
	}

	/**
     * Sets is Esper consumer listener must be create
     *
     * @param pattern
     */
	public void setListen(boolean listen) {
		this.listen = listen;
	}
}
