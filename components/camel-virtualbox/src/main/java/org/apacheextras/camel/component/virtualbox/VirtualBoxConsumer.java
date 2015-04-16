/**************************************************************************************
 https://camel-extra.github.io

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation; either version 3
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.


 You should have received a copy of the GNU Lesser General Public
 License along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.virtualbox;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.apacheextras.camel.component.virtualbox.command.NoReturnValue;
import org.apacheextras.camel.component.virtualbox.template.ConsoleCallback;
import org.apacheextras.camel.component.virtualbox.template.DetachedSession;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;
import org.virtualbox_4_2.IConsole;
import org.virtualbox_4_2.IEvent;
import org.virtualbox_4_2.IEventListener;
import org.virtualbox_4_2.IEventSource;
import org.virtualbox_4_2.IMachine;
import org.virtualbox_4_2.VBoxEventType;

import java.util.Collections;
import java.util.List;

import static org.apacheextras.camel.component.virtualbox.command.NoReturnValue.noValue;

public class VirtualBoxConsumer extends ScheduledPollConsumer {

    private final VirtualBoxTemplate virtualBoxTemplate;

    private final String machineId;

    private IEventSource eventSource;

    private IEventListener eventListener;

    private DetachedSession sessionContext;

    public VirtualBoxConsumer(Endpoint endpoint, Processor processor, VirtualBoxTemplate virtualBoxTemplate, String machineId) {
        super(endpoint, processor);
        this.virtualBoxTemplate = virtualBoxTemplate;
        this.machineId = machineId;
    }

    @Override
    protected void doStart() throws Exception {
        sessionContext = virtualBoxTemplate.executeAndDetach(machineId, new ConsoleCallback() {
            @Override
            public NoReturnValue execute(IMachine machine, IConsole console) {
                eventSource = console.getEventSource();
                eventListener = eventSource.createListener();
                List<VBoxEventType> eventTypes = Collections.singletonList(VBoxEventType.Any);
                eventSource.registerListener(eventListener, eventTypes, false);
                return noValue();
            }
        });
        super.doStart();
    }

    @Override
    protected void doStop() throws Exception {
        try {
            eventSource.unregisterListener(eventListener);
        } finally {
            sessionContext.close();
        }
        super.doStop();
    }

    @Override
    protected int poll() throws Exception {
        IEvent event = virtualBoxTemplate.executeDetached(sessionContext, machineId, new ConsoleCallback<IEvent>() {
            @Override
            public IEvent execute(IMachine machine, IConsole console) {
                IEvent event = eventSource.getEvent(eventListener, 5000);
                if (event != null) {
                    eventSource.eventProcessed(eventListener, event);
                    return event;
                } else {
                    return null;
                }
            }
        });
        if (event != null) {
            Exchange exchange = getEndpoint().createExchange();
            exchange.getIn().setBody(event);
            getProcessor().process(exchange);
            return 1;
        } else {
            return 0;
        }
    }

}
