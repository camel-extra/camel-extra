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
package org.apacheextras.camel.component.zeromq;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultMessageConvertorTest {

    @Mock
    private Exchange exg;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void byteArrayNoop() {

        Message msg = new DefaultMessage();
        byte[] bytes = new byte[14];
        msg.setBody(bytes);
        when(exg.getIn()).thenReturn(msg);

        assertEquals(bytes, new DefaultMessageConvertor().convert(exg));
    }

    @Test
    public void convertString() {

        Message msg = new DefaultMessage();
        msg.setBody("mymsg");
        when(exg.getIn()).thenReturn(msg);

        assertArrayEquals("mymsg".getBytes(), new DefaultMessageConvertor().convert(exg));
    }

    @Test
    public void objectToString() {

        Object obj = new Object();

        Message msg = new DefaultMessage();
        msg.setBody(obj);
        when(exg.getIn()).thenReturn(msg);

        assertArrayEquals(obj.toString().getBytes(), new DefaultMessageConvertor().convert(exg));
    }
}
