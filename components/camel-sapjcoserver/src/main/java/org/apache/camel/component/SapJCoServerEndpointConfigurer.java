/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.ExtendedPropertyConfigurerGetter;
import org.apache.camel.spi.PropertyConfigurerGetter;
import org.apache.camel.spi.ConfigurerStrategy;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.util.CaseInsensitiveMap;
import org.apache.camel.support.component.PropertyConfigurerSupport;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@SuppressWarnings("unchecked")
public class SapJCoServerEndpointConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        SapJCoServerEndpoint target = (SapJCoServerEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": target.setBridgeErrorHandler(property(camelContext, boolean.class, value)); return true;
        case "exceptionhandler":
        case "exceptionHandler": target.setExceptionHandler(property(camelContext, org.apache.camel.spi.ExceptionHandler.class, value)); return true;
        case "exchangepattern":
        case "exchangePattern": target.setExchangePattern(property(camelContext, org.apache.camel.ExchangePattern.class, value)); return true;
        case "ashost": target.setAshost(property(camelContext, String.class, value)); return true;
        case "client": target.setClient(property(camelContext, String.class, value)); return true;
        case "lang": target.setLang(property(camelContext, String.class, value)); return true;
        case "sysnr": target.setSysnr(property(camelContext, String.class, value)); return true;
        case "username": target.setUsername(property(camelContext, String.class, value)); return true;
        case "password": target.setPassword(property(camelContext, String.class, value)); return true;
        case "network": target.setNetwork(property(camelContext, String.class, value)); return true;
        case "connectionCount": target.setConnectionCount(property(camelContext, String.class, value)); return true;
        case "gwhost": target.setGwhost(property(camelContext, String.class, value)); return true;
        case "gwserv": target.setGwserv(property(camelContext, String.class, value)); return true;
        case "progid": target.setProgid(property(camelContext, String.class, value)); return true;
        case "repositoryDestination": target.setRepositoryDestination(property(camelContext, String.class, value)); return true;
        case "rfcName": target.setRfcName(property(camelContext, String.class, value)); return true;
        case "rfcExportparamExist": target.setRfcExportparamExist(property(camelContext, boolean.class, value)); return true;
        case "rfcImportparamExist": target.setRfcImportparamExist(property(camelContext, boolean.class, value)); return true;
        case "rfcTableparamExist": target.setRfcTableparamExist(property(camelContext, boolean.class, value)); return true;
        
        default: return false;
        }
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return boolean.class;
        case "exceptionhandler":
        case "exceptionHandler": return org.apache.camel.spi.ExceptionHandler.class;
        case "exchangepattern":
        case "exchangePattern": return org.apache.camel.ExchangePattern.class;
        case "ashost": return String.class;
        case "client": return String.class;
        case "lang": return String.class;
        case "sysnr": return String.class;
        case "username": return String.class;
        case "password": return String.class;
        case "network": return String.class;
        case "connectioncount":
        case "connectionCount": return String.class;
        case "gwhost": return String.class;
        case "gwserv": return String.class;
        case "progid": return String.class;
        case "repositoryDestination": return String.class;
        case "rfcName": return String.class;
        case "rfcExportparamExist": return boolean.class;  
        case "rfcImportparamExist": return boolean.class;  
        case "rfcTableparamExist": return boolean.class;
        
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        SapJCoServerEndpoint target = (SapJCoServerEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return target.isBridgeErrorHandler();
        case "exceptionhandler":
        case "exceptionHandler": return target.getExceptionHandler();
        case "exchangepattern":
        case "exchangePattern": return target.getExchangePattern();
        case "ashost":return target.getAshost();
        case "client":return target.getClient();
        case "lang":return target.getAshost();
        case "sysnr":return target.getSysnr();
        case "username":return target.getUsername();
        case "password":return target.getPassword();
        case "network":return target.getNetwork();
        case "connectioncount":
        case "connectionCount":return target.getConnectionCount();
        case "gwhost":return target.getGwhost();
        case "gwserv":return target.getGwserv();
        case "progid":return target.getProgid();
        case "repositoryDestination":return target.getRepositoryDestination();
        case "rfcName":return target.getRfcName();
        case "rfcExportparamExist":return target.isRfcExportparamExist();
        case "rfcImportparamExist":return target.isRfcImportparamExist();
        case "rfcTableparamExist":return target.isRfcTableparamExist();
        
        default: return null;
        }
    }
}

