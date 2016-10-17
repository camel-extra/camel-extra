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

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Test;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 * Unit test for readLock=changed&readLockMinLength=0
 */
public class FromSmbChangedReadLockZeroLengthTest extends BaseSmbTestSupport {
    private static final byte[] FILE_CONTENT = new byte[0];

    private SmbFile rootDir;
    private SmbFile sourceFile;
    private SmbFileInputStream mockInputStream;
    private SmbFileOutputStream mockOutputStream;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint mockResult;

    protected String getSmbBaseUrl() {
        return "smb://localhost/" + getShare() + "/camel/" + getClass().getSimpleName();
    }

    protected String getSmbUrl() {
        return "smb://" + getDomain() + ";" + getUsername() + "@localhost/"
                + getShare() + "/camel/" + getClass().getSimpleName()
                + "?password=" + getPassword()
                + "&consumer.delay=5000"
                + "&readLock=changed&readLockCheckInterval=1000"
                + "&readLockMinLength=0";
    }

    public void setUpFileSystem() throws Exception {
        sourceFile = createMock(SmbFile.class);
        rootDir = createMock(SmbFile.class);

        mockOutputStream = createMock(SmbFileOutputStream.class);
        mockInputStream = createMock(SmbFileInputStream.class);
        long startTime = System.currentTimeMillis();

        expect(rootDir.listFiles()).andReturn(new SmbFile[]{sourceFile}).anyTimes();

        expect(sourceFile.isDirectory()).andReturn(false).anyTimes();
        expect(sourceFile.getName()).andReturn("hello.txt").anyTimes();
        expect(sourceFile.getContentLength()).andReturn(FILE_CONTENT.length).anyTimes();
        expect(sourceFile.getLastModified()).andReturn(startTime).anyTimes();
        expect(sourceFile.getInputStream()).andReturn(mockInputStream).anyTimes();


        expect(mockInputStream.available()).andReturn(FILE_CONTENT.length);
        expect(mockInputStream.read((byte[]) anyObject())).andAnswer(new IAnswer<Integer>() {
            public Integer answer() throws Throwable {
                byte[] b = (byte[]) EasyMock.getCurrentArguments()[0];
                System.arraycopy(FILE_CONTENT, 0, b, 0, FILE_CONTENT.length);
                return FILE_CONTENT.length;
            }
        });
        expect(mockInputStream.read((byte[]) anyObject())).andReturn(-1);
        mockInputStream.close();

        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/", rootDir);

        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/hello.txt", sourceFile);
        smbApiFactory.putSmbFileOutputStream("hello.txt", mockOutputStream);
    }


    @Test
    public void testChangedReadLock() throws Exception {
        replay(rootDir, sourceFile, mockInputStream, mockOutputStream);

        mockResult.expectedMessageCount(1);
        mockResult.expectedBodiesReceived(FILE_CONTENT);

        assertMockEndpointsSatisfied();

        verify(rootDir, sourceFile, mockInputStream, mockOutputStream);
    }

    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from(getSmbUrl()).to("mock:result");
            }
        };
    }
}
