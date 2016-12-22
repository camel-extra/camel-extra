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
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

/**
 * The Google Firebase component enables you to save and modify data in a Google Firebase database
 * via the {@link FirebaseProducer} class.
 * Furthermore it allows you to consume and process all child events mapped to a specific Firebase reference
 * via the {@link FirebaseConsumer} class.
 */
@UriEndpoint(scheme = "firebase", title = "Firebase", syntax = "firebase:databaseUrl", consumerClass = FirebaseConsumer.class, label = "cloud,messaging")
public class FirebaseEndpoint extends DefaultEndpoint {

    private final FirebaseConfig firebaseConfig;

    @UriPath(description = "The Firebase database URL. Always uses https")
    @Metadata(required = "true")
    private String databaseUrl;

    @UriParam(description = "The path in the database tree where the key value pairs are to be stored")
    @Metadata(required = "true")
    private String rootReference;

    @UriParam(description = "The path to the JSON file which provided the keys used to connect to Firebase. #"
            + "This file is typically generated when you create the database")
    @Metadata(required = "true")
    private String serviceAccountFile;

    @UriParam(defaultValue = "firebaseKey", description = "The Camel exchange header name in which "
            + "the Firebase key is specified. Only needed in case you are saving or updating data")
    @Metadata(required = "false")
    private String keyName = "firebaseKey";

    @UriParam(defaultValue = "reply", description = "If true, the save or update request (set value in Firebase terms) "
            + "is fired and the reply will be ignored, else the routing thread will wait and the reply will be saved in the exchange message")
    @Metadata(required = "false")
    private boolean reply;

    public FirebaseEndpoint(String uri, FirebaseComponent firebaseComponent, FirebaseConfig firebaseConfig) {
        super(uri, firebaseComponent);
        this.firebaseConfig = firebaseConfig;
        this.setRootReference(firebaseConfig.getRootReference());
        this.setServiceAccountFile(firebaseConfig.getServiceAccountFile());
        this.databaseUrl = firebaseConfig.getDatabaseUrl();
        final String keyName = firebaseConfig.getKeyName();
        this.setReply(firebaseConfig.isAsync());
        if (keyName != null) {
            this.setKeyName(keyName);
        }
    }

    public Producer createProducer() throws Exception {
        return new FirebaseProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new FirebaseConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

    public String getRootReference() {
        return rootReference;
    }

    public void setRootReference(String rootReference) {
        this.rootReference = rootReference;
    }

    public String getServiceAccountFile() {
        return serviceAccountFile;
    }

    public void setServiceAccountFile(String serviceAccountFile) {
        this.serviceAccountFile = serviceAccountFile;
    }

    public FirebaseConfig getFirebaseConfig() {
        return firebaseConfig;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public boolean isReply() {
        return reply;
    }

    public void setReply(boolean reply) {
        this.reply = reply;
    }

    public FirebaseApp getFirebaseApp() {
        return firebaseConfig.getFirebaseApp();
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }
}
