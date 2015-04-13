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

import org.apache.camel.Exchange;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;

public abstract class VirtualBoxCommandHandler<C extends VirtualBoxCommand<?>, R> {

    protected final VirtualBoxTemplate virtualBoxTemplate;

    protected VirtualBoxCommandHandler(VirtualBoxTemplate virtualBoxTemplate) {
        this.virtualBoxTemplate = virtualBoxTemplate;
    }

    public abstract Class<C> commandClass();

    /**
     * Executes a command against the VirtualBox installation.
     *
     * @param command VirtualBox command to be executed. Cannot be null.
     * @return Result of the execution or {@link NoReturnValue} to indicate that operation do not return meaningful
     * value. Null can be returned.
     */
    public abstract R handle(C command);

    public abstract C resolveCommand(Exchange exchange, String defaultMachineId);

}
