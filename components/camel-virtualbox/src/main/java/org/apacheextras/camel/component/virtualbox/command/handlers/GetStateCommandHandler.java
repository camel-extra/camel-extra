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
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandler;
import org.apacheextras.camel.component.virtualbox.template.MachineCallback;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;
import org.virtualbox_4_2.IMachine;

import static org.apacheextras.camel.component.virtualbox.command.MachineAwareVirtualBoxCommand.resolveMachineId;

public class GetStateCommandHandler extends VirtualBoxCommandHandler<GetStateCommand, String> {

    public GetStateCommandHandler(VirtualBoxTemplate virtualBoxTemplate) {
        super(virtualBoxTemplate);
    }

    @Override
    public Class<GetStateCommand> commandClass() {
        return GetStateCommand.class;
    }

    @Override
    public String handle(GetStateCommand command) {
        return virtualBoxTemplate.execute(command.machineName(), new MachineCallback<String>() {
            @Override
            public String execute(IMachine machine) {
                return machine.getState().name();
            }
        });
    }

    @Override
    public GetStateCommand resolveCommand(Exchange exchange, String defaultMachineId) {
        return new GetStateCommand(resolveMachineId(exchange, defaultMachineId));
    }

}