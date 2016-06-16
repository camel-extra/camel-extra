package org.apacheextras.camel.component.wmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;

public class WMQTransactionManager extends AbstractPlatformTransactionManager{

	private final static Logger LOGGER = LoggerFactory.getLogger(WMQTransactionManager.class);
	
	private WMQConfig config;
	
	
	
	/*private MQQueueManager queueManager;
	
	public MQQueueManager getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(MQQueueManager queueManager) {
		this.queueManager = queueManager;
	}*/

	public WMQConfig getConfig() {
		return config;
	}

	public void setConfig(WMQConfig config) {
		this.config = config;
	}

	@Override
	protected void doBegin(Object arg0, TransactionDefinition arg1) throws TransactionException {
		// TODO Auto-generated method stub
		//setTransactionSynchronizationName("WMQTransaction:"+UUID.randomUUID().toString());
		WMQTransactionObject obj = (WMQTransactionObject) arg0;
		LOGGER.debug("Begin called on thread " + Thread.currentThread().getId());
		TransactionSynchronizationManager.setCurrentTransactionName("WMQTransaction:"+obj.getId());
		try {
			MQQueueManager manager = getConfig().createMQQueueManager();
			TransactionSynchronizationManager.bindResource("queueManager", manager);
			TransactionSynchronizationManager.bindResource("id",obj.getId());
			obj.setManager(manager);
			LOGGER.debug("Obegin id is -> " + obj.getId());
			LOGGER.debug("Obegin queueManager -> " + obj.getManager().toString());
		} catch (IllegalStateException e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
		} catch (MQException e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void doCommit(DefaultTransactionStatus arg0) throws TransactionException {
		LOGGER.debug("Commit called on thread " + Thread.currentThread().getId());
		MQQueueManager queueManager = (MQQueueManager)TransactionSynchronizationManager.getResource("queueManager");
		String id = (String)TransactionSynchronizationManager.getResource("id");
		
		LOGGER.debug("OcommitTHREADQueueManager -> " + queueManager.toString());
		LOGGER.debug("OcommitTHREADID -> " + id);
		
		WMQTransactionObject o = (WMQTransactionObject)arg0.getTransaction();
		LOGGER.debug("Ocommit id is -> " + o.getId());
		LOGGER.debug("Ocommit queueManager -> " + o.getManager().toString());
		
		
		if (queueManager != null) {
			try {
				queueManager.commit();
				queueManager.disconnect();
			} catch (MQException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TransactionSynchronizationManager.unbindResource("queueManager");
			TransactionSynchronizationManager.unbindResource("id");
		}
		
	/*	// TODO Auto-generated method stub
		LOGGER.info("commit called");
		try {
			LOGGER.debug("Attempting to get queue mananger for this transaction");
						
			@SuppressWarnings("unchecked")
			Map<String,MQQueueManager> queueManagers = (Map<String,MQQueueManager>)TransactionSynchronizationManager.getResource("queueManagers");
			
			if (queueManagers != null) {
				LOGGER.debug("Set size is -> " + queueManagers.size());
				for(Entry<String,MQQueueManager> entry: queueManagers.entrySet()){
					LOGGER.debug("Committing individual queueMananger instance " + entry.getKey());
					MQQueueManager manager = entry.getValue();
					manager.commit();
				}
				TransactionSynchronizationManager.unbindResource("queueManagers");
			} else {
				LOGGER.debug("QueueManager size is 0");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	*/	
	}

	@Override
	protected Object doGetTransaction() throws TransactionException {
		// TODO Auto-generated method stub
		
		LOGGER.info("doGetTransaction called on thread " + Thread.currentThread().getId());
		WMQTransactionObject transaction = new WMQTransactionObject();
		MQQueueManager queueManager = (MQQueueManager)TransactionSynchronizationManager.getResource("queueManager");
		String id = (String)TransactionSynchronizationManager.getResource("id");
		if(queueManager != null) {
			transaction.setManager(queueManager);
			transaction.setId(id);
		}
		
		return transaction;
		//return null;
	}
	
	@Override
	protected boolean isExistingTransaction(Object transaction) {
		LOGGER.debug("isExistingTransaction called on thread " + Thread.currentThread().getId());
		WMQTransactionObject object = (WMQTransactionObject) transaction;
		LOGGER.debug("Get mananger -> " + object.getManager());
		LOGGER.debug("Get id -> " + object.getId());
		return object.getManager() != null; 
	}

	@Override
	protected void doRollback(DefaultTransactionStatus arg0) throws TransactionException {
		// TODO Auto-generated method stub
		//LOGGER.info("doRollback called");
		try {
			//LOGGER.debug("Attempting to get queue mananger for this transaction");
			
			MQQueueManager queueManager = (MQQueueManager)TransactionSynchronizationManager.getResource("queueManager");
			queueManager.backout();
			TransactionSynchronizationManager.unbindResource("queueManager");
		} catch (MQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
