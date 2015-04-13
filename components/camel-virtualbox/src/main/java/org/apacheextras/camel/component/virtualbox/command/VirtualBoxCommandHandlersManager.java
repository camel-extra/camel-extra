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

import java.util.HashMap;
import java.util.Map;

public class VirtualBoxCommandHandlersManager {

    private final Map<Class<?>, VirtualBoxCommandHandler<?,?>> commandHandlers;

    public VirtualBoxCommandHandlersManager(Iterable<VirtualBoxCommandHandler<?,?>> commandHandlers) {
        this.commandHandlers = new HashMap<Class<?>, VirtualBoxCommandHandler<?,?>>();
        for (VirtualBoxCommandHandler<?,?> commandHandler : commandHandlers) {
            this.commandHandlers.put(commandHandler.commandClass(), commandHandler);
        }
    }

    public <C extends VirtualBoxCommand<R>, R> R handleCommand(C command) {
        @SuppressWarnings("unchecked")
        VirtualBoxCommandHandler<C,R> handler = (VirtualBoxCommandHandler<C, R>) resolveCommandHandler(command.getClass());
        return handler.handle(command);
    }

    public <T> T handleCommand(Exchange exchange, String defaultMachineId) {
        Object body = exchange.getIn().getBody();
        if (VirtualBoxCommand.class.isAssignableFrom(body.getClass())) {
            @SuppressWarnings("unchecked")
            VirtualBoxCommand<T> command = (VirtualBoxCommand) body;
            return handleCommand(command);
        } else {
            String commandName = exchange.getIn().getBody(String.class);
            String commandClassName = getClass().getPackage().getName() + ".handlers." + commandName + "Command";
            Class<?> commandClass = exchange.getContext().getClassResolver().resolveClass(commandClassName);
            VirtualBoxCommandHandler<?, ?> handler = resolveCommandHandler(commandClass);
            @SuppressWarnings("unchecked")
            VirtualBoxCommand<T> command = (VirtualBoxCommand<T>) handler.resolveCommand(exchange, defaultMachineId);
            return handleCommand(command);
        }
    }

    private VirtualBoxCommandHandler<?,?> resolveCommandHandler(Class<?> commandClass) {
        VirtualBoxCommandHandler<?,?> commandHandler = commandHandlers.get(commandClass);
        if(commandHandler == null) {
            throw new NoHandlerRegisteredException(commandClass);
        }
        return commandHandler;
    }

}
