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

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.virtualbox_4_2.IEvent;
import org.virtualbox_4_2.IEventListener;
import org.virtualbox_4_2.ISnapshot;

import java.util.HashMap;
import java.util.Map;

import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.BIOS_SETTINGS;
import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.CONSOLE;
import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.EVENT_SOURCE;
import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.MACHINE;
import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.MACHINE_NAME_USED;
import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.VBOX_VERSION;
import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.VIRTUAL_BOX_MANAGER;
import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.VM_STATE;
import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.mockLockedSession;
import static org.apacheextras.camel.component.virtualbox.command.MachineAwareVirtualBoxCommand.HEADER_MACHINE;
import static org.apacheextras.camel.component.virtualbox.command.handlers.SetBiosSystemTimeOffsetCommand.HEADER_OFFSET;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class VirtualBoxConsumerTest extends CamelTestSupport {

    // Fixtures

    String mockEndpoint = "mock:test";

    String machine = "machine";

    MockEndpoint mock;

    // Route fixtures

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("virtualbox:" + machine + "?vboxManagerFactoryClass=org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory").to(mockEndpoint);
            }
        };
    }

    // Fixtures setup

    @Override
    protected void doPreSetup() throws Exception {
        MockVirtualBoxManagerFactory.reset();
        mockLockedSession();
    }

    @Override
    protected void doPostSetup() throws Exception {
        super.doPostSetup();
        mock = getMockEndpoint(mockEndpoint);
    }

    // Tests

    @Test
    public void shouldConsumeEvents() throws InterruptedException {
        // Given
        mock.expectedMinimumMessageCount(1);

        // When
        mock.assertIsSatisfied();

        // Then
        verify(EVENT_SOURCE).getEvent(any(IEventListener.class), anyInt());
        verify(EVENT_SOURCE).eventProcessed(any(IEventListener.class), any(IEvent.class));
    }

}
