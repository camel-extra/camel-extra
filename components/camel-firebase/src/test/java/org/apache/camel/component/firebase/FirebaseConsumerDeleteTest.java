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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.firebase.data.FirebaseMessage;
import org.apache.camel.component.firebase.data.Operation;
import org.apache.camel.component.firebase.provider.ConfigurationProvider;
import org.apache.camel.component.firebase.provider.SampleInputProvider;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Starts a route which listens to the remove event in Firebase. It then writes and deletes an entry in Firebase and
 * asserts, if the entry was deleted or not.
 * In order to run these tests, please add a file named firebase-admin-connection.json to the test resources folder
 * with the Firebase admin connection properties. This file can be downloaded from https://console.firebase.google.com/
 */
public class FirebaseConsumerDeleteTest {

    private final ReentrantLock reentrantLock = new ReentrantLock();

    private final Condition wake = reentrantLock.newCondition();

    @Test
    public void whenDeleteDeleteMessageShouldBeIntercepted() throws Exception {
        CamelContext context = new DefaultCamelContext();
        boolean[] deleteMessageReceived = {false};
        FirebaseConfig firebaseConfig = ConfigurationProvider.createDemoConfig();
        createAndDeleteContent(firebaseConfig, false);
        setupRoute(context, deleteMessageReceived);

        context.start();
        try {
            reentrantLock.lock();
            wake.await(30, TimeUnit.SECONDS);
        } finally {
            reentrantLock.unlock();
        }
        assertThat(deleteMessageReceived[0]).isTrue();
        context.stop();
    }

    private void createAndDeleteContent(FirebaseConfig firebaseConfig, boolean delete) {
        final DatabaseReference rootReference = FirebaseDatabase.getInstance(firebaseConfig.getFirebaseApp())
                .getReference(ConfigurationProvider.createRootReference()).child(SampleInputProvider.createDeleteKey());
        rootReference
                .setValue("AETHELWULF 839-856", (databaseError, databaseReference) -> {
                    if (delete) {
                        databaseReference.removeValue();
                    }
                });
    }

    private void setupRoute(CamelContext context, final boolean[] deleteMessageReceived) throws Exception {
        boolean deleteFired[] = {false};
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                try {
                    from(String.format("firebase://%s?rootReference=%s&serviceAccountFile=%s",
                            ConfigurationProvider.createDatabaseUrl(), ConfigurationProvider.createRootReference(), ConfigurationProvider.createFirebaseConfigLink()))
                            .to("log:firebasetest?level=WARN")
                            .process(exchange -> {
                                FirebaseMessage firebaseMessage = (FirebaseMessage) exchange.getIn().getBody();
                                if (firebaseMessage.getOperation() == Operation.CHILD_REMOVED) {
                                    deleteMessageReceived[0] = true;
                                    try {
                                        reentrantLock.lock();
                                        wake.signal();
                                    } finally {
                                        reentrantLock.unlock();
                                    }
                                } else {
                                    if (!deleteFired[0]) {
                                        deleteFired[0] = true;
                                        FirebaseConfig firebaseConfig = ConfigurationProvider.createDemoConfig();
                                        createAndDeleteContent(firebaseConfig, true);
                                    }
                                }
                            });

                } catch (Exception e) {
                    fail(e.toString());
                }
            }
        });
    }
}
