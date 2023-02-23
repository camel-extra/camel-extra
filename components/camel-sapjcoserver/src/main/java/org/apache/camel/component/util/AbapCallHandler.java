package org.apache.camel.component.util;

import java.util.Iterator;

import org.apache.camel.component.SapJCoServerConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

public class AbapCallHandler implements JCoServerFunctionHandler {

	private SapJCoServerConsumer sapJCoServerConsumer;
	public boolean isImportParamExisted;
	public boolean isExportParamExisted;
	public boolean isTableParamExisted;

	private static Logger logger = LoggerFactory.getLogger(AbapCallHandler.class);

	

	public SapJCoServerConsumer getSapJCoServerConsumer() {
		return sapJCoServerConsumer;
	}

	public void setSapJCoServerConsumer(SapJCoServerConsumer sapJCoServerConsumer) {
		this.sapJCoServerConsumer = sapJCoServerConsumer;
	}

	public boolean isImportParamExisted() {
		return isImportParamExisted;
	}

	public void setImportParamExisted(boolean isImportParamExisted) {
		this.isImportParamExisted = isImportParamExisted;
	}

	public boolean isExportParamExisted() {
		return isExportParamExisted;
	}

	public void setExportParamExisted(boolean isExportParamExisted) {
		this.isExportParamExisted = isExportParamExisted;
	}

	public boolean isTableParamExisted() {
		return isTableParamExisted;
	}

	public void setTableParamExisted(boolean isTableParamExisted) {
		this.isTableParamExisted = isTableParamExisted;
	}

	private void printRequestInformation(JCoServerContext serverCtx, JCoFunction function) {
		logger.info("----------------------------------------------------------------");
		logger.info("call              : " + function.getName());
		logger.info("ConnectionId      : " + serverCtx.getConnectionID());
		logger.info("SessionId         : " + serverCtx.getSessionID());
		logger.info("TID               : " + serverCtx.getTID());
		logger.info("repository name   : " + serverCtx.getRepository().getName());
		logger.info("is in transaction : " + serverCtx.isInTransaction());
		logger.info("is stateful       : " + serverCtx.isStatefulSession());
		logger.info("----------------------------------------------------------------");
		logger.info("gwhost: " + serverCtx.getServer().getGatewayHost());
		logger.info("gwserv: " + serverCtx.getServer().getGatewayService());
		logger.info("progid: " + serverCtx.getServer().getProgramID());
		logger.info("----------------------------------------------------------------");
		logger.info("attributes  : ");
		logger.info(serverCtx.getConnectionAttributes().toString());
		logger.info("----------------------------------------------------------------");
	}

	public void handleRequest(JCoServerContext serverCtx, JCoFunction function) {
		// Check if the called function is the supported one.
		
		printRequestInformation(serverCtx, function);

		String xmlResultTable = null;

		if (isImportParamExisted) {
			logger.info("--------------------- Import ParamList ------------------------------------------");
			Iterator<JCoField> importIterator = function.getImportParameterList().iterator();
			StringBuilder importParamBuilder=new StringBuilder();
			while (importIterator != null && importIterator.hasNext()) {
				JCoField importParam = importIterator.next();
				logger.info(importParam.getName() + "  " + importParam.getValue() + "\n ");
				importParamBuilder.append(importParam.getName() + "  " + importParam.getValue() + "\n ");
			}
			getSapJCoServerConsumer().onEventListener(importParamBuilder.toString());
			
		} else {
			logger.info("--------------------- No Import ParamList  ------------------------------------------");
		}

		if (isTableParamExisted) {
			logger.info("--------------------- TableParameterList------------------------------------------");
			JCoParameterList tables = function.getTableParameterList();

			if (tables != null) {
				xmlResultTable = XMLUtil.prettyPrintXml(tables.toXML());
				logger.info("Table Result as XML " + " \n" + XMLUtil.prettyPrintXml(xmlResultTable) + "\n ");
				getSapJCoServerConsumer().onEventListener(XMLUtil.prettyPrintXml(xmlResultTable));
			}
		} else {
			logger.info("--------------------- No TableParameterList------------------------------------------");
		}

		if (isExportParamExisted) {
			logger.info("--------------------- Export ParamList ------------------------------------------");
			Iterator<JCoField> exportIterator = function.getExportParameterList().iterator();
			StringBuilder exportParamBuilder=new StringBuilder();
			while (exportIterator != null && exportIterator.hasNext()) {
				JCoField exportParam = exportIterator.next();
				logger.info(exportParam.getName() + "  " + exportParam.getValue() + "\n ");
				exportParamBuilder.append(exportParam.getName() + "  " + exportParam.getValue() + "\n ");
				getSapJCoServerConsumer().onEventListener(exportParamBuilder.toString());
			}
		} else {
			logger.info("--------------------- No Export ParamList ------------------------------------------");
		}
	}
}