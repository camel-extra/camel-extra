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

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.apacheextras.camel.component.virtualbox.command.NoHandlerRegisteredException;
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandlersManager;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * VirtualBox producer sends commands to the VirtualBox manager. It may be used to interact with and control the
 * behavior of the virtual machines.
 */
public class VirtualBoxProducer extends DefaultProducer {

    private static final Logger LOG = getLogger(VirtualBoxProducer.class);

    private final VirtualBoxCommandHandlersManager handlersManager;

    private final String machineId;

    public VirtualBoxProducer(VirtualBoxEndpoint endpoint,
                              VirtualBoxCommandHandlersManager handlersManager, String machineId) {
        super(endpoint);
        this.handlersManager = handlersManager;
        this.machineId = machineId;
    }

    // Overridden

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.debug("Received exchange: {}", exchange);
        try {
            Object commandResult = handlersManager.handleCommand(exchange, machineId);
            if(commandResult == null) {
                LOG.warn("Null result returned by the VirtualBox command handler for exchange {}.",
                        exchange.getExchangeId());
            }
            exchange.getIn().setBody(commandResult);
        } catch (NoHandlerRegisteredException e) {
            LOG.warn("No proper handler registered: ", e);
        }
    }

}
