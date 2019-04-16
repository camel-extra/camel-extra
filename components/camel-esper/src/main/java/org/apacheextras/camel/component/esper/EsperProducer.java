/**
 * ************************************************************************************
 * https://camel-extra.github.io
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 * http://www.gnu.org/licenses/gpl-2.0-standalone.html
 **************************************************************************************
 */
package org.apacheextras.camel.component.esper;

import java.util.HashMap;
import java.util.Map;

import com.espertech.esper.client.EPRuntime;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.support.DefaultProducer;

/**
 * @version $Revision: 1.1 $
 */
public class EsperProducer extends DefaultProducer {

    private final EsperEndpoint endpoint;

    public EsperProducer(EsperEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    /**
     * @param exchange
     * @throws Exception
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        final Message in = exchange.getIn();
        if (this.endpoint.isMapBody()) {
        	getEsperRuntime().sendEvent(in.getBody(Map.class), this.endpoint.getName());
        } else if (endpoint.isMapEvents()) {
            Map<String, Object> map = new HashMap<String, Object>(in.getHeaders());
            map.put("body", in.getBody());
            getEsperRuntime().sendEvent(map, endpoint.getName());
        } else {
            getEsperRuntime().sendEvent(in.getBody());
        }
    }

    public EPRuntime getEsperRuntime() {
        return endpoint.getEsperRuntime();
    }
}
