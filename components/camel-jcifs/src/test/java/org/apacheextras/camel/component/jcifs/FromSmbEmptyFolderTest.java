/**
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
 */
package org.apacheextras.camel.component.jcifs;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.easymock.EasyMock.*;

/**
 * Unit test to consume from an empty smb folder
 */
public class FromSmbEmptyFolderTest extends BaseSmbTestSupport {

  private SmbFile rootDir;

  @EndpointInject(uri = "mock:result")
  private MockEndpoint mockResult;

  @Override
  protected void setUpFileSystem() throws Exception {
    rootDir = createMock(SmbFile.class);

    expect(rootDir.listFiles()).andThrow(new NullPointerException("NullPointerException"));
    smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/", rootDir);
  }

  private String getSmbBaseUrl() {
    return "smb://localhost/" + getShare() + "/camel/" + getClass().getSimpleName();
  }

  private String getSmbUrl() throws UnsupportedEncodingException {
    final String uri = "smb://" + getDomain() + ";" + getUsername() + "@localhost/"
        + getShare() + "/camel/" + getClass().getSimpleName()
        + "?delete=true"
        + "&include=test.txt"
        + "&localWorkDirectory=/temp/folder/"
        + "&password=" + getPassword()
        + "&sortBy=file:name;file:modified";
    return uri;
  }

  @Test
  public void testPollEmptyFolder() throws Exception {

    replay(rootDir);

    mockResult.expectedMessageCount(0);
    assertMockEndpointsSatisfied();

    // Sleep an extra second to make sure we capture the rename
    Thread.sleep(1000);

    verify(rootDir);
  }

  protected RouteBuilder createRouteBuilder() {
    return new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        from(getSmbUrl())
            .to("mock:result");
      }
    };
  }
}
