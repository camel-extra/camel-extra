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

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apacheextras.camel.component.virtualbox.command.StaticCommandHandlersResolver;
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandler;
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandlersManager;
import org.apacheextras.camel.component.virtualbox.template.EmptyProgressListener;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxManagerFactory;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;
import org.apacheextras.camel.component.virtualbox.template.WebServiceVirtualBoxManagerFactory;

public class VirtualBoxEndpoint extends DefaultEndpoint {

    private final String machineId;

    private VirtualBoxCommandHandlersManager commandHandlersManager;

    private VirtualBoxCommandHandlersManager resolvedCommandHandlersManager;

    private String url;

    private String username;

    private String password;

    private Class<? extends VirtualBoxManagerFactory> vboxManagerFactoryClass;

    private VirtualBoxTemplate resolvedVirtualBoxTemplate;

    public VirtualBoxEndpoint(String endpointUri, VirtualBoxComponent component, String machineId, VirtualBoxCommandHandlersManager commandHandlersManager) {
        super(endpointUri, component);
        this.machineId = machineId;
        this.commandHandlersManager = commandHandlersManager;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        resolvedCommandHandlersManager = resolveCommandHandlersManager();
    }

    private VirtualBoxCommandHandlersManager resolveCommandHandlersManager() {
        if (getComponent().getCommandHandlersManager() != null) {
            return getComponent().getCommandHandlersManager();
        } else if (commandHandlersManager != null) {
            return commandHandlersManager;
        } else {
            VirtualBoxManagerFactory virtualBoxManagerFactory = null;
            if (vboxManagerFactoryClass != null) {
                try {
                    virtualBoxManagerFactory = vboxManagerFactoryClass.newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else {
                virtualBoxManagerFactory = new WebServiceVirtualBoxManagerFactory(url, username, password);
            }
            resolvedVirtualBoxTemplate = new VirtualBoxTemplate(virtualBoxManagerFactory);
            Iterable<VirtualBoxCommandHandler<?, ?>> listeners =
                    new StaticCommandHandlersResolver(resolvedVirtualBoxTemplate, new EmptyProgressListener()).resolveCommandHandlers();
            return new VirtualBoxCommandHandlersManager(listeners);
        }
    }

    @Override
    public Producer createProducer() throws Exception {
        return new VirtualBoxProducer(resolvedCommandHandlersManager, this, machineId);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        return new VirtualBoxConsumer(this, processor, resolvedVirtualBoxTemplate, machineId);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public VirtualBoxCommandHandlersManager getCommandHandlersManager() {
        return commandHandlersManager;
    }

    public void setCommandHandlersManager(VirtualBoxCommandHandlersManager commandHandlersManager) {
        this.commandHandlersManager = commandHandlersManager;
    }

    @Override
    public VirtualBoxComponent getComponent() {
        return (VirtualBoxComponent) super.getComponent();
    }

    public VirtualBoxCommandHandlersManager getResolvedCommandHandlersManager() {
        return resolvedCommandHandlersManager;
    }

    public void setResolvedCommandHandlersManager(VirtualBoxCommandHandlersManager resolvedCommandHandlersManager) {
        this.resolvedCommandHandlersManager = resolvedCommandHandlersManager;
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

    public Class<? extends VirtualBoxManagerFactory> getVboxManagerFactoryClass() {
        return vboxManagerFactoryClass;
    }

    public void setVboxManagerFactoryClass(Class<? extends VirtualBoxManagerFactory> vboxManagerFactoryClass) {
        this.vboxManagerFactoryClass = vboxManagerFactoryClass;
    }

}
