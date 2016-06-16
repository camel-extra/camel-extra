package org.apacheextras.camel.component.wmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;

public class WMQTransactionManager extends AbstractPlatformTransactionManager{

	private final static Logger LOGGER = LoggerFactory.getLogger(WMQTransactionManager.class);
	
	private WMQConfig config;

	public WMQConfig getConfig() {
		return config;
	}

	public void setConfig(WMQConfig config) {
		this.config = config;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Responsible for creating a transaction. This method will create a MQQueueManager instance and assign it to the transaction.
	 * 
	 * This MQQueueManager is then used for the entirety of the transaction. 
	 */
	@Override
	protected void doBegin(Object arg0, TransactionDefinition arg1) throws TransactionException {
		LOGGER.debug("Begin called on thread " + Thread.currentThread().getId());
		
		WMQTransactionObject obj = (WMQTransactionObject) arg0;
		
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

	/**
	 * {@inheritDoc}
	 * 
	 * Responsible for committing a transaction. 
	 * This method retrieves the MQQueueManager for this transaction and calls its commit function followed by a disconnect.
	 */
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
	}

	/**
	 * {@inheritDoc}
	 * 
	 * This method is responsible for 2 things based on whether a transaction already exists:
	 * 
	 * If a transaction does not exist: Create a empty WMQTransactionObject
	 * 
	 * If a transaction does exist - this is checked by the presence of a MQQueueManager resource which is bound to the thread:
	 *  Create a empty WMQTransactionObject
	 *  Take the MQQueueManager for this transaction/thread and set it on the TransactionObject
	 *  This case happens if there are multiple parallel transactions using the same transaction manager
	 * 
	 */
	@Override
	protected Object doGetTransaction() throws TransactionException {
		LOGGER.debug("doGetTransaction called on thread " + Thread.currentThread().getId());
		WMQTransactionObject transaction = new WMQTransactionObject();
		MQQueueManager queueManager = (MQQueueManager)TransactionSynchronizationManager.getResource("queueManager");
		String id = (String)TransactionSynchronizationManager.getResource("id");
		if(queueManager != null) {
			transaction.setManager(queueManager);
			transaction.setId(id);
		}
		
		return transaction;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Tells us if a transaction exists or not by checkign the presence of a MQQueueManager on the transaction object
	 */
	@Override
	protected boolean isExistingTransaction(Object transaction) {
		LOGGER.debug("isExistingTransaction called on thread " + Thread.currentThread().getId());
		WMQTransactionObject object = (WMQTransactionObject) transaction;
		LOGGER.debug("Get mananger -> " + object.getManager());
		LOGGER.debug("Get id -> " + object.getId());
		return object.getManager() != null; 
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Responsible for rolling back a transaction. 
	 * This method retrieves the MQQueueManager for this transaction and calls its backout function followed by a disconnect.
	 */
	@Override
	protected void doRollback(DefaultTransactionStatus arg0) throws TransactionException {
		LOGGER.debug("Rollback called on thread " + Thread.currentThread().getId());
		MQQueueManager queueManager = (MQQueueManager)TransactionSynchronizationManager.getResource("queueManager");
		String id = (String)TransactionSynchronizationManager.getResource("id");
		
		LOGGER.debug("OrollbackTHREADQueueManager -> " + queueManager.toString());
		LOGGER.debug("OrollbackTHREADID -> " + id);
		
		WMQTransactionObject o = (WMQTransactionObject)arg0.getTransaction();
		LOGGER.debug("Orollback id is -> " + o.getId());
		LOGGER.debug("Orollback queueManager -> " + o.getManager().toString());
		
		
		if (queueManager != null) {
			try {
				queueManager.backout();
				queueManager.disconnect();
			} catch (MQException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TransactionSynchronizationManager.unbindResource("queueManager");
			TransactionSynchronizationManager.unbindResource("id");
		}
	}

}
