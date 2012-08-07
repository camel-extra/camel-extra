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

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.apache.camel.Exchange;
import org.apache.camel.impl.PollingConsumerSupport;

/**
 * @version $Revision: 1.1 $
 */
public class EsperPollingConsumer extends PollingConsumerSupport implements UpdateListener {
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
        EventBean bean;
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
