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

import org.apacheextras.camel.component.virtualbox.template.VirtualBoxManagerFactory;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.virtualbox_4_2.IBIOSSettings;
import org.virtualbox_4_2.IConsole;
import org.virtualbox_4_2.IEventListener;
import org.virtualbox_4_2.IEventSource;
import org.virtualbox_4_2.IMachine;
import org.virtualbox_4_2.IProgress;
import org.virtualbox_4_2.ISession;
import org.virtualbox_4_2.ISnapshot;
import org.virtualbox_4_2.IVirtualBox;
import org.virtualbox_4_2.MachineState;
import org.virtualbox_4_2.VirtualBoxManager;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.virtualbox_4_2.SessionState.Locked;

public class MockVirtualBoxManagerFactory implements VirtualBoxManagerFactory {

    // Return values

    static final String VBOX_VERSION = "VBOX_VERSION";
    static final MachineState VM_STATE = MachineState.Running;

    // Mocked collaborators

    static final IEventSource EVENT_SOURCE = mock(IEventSource.class, RETURNS_DEEP_STUBS);
    static final IConsole CONSOLE = mock(IConsole.class);
    static final IProgress PROGRESS = mock(IProgress.class);
    static final ISession SESSION = mock(ISession.class);
    static final IMachine MACHINE = mock(IMachine.class);
    static final IBIOSSettings BIOS_SETTINGS = mock(IBIOSSettings.class);
    static ArgumentCaptor<String> MACHINE_NAME_USED = ArgumentCaptor.forClass(String.class);
    static final IVirtualBox VIRTUAL_BOX = mock(IVirtualBox.class);
    static final VirtualBoxManager VIRTUAL_BOX_MANAGER = mock(VirtualBoxManager.class);

    // Mock creation

    @Override
    public VirtualBoxManager create() {
        when(MACHINE.getState()).thenReturn(VM_STATE);
        when(MACHINE.getBIOSSettings()).thenReturn(BIOS_SETTINGS);

        when(SESSION.getConsole()).thenReturn(CONSOLE);

        when(CONSOLE.powerDown()).thenReturn(PROGRESS);
        when(CONSOLE.restoreSnapshot(any(ISnapshot.class))).thenReturn(PROGRESS);
        when(CONSOLE.getEventSource()).thenReturn(EVENT_SOURCE);

        when(PROGRESS.getPercent()).thenReturn(100L);

        when(VIRTUAL_BOX.getVersion()).thenReturn(VBOX_VERSION);
        when(VIRTUAL_BOX.findMachine(MACHINE_NAME_USED.capture())).thenReturn(MACHINE);

        when(VIRTUAL_BOX_MANAGER.getSessionObject()).thenReturn(SESSION);
        when(VIRTUAL_BOX_MANAGER.getVBox()).thenReturn(VIRTUAL_BOX);

        return VIRTUAL_BOX_MANAGER;
    }

    static void reset() {
        Mockito.reset(EVENT_SOURCE, CONSOLE, PROGRESS, SESSION, MACHINE, BIOS_SETTINGS, VIRTUAL_BOX, VIRTUAL_BOX_MANAGER);
        MACHINE_NAME_USED = ArgumentCaptor.forClass(String.class);
    }

    static void mockLockedSession() {
        when(SESSION.getState()).thenReturn(Locked);
    }

}
