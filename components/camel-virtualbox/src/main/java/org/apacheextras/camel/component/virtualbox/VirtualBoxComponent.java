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
package org.apacheextras.camel.component.virtualbox;

import org.apache.camel.impl.DefaultComponent;
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandlersManager;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxManagerFactory;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;

import java.util.Map;

public class VirtualBoxComponent extends DefaultComponent {

    private VirtualBoxTemplate virtualBoxTemplate;

    private VirtualBoxCommandHandlersManager commandHandlersManager;

    private String machineId;

    private Class<? extends VirtualBoxManagerFactory> vboxManagerFactoryClass;

    private String url;

    private String username;

    private String password;

    @Override
    protected VirtualBoxEndpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        String resolvedMachineId = machineId = (machineId != null ? machineId : remaining);
        return new VirtualBoxEndpoint(uri, this, virtualBoxTemplate, commandHandlersManager,
                resolvedMachineId, vboxManagerFactoryClass, url, username, password);
    }


    public VirtualBoxTemplate getVirtualBoxTemplate() {
        return virtualBoxTemplate;
    }

    public void setVirtualBoxTemplate(VirtualBoxTemplate virtualBoxTemplate) {
        this.virtualBoxTemplate = virtualBoxTemplate;
    }

    public VirtualBoxCommandHandlersManager getCommandHandlersManager() {
        return commandHandlersManager;
    }

    public void setCommandHandlersManager(VirtualBoxCommandHandlersManager commandHandlersManager) {
        this.commandHandlersManager = commandHandlersManager;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public Class<? extends VirtualBoxManagerFactory> getVboxManagerFactoryClass() {
        return vboxManagerFactoryClass;
    }

    public void setVboxManagerFactoryClass(Class<? extends VirtualBoxManagerFactory> vboxManagerFactoryClass) {
        this.vboxManagerFactoryClass = vboxManagerFactoryClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
