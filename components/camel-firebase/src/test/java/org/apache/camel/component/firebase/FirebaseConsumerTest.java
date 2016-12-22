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


import com.google.firebase.database.FirebaseDatabase;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.firebase.provider.ConfigurationProvider;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Writes a dummy message and then checks, if the consumer receives at least one message.
 * In order to run these tests, please add a file named firebase-admin-connection.json to the test resources folder
 * with the Firebase admin connection properties. This file can be downloaded from https://console.firebase.google.com/
 */
public class FirebaseConsumerTest extends CamelTestSupport {

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition wake = lock.newCondition();

    @Test
    public void whenFirebaseListenerShouldReceiveMessages() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);
        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        final String databaseUrl = "gil-sample-app.firebaseio.com";
        final String originalRootReference = "server/saving-data";
        String serviceAccountFile = ConfigurationProvider.createFirebaseConfigLink();
        String rootReference = URLEncoder.encode(originalRootReference, "UTF-8");
        insertDummyData(String.format("https://%s", databaseUrl), originalRootReference, serviceAccountFile);

        return new RouteBuilder() {
            public void configure() {
                try {
                    from(String.format("firebase://" + databaseUrl + "?rootReference=%s&serviceAccountFile=%s",
                            rootReference, serviceAccountFile))
                            .to("log:firebasetest?level=WARN")
                            .to("mock:result");
                } catch (Exception e) {
                    fail(e.toString());
                }
            }
        };
    }

    private void insertDummyData(String databaseUrl, String originalRootReference, String serviceAccountFile) throws IOException, InterruptedException {
        FirebaseConfig config = new FirebaseConfig.Builder(databaseUrl, originalRootReference, URLDecoder.decode(serviceAccountFile, "UTF-8"))
                .build();
        config.init();
        FirebaseDatabase
                .getInstance(config.getFirebaseApp())
                .getReference(config.getRootReference()).child("dummy").setValue("test", (databaseError, databaseReference) -> {
                    try {
                        lock.lock();
                        wake.signal();
                    } finally {
                        lock.unlock();
                    }
                });
        try {
            lock.lock();
            wake.await();
        } finally {
            lock.unlock();
        }
    }
}