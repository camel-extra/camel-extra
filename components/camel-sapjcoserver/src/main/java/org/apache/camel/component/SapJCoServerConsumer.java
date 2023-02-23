package org.apache.camel.component;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.util.AbapCallHandler;
import org.apache.camel.component.util.SapDestinationDataProvider;
import org.apache.camel.component.util.SapServerDataProvider;
import org.apache.camel.support.DefaultConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.ServerDataProvider;
import com.sap.conn.jco.server.DefaultServerHandlerFactory;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerContextInfo;
import com.sap.conn.jco.server.JCoServerErrorListener;
import com.sap.conn.jco.server.JCoServerExceptionListener;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerState;
import com.sap.conn.jco.server.JCoServerStateChangedListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

public class SapJCoServerConsumer extends DefaultConsumer implements JCoServerErrorListener, JCoServerExceptionListener, JCoServerStateChangedListener{
    private final SapJCoServerEndpoint endpoint;
    private static Logger logger = LoggerFactory.getLogger(SapJCoServerConsumer.class);

    SapDestinationDataProvider myDestinationDataProvider=new SapDestinationDataProvider();
    SapServerDataProvider myServerDataProvider=new SapServerDataProvider() ;

    private ExecutorService executorService;
    
    private String sapSystemName;

    public SapJCoServerConsumer(SapJCoServerEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
    }

    private void initjco() {
    	logger.info("####### initjco was called #########");
		Properties destinationProperties = new Properties();
		destinationProperties.setProperty(DestinationDataProvider.JCO_ASHOST, endpoint.getAshost());
		destinationProperties.setProperty(DestinationDataProvider.JCO_SYSNR, endpoint.getSysnr());
		destinationProperties.setProperty(DestinationDataProvider.JCO_CLIENT, endpoint.getClient());
		destinationProperties.setProperty(DestinationDataProvider.JCO_USER, endpoint.getUsername());
		destinationProperties.setProperty(DestinationDataProvider.JCO_PASSWD, endpoint.getPassword());
		destinationProperties.setProperty(DestinationDataProvider.JCO_LANG, endpoint.getLang());
		destinationProperties.setProperty(DestinationDataProvider.JCO_NETWORK, endpoint.getNetwork());
		
		
		Properties serverProperties = new Properties();

		serverProperties.setProperty(ServerDataProvider.JCO_CONNECTION_COUNT, endpoint.getConnectionCount());
		serverProperties.setProperty(ServerDataProvider.JCO_GWHOST, endpoint.getGwhost());
		serverProperties.setProperty(ServerDataProvider.JCO_GWSERV, endpoint.getGwserv());
		serverProperties.setProperty(ServerDataProvider.JCO_PROGID, endpoint.getProgid());
		serverProperties.setProperty(ServerDataProvider.JCO_REP_DEST, endpoint.getRepositoryDestination());
			
		sapSystemName=endpoint.getSystemName().trim();
		logger.info("sapSystemName->{}",sapSystemName);
		myDestinationDataProvider.addDestination(sapSystemName, destinationProperties);
		com.sap.conn.jco.ext.Environment.registerDestinationDataProvider(myDestinationDataProvider);
		myServerDataProvider.addServer(sapSystemName, serverProperties);
		com.sap.conn.jco.ext.Environment.registerServerDataProvider(myServerDataProvider);
		
	}
    
    
    @Override
    protected void doStart() throws Exception {
    	logger.info("####### doStart was called #########");
        super.doStart();        
        // start a single threaded pool to monitor events
     
        
        initjco();

        
        JCoServer server;
		try {
			server = JCoServerFactory.getServer(
					sapSystemName);
		} catch (JCoException e) {
			throw new RuntimeException("Unable to create the server "
					+ endpoint.getSystemName()
					+ ", because of " + e.getMessage(), e);
		}
		
		AbapCallHandler abapCallHandler =new AbapCallHandler();
		abapCallHandler.setSapJCoServerConsumer(this);
		abapCallHandler.setExportParamExisted(endpoint.isRfcExportparamExist());
		abapCallHandler.setImportParamExisted(endpoint.isRfcImportparamExist());
		abapCallHandler.setTableParamExisted(endpoint.isRfcTableparamExist());

		DefaultServerHandlerFactory.FunctionHandlerFactory factory = new DefaultServerHandlerFactory.FunctionHandlerFactory();
		factory.registerHandler(endpoint.getRfcName(), abapCallHandler);
		server.setCallHandlerFactory(factory);

		// Add listener for errors.
		server.addServerErrorListener(this);
		// Add listener for exceptions.
		server.addServerExceptionListener(this);
		// Add server state change listener.
		server.addServerStateChangedListener(this);

		executorService = endpoint.createExecutor();
        // submit task to the thread pool
        executorService.submit(() -> {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			try {
				while ((line = br.readLine()) != null) {
					// Check if the server should be ended.
					if (line.equalsIgnoreCase("end")) {
						// Stop the server.
						server.stop();
					}
				}
			} catch (IOException e) {
				logger.error("IOException ->{}",e);
			}
        });
        
        server.start();
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();

        // shutdown the thread pool gracefully
        getEndpoint().getCamelContext().getExecutorServiceManager().shutdownGraceful(executorService);
    }

    public void onEventListener(final Object event) {
        final Exchange exchange = createExchange(false);

        exchange.getIn().setBody(event.toString());

        try {
            // send message to next processor in the route
            getProcessor().process(exchange);
        } catch (Exception e) {
            exchange.setException(e);
        } finally {
            if (exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
            releaseExchange(exchange, false);
        }
    }

    @Override
	public void serverExceptionOccurred(JCoServer jcoServer, String connectionId, JCoServerContextInfo arg2,
			Exception exception) {
		logger.error("Exception occured on " + jcoServer.getProgramID() + " connection " + connectionId, exception);
	}

	@Override
	public void serverErrorOccurred(JCoServer jcoServer, String connectionId, JCoServerContextInfo arg2, Error error) {
		logger.error("Error occured on " + jcoServer.getProgramID() + " connection " + connectionId, error);
	}

	@Override
	public void serverStateChangeOccurred(JCoServer server, JCoServerState oldState, JCoServerState newState) {
		// Defined states are: STARTED, DEAD, ALIVE, STOPPED;
		// see JCoServerState class for details.
		// Details for connections managed by a server instance
		// are available via JCoServerMonitor
		logger.info("Server state changed from " + oldState.toString() + " to " + newState.toString()
				+ " on server with program id " + server.getProgramID());
		if (newState.equals(JCoServerState.ALIVE)) {
			logger.info("Server with program ID '" + server.getProgramID() + "' is running");
		}
		if (newState.equals(JCoServerState.STOPPED)) {
			logger.info("Exit program");
			System.exit(0);
		}
	}
}
