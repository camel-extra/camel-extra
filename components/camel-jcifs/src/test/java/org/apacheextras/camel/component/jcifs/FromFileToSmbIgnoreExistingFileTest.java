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

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

/**
 * Unit test to verify that the fileExist=Ignore option does not delete the existing
 * file and that it continues routing the exchange to the next endpoint without
 * throwing an exception.
 */
public class FromFileToSmbIgnoreExistingFileTest extends BaseSmbTestSupport {

    SmbFile rootDir;
    SmbFile logoOne;
    SmbFile logoTwo;
    SmbFileOutputStream mockOutputStream;

    protected String getSmbBaseUrl() {
        return "smb://localhost/" + getShare() + "/camel/" + getClass().getSimpleName();
    }

    private String getSmbUrl() {
        return "smb://" + getDomain() + ";" + getUsername() + "@localhost/" + getShare() + "/camel/" + getClass().getSimpleName() + "?password=" + getPassword()
               + "&fileExist=Ignore";
    }

    @Override
    @Before
    public void setUpFileSystem() throws Exception {
        logoOne = createStrictMock(SmbFile.class);
        logoTwo = createStrictMock(SmbFile.class);
        rootDir = createStrictMock(SmbFile.class);
        mockOutputStream = createMock(SmbFileOutputStream.class);

        expect(rootDir.exists()).andReturn(true).times(2);

        //Logo one already exists so we expect that one NOT to be written!
        expect(logoOne.exists()).andReturn(true).times(1);

        //Log two doesn't exist so we expect the file to be written as usual
        expect(logoTwo.exists()).andReturn(false).times(1);
        expect(logoTwo.getName()).andReturn("logo2.png").times(1);
        mockOutputStream.write((byte[])anyObject(), eq(0), eq(15358));
        mockOutputStream.close();

        smbApiFactory.putSmbFiles(getSmbBaseUrl(), rootDir);
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/logo1.png", logoOne);
        smbApiFactory.putSmbFiles(getSmbBaseUrl() + "/logo2.png", logoTwo);
        smbApiFactory.putSmbFileOutputStream("logo2.png", mockOutputStream);

    }

    @Test
    public void testFromFileToSmbIgnoreExistingFile() throws Exception {
        replay(rootDir, logoOne, logoTwo, mockOutputStream);

        MockEndpoint mock = getMockEndpoint("mock:result");
        //Important: Both exchanges should reach the final endpoint even though one file was ignored because it already existed
        mock.expectedMessageCount(2);

        assertMockEndpointsSatisfied();

        verify(rootDir, logoOne, logoTwo, mockOutputStream);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:src/test/data?noop=true&consumer.delay=3000").to(getSmbUrl()).to("mock:result");
            }
        };
    }
}
