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
package org.apacheextras.camel.component.virtualbox.command;

import org.apache.camel.Exchange;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class MachineAwareVirtualBoxCommand<R> implements VirtualBoxCommand<R> {

    private final static Logger LOG = getLogger(MachineAwareVirtualBoxCommand.class);

    public static final String HEADER_MACHINE = "virtualbox_machine";

    protected final String machineName;

    protected MachineAwareVirtualBoxCommand(String machineName) {
        this.machineName = machineName;
    }

    public String machineName() {
        return machineName;
    }

    public static String resolveMachineId(Exchange exchange, String defaultMachineId) {
        String messageMachineId = exchange.getIn().getHeader(HEADER_MACHINE, String.class);
        if (messageMachineId == null) {
            LOG.debug("Machine ID specified in the header: {} ID will be used instead of default {}",
                    messageMachineId, defaultMachineId);
            return messageMachineId;
        } else {
            LOG.debug("Machine ID not specified in header, using default machine ID: {}", defaultMachineId);
            return defaultMachineId;
        }
    }

}
