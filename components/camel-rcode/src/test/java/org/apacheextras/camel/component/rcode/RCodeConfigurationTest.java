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

import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class RCodeConfigurationTest {

    RCodeConfiguration rCodeConfiguration;

    @Before
    public void setUp() {
        rCodeConfiguration = new RCodeConfiguration();
    }

    @Test
    public void testRCodeConfigurationByURI() throws URISyntaxException {
        URI uri = new URI("rcode://localhost:1234");
        rCodeConfiguration = new RCodeConfiguration(uri);
        assertNotNull(rCodeConfiguration);
    }

    @Test
    public void testRCodeConfigurationCopy() {
        RCodeConfiguration copy = rCodeConfiguration.copy();
        assertNotEquals(copy, rCodeConfiguration);
        assertEquals(copy.getBufferSize(), rCodeConfiguration.getBufferSize());
        assertEquals(copy.getHost(), rCodeConfiguration.getHost());
        assertEquals(copy.getPassword(), rCodeConfiguration.getPassword());
        assertEquals(copy.getPort(), rCodeConfiguration.getPort());
        assertEquals(copy.getUser(), rCodeConfiguration.getUser());
    }
}
