/**************************************************************************************
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
 ***************************************************************************************/
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
