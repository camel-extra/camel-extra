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
package org.apacheextras.camel.component.jcifs;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileFilter;
import jcifs.smb.SmbFilenameFilter;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;

/**
 * Unit test to consume from an empty smb folder Verify that we catch NPE for
 * empty folders - see the following discussion for details:
 * http://camel-extra.1091541.n5.nabble.com/NPE-on-SmbOperations-td256.html
 */
public class FromSmbEmptyFolderTest extends BaseSmbTestSupport {

    private SmbFile rootDir;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint mockResult;

    @Override
    protected void setUpFileSystem() throws Exception {
        rootDir = new FakeSmbFile(getSmbBaseUrl() + "/");

        // expect(rootDir.listFiles());
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/", rootDir);
    }

    private String getSmbBaseUrl() {
        return "smb://localhost/" + getShare() + "/camel/" + getClass().getSimpleName();
    }

    private String getSmbUrl() throws UnsupportedEncodingException {
        final String uri = "smb://" + getDomain() + ";" + getUsername() + "@localhost/" + getShare() + "/camel/" + getClass().getSimpleName() + "?delete=true"
                           + "&include=test.txt" + "&localWorkDirectory=/temp/folder/" + "&password=" + getPassword() + "&sortBy=file:name;file:modified";
        return uri;
    }

    @Test
    public void testPollEmptyFolder() throws Exception {

        mockResult.expectedMessageCount(0);
        assertMockEndpointsSatisfied();

        // Sleep an extra second to make sure we capture the rename
        Thread.sleep(1000);

    }

    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(getSmbUrl()).to("mock:result");
            }
        };
    }

    class FakeSmbFile extends SmbFile {

        public FakeSmbFile(String url) throws MalformedURLException {
            super(url);
        }

        @Override
        public SmbFile[] listFiles(SmbFileFilter filter) throws SmbException {
            return null;
        }

        @Override
        public SmbFile[] listFiles(String wildcard) throws SmbException {
            return null;
        }

        @Override
        public SmbFile[] listFiles(SmbFilenameFilter filter) throws SmbException {
            return null;
        }

        public SmbFile[] listFiles() throws SmbException {
            // Create a fake NullPointerException that seems to come from
            // jcifs.smb.Dfs.resolve(Dfs.java:169)
            NullPointerException exception = new NullPointerException();

            final List<StackTraceElement> newStackTraceList = new ArrayList<StackTraceElement>();
            final StackTraceElement[] stackTrace = exception.getStackTrace();
            int i;
            // Remove FakeSmbFile from stacktrace
            for (i = 0; i < stackTrace.length; i++) {
                if (stackTrace[i].getClassName().contains("FakeSmbFile")) {
                    i++;
                    break;
                }
            }
            for (int j = i; j < stackTrace.length; j++) {
                newStackTraceList.add(stackTrace[j]);
            }
            // Add the JCIFS API stacktrace
            final StackTraceElement[] newStackTrace = new StackTraceElement[newStackTraceList.size() + 7];
            newStackTrace[0] = new StackTraceElement("jcifs.smb.Dfs", "resolve", "Dfs.java", 169);
            newStackTrace[1] = new StackTraceElement("jcifs.smb.SmbFile", "resolveDfs", "SmbFile.java", 671);
            newStackTrace[2] = new StackTraceElement("jcifs.smb.SmbFile", "send", "SmbFile.java", 773);
            newStackTrace[3] = new StackTraceElement("jcifs.smb.SmbFile", "doFindFirstNext", "SmbFile.java", 1986);
            newStackTrace[4] = new StackTraceElement("jcifs.smb.SmbFile", "doEnum", "SmbFile.java", 1738);
            newStackTrace[5] = new StackTraceElement("jcifs.smb.SmbFile", "listFiles", "SmbFile.java", 1715);
            newStackTrace[6] = new StackTraceElement("jcifs.smb.SmbFile", "listFiles", "SmbFile.java", 1648);

            int k = 7;
            for (Iterator<StackTraceElement> it = newStackTraceList.iterator(); it.hasNext(); k++) {
                final StackTraceElement element = (StackTraceElement)it.next();
                newStackTrace[k] = element;
            }
            exception.setStackTrace(newStackTrace);
            throw exception;
        }
    }
}
