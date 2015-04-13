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
package org.apacheextras.camel.component.rcode;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 * Creates {@link RConnection}s.
 */
public class RConnectionFactory {

    protected RConnectionFactory() {
    }

    /**
     * Returns the RConnection instance.
     *
     * @return RConnectionFactory
     */
    public static RConnectionFactory getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * Creates a {@link RConnection}.
     *
     * @param rCodeConfiguration the configuration.
     * @return the new created connection.
     * @throws RserveException
     * @see RConnection#RConnection(String, int)
     */
    public RConnection createConnection(RCodeConfiguration rCodeConfiguration) throws RserveException {
        return new RConnection(rCodeConfiguration.getHost(), rCodeConfiguration.getPort());
    }

    /**
     * Contains the instance of the RConnection factory.
     */
    protected static class SingletonHolder {
        public static RConnectionFactory instance = new RConnectionFactory(); // TURN_OFF_WARNINGS
    }
}
