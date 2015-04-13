/**************************************************************************************
 https://camel-extra.github.io

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
package org.apacheextras.camel.component.virtualbox.template;

import org.virtualbox_4_2.IConsole;
import org.virtualbox_4_2.IMachine;
import org.virtualbox_4_2.IProgress;
import org.virtualbox_4_2.ISession;
import org.virtualbox_4_2.IVirtualBox;
import org.virtualbox_4_2.LockType;
import org.virtualbox_4_2.VirtualBoxManager;

import static org.apacheextras.camel.component.virtualbox.template.VirtualBoxSessionUtil.acquireLockedSession;
import static org.apacheextras.camel.component.virtualbox.template.VirtualBoxSessionUtil.closeSession;

public class VirtualBoxTemplate {

    private final VirtualBoxManagerFactory virtualBoxManagerFactory;

    public VirtualBoxTemplate(VirtualBoxManagerFactory virtualBoxManagerFactory) {
        this.virtualBoxManagerFactory = virtualBoxManagerFactory;
    }

    public <T> T execute(VirtualBoxManagerCallback<T> virtualBoxManagerCallback) {
        VirtualBoxManager virtualBoxManager = virtualBoxManagerFactory.create();
        try {
            return virtualBoxManagerCallback.execute(virtualBoxManager);
        } finally {
            virtualBoxManager.disconnect();
        }
    }

    public <T> T execute(VirtualBoxCallback<T> virtualBoxCallback) {
        VirtualBoxManager virtualBoxManager = virtualBoxManagerFactory.create();
        try {
            IVirtualBox virtualBox = virtualBoxManager.getVBox();
            return virtualBoxCallback.execute(virtualBox);
        } finally {
            virtualBoxManager.disconnect();
        }
    }

    public <T> T execute(String machineName, boolean mutable, MachineCallback<T> machineCallback) {
        VirtualBoxManager virtualBoxManager = virtualBoxManagerFactory.create();
        ISession session = null;
        try {
            IVirtualBox virtualBox = virtualBoxManager.getVBox();
            IMachine machine = virtualBox.findMachine(machineName);
            if (mutable) {
                session = acquireLockedSession(virtualBoxManager, machine, LockType.Shared);
                machine = session.getMachine();
            }
            return machineCallback.execute(machine);
        } finally {
            closeSession(virtualBoxManager, session);
        }
    }

    public <T> T execute(String machineName, MachineCallback<T> machineCallback) {
        return execute(machineName, false, machineCallback);
    }

    public void execute(String machineName, ConsoleProgressCallback consoleProgressCallback, ProgressListener progressListener, LockType lockType) {
        VirtualBoxManager virtualBoxManager = virtualBoxManagerFactory.create();
        ISession session = null;
        try {
            IMachine machine = virtualBoxManager.getVBox().findMachine(machineName);
            session = acquireLockedSession(virtualBoxManager, machine, lockType);
            IConsole console = session.getConsole();
            IProgress progress = consoleProgressCallback.execute(machine, console);
            long percentageProgress = 0;
            do {
                if (progressListener != null) {
                    progressListener.onPercentageProgress(percentageProgress);
                }
                Thread.sleep(100);
            } while ((percentageProgress = progress.getPercent()) < 100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            closeSession(virtualBoxManager, session);
        }
    }

    public void execute(String machineName, ConsoleProgressCallback consoleProgressCallback, LockType lockType) {
        execute(machineName, consoleProgressCallback, null, lockType);
    }

    public void execute(String machineName, ConsoleProgressCallback consoleProgressCallback, ProgressListener progressListener) {
        execute(machineName, consoleProgressCallback, progressListener, LockType.Shared);
    }

    public <T> T execute(String machineName, ConsoleCallback<T> consoleCallback, LockType lockType) {
        VirtualBoxManager virtualBoxManager = virtualBoxManagerFactory.create();
        ISession session = null;
        try {
            IMachine machine = virtualBoxManager.getVBox().findMachine(machineName);
            session = acquireLockedSession(virtualBoxManager, machine, lockType);
            IConsole console = session.getConsole();
            return consoleCallback.execute(machine, console);
        } finally {
            closeSession(virtualBoxManager, session);
        }
    }

    public <T> T execute(String machineName, ConsoleCallback<T> consoleCallback) {
        return execute(machineName, consoleCallback, LockType.Shared);
    }

    public <T> DetachedSessionWithReturnValue<T> executeAndDetach(String machineName, ConsoleCallback<T> consoleCallback, LockType lockType) {
        VirtualBoxManager virtualBoxManager = virtualBoxManagerFactory.create();
        ISession session = null;
        try {
            IMachine machine = virtualBoxManager.getVBox().findMachine(machineName);
            session = acquireLockedSession(virtualBoxManager, machine, lockType);
            IConsole console = session.getConsole();
            T operationValue = consoleCallback.execute(machine, console);
            return new DetachedSessionWithReturnValue<T>(virtualBoxManager, session, operationValue);
        } catch (RuntimeException e) {
            closeSession(virtualBoxManager, session);
            throw e;
        }
    }

    public <T> DetachedSessionWithReturnValue<T> executeAndDetach(String machineName, ConsoleCallback<T> consoleCallback) {
        return executeAndDetach(machineName, consoleCallback, LockType.Shared);
    }

    public <T> T executeDetached(DetachedSession detachedSession, String machineName, ConsoleCallback<T> consoleCallback) {
        try {
            VirtualBoxManager virtualBoxManager = detachedSession.virtualBoxManager();
            ISession session = detachedSession.session();
            IVirtualBox virtualBox = virtualBoxManager.getVBox();
            IMachine machine = virtualBox.findMachine(machineName);
            IConsole console = session.getConsole();
            return consoleCallback.execute(machine, console);
        } catch (RuntimeException e) {
            detachedSession.close();
            throw e;
        }
    }

}