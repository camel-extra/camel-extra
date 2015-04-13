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
import org.apacheextras.camel.component.virtualbox.command.ProgressingVirtualBoxCommandHandler;
import org.apacheextras.camel.component.virtualbox.template.ConsoleProgressCallback;
import org.apacheextras.camel.component.virtualbox.template.ProgressListener;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;
import org.virtualbox_4_2.IConsole;
import org.virtualbox_4_2.IMachine;
import org.virtualbox_4_2.IProgress;

import static org.apacheextras.camel.component.virtualbox.command.MachineAwareVirtualBoxCommand.resolveMachineId;
import static org.apacheextras.camel.component.virtualbox.command.NoReturnValue.noValue;

public class PowerDownCommandHandler extends ProgressingVirtualBoxCommandHandler<PowerDownCommand, NoReturnValue> {

    public PowerDownCommandHandler(VirtualBoxTemplate virtualBoxTemplate, ProgressListener progressListener) {
        super(virtualBoxTemplate, progressListener);
    }

    @Override
    public Class<PowerDownCommand> commandClass() {
        return PowerDownCommand.class;
    }

    @Override
    public NoReturnValue handle(PowerDownCommand command) {
        virtualBoxTemplate.execute(command.machineName(), new ConsoleProgressCallback() {
            @Override
            public IProgress execute(IMachine machine, IConsole console) {
                return console.powerDown();
            }
        }, progressListener);
        return noValue();
    }

    @Override
    public PowerDownCommand resolveCommand(Exchange exchange, String defaultMachineId) {
        return new PowerDownCommand(resolveMachineId(exchange, defaultMachineId));
    }

}
