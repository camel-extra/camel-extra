/**************************************************************************************
 https://camel-extra.github.io

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.


 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/gpl-2.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.esper;

import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;

/**
 * @version $Revision: 1.1 $
 */
public class EsperConsumer extends DefaultConsumer implements UpdateListener {
    private final EsperEndpoint endpoint;
    private EPStatement statement;

    public EsperConsumer(EsperEndpoint endpoint, EPStatement statement, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.statement = statement;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        if (statement.isDestroyed()) {
            // statement is destroyed! re-init it!
            statement = endpoint.createStatement();
        }
        statement.addListener(this);
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
        if (!statement.isDestroyed()) {
            // statement is not destroyed! remove the listener!
            statement.removeListener(this);
        }
        endpoint.removeConsumer();
    }

    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        for (int i = 0; newEvents != null && i < newEvents.length; i++) {
            EventBean newEvent = newEvents[i];
            EventBean oldEvent = null;
            if (oldEvents != null && oldEvents.length > i) {
                oldEvent = oldEvents[i];
            }
            Exchange exchange = endpoint.createExchange(newEvent, oldEvent, statement);
            try {
                getProcessor().process(exchange);
            } catch (Exception e) {
                getExceptionHandler().handleException("Cannot update event", exchange, e);
            }
        }
    }

}
