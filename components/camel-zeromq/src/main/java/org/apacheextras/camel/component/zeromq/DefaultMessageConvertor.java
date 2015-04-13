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

public class DefaultMessageConvertor implements MessageConverter {

    @Override
    public byte[] convert(Exchange arg0) {
        Object msg = arg0.getIn().getBody();
        if (msg instanceof String) {
            return ((String) msg).getBytes();
        } else if (msg instanceof byte[]) {
            return (byte[]) msg;
        } else {
            return msg.toString().getBytes();
        }
    }
}
