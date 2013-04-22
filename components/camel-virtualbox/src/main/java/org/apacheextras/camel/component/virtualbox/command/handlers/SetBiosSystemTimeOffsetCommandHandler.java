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
package org.apacheextras.camel.component.virtualbox.command.handlers;

import org.apache.camel.Exchange;
import org.apacheextras.camel.component.virtualbox.command.NoReturnValue;
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandler;
import org.apacheextras.camel.component.virtualbox.template.MachineCallback;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;
import org.virtualbox_4_2.IMachine;

import static org.apacheextras.camel.component.virtualbox.command.MachineAwareVirtualBoxCommand.HEADER_MACHINE;
import static org.apacheextras.camel.component.virtualbox.command.NoReturnValue.noValue;
import static org.apacheextras.camel.component.virtualbox.command.handlers.SetBiosSystemTimeOffsetCommand.HEADER_OFFSET;

public class SetBiosSystemTimeOffsetCommandHandler extends VirtualBoxCommandHandler<SetBiosSystemTimeOffsetCommand, NoReturnValue> {

    public SetBiosSystemTimeOffsetCommandHandler(VirtualBoxTemplate virtualBoxTemplate) {
        super(virtualBoxTemplate);
    }

    @Override
    public Class<SetBiosSystemTimeOffsetCommand> commandClass() {
        return SetBiosSystemTimeOffsetCommand.class;
    }

    @Override
    public NoReturnValue handle(final SetBiosSystemTimeOffsetCommand command) {
        return virtualBoxTemplate.execute(command.machineName(), new MachineCallback<NoReturnValue>() {
            @Override
            public NoReturnValue execute(IMachine machine) {
                machine.getBIOSSettings().setTimeOffset(command.offset());
                return noValue();
            }
        });
    }

    @Override
    public SetBiosSystemTimeOffsetCommand resolveCommand(Exchange exchange) {
        String machine = exchange.getIn().getHeader(HEADER_MACHINE, String.class);
        long offset = exchange.getIn().getHeader(HEADER_OFFSET, Long.class);
        return new SetBiosSystemTimeOffsetCommand(machine, offset);
    }

}
