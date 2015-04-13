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
package org.apacheextras.camel.component.virtualbox.command;

import org.apacheextras.camel.component.virtualbox.command.handlers.GetStateCommandHandler;
import org.apacheextras.camel.component.virtualbox.command.handlers.GetVersionCommandHandler;
import org.apacheextras.camel.component.virtualbox.command.handlers.PowerDownCommandHandler;
import org.apacheextras.camel.component.virtualbox.command.handlers.RestoreCurrentSnapshotCommandHandler;
import org.apacheextras.camel.component.virtualbox.command.handlers.SetBiosSystemTimeOffsetCommandHandler;
import org.apacheextras.camel.component.virtualbox.command.handlers.StartVmCommandHandler;
import org.apacheextras.camel.component.virtualbox.template.ProgressListener;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;

import java.util.Arrays;

public class StaticCommandHandlersResolver implements CommandHandlersResolver {

    private final VirtualBoxTemplate virtualBoxTemplate;
    private final ProgressListener progressListener;

    public StaticCommandHandlersResolver(VirtualBoxTemplate virtualBoxTemplate, ProgressListener progressListener) {
        this.virtualBoxTemplate = virtualBoxTemplate;
        this.progressListener = progressListener;
    }

    @Override
    public Iterable<VirtualBoxCommandHandler<?, ?>> resolveCommandHandlers() {
        return Arrays.asList(
                new GetStateCommandHandler(virtualBoxTemplate),
                new GetVersionCommandHandler(virtualBoxTemplate),
                new PowerDownCommandHandler(virtualBoxTemplate, progressListener),
                new RestoreCurrentSnapshotCommandHandler(virtualBoxTemplate, progressListener),
                new SetBiosSystemTimeOffsetCommandHandler(virtualBoxTemplate),
                new StartVmCommandHandler(virtualBoxTemplate)
        );
    }

}
