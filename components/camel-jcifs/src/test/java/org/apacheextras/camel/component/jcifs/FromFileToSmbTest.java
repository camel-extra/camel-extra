package org.apacheextras.camel.component.jcifs;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test to verify that we can pool an ASCII file from a local file path and store it on the SMB Server
 * 
 * @author Pontus Ullgren 
 */
public class FromFileToSmbTest extends BaseSmbTestSupport {
	
	SmbFile rootDir;
	SmbFile logoOne;
	SmbFile logoTwo;
	SmbFileOutputStream mockOutputStream;
	
	protected String getSmbBaseUrl() {
		return "smb://localhost/"+getShare()+"/camel/"+getClass().getSimpleName();
    }
	
	private String getSmbUrl() {
		return "smb://"+getDomain()+";"+getUsername()+"@localhost/"+getShare()+"/camel/"+getClass().getSimpleName()+"?password="+getPassword()+"&fileExist=Override";
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
		smbApiFactory.putSmbFiles(getSmbBaseUrl()+"/logo1.png", logoOne);
		smbApiFactory.putSmbFiles(getSmbBaseUrl()+"/logo2.png", logoTwo);
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
