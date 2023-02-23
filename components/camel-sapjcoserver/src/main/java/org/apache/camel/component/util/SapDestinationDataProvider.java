package org.apache.camel.component.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

public class SapDestinationDataProvider implements DestinationDataProvider {

	private static final Logger LOG = LoggerFactory.getLogger(SapDestinationDataProvider.class);
	Map<String, Properties> propertiesForDestinationName = new HashMap<String, Properties>();

	public void addDestination(String destinationName, Properties properties) {
		propertiesForDestinationName.put(destinationName, properties);
	}

	public Properties getDestinationProperties(String destinationName) {
		if (propertiesForDestinationName.containsKey(destinationName)) {
			return propertiesForDestinationName.get(destinationName);
		} else {
			throw new RuntimeException("JCo destination not found: " + destinationName);
		}
	}

	@Override
	public boolean supportsEvents() {
		return false;
	}

	@Override
	public void setDestinationDataEventListener(DestinationDataEventListener arg0) {
		// TODO Auto-generated method stub
		
	}
}