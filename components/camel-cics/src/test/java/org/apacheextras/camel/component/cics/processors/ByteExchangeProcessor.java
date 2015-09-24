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
package org.apacheextras.camel.component.cics.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sergio Gutierrez (sgutierr@redhat.com)
 * @author Jose Roman Martin Gil (rmarting@redhat.com)
 */
public class ByteExchangeProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ByteExchangeProcessor.class);
    
    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("Processing Exchange {}", exchange.getIn().getBody());
        
        if (exchange.getIn().getBody() instanceof byte[]) {
            byte[] inBody = (byte[]) exchange.getIn().getBody();
            
            String data = new String(inBody, "Cp1145");
            
            LOGGER.info("String Data in Exchange:\n-** EXCHANGE BODY **-\n{}\n-** END EXCHANGE BODY **-", data);
        }
    }

}
