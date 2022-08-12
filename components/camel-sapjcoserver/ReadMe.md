Camel Component Project
=======================

This project is a template of a Camel component.

To build this project use

    mvn install

For more help see the Apache Camel documentation:

    https://camel.apache.org/writing-components.html
    
    https://github.com/apache/camel/tree/main/components/camel-timer
    
    

 
 ### TODO ####
    
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