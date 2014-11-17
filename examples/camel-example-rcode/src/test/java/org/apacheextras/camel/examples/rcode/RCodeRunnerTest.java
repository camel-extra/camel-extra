/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

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
package org.apacheextras.camel.examples.rcode;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;

public class RCodeRunnerTest {

  @Test
  public void testMainHelp() {
    final String[] args = new String[]{"h"};
    try {
      RCodeRunner.main(args);
    } catch (Exception e) {
      fail("Unable to call the help command:\n" + e.getMessage());
    }
  }

  @Test
  @Ignore
  public void testMainSourceTarget() {
    String sourceOption = new StringBuilder()
        .append("--source")
        .toString();
    String source = new StringBuilder()
        .append(RCodeRunner.class.getResource("builder/data/").toString())
        .toString();
    String targetOption = new StringBuilder()
        .append("--target")
        .toString();
    String target = new StringBuilder()
        .append(RCodeRunner.class.getResource("builder/output/testout").toString())
            .toString();
    final String[] args = new String[]{
        sourceOption,
        source.replaceFirst("file:", ""),
        targetOption,
        target.replaceFirst("file:", "")
    };
    for (String arg : args) {
      System.out.println(arg);
    }
    try {
      RCodeRunner.main(args);
    } catch (Exception e) {
      fail("Unable to process source and target:\n" + e.getMessage());
    }
  }

}
