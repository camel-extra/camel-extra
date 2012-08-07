/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

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
package org.apachextras.camel.component.esper;

import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RuntimeExchangeException;
import org.apache.camel.impl.DefaultConsumer;

/**
 * @version $Revision: 1.1 $
 */
public class EsperConsumer extends DefaultConsumer implements UpdateListener {
    private EsperEndpoint endpoint;
    private EPStatement statement;

    public EsperConsumer(EsperEndpoint endpoint, EPStatement statement, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.statement = statement;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        statement.addListener(this);
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
        statement.removeListener(this);
        endpoint.removeConsumer();
    }

    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
      if (newEvents != null) {
	        for (EventBean eventBean : newEvents) {
	            Object underlying = eventBean.getUnderlying();
	            Exchange exchange = endpoint.createExchange(eventBean, statement);
	            try {
	                getProcessor().process(exchange);
	            } catch (Exception e) {
	                throw new RuntimeExchangeException("Cannot update event", exchange, e);
	            }
	        }
		  }
    }

}
