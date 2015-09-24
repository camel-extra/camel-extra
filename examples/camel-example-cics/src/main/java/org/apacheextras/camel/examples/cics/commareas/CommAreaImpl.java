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

public abstract class CommAreaImpl implements CommArea {

    public static final String ENCODING_CP1145 = "Cp1145";

    public static final String ENCODING_CP285 = "Cp285";

    public static final byte BYTE_SPACE = 64;

    protected byte[] getBytes(String source, String encoding) throws java.io.UnsupportedEncodingException {
        if (null != encoding) {
            return source.getBytes(encoding);
        }
        return source.getBytes();
    }

    protected void fillBytes(byte paramByte, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
            throws ArrayIndexOutOfBoundsException {
        int i = 1;
        paramArrayOfByte[paramInt1] = paramByte;
        while (i * 2 <= paramInt2) {
            System.arraycopy(paramArrayOfByte, paramInt1, paramArrayOfByte, paramInt1 + i, i);
            i *= 2;
        }
        System.arraycopy(paramArrayOfByte, paramInt1, paramArrayOfByte, paramInt1 + i, paramInt2 - i);
    }

    protected void marshallData(String data, byte[] byteCommArea, int start) {
        byte[] srcData;
        try {
            srcData = data.getBytes(ENCODING_CP1145);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Code page not supported:" + ENCODING_CP1145, e);
        }
        System.arraycopy(srcData, 0, byteCommArea, start, srcData.length);
    }

}
