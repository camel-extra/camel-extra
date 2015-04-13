/**************************************************************************************
 https://camel-extra.github.io

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
package org.apacheextras.camel.itest.karaf;

import org.apache.camel.test.AvailablePortFinder;
import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.junit.Assert;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.karaf.options.KarafDistributionOption;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.ops4j.pax.exam.options.UrlReference;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.*;

public class AbstractFeatureTest extends Assert {

  public static final String CAMEL_EXTRA_GROUP_ID = "org.apache-extras.camel-extra.karaf";
  public static final String CAMEL_EXTRA_ARTIFACT_ID = "camel-extra";
  public static final String KARAF_VERSION = "2.3.7";

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFeatureTest.class);


  @Inject
  protected BundleContext bundleContext;

  @Inject
  protected FeaturesService featuresService;

  private String cachedComponentName = extractName(getClass());

  protected String componentName() {
      return cachedComponentName;
  }

  protected String fullComponentName() {
   return "camel-" + componentName();
  }

  @Configuration
  public Option[] configure() {
      return commonOptions();
  }

  public boolean isInstalled(Feature feature) {
    if(null != featuresService) {
      return featuresService.isInstalled(feature);
    }
    return false;
  }

  public Feature getFeature(String featureName) {
    Feature feature = null;
    if(null != featuresService) {
      try {
        feature = featuresService.getFeature(featureName);
      } catch (Exception e) {
        LOGGER.error("Error when receiving feature: {}", e.getMessage());
      }
    }
    return feature;
  }

  /**
   * Returns the Karaf feature url.
   * @return Karaf UrlReference
   */
  public UrlReference getKarafFeatureUrl() {
    final String type = "xml/features";
    return mavenBundle()
        .groupId("org.apache.karaf.assemblies.features")
        .artifactId("standard")
        .version(KARAF_VERSION)
        .type(type);
  }


  /**
   * Configures Apache Karaf to run with Camel Extra and returns the configuration options.
   *
   * @return configured options
   */
  public Option[] commonOptions() {
    return new Option[]{
        karafDistributionConfiguration()
            .frameworkUrl(
                maven()
                    .groupId("org.apache.karaf")
                    .artifactId("apache-karaf")
                    .type("tar.gz")
                    .versionAsInProject()
            )
            .karafVersion(KARAF_VERSION)
            .name("Apache Karaf")
            .unpackDirectory(new File("target/paxexam/unpack")),
        KarafDistributionOption.keepRuntimeFolder(),
        logLevel(LogLevelOption.LogLevel.INFO),
        configureConsole().ignoreRemoteShell().ignoreLocalConsole(),
        features(
            maven()
                .groupId(CAMEL_EXTRA_GROUP_ID)
                .artifactId(CAMEL_EXTRA_ARTIFACT_ID)
                .type("xml")
                .classifier("features")
                .versionAsInProject(),
            fullComponentName()),
        // Suppress the port collisions warnings in parallel tests
        editConfigurationFilePut("etc/org.apache.karaf.management.cfg", "rmiServerPort", AvailablePortFinder.getNextAvailable() + ""),
        editConfigurationFilePut("etc/org.apache.karaf.management.cfg", "rmiRegistryPort", AvailablePortFinder.getNextAvailable() + ""),
        // Add apache-snapshots repository
        editConfigurationFilePut("etc/org.ops4j.pax.url.mvn.cfg", "org.ops4j.pax.url.mvn.repositories",
            "http://repo1.maven.org/maven2@id=central, "
            + "http://svn.apache.org/repos/asf/servicemix/m2-repo@id=servicemix, "
            + "http://repository.springsource.com/maven/bundles/release@id=springsource.release, "
            + "http://repository.springsource.com/maven/bundles/external@id=springsource.external, "
            + "http://oss.sonatype.org/content/repositories/releases/@id=sonatype, "
            + "http://repository.apache.org/content/groups/snapshots-group@snapshots@noreleases@id=apache")
    };
  }

  /**
   * Extracts the component name from the test case convention name. The convention for a component integration test is:</br>
   * <code>Camel{ComponentName}Test</code></br>
   * Each capital letter between Camel and Test defines that there is a '-' delimiter in the component name.
   *
   * @param clazz test class
   * @return component name
   */
  public static String extractName(Class<?> clazz) {
    final String name = clazz.getName();
    final int idx1 = name.indexOf("Camel") + "Camel".length();
    final int idx2 = name.indexOf("Test");
    final StringBuilder sb = new StringBuilder();
    for (int i = idx1; i < idx2; i++) {
      char c = name.charAt(i);
      if (Character.isUpperCase(c) && sb.length() > 0) {
        sb.append('-');
      }
      sb.append(Character.toLowerCase(c));
    }
    return sb.toString();
  }

    // Assertion helpers

    protected void assertIsFeatureInstalled() {
        Feature feature = getFeature(fullComponentName());
        assertTrue(isInstalled(feature));
    }

}