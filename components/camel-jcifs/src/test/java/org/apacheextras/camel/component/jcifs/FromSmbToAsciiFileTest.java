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

import java.io.File;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;

import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 * Unit test to verify that we can pool an ASCII file from the SMB Server and store it on a local file path
 */
public class FromSmbToAsciiFileTest extends BaseSmbTestSupport {
    
    SmbFile rootDir;
    SmbFile sourceFile;
    SmbFileInputStream mockInputStream;
    
    
    protected String getSmbBaseUrl() {
        return "smb://localhost/" + getShare() + "/camel/" + getClass().getSimpleName() + "/";
    }
    
    private String getSmbUrl() {
        return "smb://" + getDomain() + ";" + getUsername() + "@localhost/"
             + getShare() + "/camel/" + getClass().getSimpleName()
             + "?password=" + getPassword() + "&fileExist=Override";
    }

    @Before
    public void setUpFileSystem() throws Exception {
        sourceFile = createMock(SmbFile.class);
        rootDir = createMock(SmbFile.class);
        mockInputStream = createMock(SmbFileInputStream.class);
        long startTime = System.currentTimeMillis();
        
        expect(rootDir.listFiles()).andReturn(new SmbFile[]{sourceFile}).anyTimes();
        expect(rootDir.isDirectory()).andReturn(true).anyTimes();
        
        
        expect(sourceFile.isDirectory()).andReturn(false).anyTimes();
        expect(sourceFile.getName()).andReturn("hello.txt").anyTimes();
        expect(sourceFile.getContentLength()).andReturn(26).anyTimes();
        expect(sourceFile.getLastModified()).andReturn(startTime).anyTimes();
        expect(sourceFile.getInputStream()).andReturn(mockInputStream).anyTimes();
    
        
        expect(mockInputStream.available()).andReturn(26);
        expect(mockInputStream.read((byte[]) anyObject())).andAnswer(new IAnswer<Integer>() {
            public Integer answer() throws Throwable {
                byte[] b = (byte[]) EasyMock.getCurrentArguments()[0];
                byte[] msg = "Hello World from SMBServer".getBytes();
                System.arraycopy(msg, 0, b, 0, msg.length);
                return msg.length;
            }
        });
        expect(mockInputStream.read((byte[]) anyObject())).andReturn(-1);
        mockInputStream.close();
        
        smbApiFactory.putSmbFiles(getSmbBaseUrl(), rootDir);
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "hello.txt", sourceFile);
    };
    
    
    @Test
    public void testFromSmbToFile() throws Exception {
        replay(rootDir, sourceFile, mockInputStream);
        
        MockEndpoint resultEndpoint = getMockEndpoint("mock:result");
        resultEndpoint.expectedMinimumMessageCount(1);
        resultEndpoint.expectedBodiesReceived("Hello World from SMBServer");
        
        resultEndpoint.assertIsSatisfied();

        verify(rootDir, sourceFile, mockInputStream);
        
        // assert the file
        File file = new File("target/smbtest/deleteme.txt");
        assertTrue("The ASCII file should exists", file.exists());
        assertTrue("File size wrong", file.length() > 10);
        file.delete();
    }
    
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                String fileUrl = "file:target/smbtest/?fileExist=Override&noop=true";
                from(getSmbUrl()).setHeader(Exchange.FILE_NAME, constant("deleteme.txt")).
                        convertBodyTo(String.class).to(fileUrl).to("mock:result");
            }
        };
    }
}
