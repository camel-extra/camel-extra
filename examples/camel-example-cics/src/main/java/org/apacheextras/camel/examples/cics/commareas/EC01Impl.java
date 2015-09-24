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
package org.apacheextras.camel.examples.cics.commareas;

import java.io.UnsupportedEncodingException;

public class EC01Impl extends CommAreaImpl implements EC01 {

    private static final int COMMAREA_SIZE = 18;

    private final byte[] byteCommArea = new byte[COMMAREA_SIZE];

    private final String commArea = "";

    @Override
    public String getData() {
        return this.commArea;
    }

    @Override
    public byte[] getDataBuffer() {
        try {
            System.arraycopy(getBytes(this.commArea, ENCODING_CP1145), 0, byteCommArea, 0,
                    Math.min(byteCommArea.length, this.commArea.length()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return this.byteCommArea;
    }

}