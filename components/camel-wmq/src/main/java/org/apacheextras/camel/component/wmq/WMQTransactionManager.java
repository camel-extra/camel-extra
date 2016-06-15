package org.apacheextras.camel.component.wmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;

public class WMQTransactionManager extends AbstractPlatformTransactionManager{

	private final static Logger LOGGER = LoggerFactory.getLogger(WMQTransactionManager.class);
	
	/*private MQQueueManager queueManager;
	
	public MQQueueManager getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(MQQueueManager queueManager) {
		this.queueManager = queueManager;
	}*/

	@Override
	protected void doBegin(Object arg0, TransactionDefinition arg1) throws TransactionException {
		// TODO Auto-generated method stub
		//setTransactionSynchronizationName("WMQTransaction:"+UUID.randomUUID().toString());
		LOGGER.info("begin called");
		LOGGER.info("begin name -> " + arg1.getName());
	}

	@Override
	protected void doCommit(DefaultTransactionStatus arg0) throws TransactionException {
		
		// TODO Auto-generated method stub
		LOGGER.info("commit called");
		try {
			LOGGER.debug("Attempting to get queue mananger for this transaction");
						
			@SuppressWarnings("unchecked")
			Set<MQQueueManager> queueManagers = (Set<MQQueueManager>)TransactionSynchronizationManager.getResource("queueManagers");
			
			LOGGER.debug("Set size is -> " + queueManagers.size());
			
			for(Iterator<MQQueueManager> iterator = queueManagers.iterator();iterator.hasNext();) {
				LOGGER.debug("Committing individual queueManager");
				MQQueueManager queueManager = iterator.next();
				queueManager.commit();
			}
			TransactionSynchronizationManager.unbindResource("queueManagers");
		} catch (Exception e) {
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
			LOGGER.debug("Attempting to get queue mananger for this transaction");
			
			MQQueueManager queueManager = (MQQueueManager)TransactionSynchronizationManager.getResource("queueManager");
			queueManager.backout();
			TransactionSynchronizationManager.unbindResource("queueManager");
		} catch (MQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
