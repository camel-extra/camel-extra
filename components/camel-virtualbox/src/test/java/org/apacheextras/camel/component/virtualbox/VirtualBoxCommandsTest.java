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
import org.virtualbox_4_2.ISnapshot;

import java.util.HashMap;
import java.util.Map;

import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.BIOS_SETTINGS;
import static org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory.CONSOLE;
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
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class VirtualBoxCommandsTest extends CamelTestSupport {

    // Fixtures

    String startTest = "direct:test";
    String mockEndpoint = "mock:test";

    String machine = "machine";

    MockEndpoint mock;

    // Route fixtures

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(startTest).
                        to("virtualbox:" + machine + "?vboxManagerFactoryClass=org.apacheextras.camel.component.virtualbox.MockVirtualBoxManagerFactory").
                        to(mockEndpoint);
            }
        };
    }

    // Fixtures setup

    @Override
    protected void doPostSetup() throws Exception {
        super.doPostSetup();
        mock = getMockEndpoint(mockEndpoint);
        MockVirtualBoxManagerFactory.reset();
    }

    // Tests

    @Test
    public void shouldGetState() throws InterruptedException {
        // Given
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put(HEADER_MACHINE, machine);
        mock.expectedBodiesReceived(VM_STATE);

        // When
        sendBody(startTest, "GetState", headers);

        // Then
        mock.assertIsSatisfied();
        assertEquals(machine, MACHINE_NAME_USED.getValue());
    }

    @Test
    public void shouldGetVersion() throws InterruptedException {
        // Given
        mock.expectedBodiesReceived(VBOX_VERSION);

        // When
        sendBody(startTest, "GetVersion");

        // Then
        mock.assertIsSatisfied();
    }

    @Test
    public void shouldPowerDown() throws InterruptedException {
        // Given
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put(HEADER_MACHINE, machine);
        mockLockedSession();

        // When
        sendBody(startTest, "PowerDown", headers);

        // Then
        verify(CONSOLE).powerDown();
        assertEquals(machine, MACHINE_NAME_USED.getValue());
    }

    @Test
    public void shouldRestoreCurrentSnapshot() throws InterruptedException {
        // Given
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put(HEADER_MACHINE, machine);
        mockLockedSession();

        // When
        sendBody(startTest, "RestoreCurrentSnapshot", headers);

        // Then
        verify(CONSOLE).restoreSnapshot(any(ISnapshot.class));
        verify(MACHINE).getCurrentSnapshot();
        assertEquals(machine, MACHINE_NAME_USED.getValue());
    }

    @Test
    public void shouldStartVm() throws InterruptedException {
        // Given
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put(HEADER_MACHINE, machine);

        // When
        sendBody(startTest, "StartVm", headers);

        // Then
        verify(VIRTUAL_BOX_MANAGER).startVm(eq(machine), anyString(), anyInt());
    }

    @Test
    public void shouldSetBiosSystemTimeOffset() throws InterruptedException {
        // Given
        long offset = 123;
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put(HEADER_OFFSET, offset);

        // When
        sendBody(startTest, "SetBiosSystemTimeOffset", headers);

        // Then
        verify(BIOS_SETTINGS).setTimeOffset(eq(offset));
    }

}