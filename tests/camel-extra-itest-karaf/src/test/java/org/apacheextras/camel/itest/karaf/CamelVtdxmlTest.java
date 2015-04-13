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
package org.apacheextras.camel.itest.karaf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.junit.PaxExam;

@RunWith(PaxExam.class)
public class CamelVtdxmlTest extends AbstractFeatureTest {

  public static final String COMPONENT = extractName(CamelVtdxmlTest.class);

  @Test
  public void test() {
    assertTrue(getKarafFeatureUrl().toString().contains(KARAF_VERSION));
    assertEquals("camel-" + COMPONENT,
        getFeature(
            new StringBuilder()
                .append("camel-")
                .append(COMPONENT)
                .toString())
            .getName()
    );
    assertTrue(isInstalled(getFeature(
        new StringBuilder()
            .append("camel-")
            .append(COMPONENT)
            .toString()
    )));
  }
}
