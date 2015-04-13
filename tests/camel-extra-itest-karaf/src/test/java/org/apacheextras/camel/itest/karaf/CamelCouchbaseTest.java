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

import org.apache.karaf.features.Feature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.junit.PaxExam;

@RunWith(PaxExam.class)
public class CamelCouchbaseTest extends AbstractFeatureTest {

  @Test
  public void shouldInstallFeature() {
    assertIsFeatureInstalled();
  }

  @Test
  public void shouldInstallFeatureOnValidKaraf() {
    assertTrue(getKarafFeatureUrl().toString().contains(KARAF_VERSION));
  }

  @Test
  public void shouldUseProperFeatureName() {
    Feature feature = getFeature(fullComponentName());
    assertEquals(fullComponentName(), feature.getName());
  }
}
