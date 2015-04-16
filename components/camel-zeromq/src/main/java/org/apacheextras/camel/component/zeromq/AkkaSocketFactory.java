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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class AkkaSocketFactory implements SocketFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(AkkaSocketFactory.class);

    private final long highWaterMark;
    private final long linger;

    public AkkaSocketFactory(long highWaterMark, long linger) {
        this.highWaterMark = highWaterMark;
        this.linger = linger;
    }

    void applySocketOptions(Socket socket) {
        if (highWaterMark >= 0) {
            socket.setHWM(highWaterMark);
        }
        if (linger >= 0) {
            socket.setLinger(linger);
        }
    }

    @Override
    public Socket createConsumerSocket(Context context, ZeromqSocketType socketType) {
        LOGGER.debug("Creating consumer socket [{}]", socketType);
        Socket socket;
        switch (socketType) {
        default:
            throw new ZeromqException("Unsupported socket type for consumer: " + this);
        case ROUTER:
            socket = context.socket(ZMQ.ROUTER);
            break;
        case SUBSCRIBE:
            socket = context.socket(ZMQ.SUB);
            break;
        case PULL:
            socket = context.socket(ZMQ.PULL);
            break;
        }
        applySocketOptions(socket);
        return socket;
    }

    @Override
    public Socket createProducerSocket(Context context, ZeromqSocketType socketType) {
        LOGGER.debug("Creating producer socket [{}]", socketType);
        Socket socket;
        switch (socketType) {
        case DEALER:
            socket = context.socket(ZMQ.DEALER);
            break;
        case PUBLISH:
            socket = context.socket(ZMQ.PUB);
            break;
        case PUSH:
            socket = context.socket(ZMQ.PUSH);
            break;
        default:
            throw new ZeromqException("Unsupported socket type for producer: " + socketType);
        }
        applySocketOptions(socket);
        return socket;
    }

}
