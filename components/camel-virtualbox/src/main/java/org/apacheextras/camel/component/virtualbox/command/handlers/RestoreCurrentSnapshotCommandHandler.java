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
import org.apacheextras.camel.component.virtualbox.command.ProgressingVirtualBoxCommandHandler;
import org.apacheextras.camel.component.virtualbox.template.ConsoleProgressCallback;
import org.apacheextras.camel.component.virtualbox.template.ProgressListener;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;
import org.virtualbox_4_2.IConsole;
import org.virtualbox_4_2.IMachine;
import org.virtualbox_4_2.IProgress;
import org.virtualbox_4_2.LockType;

import static org.apacheextras.camel.component.virtualbox.command.MachineAwareVirtualBoxCommand.HEADER_MACHINE;
import static org.apacheextras.camel.component.virtualbox.command.NoReturnValue.noValue;

public class RestoreCurrentSnapshotCommandHandler extends ProgressingVirtualBoxCommandHandler<RestoreCurrentSnapshotCommand, NoReturnValue> {

    public RestoreCurrentSnapshotCommandHandler(VirtualBoxTemplate virtualBoxTemplate, ProgressListener progressListener) {
        super(virtualBoxTemplate, progressListener);
    }

    @Override
    public Class<RestoreCurrentSnapshotCommand> commandClass() {
        return RestoreCurrentSnapshotCommand.class;
    }

    @Override
    public NoReturnValue handle(RestoreCurrentSnapshotCommand command) {
        virtualBoxTemplate.execute(command.machineName(), new ConsoleProgressCallback() {
            @Override
            public IProgress execute(IMachine machine, IConsole console) {
                return console.restoreSnapshot(machine.getCurrentSnapshot());
            }
        }, progressListener, LockType.Write);
        return noValue();
    }

    @Override
    public RestoreCurrentSnapshotCommand resolveCommand(Exchange exchange) {
        return new RestoreCurrentSnapshotCommand(exchange.getIn().getHeader(HEADER_MACHINE, String.class));
    }

}
