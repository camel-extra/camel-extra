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

import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Injector;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.util.ReflectionInjector;
import org.apacheextras.camel.component.virtualbox.command.CommandHandlersResolver;
import org.apacheextras.camel.component.virtualbox.command.StaticCommandHandlersResolver;
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandler;
import org.apacheextras.camel.component.virtualbox.command.VirtualBoxCommandHandlersManager;
import org.apacheextras.camel.component.virtualbox.template.EmptyProgressListener;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxManagerFactory;
import org.apacheextras.camel.component.virtualbox.template.VirtualBoxTemplate;
import org.apacheextras.camel.component.virtualbox.template.WebServiceVirtualBoxManagerFactory;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@UriEndpoint(scheme = "virtualbox", title = "VirtuaBox", syntax = "virtualbox:machine[?options]", consumerClass = VirtualBoxConsumer.class)
public class VirtualBoxEndpoint extends DefaultEndpoint {

    private static final Logger LOG = getLogger(VirtualBoxEndpoint.class);

    private static final Injector INJECTOR = new ReflectionInjector();

    private VirtualBoxCommandHandlersManager commandHandlersManager;

    private VirtualBoxCommandHandlersManager resolvedCommandHandlersManager;

    private VirtualBoxTemplate virtualBoxTemplate;

    private VirtualBoxTemplate resolvedVirtualBoxTemplate;

    private String machineId;

    private Class<? extends VirtualBoxManagerFactory> vboxManagerFactoryClass;

    private VirtualBoxManagerFactory resolvedVirtualBoxManagerFactory;

    private String url;

    private String username;

    private String password;

    public VirtualBoxEndpoint(String endpointUri, VirtualBoxComponent component, VirtualBoxTemplate virtualBoxTemplate, VirtualBoxCommandHandlersManager commandHandlersManager,
                              String machineId, Class<? extends VirtualBoxManagerFactory> vboxManagerFactoryClass, String url, String username, String password) {
        super(endpointUri, component);
        this.commandHandlersManager = commandHandlersManager;
        this.virtualBoxTemplate = virtualBoxTemplate;
        this.machineId = machineId;
        this.vboxManagerFactoryClass = vboxManagerFactoryClass;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        this.resolvedVirtualBoxManagerFactory = resolveVirtualBoxManagerFactory();
        this.resolvedVirtualBoxTemplate = resolveVirtualBoxTemplate();
        this.resolvedCommandHandlersManager = resolveCommandHandlersManager();
    }

    private VirtualBoxManagerFactory resolveVirtualBoxManagerFactory() {
        if (vboxManagerFactoryClass != null) {
            return INJECTOR.newInstance(vboxManagerFactoryClass);
        } else {
            LOG.debug("No vboxManagerFactoryClass specified - default {} with URL {} will be used.", WebServiceVirtualBoxManagerFactory.class, url);
            return new WebServiceVirtualBoxManagerFactory(url, username, password);
        }
    }

    private VirtualBoxTemplate resolveVirtualBoxTemplate() {
        if (virtualBoxTemplate != null) {
            return virtualBoxTemplate;
        } else {
            return new VirtualBoxTemplate(resolvedVirtualBoxManagerFactory);
        }
    }

    private VirtualBoxCommandHandlersManager resolveCommandHandlersManager() {
        if (commandHandlersManager != null) {
            return commandHandlersManager;
        } else {
            LOG.debug("Resolving available command handlers using {}", StaticCommandHandlersResolver.class);
            CommandHandlersResolver handlersResolver = new StaticCommandHandlersResolver(resolvedVirtualBoxTemplate, new EmptyProgressListener());
            Iterable<VirtualBoxCommandHandler<?, ?>> commandHandlers = handlersResolver.resolveCommandHandlers();
            return new VirtualBoxCommandHandlersManager(commandHandlers);
        }
    }

    @Override
    public VirtualBoxProducer createProducer() throws Exception {
        return new VirtualBoxProducer(this, resolvedCommandHandlersManager, machineId);
    }

    @Override
    public VirtualBoxConsumer createConsumer(Processor processor) throws Exception {
        return new VirtualBoxConsumer(this, processor, resolvedVirtualBoxTemplate, machineId);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public VirtualBoxComponent getComponent() {
        return (VirtualBoxComponent)super.getComponent();
    }

    public VirtualBoxCommandHandlersManager getCommandHandlersManager() {
        return commandHandlersManager;
    }

    public void setCommandHandlersManager(VirtualBoxCommandHandlersManager commandHandlersManager) {
        this.commandHandlersManager = commandHandlersManager;
    }

    public VirtualBoxCommandHandlersManager getResolvedCommandHandlersManager() {
        return resolvedCommandHandlersManager;
    }

    public void setResolvedCommandHandlersManager(VirtualBoxCommandHandlersManager resolvedCommandHandlersManager) {
        this.resolvedCommandHandlersManager = resolvedCommandHandlersManager;
    }

    public VirtualBoxTemplate getVirtualBoxTemplate() {
        return virtualBoxTemplate;
    }

    public void setVirtualBoxTemplate(VirtualBoxTemplate virtualBoxTemplate) {
        this.virtualBoxTemplate = virtualBoxTemplate;
    }

    public VirtualBoxTemplate getResolvedVirtualBoxTemplate() {
        return resolvedVirtualBoxTemplate;
    }

    public void setResolvedVirtualBoxTemplate(VirtualBoxTemplate resolvedVirtualBoxTemplate) {
        this.resolvedVirtualBoxTemplate = resolvedVirtualBoxTemplate;
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

    public VirtualBoxManagerFactory getResolvedVirtualBoxManagerFactory() {
        return resolvedVirtualBoxManagerFactory;
    }

    public void setResolvedVirtualBoxManagerFactory(VirtualBoxManagerFactory resolvedVirtualBoxManagerFactory) {
        this.resolvedVirtualBoxManagerFactory = resolvedVirtualBoxManagerFactory;
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
