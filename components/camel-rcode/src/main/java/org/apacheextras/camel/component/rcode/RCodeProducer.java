/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.


 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/gpl-2.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.rcode;

import java.net.ConnectException;
import java.util.Map;
import java.util.Map.Entry;
import javax.security.auth.login.LoginException;

import org.apache.camel.CamelExchangeException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultProducer;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;

public class RCodeProducer extends DefaultProducer {

    private RCodeEndpoint endpoint;

    private RCodeOperation operation;

    /**
     * Creates an RCodeProducer with and endpoint and operation.
     *
     * @param rCodeEndpoint RCodeEndpoint
     * @param operation     RCodeOperation
     */
    public RCodeProducer(RCodeEndpoint rCodeEndpoint, RCodeOperation operation) {
        super(rCodeEndpoint);
        this.endpoint = rCodeEndpoint;
        this.operation = operation;
    }

    @Override
    public void process(Exchange exchange) throws LoginException, ConnectException,
            REngineException, REXPMismatchException,
            CamelExchangeException {
        final Message in = exchange.getIn();
        final Map<String, Object> headers = in.getHeaders();
        final RCodeOperation configuredOperation = operation;

        if (!endpoint.isConnected()) {
            endpoint.reconnect();
        }

        if (headers.containsKey(RCodeConstants.RSERVE_OPERATION)) {
            final String op = headers.get(RCodeConstants.RSERVE_OPERATION).toString().toUpperCase();
            operation = RCodeOperation.valueOf(op);
        }

        executeOperation(in);

        // Reset the operation to the original value in case of header
        // controlled operation changes
        if (headers.containsKey(RCodeConstants.RSERVE_OPERATION)) {
            operation = configuredOperation;
        }

        exchange.getOut().getHeaders().putAll(in.getHeaders());
        exchange.getOut().setAttachments(in.getAttachments());
    }

    private void executeOperation(Message in)
            throws REngineException, REXPMismatchException, CamelExchangeException {
        final Exchange exchange = in.getExchange();

        switch (operation) {
            case ASSIGN_CONTENT: {
                final Entry<String, String> assignment = in.getMandatoryBody(Entry.class);
                endpoint.sendAssign(assignment.getKey(), assignment.getValue());
            }
            break;
            case ASSIGN_EXPRESSION: {
                final Entry<String, REXP> assignment = in.getMandatoryBody(Entry.class);
                endpoint.sendAssign(assignment.getKey(), assignment.getValue());
            }
            break;
            case EVAL: {
                final String command = in.getMandatoryBody(String.class);
                REXP rexp = endpoint.sendEval(command);
                exchange.getOut().getHeaders().putAll(exchange.getIn().getHeaders());
                exchange.getOut().setBody(rexp);
            }
            break;
            case VOID_EVAL: {
                final String command = in.getMandatoryBody(String.class);
                endpoint.sendVoidEval(command);
            }
            break;
            case PARSE_AND_EVAL: {
                final String command = in.getMandatoryBody(String.class);
                REXP rexp = endpoint.sendParseAndEval(command);
                exchange.getOut().getHeaders().putAll(exchange.getIn().getHeaders());
                exchange.getOut().setBody(rexp);
            }
            break;
        }
    }
}