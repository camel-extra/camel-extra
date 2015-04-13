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
package org.apacheextras.camel.component.virtualbox.command.handlers;

import org.apache.camel.Exchange;
import org.apacheextras.camel.component.virtualbox.command.NoReturnValue;
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandler;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxManagerCallback;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;
import org.virtualbox_4_2.VirtualBoxManager;

import static org.apacheextras.camel.component.virtualbox.command.MachineAwareVirtualBoxCommand.resolveMachineId;
import static org.apacheextras.camel.component.virtualbox.command.NoReturnValue.noValue;

public class StartVmCommandHandler extends VirtualBoxCommandHandler<StartVmCommand, NoReturnValue> {

    public StartVmCommandHandler(VirtualBoxTemplate virtualBoxTemplate) {
        super(virtualBoxTemplate);
    }

    @Override
    public Class<StartVmCommand> commandClass() {
        return StartVmCommand.class;
    }

    @Override
    public NoReturnValue handle(final StartVmCommand command) {
        virtualBoxTemplate.execute(new VirtualBoxManagerCallback<Object>() {
            @Override
            public NoReturnValue execute(VirtualBoxManager virtualBoxManager) {
                virtualBoxManager.startVm(command.machineName(), null, 7000);
                return noValue();
            }
        });
        return noValue();
    }

    @Override
    public StartVmCommand resolveCommand(Exchange exchange, String defaultMachineId) {
        return new StartVmCommand(resolveMachineId(exchange, defaultMachineId));
    }

}