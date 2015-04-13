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

import org.apache.camel.EndpointInject;
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
public class FromSmbRecursiveTest extends BaseSmbTestSupport {
    
    SmbFile rootDir;
    SmbFile sub1Dir;
    SmbFile sub2Dir;
    SmbFile source1File;
    SmbFile source2File;
    SmbFileInputStream mockInputStream1;
    SmbFileInputStream mockInputStream2;
    
    @EndpointInject(uri = "mock:result")
    private MockEndpoint mockResult;
    
    
    protected String getSmbBaseUrl() {
        return "smb://localhost/" + getShare() + "/camel/" + getClass().getSimpleName();
    }
    
    private String getSmbUrl() {
        return "smb://" + getDomain() + ";" + getUsername() + "@localhost/"
             + getShare() + "/camel/" + getClass().getSimpleName()
             + "?password=" + getPassword() + "&recursive=true&fileExist=Override";
    }

    @Before
    public void setUpFileSystem() throws Exception {
        rootDir = createMock(SmbFile.class);
        sub1Dir = createMock(SmbFile.class);
        sub2Dir = createMock(SmbFile.class);
        
        source1File = createMock(SmbFile.class);
        source2File = createMock(SmbFile.class);
        
        mockInputStream1 = createMock(SmbFileInputStream.class);
        mockInputStream2 = createMock(SmbFileInputStream.class);
        long startTime = System.currentTimeMillis();
        
        expect(rootDir.listFiles()).andReturn(new SmbFile[]{sub1Dir, sub2Dir}).anyTimes();
        expect(rootDir.isDirectory()).andReturn(true).anyTimes();
        expect(rootDir.getName()).andReturn(getClass().getSimpleName()).anyTimes();
        
        expect(sub1Dir.listFiles()).andReturn(new SmbFile[]{source1File}).anyTimes();
        expect(sub1Dir.isDirectory()).andReturn(true).anyTimes();
        expect(sub1Dir.getName()).andReturn("sub1").anyTimes();
        
        expect(source1File.isDirectory()).andReturn(false).anyTimes();
        expect(source1File.getName()).andReturn("hello.txt").anyTimes();
        expect(source1File.getContentLength()).andReturn(26).anyTimes();
        expect(source1File.getLastModified()).andReturn(startTime).anyTimes();
        expect(source1File.getInputStream()).andReturn(mockInputStream1).anyTimes();
        
        expect(sub2Dir.listFiles()).andReturn(new SmbFile[]{source1File}).anyTimes();
        expect(sub2Dir.isDirectory()).andReturn(true).anyTimes();
        expect(sub2Dir.getName()).andReturn("sub2").anyTimes();
        
        expect(source2File.isDirectory()).andReturn(false).anyTimes();
        expect(source2File.getName()).andReturn("hello.txt").anyTimes();
        expect(source2File.getContentLength()).andReturn(26).anyTimes();
        expect(source2File.getLastModified()).andReturn(startTime).anyTimes();
        expect(source2File.getInputStream()).andReturn(mockInputStream2).anyTimes();
    
        expect(mockInputStream1.available()).andReturn(26);
        expect(mockInputStream1.read((byte[]) anyObject())).andAnswer(new IAnswer<Integer>() {
            public Integer answer() throws Throwable {
                byte[] b = (byte[]) EasyMock.getCurrentArguments()[0];
                byte[] msg = "Hello World from SMBServer sub1".getBytes();
                System.arraycopy(msg, 0, b, 0, msg.length);
                return msg.length;
            }
        });
        expect(mockInputStream1.read((byte[]) anyObject())).andReturn(-1);
        mockInputStream1.close();
        
        expect(mockInputStream2.available()).andReturn(26);
        expect(mockInputStream2.read((byte[]) anyObject())).andAnswer(new IAnswer<Integer>() {
            public Integer answer() throws Throwable {
                byte[] b = (byte[]) EasyMock.getCurrentArguments()[0];
                byte[] msg = "Hello World from SMBServer sub2".getBytes();
                System.arraycopy(msg, 0, b, 0, msg.length);
                return msg.length;
            }
        });
        expect(mockInputStream2.read((byte[]) anyObject())).andReturn(-1);
        mockInputStream2.close();
        
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/", rootDir);
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/sub1/", sub1Dir);
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/sub2/", sub2Dir);
        
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/sub1/hello.txt", source1File);
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/sub2/hello.txt", source2File);
    };
    
    
    @Test
    public void testRecursiveGetFromSmb() throws Exception {
        replay(rootDir, sub1Dir, sub2Dir, source1File, source2File, mockInputStream1, mockInputStream2);
        
        mockResult.expectedMinimumMessageCount(2);
        mockResult.expectedBodiesReceived("Hello World from SMBServer sub1", "Hello World from SMBServer sub2");
        
        mockResult.assertIsSatisfied();

        verify(rootDir, sub1Dir, sub2Dir, source1File, source2File, mockInputStream1, mockInputStream2);
    }
    
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from(getSmbUrl()).to("mock:result");
            }
        };
    }
}
