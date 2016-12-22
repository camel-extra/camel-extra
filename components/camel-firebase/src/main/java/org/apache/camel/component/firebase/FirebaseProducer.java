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

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.apache.camel.AsyncCallback;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.component.firebase.exception.DatabaseErrorException;
import org.apache.camel.impl.DefaultAsyncProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The producer, which can be used to set a value for a specific key in Firebase.
 */
public class FirebaseProducer extends DefaultAsyncProducer {

    private static final Logger LOG = LoggerFactory.getLogger(FirebaseProducer.class);
    private final String rootReference;
    private final FirebaseEndpoint endpoint;

    public FirebaseProducer(FirebaseEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
        rootReference = endpoint.getRootReference();
    }

    @Override
    public boolean process(Exchange exchange, AsyncCallback callback) {
        final Message in = exchange.getIn();
        final Message out = exchange.getOut();
        String firebaseKey = (String) in.getHeader(endpoint.getKeyName());
        Object value = in.getBody();
        DatabaseReference ref = FirebaseDatabase
                .getInstance(endpoint.getFirebaseApp())
                .getReference(rootReference).child(firebaseKey);
        final boolean reply = endpoint.isReply();
        out.setHeaders(in.getHeaders());
        if (reply) { // Wait for reply
            processReply(exchange, callback, value, ref);
        } else { // Fire and forget
            ref.setValue(value);
            out.setBody(in.getBody());
            callback.done(true);
        }
        return !reply;
    }

    private void processReply(Exchange exchange, AsyncCallback callback, Object value, DatabaseReference ref) {
        ref.setValue(value, (DatabaseError databaseError, DatabaseReference databaseReference) -> {
            if (databaseError != null) {
                exchange.setException(new DatabaseErrorException(databaseError));
            } else {
                exchange.getOut().setBody(databaseReference);
            }
            callback.done(false);
        });
    }
}
