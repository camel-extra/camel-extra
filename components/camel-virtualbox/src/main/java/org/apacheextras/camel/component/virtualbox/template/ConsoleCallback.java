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

/**
 * Handler interface used to safely execute a block of code on the behalf of the {@link org.virtualbox_4_2.IConsole}.
 * Execution of the block can result in returning a value.
 *
 * @param <T> Type of returned value.
 */
public interface ConsoleCallback<T> {

    T execute(IMachine machine, IConsole console);

}
