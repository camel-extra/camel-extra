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
import jcifs.smb.SmbFileOutputStream;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 * Unit test to verify that we can pool an ASCII file from a local file path and store it on the SMB Server
 * 
 * @author Pontus Ullgren 
 */
public class AnonymousFromFileToSmbTest extends BaseSmbTestSupport {
    
    SmbFile rootDir;
    SmbFile logoOne;
    SmbFile logoTwo;
    SmbFileOutputStream mockOutputStream;
    
    protected String getSmbBaseUrl() {
        return "smb://localhost/" + getShare() + "/camel/" + getClass().getSimpleName();
    }
    
    private String getSmbUrl() {
        return "smb://localhost/" + getShare() + "/camel/" + getClass().getSimpleName() + "?password=" + getPassword() + "&fileExist=Override";
    }
    
    
    @Before
    public void setUpFileSystem() throws Exception {
        logoOne = createStrictMock(SmbFile.class);
        logoTwo = createStrictMock(SmbFile.class);
        rootDir = createStrictMock(SmbFile.class);
        mockOutputStream = createMock(SmbFileOutputStream.class);
        
        expect(rootDir.exists()).andReturn(true).times(2);
        expect(logoOne.exists()).andReturn(false).times(1);
        expect(logoOne.getName()).andReturn("logo1.png");
        expect(logoTwo.exists()).andReturn(false).times(1);
        expect(logoTwo.getName()).andReturn("logo2.png");
        
        mockOutputStream.write((byte[]) anyObject(), eq(0), eq(15358));
        mockOutputStream.close();
        
        mockOutputStream.write((byte[]) anyObject(), eq(0), eq(19882));
        mockOutputStream.close();
        
        
        smbApiFactory.putSmbFiles(getSmbBaseUrl(), rootDir);
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/logo1.png", logoOne);
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/logo2.png", logoTwo);
        smbApiFactory.putSmbFileOutputStream("logo1.png", mockOutputStream);
        smbApiFactory.putSmbFileOutputStream("logo2.png", mockOutputStream);
        
    };
    
    @Test
    public void testFromFileToSmb() throws Exception {
        replay(rootDir, logoOne, logoTwo, mockOutputStream);
        
        
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(2);
        
        assertMockEndpointsSatisfied();
        
        verify(rootDir, logoOne, logoTwo, mockOutputStream);
    }
    
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("file:src/test/data?noop=true&consumer.delay=3000").to(getSmbUrl()).to("mock:result");
            }
        };
    }
}
