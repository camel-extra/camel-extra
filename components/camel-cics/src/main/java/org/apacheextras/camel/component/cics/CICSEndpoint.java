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
package org.apacheextras.camel.component.cics;

import java.util.Map;

import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
// Apache Camel 2.15
// import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CICS Endpoint<br/>
 * 
 * A CICS Endpoint is defined by the next pattern:<br/>
 * 
 * <code>cics://host[:port]/serverName[?options]</code>
 * 
 * @author Sergio Gutierrez (sgutierr@redhat.com)
 * @author Jose Roman Martin Gil (rmarting@redhat.com)
 */
@UriEndpoint(scheme = "cics", title = "CICS", syntax = "cics:host[:port]/server[?options]", producerOnly = true, consumerOnly = false)
public class CICSEndpoint extends DefaultEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(CICSEndpoint.class);

    @UriPath
    @Metadata(required = "true")
    private String host;

    @UriPath(defaultValue = "2006")
    @Metadata(required = "false")
    private int port = 2006;

    @UriPath
    @Metadata(required = "true")
    private String server;

    @UriParam
    @Metadata(required = "false")
    private String userId;

    @UriParam
    @Metadata(required = "false")
    private String password;

    @UriParam
    @Metadata(required = "false")
    private String sslPassword;

    @UriParam
    @Metadata(required = "false")
    private String sslKeyring;

    @UriParam
    @Metadata(required = "false")
    private String traceLevel;

    @UriParam
    @Metadata(required = "false")
    private Boolean ctgDebug = Boolean.FALSE;

    @UriParam(defaultValue = "Cp1145")
    @Metadata(required = "false")
    private String encoding = "Cp1145"; // "Cp285";

    /** Other parameters */
    private Map<String, Object> parameters;

    /**
     * @param endpointUri
     * @param component
     */
    public CICSEndpoint(String endpointUri, Component component) {
        super(endpointUri, component);

        LOGGER.info("New CICS Endpoint with endpointUri: {}", endpointUri);

        // Extract remaining
        int startOptions = endpointUri.indexOf("?");

        String remaining = "";
        if (startOptions == -1) {
            remaining = endpointUri.substring(getScheme().length() + 3);
        } else {
            remaining = endpointUri.substring(getScheme().length() + 3, startOptions);
        }

        // Apply host, port and server properties
        setRemaining(remaining);
    }

    /**
     * @param endpointUri
     * @param component
     * @param remaining
     *            arde.intrallianz.es:2006/CI0ARDE
     * @param parameters
     *            {ServerName=CI0ARDE, TraceLevel=9}
     */
    public CICSEndpoint(String endpointUri, CICSComponent component, String remaining, Map<String, Object> parameters) {
        super(endpointUri, component);

        LOGGER.info("New CICS Endpoint with endpointUri: {}. remaining: {}, parameters: {}", endpointUri, remaining, parameters);

        // Apply host, port and server properties
        setRemaining(remaining);

        // Set connection parameters
        this.parameters = parameters;
    }

    /**
     * 
     * @return A new instance of {@link CICSProducer}
     * 
     * @see org.apache.camel.Endpoint#createProducer()
     */
    @Override
    public Producer createProducer() throws Exception {
        return new CICSProducer(this);
    }

    /**
     * This component is only for use as a Producer, not as a Consumer
     * 
     * @return <code>null</code>
     * 
     * @see org.apache.camel.Endpoint#createConsumer(org.apache.camel.Processor)
     */
    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        return null;
    }

    /**
     * @return <code>true</code>
     * 
     * @see org.apache.camel.IsSingleton#isSingleton()
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

    /* GETTERS/SETTERS */

    /**
     * @return "cics"
     */
    public String getScheme() {
        return "cics";
    }

    /**
     * Evaluate remaining uri string and set host, port and program properties
     * 
     * @param remaining
     *            String with connection string in <code>host[:port]/server</code> format
     */
    public void setRemaining(String remaining) {
        LOGGER.info("Getting host, port and server from remaining {}", remaining);

        int startPort = remaining.indexOf(":");
        int startServer = remaining.indexOf("/");

        // Evaluate port section
        if (startPort != -1) {
            this.host = remaining.substring(0, startPort);
            this.port = Integer.parseInt(remaining.substring(startPort + 1, startServer));
        } else {
            this.host = remaining.substring(0, startServer);
        }

        this.server = remaining.substring(startServer + 1);
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String serverName) {
        this.server = serverName;
    }

    /**
     * Optional parameters to be used by endpoints
     * 
     * @param parameters
     *            parameters
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSslPassword() {
        return sslPassword;
    }

    public void setSslPassword(String sslPassword) {
        this.sslPassword = sslPassword;
    }

    public String getSslKeyring() {
        return sslKeyring;
    }

    public void setSslKeyring(String sslKeyring) {
        this.sslKeyring = sslKeyring;
    }

    public String getTraceLevel() {
        return traceLevel;
    }

    public void setTraceLevel(String traceLevel) {
        this.traceLevel = traceLevel;
    }

    public Boolean getCtgDebug() {
        return ctgDebug;
    }

    public void setCtgDebug(Boolean ctgDebugOn) {
        this.ctgDebug = ctgDebugOn;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CICSEndpoint [host=");
        builder.append(host);
        builder.append(", port=");
        builder.append(port);
        builder.append(", server=");
        builder.append(server);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", password=");
        builder.append("* PASSWORD *");
        builder.append(", sslPassword=");
        builder.append("* SSL PASSWORD *");
        builder.append(", sslKeyring=");
        builder.append(sslKeyring);
        builder.append(", traceLevel=");
        builder.append(traceLevel);
        builder.append(", ctgDebug=");
        builder.append(ctgDebug);
        builder.append(", encoding=");
        builder.append(encoding);
        builder.append(", parameters=");
        builder.append(parameters);
        builder.append("]");
        return builder.toString();
    }

}
