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

package org.apacheextras.camel.component.couchbase;

/**
 * Couchbase Constants and default connection parameters
 */

public interface CouchbaseConstants {

    static String COUCHBASE_URI_ERROR = "Invalid URI. Format must be of the form couchbase:http[s]://hostname[:port]/bucket?[options...]";
    static String COUCHBASE_PUT = "CCB_PUT";
    static String COUCHBASE_GET = "CCB_GET";
    static String COUCHBASE_DELETE = "CCB_DEL";
    static String DEFAULT_DESIGN_DOCUMENT_NAME = "beer";
    static String DEFAULT_VIEWNAME = "brewery_beers";
    static String HEADER_KEY = "CCB_KEY";
    static String HEADER_ID = "CCB_ID";
    static String HEADER_TTL = "CCB_TTL";
    static String HEADER_DESIGN_DOCUMENT_NAME = "CCB_DDN";
    static String HEADER_VIEWNAME = "CCB_VN";

    static int DEFAULT_PRODUCER_RETRIES = 2;
    static int DEFAULT_PAUSE_BETWEEN_RETRIES = 5000;
    static int DEFAULT_COUCHBASE_PORT = 8091;
    static int DEFAULT_TTL = 0;
    static long DEFAULT_OP_TIMEOUT = 2500;
    static int DEFAULT_TIMEOUT_EXCEPTION_THRESHOLD = 998;
    static int DEFAULT_READ_BUFFER_SIZE = 16384;
    static long DEFAULT_OP_QUEUE_MAX_BLOCK_TIME = 10000;
    static long DEFAULT_MAX_RECONNECT_DELAY = 30000;
    static long DEFAULT_OBS_POLL_INTERVAL = 400;
    static long DEFAULT_OBS_TIMEOUT = -1;
    static String DEFAULT_CONSUME_PROCESSED_STRATEGY = "none";

}
