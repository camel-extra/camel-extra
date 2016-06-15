package org.apacheextras.camel.component.wmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;

public class WMQTransactionManager extends AbstractPlatformTransactionManager{

	private final static Logger LOGGER = LoggerFactory.getLogger(WMQTransactionManager.class);
	
	private MQQueueManager queueManager;
	
	public MQQueueManager getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(MQQueueManager queueManager) {
		this.queueManager = queueManager;
	}

	@Override
	protected void doBegin(Object arg0, TransactionDefinition arg1) throws TransactionException {
		// TODO Auto-generated method stub
		LOGGER.info("begin called");		
	}

	@Override
	protected void doCommit(DefaultTransactionStatus arg0) throws TransactionException {
		
		// TODO Auto-generated method stub
		LOGGER.info("commit called");
		try {
			queueManager.commit();
		} catch (MQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected Object doGetTransaction() throws TransactionException {
		// TODO Auto-generated method stub
		
		LOGGER.info("doGetTransaction called");
		return new WMQTransactionObject();
		//return null;
	}

	@Override
	protected void doRollback(DefaultTransactionStatus arg0) throws TransactionException {
		// TODO Auto-generated method stub
		LOGGER.info("doRollback called");
		try {
			queueManager.backout();
		} catch (MQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
