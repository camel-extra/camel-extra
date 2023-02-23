SapJCoServer
=======================

This component only support Consumer Mode, it can recieved SAPJCO ABAP function's result and wrap it as body in camel Exchange
    
### Run program with -D param as below

```

-Djava.library.path=<SAP_JCO_LIB>   -Dloader.path=<SAP_JCO_LIB>  
```
 
### Example 
    
```

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SapJCoServerRouteBuilder extends RouteBuilder {
	
	private static final Logger LOG = LoggerFactory.getLogger(SapJCoServerRouteBuilder.class);
	public void configure() throws Exception {
		from("SapJCoServer://SapSystemName?"
				+ "ashost=****&"
				+ "client=****&"
				+ "sysnr=01&"
				+ "gwserv=3300&"
				+ "lang=en&"
				+ "network=lan&"
				+ "connectionCount=2&"
				+ "username=****&"
				+ "password=****&"
				+ "gwhost=xx.xx.xx.xxx&"
				+ "gwserv=3300&"
				+ "rfcName=STFC_CONNECTION&"
				+ "progid=*****&"
				+ "repositoryDestination=SapSystemName&"
				+ "rfcExportparamExist=false&"
				+ "rfcImportparamExist=true&"
				+ "rfcTableparamExist=false")
	    .log("Processing ..... ${body}");
	}
}`

```
