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
package org.apacheextras.camel.component.virtualbox;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.apacheextras.camel.component.virtualbox.command.NoHandlerRegisteredException;
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandlersManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VirtualBoxProducer extends DefaultProducer {

    private static final Logger LOG = LoggerFactory.getLogger(VirtualBoxProducer.class);

    private final VirtualBoxCommandHandlersManager handlersManager;

    public VirtualBoxProducer(VirtualBoxCommandHandlersManager handlersManager, VirtualBoxEndpoint endpoint) {
        super(endpoint);
        this.handlersManager = handlersManager;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.debug("Received exchange: {}", exchange);
        try {
            Object commandResult = handlersManager.handleCommand(exchange);
            exchange.getIn().setBody(commandResult);
        } catch (NoHandlerRegisteredException e) {
            LOG.info("No proper handler registered: ", e);
        }
    }

}
