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

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandlersManager;

import java.util.Map;

public class VirtualBoxComponent extends DefaultComponent {

    private VirtualBoxCommandHandlersManager commandHandlersManager;

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        return new VirtualBoxEndpoint(uri, this, remaining, commandHandlersManager);
    }

    public VirtualBoxCommandHandlersManager getCommandHandlersManager() {
        return commandHandlersManager;
    }

    public void setCommandHandlersManager(VirtualBoxCommandHandlersManager commandHandlersManager) {
        this.commandHandlersManager = commandHandlersManager;
    }

}
