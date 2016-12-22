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
package org.apache.camel.component.firebase.provider;

import org.apache.camel.component.firebase.FirebaseConfig;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static org.junit.Assert.assertNotNull;

/**
 * Provides the path of the configuration used to access Firebase.
 */
public final class ConfigurationProvider {

    private ConfigurationProvider() {
    }

    public static String createFirebaseConfigLink() throws URISyntaxException, UnsupportedEncodingException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("firebase-admin-connection.json");
        assertNotNull(url);
        File f = new File(url.toURI());
        return URLEncoder.encode(f.getAbsolutePath(), "UTF-8");
    }

    public static FirebaseConfig createDemoConfig() throws IOException, URISyntaxException {
        FirebaseConfig firebaseConfig = new FirebaseConfig.Builder(String.format("https://%s", createDatabaseUrl()), createRootReference(),
                URLDecoder.decode(createFirebaseConfigLink(), "UTF-8")).build();
        firebaseConfig.init();
        return firebaseConfig;
    }

    public static String createRootReference() {
        return "server/saving-data";
    }

    public static String createDatabaseUrl() {
        return "gil-sample-app.firebaseio.com";
    }
}
