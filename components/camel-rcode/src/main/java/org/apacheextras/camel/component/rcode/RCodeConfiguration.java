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

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import org.apache.camel.RuntimeCamelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The RCodeConfiguration object contains all elements that can be configured on
 * an endpoint or component.
 */
public final class RCodeConfiguration implements Cloneable {

    /**
     * Add logger to provide some level of detailed output.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RCodeConfiguration.class);
    /**
     * The default rServe host set to <code>127.0.0.1</code>.
     */
    public static final String DEFAULT_RSERVE_HOST = getHostAddress();
    /**
     * The default rServe port set to <code>6311</code>.
     */
    public static final int DEFAULT_RSERVE_PORT = 6311;
    /**
     * Default buffer size set to <code>2MB</code> in bytes.
     */
    public static final long DEFAULT_BUFFER_SIZE = 1024 * 1024 * 2;
    /**
     * Lowest buffer size is 32KB.
     */
    public static final long LOWEST_BUFFER_SIZE = 32768;
    /**
     * Largest buffer size is 1GB.
     */
    public static final long LARGEST_BUFFER_SIZE = 1073741824;

    // Initialize the default host
    private String host = DEFAULT_RSERVE_HOST;
    // Initialize the default port
    private int port = DEFAULT_RSERVE_PORT;
    // Field for user
    private String user;
    // Field for password
    private String password;
    // Field defining the buffer size
    private long bufferSize = DEFAULT_BUFFER_SIZE;

    /**
     * Creates a new RCodeConfiguration object with default values. The default
     * values are:</br>
     * <ul>
     * <li>host = 127.0.0.1</li>
     * <li>port = 6311</li>
     * <li>bufferSize = 2MB</li>
     * </ul>
     */
    public RCodeConfiguration() {
    }

    /**
     * Creates ab RCodeConfiguration based on an URI parameter.
     *
     * @param uri URI
     */
    public RCodeConfiguration(URI uri) {
        // Configure the host based on the endpoint URI
        final String uriHost = uri.getHost();
        if (null != uriHost && !uriHost.trim().isEmpty()) {
            setHost(uriHost);
        }
        // Configure port based on endpoint URI
        final int uriPort = uri.getPort();
        if (-1 != uriPort) {
            setPort(uriPort);
        }
    }

    /**
     * Copies the existing RCodeConfiguration into a new object. The new object
     * is an actual clone of the original.
     *
     * @return RCodeConfiguration
     */
    public RCodeConfiguration copy() {
        try {
            return (RCodeConfiguration)clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeCamelException(ex);
        }
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the bufferSize
     */
    public long getBufferSize() {
        return bufferSize;
    }

    /**
     * @param bufferSize the bufferSize to set
     */
    public void setBufferSize(long bufferSize) {
        // Set the buffer size to it's limits in bytes
        // Note: The boundaries that can be processed are 32K and 1GB
        if (bufferSize < LOWEST_BUFFER_SIZE) {
            this.bufferSize = LOWEST_BUFFER_SIZE;
        } else if (bufferSize > LARGEST_BUFFER_SIZE) {
            this.bufferSize = LARGEST_BUFFER_SIZE;
        }
        this.bufferSize = bufferSize;
    }

    private static String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            final String default_host = "127.0.0.1";
            LOGGER.info("Could not retrieve host information. Setting default to '{}'.\n" + "Reason was: {}", default_host, e.getMessage());
            return default_host; // TURN_OFF_WARNINGS
        }
    }
}
