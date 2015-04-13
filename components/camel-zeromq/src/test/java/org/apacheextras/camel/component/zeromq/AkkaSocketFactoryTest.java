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

import org.junit.Test;
import org.zeromq.ZMQ.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AkkaSocketFactoryTest {

    @Test(expected = ZeromqException.class)
    public void publishIsProducer() {
        new AkkaSocketFactory(0, 0).createConsumerSocket(null, ZeromqSocketType.PUBLISH);
    }

    @Test(expected = ZeromqException.class)
    public void pullIsConsumer() {
        new AkkaSocketFactory(0, 0).createProducerSocket(null, ZeromqSocketType.PULL);
    }

    @Test(expected = ZeromqException.class)
    public void pushIsProducer() {
        new AkkaSocketFactory(0, 0).createConsumerSocket(null, ZeromqSocketType.PUSH);
    }

    @Test(expected = ZeromqException.class)
    public void subscribeIsConsumer() {
        new AkkaSocketFactory(0, 0).createProducerSocket(null, ZeromqSocketType.SUBSCRIBE);
    }

    @Test
    public void testOptionsApplied() {
        Socket socket = mock(Socket.class);
        new AkkaSocketFactory(100, 200).applySocketOptions(socket);
        verify(socket).setHWM(100);
        verify(socket).setLinger(200);

        socket = mock(Socket.class);
        new AkkaSocketFactory(100, -1).applySocketOptions(socket);
        verify(socket).setHWM(100);

        socket = mock(Socket.class);
        new AkkaSocketFactory(-1, 200).applySocketOptions(socket);
        verify(socket).setLinger(200);
    }

}
