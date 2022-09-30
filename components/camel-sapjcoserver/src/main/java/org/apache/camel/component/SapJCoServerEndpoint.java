package org.apache.camel.component;

import org.apache.camel.Category;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.support.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

import java.util.concurrent.ExecutorService;

/**
 * SapJCoServer component which does bla bla.
 *
 * TODO: Update one line description above what the component does.
 */
@UriEndpoint(firstVersion = "1.0-SNAPSHOT", scheme = "SapJCoServer", title = "SapJCoServer", syntax="SapJCoServer:systemName",
             category = {Category.SAP})
public class SapJCoServerEndpoint extends DefaultEndpoint {

    @UriPath @Metadata(required = true)
    private String systemName;
    
    // @UriParam(defaultValue = "true", label = "advanced")
    @UriParam (label = "common", description  = "ip or name for ashost") @Metadata(required = true)
    private String ashost;
    

    @UriParam (label = "common", description  = "sap client id") @Metadata(required = true)
    private String client;
    
    @UriParam (label = "common", description  = "language", defaultValue ="en") 
    private String lang="en";

    @UriParam (label = "common", description  = "sap jco client sysnr")  @Metadata(required = true)
    private String sysnr;

    @UriParam (label = "common", description  = "sap jco client username")  @Metadata(required = true)
    private String username;

    @UriParam (label = "common", description  = "sap jco client password")  @Metadata(required = true)
    private String password;
    
    @UriParam (label = "common", description  = "sap jco client network" , defaultValue ="lan")  
    private String network="lan";
    
    @UriParam (label = "common", description  = "sap jco client connectionCount" , defaultValue ="2")  
    private String connectionCount="2";
    
    @UriParam (label = "common", description  = "sap jco server ghost" )   @Metadata(required = true)
    private String gwhost;
    
    @UriParam (label = "common", description  = "sap jco server gwserv" )   @Metadata(required = true)
    private String gwserv;
    
    @UriParam (label = "common", description  = "sap jco server pgrogid" )   @Metadata(required = true)
    private String progid;
    
    @UriParam (label = "common", description  = "sap jco server repositoryDestination" )   @Metadata(required = true)
    private String repositoryDestination;
    
    @UriParam (label = "common", description  = "sap rfc function name" )   @Metadata(required = true)
    private String rfcName;
    
    @UriParam (label = "common", description  = "sap rfc function if export param exsting" )   @Metadata(required = true)
    private boolean rfcExportparamExist;

    
    @UriParam (label = "common", description  = "sap rfc function if import param exsting" )   @Metadata(required = true)
    private boolean rfcImportparamExist;
    
    @UriParam (label = "common", description  = "sap rfc function if table param exsting" )   @Metadata(required = true)
    private boolean rfcTableparamExist;
    
    
    public SapJCoServerEndpoint() {
    }

    public SapJCoServerEndpoint(String uri, SapJCoServerComponent component) {
        super(uri, component);
    }
    
 
    public Consumer createConsumer(Processor processor) throws Exception {
        Consumer consumer = new SapJCoServerConsumer(this, processor);
        configureConsumer(consumer);
        return consumer;
    }


    
    public ExecutorService createExecutor() {
        // TODO: Delete me when you implemented your custom component
        return getCamelContext().getExecutorServiceManager().newSingleThreadExecutor(this, "SapJCoServerConsumer");
    }

	@Override
	public Producer createProducer() throws Exception {
		 throw new RuntimeCamelException("Cannot produce to a SapJCoServerEndpoint: " + getEndpointUri());
	}
	
	
    public String getSystemName() {
    	if (systemName == null) {
    		 systemName = getEndpointUri();
        }
		return systemName;
	}

	
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getAshost() {
		return ashost;
	}

	public void setAshost(String ashost) {
		this.ashost = ashost;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSysnr() {
		return sysnr;
	}

	public void setSysnr(String sysnr) {
		this.sysnr = sysnr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	
	public String getConnectionCount() {
		return connectionCount;
	}

	public void setConnectionCount(String connectionCount) {
		this.connectionCount = connectionCount;
	}

	public String getGwhost() {
		return gwhost;
	}

	public void setGwhost(String gwhost) {
		this.gwhost = gwhost;
	}



	public String getGwserv() {
		return gwserv;
	}

	public void setGwserv(String gwserv) {
		this.gwserv = gwserv;
	}

	public String getProgid() {
		return progid;
	}

	public void setProgid(String progid) {
		this.progid = progid;
	}

	public String getRepositoryDestination() {
		return repositoryDestination;
	}

	public void setRepositoryDestination(String repositoryDestination) {
		this.repositoryDestination = repositoryDestination;
	}

	public String getRfcName() {
		return rfcName;
	}

	public void setRfcName(String rfcName) {
		this.rfcName = rfcName;
	}

	public boolean isRfcExportparamExist() {
		return rfcExportparamExist;
	}

	public void setRfcExportparamExist(boolean rfcExportparamExist) {
		this.rfcExportparamExist = rfcExportparamExist;
	}

	public boolean isRfcImportparamExist() {
		return rfcImportparamExist;
	}

	public void setRfcImportparamExist(boolean rfcImportparamExist) {
		this.rfcImportparamExist = rfcImportparamExist;
	}

	public boolean isRfcTableparamExist() {
		return rfcTableparamExist;
	}

	public void setRfcTableparamExist(boolean rfcTableparamExist) {
		this.rfcTableparamExist = rfcTableparamExist;
	}
}
