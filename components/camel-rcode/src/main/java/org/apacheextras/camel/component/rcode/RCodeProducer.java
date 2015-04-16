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
package org.apacheextras.camel.component.rcode;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultProducer;
import org.rosuda.REngine.REXP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Map.Entry;

public class RCodeProducer extends DefaultProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RCodeProducer.class);

    private RCodeEndpoint endpoint;

    private RCodeOperation operation;

    /**
     * Creates an RCodeProducer with and endpoint and operation.
     *
     * @param rCodeEndpoint RCodeEndpoint
     * @param operation RCodeOperation
     */
    public RCodeProducer(RCodeEndpoint rCodeEndpoint, RCodeOperation operation) {
        super(rCodeEndpoint);
        this.endpoint = rCodeEndpoint;
        this.operation = operation;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
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

    private void executeOperation(Message in) throws Exception {
        switch (operation) {
        case ASSIGN_CONTENT:
            assignContent(in);
            break;
        case ASSIGN_EXPRESSION:
            assignExpression(in);
            break;
        case EVAL:
            eval(in);
            break;
        case VOID_EVAL:
            voidEval(in);
            break;
        case PARSE_AND_EVAL:
            parseAndEval(in);
            break;
        default:
            LOGGER.info("Nothing to do since operation has not been configured yet");
            break;
        }
    }

    private void assignContent(Message in) throws Exception {
        final Entry<String, String> assignment = in.getMandatoryBody(Entry.class);
        endpoint.sendAssign(assignment.getKey(), assignment.getValue());
    }

    private void assignExpression(Message in) throws Exception {
        final Entry<String, REXP> assignment = in.getMandatoryBody(Entry.class);
        endpoint.sendAssign(assignment.getKey(), assignment.getValue());
    }

    private void eval(Message in) throws Exception {
        final Exchange exchange = in.getExchange();
        final String command = in.getMandatoryBody(String.class);
        final REXP rexp = endpoint.sendEval(command);
        exchange.getOut().getHeaders().putAll(exchange.getIn().getHeaders());
        exchange.getOut().setBody(rexp);
    }

    private void voidEval(Message in) throws Exception {
        final String command = in.getMandatoryBody(String.class);
        endpoint.sendVoidEval(command);
    }

    private void parseAndEval(Message in) throws Exception {
        final Exchange exchange = in.getExchange();
        final String command = in.getMandatoryBody(String.class);
        REXP rexp = endpoint.sendParseAndEval(command);
        exchange.getOut().getHeaders().putAll(exchange.getIn().getHeaders());
        exchange.getOut().setBody(rexp);
    }
}
