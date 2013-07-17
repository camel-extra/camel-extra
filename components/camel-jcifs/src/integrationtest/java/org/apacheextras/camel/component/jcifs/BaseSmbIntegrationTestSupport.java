package org.apacheextras.camel.component.jcifs;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Before;

public class BaseSmbIntegrationTestSupport extends CamelTestSupport {

	private static Properties properties;
	
	@Before
	public void setUp() throws Exception {
		properties = new Properties();
		File configFile = new File(System.getProperty("user.home")+File.separator+"camelsmb.prp");
		if ( !configFile.exists() ) {
			Assert.fail("Copy src/test/resources/camelsmb.prp.template to " +System.getProperty("user.home")+File.separator+"camelsmb.prp and edit for correct details.");
		}
		properties.load(new FileInputStream(configFile));
		super.setUp();
	}
	
	public String getUsername() {
		return properties.getProperty("username");
	}
	
	public String getDomain() {
		return properties.getProperty("domain");
	}
	
	public String getPassword() {
		return properties.getProperty("password");
	}
	
	public String getShare() {
		return properties.getProperty("share");
	}
	
	public String getLocalSharePath() {
		return properties.getProperty("localpath");
	}
}
