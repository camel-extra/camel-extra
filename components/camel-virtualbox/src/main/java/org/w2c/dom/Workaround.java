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
package org.w2c.dom;

/**
 * This interface is an workaround around the fact that VirtualBox OSGI manifest requires 'org.w2c.dom' package
 * while it is impossible to find one almost anywhere. The workaround around this issue is to make camel-virtualbox
 * exports the 'org.w2c.dom' package itself.
 *
 * 'org.w2c.dom' workaround is explained here -
 * https://code.google.com/a/apache-extras.org/p/camel-extra/issues/detail?id=53
 */
public interface Workaround {
}