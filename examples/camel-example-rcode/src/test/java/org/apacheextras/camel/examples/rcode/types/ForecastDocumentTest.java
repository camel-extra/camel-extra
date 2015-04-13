/**
 * ************************************************************************************
 * https://camel-extra.github.io
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 * http://www.gnu.org/licenses/gpl-2.0-standalone.html
 **************************************************************************************
 */
package org.apacheextras.camel.examples.rcode.types;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ForecastDocumentTest {

  @Test
  public void createInitializedForecastDocument() {
    final ForecastDocument forecastDocument = new ForecastDocument(
        "Test ForecastDocument",
        "tmp/path",
        new Date(),
        new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}
    );
    assertEquals("Test ForecastDocument", forecastDocument.getTitle());
    assertEquals("tmp/path", forecastDocument.getPath());
    assertNotNull(forecastDocument.getDate());
    assertArrayEquals(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, forecastDocument.getJpegGraph());
  }

}
