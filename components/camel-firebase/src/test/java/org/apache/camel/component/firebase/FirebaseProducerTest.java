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
import org.apache.camel.CamelContext;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.firebase.provider.ConfigurationProvider;
import org.apache.camel.component.firebase.provider.SampleInputProvider;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Before;
import org.junit.Test;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.stream.IntStream.range;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tests two scenarios: a synchronous and one asynchronous request.
 * In order to run these tests, please add a file named firebase-admin-connection.json to the test resources folder
 * with the Firebase admin connection properties. This file can be downloaded from https://console.firebase.google.com/
 */
public class FirebaseProducerTest {

    private final ReentrantLock reentrantLock = new ReentrantLock();

    private final Condition wake = reentrantLock.newCondition();

    private SampleInputProvider sampleInputProvider;

    @Before
    public void setUp() throws Exception {
        sampleInputProvider = new SampleInputProvider();
    }

    @Test
    public void whenFirebaseSetShouldReceiveMessageAsDBReference() throws Exception {
        startRoute(true, DatabaseReference.class);
    }

    @Test
    public void whenFirebaseSetShouldReceiveMessageAsDbString() throws Exception {
        startRoute(false, String.class);
    }

    @Test
    public void whenMultipleFirebaseSetShouldReceiveExpectedMessages() {
        range(0, 10).forEach(i -> {
            try {
                startRoute(true, DatabaseReference.class);
                startRoute(false, String.class);
            } catch (Exception e) {
                fail("Multiple test fails: " + e);
            }
        });
    }

    private void startRoute(final boolean reply, final Class<?> expectedBodyClass) throws Exception {
        sampleInputProvider.copySampleFile();
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                String rootReference = URLEncoder.encode(ConfigurationProvider.createRootReference(), "UTF-8");
                String serviceAccountFile = ConfigurationProvider.createFirebaseConfigLink();
                from(sampleInputProvider.getTargetFolder().toUri().toString())
                        .process(exchange -> {
                            GenericFile file = (GenericFile) exchange.getIn().getBody();
                            String content = new String(Files.readAllBytes(Paths.get(file.getAbsoluteFilePath())), "UTF-8");
                            String[] keyValue = content.split("=");
                            final Message out = exchange.getOut();
                            out.setHeader("firebaseKey", keyValue[0]);
                            out.setBody(keyValue[1].trim());
                        })
                        .to(String.format("firebase://%s?rootReference=%s&serviceAccountFile=%s&reply=%b",
                                ConfigurationProvider.createDatabaseUrl(), rootReference, serviceAccountFile, reply))
                        .to("log:whenFirebaseSet?level=WARN")
                        .process(exchange1 -> {
                            assertThat(exchange1.getIn().getBody().getClass()).isEqualTo(expectedBodyClass);
                            if (reply) {
                                assertThat(exchange1.getIn().getHeader("firebaseKey")).isNotNull();
                            }
                            try {
                                reentrantLock.lock();
                                wake.signal();
                            } finally {
                                reentrantLock.unlock();
                            }
                        });
            }
        });
        context.start();
        try {
            reentrantLock.lock();
            wake.await();
        } finally {
            reentrantLock.unlock();
        }
        context.stop();
    }
}