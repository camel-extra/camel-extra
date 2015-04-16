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
import jcifs.smb.TestableSmbFile;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 * Unit test to test both consumer.moveNamePrefix and consumer.moveNamePostfix
 * options.
 */
public class FromSmbMoveFilesInOrderTest extends BaseSmbTestSupport {

    private SmbFile rootDir;
    private SmbFile sub2Dir;
    private List<Object> mocks;
    private List<String> fileContents;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint mockResult;

    protected String getSmbBaseUrl() {
        return "smb://localhost/" + getShare() + "/camel/" + getClass().getSimpleName();
    }

    private String getSmbUrl() {
        return "smb://" + getDomain() + ";" + getUsername() + "@localhost/" + getShare() + "/camel/" + getClass().getSimpleName() + "?password=" + getPassword()
               + "&maxMessagesPerPoll=1" + "&sortBy=file:modified" + "&eagerMaxMessagesPerPoll=false"
               // + "&consumer.eagerLimitMaxMessagesPerPoll=false" // this is an
               // alternatiwe which worked
               + "&delete=true"; // &consumer.delay=5000
    }

    public void setUpFileSystem() throws Exception {
        rootDir = createMock(SmbFile.class);
        sub2Dir = createMock(SmbFile.class);
        mocks = new ArrayList<Object>();
        fileContents = new ArrayList<String>();

        final ListSmbDirAnswer listDirAnswer = new ListSmbDirAnswer();
        listDirAnswer.sourceFilesMocks = new ArrayList<SmbFile>();

        expect(sub2Dir.listFiles()).andReturn(new SmbFile[] {}).anyTimes();
        expect(sub2Dir.exists()).andReturn(true).anyTimes();
        expect(sub2Dir.isDirectory()).andReturn(true).anyTimes();

        Date initialDate = new Date();

        for (int i = 0; i < 10; i++) {

            TestableSmbFile sourceFile = createMock(TestableSmbFile.class);
            SmbFileInputStream mockInputStream = createMock(SmbFileInputStream.class);

            listDirAnswer.sourceFilesMocks.add(sourceFile);

            mocks.add(sourceFile);
            mocks.add(mockInputStream);
            final String stringContent = "Content of File " + i;
            final byte[] content = stringContent.getBytes();
            fileContents.add(stringContent);

            expect(sourceFile.isDirectory()).andReturn(false).anyTimes();
            expect(sourceFile.getName()).andReturn("hello" + i + ".txt").anyTimes();
            expect(sourceFile.getContentLength()).andReturn(content.length).anyTimes();
            // files will be in reversed order so we can test if sorting works
            // well
            expect(sourceFile.getLastModified()).andReturn(initialDate.getTime() - i * 100).anyTimes();
            expect(sourceFile.getInputStream()).andReturn(mockInputStream).anyTimes();
            sourceFile.delete(anyString());
            expectLastCall().anyTimes();
            sourceFile.delete();
            expectLastCall().anyTimes();

            expect(mockInputStream.available()).andReturn(content.length).anyTimes();

            final InpotReadAnswer readAnswer = new InpotReadAnswer(content);
            final InputClosedAnswer closeAnswer = new InputClosedAnswer(readAnswer);

            expect(mockInputStream.read((byte[])anyObject())).andAnswer(readAnswer).anyTimes();
            mockInputStream.close();
            expectLastCall().andAnswer(closeAnswer).anyTimes();

            smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/hello" + i + ".txt", sourceFile);
        }

        expect(rootDir.listFiles()).andAnswer(listDirAnswer).anyTimes();
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/", rootDir);

    }

    @Test
    public void testPollFileAndShouldBeMoved() throws Exception {
        replay(rootDir, sub2Dir);
        replay(mocks.toArray());

        mockResult.expectedMessageCount(10);
        final List<String> fileContents1 = new ArrayList<String>(fileContents);
        Collections.reverse(fileContents1);
        mockResult.expectedBodiesReceived(fileContents1);

        assertMockEndpointsSatisfied();

        verify(rootDir, sub2Dir);
        verify(mocks.toArray());
    }

    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from(getSmbUrl()).to("mock:result");
            }
        };
    }

    private static class InpotReadAnswer implements IAnswer<Integer> {
        private final byte[] FILE_CONTENT;
        boolean hasBytes;

        public InpotReadAnswer(byte[] FILE_CONTENT) {
            this.FILE_CONTENT = FILE_CONTENT;
            hasBytes = true;
        }

        public Integer answer() throws Throwable {
            if (!hasBytes)
                return -1;
            hasBytes = false;
            byte[] b = (byte[])EasyMock.getCurrentArguments()[0];
            System.arraycopy(FILE_CONTENT, 0, b, 0, FILE_CONTENT.length);
            return FILE_CONTENT.length;
        }
    }

    private static class InputClosedAnswer implements IAnswer<Object> {
        private final InpotReadAnswer readAnswer;

        public InputClosedAnswer(InpotReadAnswer readAnswer) {
            this.readAnswer = readAnswer;
        }

        @Override
        public Object answer() throws Throwable {
            readAnswer.hasBytes = true;
            return null;
        }
    }

    private class ListSmbDirAnswer implements IAnswer<SmbFile[]> {
        List<SmbFile> sourceFilesMocks;

        @Override
        public SmbFile[] answer() throws Throwable {
            final SmbFile[] smbFiles = sourceFilesMocks.toArray(new SmbFile[] {});
            sourceFilesMocks.remove(smbFiles.length - 1);
            return smbFiles;
        }
    }
}
