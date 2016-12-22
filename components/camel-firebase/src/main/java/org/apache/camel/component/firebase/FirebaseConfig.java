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
package org.apache.camel.component.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Contains the elements needed to connect to Firebase
 */
public final class FirebaseConfig {

    private final String databaseUrl;

    private final String rootReference;

    private final String serviceAccountFile;

    private final String keyName;

    private final boolean async;

    private FirebaseApp firebaseApp;

    private FirebaseConfig(Builder builder) {
        this.databaseUrl = builder.databaseUrl;
        this.rootReference = builder.rootReference;
        this.serviceAccountFile = builder.serviceAccountFile;
        this.keyName = builder.keyName;
        this.async = builder.async;
    }

    public void init() throws IOException {
        try (InputStream in = Files.newInputStream(Paths.get(serviceAccountFile))) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setServiceAccount(in)
                    .setDatabaseUrl(this.databaseUrl)
                    .build();
            firebaseApp = FirebaseApp.initializeApp(options, UUID.randomUUID().toString());
        }
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getRootReference() {
        return rootReference;
    }

    public String getServiceAccountFile() {
        return serviceAccountFile;
    }

    public String getKeyName() {
        return keyName;
    }

    public FirebaseApp getFirebaseApp() {
        return firebaseApp;
    }

    public boolean isAsync() {
        return async;
    }

    public static class Builder {

        private final String databaseUrl;

        private final String rootReference;

        private final String serviceAccountFile;

        private String keyName;

        private boolean async;

        public Builder(String databaseUrl, String rootReference, String serviceAccountFile) {
            this.databaseUrl = databaseUrl;
            this.rootReference = rootReference;
            this.serviceAccountFile = serviceAccountFile;
        }

        public Builder setKeyName(String keyName) {
            this.keyName = keyName;
            return this;
        }

        public Builder setAsync(boolean async) {
            this.async = async;
            return this;
        }

        public FirebaseConfig build() {
            return new FirebaseConfig(this);
        }
    }
}
