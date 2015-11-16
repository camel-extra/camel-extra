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

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The RCodeEndpoint is the components Camel endpoint.
 */
@UriEndpoint(scheme = "rcode", title = "RCode", syntax = "rcode://host[:port]/operation[?options]")
public class RCodeEndpoint extends DefaultEndpoint {

    // Logger to provide a certain level of information
    private static final Logger LOGGER = LoggerFactory.getLogger(RCodeEndpoint.class);
    // RConnection utilizes the RServe package of 'R'
    private RConnection rConnection;
    // Configuration contains all default values that can be overwritten by
    // the endpoint configuration.
    private RCodeConfiguration rCodeConfiguration;
    // Contains all supported operations as enumeration
    private RCodeOperation operation;

    /**
     * Creates an empty endpoint instance based on the default configuration.
     */
    public RCodeEndpoint() {
        // Nothing to do
    }

    /**
     * Creates an endpoint based on the uri and the given component.
     *
     * @param endpointUri String
     * @param component RCodeComponent
     */
    public RCodeEndpoint(String endpointUri, RCodeComponent component) {
        super(endpointUri, component);
    }

    /**
     * Creates an endpoint based on the uri, component, configuration and
     * operation.
     *
     * @param endpointUri String
     * @param component RCodeComponent
     * @param configuration RCodeConfiguration
     * @param operation RCodeOperation
     */
    public RCodeEndpoint(String endpointUri, RCodeComponent component, RCodeConfiguration configuration, RCodeOperation operation) {
        super(endpointUri, component);
        this.rCodeConfiguration = configuration;
        this.operation = operation;
    }

    /**
     * Creates the endpoints producer.
     *
     * @return RCodeProducer
     * @throws Exception
     */
    @Override
    public Producer createProducer() throws Exception {
        return new RCodeProducer(this, operation);
    }

    /**
     * Creates the endpoints consumer.
     *
     * @param processor Processor
     * @return Consumer
     * @throws Exception
     */
    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Validates of the rConnection is a singleton.
     *
     * @return boolean
     */
    @Override
    public boolean isSingleton() {
        // RConnection is not thread-safe to be shared
        return false;
    }

    /**
     * Start the endpoint and connects to 'R'
     *
     * @throws RserveException
     * @throws Exception
     */
    @Override
    protected void doStart() throws Exception {
        super.doStart();
        // Connects after the endpoint has started
        connect();
    }

    /**
     * Closes the RConnection and stops the endpoint.
     *
     * @throws Exception
     */
    @Override
    protected void doStop() throws Exception {
        // Closes the RConnection when shutting down
        rConnection.close();
        super.doStop();
    }

    /**
     * Provides the information if the endpoint is connected.
     *
     * @return boolean
     */
    public boolean isConnected() {
        return rConnection.isConnected();
    }

    /**
     * Reconnects the 'R' connection.
     */
    public void reconnect() {
        connect();
    }

    /**
     * Connects the RConnection to the underlying RServe instance.
     */
    private void connect() {
        // Create the RConnection instance via the Factory pattern
        if (null == rConnection) {
            try {
                rConnection = RConnectionFactory.getInstance().createConnection(rCodeConfiguration);
            } catch (RserveException ex) {
                LOGGER.error("Could not create a connection due to: {}, {}", ex.getMessage(), ex);
                throw new RuntimeCamelException(ex);
            }
        }
        // Login to the RConnection
        if (rConnection.needLogin()) {
            try {
                rConnection.login(rCodeConfiguration.getUser(), rCodeConfiguration.getPassword());
            } catch (RserveException ex) {
                LOGGER.error("Unable to login due to: {}, {}", ex.getMessage(), ex);
                throw new RuntimeCamelException(ex);
            }
        }
        // Set the encoding to UTF-8
        try {
            rConnection.setStringEncoding("utf8");
        } catch (RserveException ex) {
            LOGGER.error("Unable to set the encoding due to: {}, {}", ex.getMessage(), ex);
            throw new RuntimeCamelException(ex);
        }
    }

    /**
     * Sends a String command and returns an R expression.
     *
     * @param command String
     * @return REXP
     */
    public REXP sendEval(String command) throws RserveException {
        return rConnection.eval(command);
    }

    /**
     * Sends a command to 'R' without getting a response back.
     *
     * @param command String
     * @throws RserveException
     */
    public void sendVoidEval(String command) throws RserveException {
        rConnection.voidEval(command);
    }

    /**
     * Sends a symbol and a String expression to the R code environment.
     *
     * @param symbol String
     * @param content String
     * @throws RserveException
     */
    public void sendAssign(String symbol, String content) throws RserveException {
        rConnection.assign(symbol, content);
    }

    /**
     * Sends a symbol and an R expression to the R code environment.
     *
     * @param symbol String
     * @param rexp REXP
     * @throws RserveException
     */
    public void sendAssign(String symbol, REXP rexp) throws RserveException {
        rConnection.assign(symbol, rexp);
    }

    /**
     * Sends an R command as String, parses and executes the code before it
     * returns the result as R expression.
     *
     * @param command String
     * @return REXP
     * @throws REngineException
     * @throws REXPMismatchException
     */
    public REXP sendParseAndEval(String command) throws REngineException, REXPMismatchException {
        return rConnection.parseAndEval(command);
    }

    /**
     * @return the rCodeConfiguration
     */
    public RCodeConfiguration getConfiguration() {
        return rCodeConfiguration;
    }

    /**
     * @param configuration the rCodeConfiguration to set
     */
    public void setConfiguration(RCodeConfiguration configuration) {
        this.rCodeConfiguration = configuration;
    }

    /**
     * @return host String
     */
    public String getHost() {
        return rCodeConfiguration.getHost();
    }

    /**
     * @param host
     */
    public void setHost(String host) {
        rCodeConfiguration.setHost(host);
    }

    /**
     * @return port int
     */
    public int getPort() {
        return rCodeConfiguration.getPort();
    }

    /**
     * @param port
     */
    public void setPort(int port) {
        rCodeConfiguration.setPort(port);
    }

    /**
     * @return user String
     */
    public String getUser() {
        return rCodeConfiguration.getUser();
    }

    /**
     * @param user
     */
    public void setUser(String user) {
        rCodeConfiguration.setUser(user);
    }

    /**
     * @return password String
     */
    public String getPassword() {
        return rCodeConfiguration.getPassword();
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        rCodeConfiguration.setPassword(password);
    }

    /**
     * @return bufferSize long
     */
    public long getBufferSize() {
        return getConfiguration().getBufferSize();
    }

    /**
     * @param bufferSize
     */
    public void setBufferSize(long bufferSize) {
        getConfiguration().setBufferSize(bufferSize);
    }
}
