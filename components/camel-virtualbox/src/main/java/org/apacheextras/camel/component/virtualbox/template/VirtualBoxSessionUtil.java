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

import org.virtualbox_4_2.IMachine;
import org.virtualbox_4_2.ISession;
import org.virtualbox_4_2.LockType;
import org.virtualbox_4_2.SessionState;
import org.virtualbox_4_2.VBoxException;
import org.virtualbox_4_2.VirtualBoxManager;

public final class VirtualBoxSessionUtil {

    private VirtualBoxSessionUtil() {
    }

    public static void closeSession(VirtualBoxManager virtualBoxManager, ISession session) {
        try {
            if (session != null && session.getState() == SessionState.Locked) {
                session.unlockMachine();
            }
        } finally {
            virtualBoxManager.disconnect();
        }
    }

    public static ISession acquireLockedSession(VirtualBoxManager virtualBoxManager, IMachine machine, LockType lockType) {
        ISession session = virtualBoxManager.getSessionObject();
        do {
            try {
                machine.lockMachine(session, lockType);
            } catch (VBoxException e) {
                if (!e.getMessage().contains("is already locked for a session (or being unlocked) (0x80BB0007)")) {
                    throw e;
                }
            }
        } while (session.getState() != SessionState.Locked);
        return session;
    }

}
