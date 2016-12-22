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
package org.apache.camel.component.firebase.data;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Contains a message sent by Firebase.
 */
public final class FirebaseMessage {

    private final Operation operation;

    private final DataSnapshot dataSnapshot;

    private final String previousChildName;

    private final DatabaseError databaseError;

    private FirebaseMessage(Builder builder) {
        this.operation = builder.operation;
        this.dataSnapshot = builder.dataSnapshot;
        this.previousChildName = builder.previousChildName;
        this.databaseError = builder.databaseError;
    }

    public Operation getOperation() {
        return operation;
    }

    public DataSnapshot getDataSnapshot() {
        return dataSnapshot;
    }

    public String getPreviousChildName() {
        return previousChildName;
    }

    public DatabaseError getDatabaseError() {
        return databaseError;
    }

    public static class Builder {
        private final Operation operation;

        private DataSnapshot dataSnapshot;

        private String previousChildName;

        private DatabaseError databaseError;

        public Builder(Operation operation) {
            this.operation = operation;
        }

        public Builder(Operation operation, DataSnapshot dataSnapshot) {
            this.operation = operation;
            this.dataSnapshot = dataSnapshot;
        }

        public Builder setPreviousChildName(String previousChildName) {
            this.previousChildName = previousChildName;
            return this;
        }

        public Builder setDataSnapshot(DataSnapshot dataSnapshot) {
            this.dataSnapshot = dataSnapshot;
            return this;
        }

        public Builder setDatabaseError(DatabaseError databaseError) {
            this.databaseError = databaseError;
            return this;
        }

        public FirebaseMessage build() {
            return new FirebaseMessage(this);
        }
    }
}
