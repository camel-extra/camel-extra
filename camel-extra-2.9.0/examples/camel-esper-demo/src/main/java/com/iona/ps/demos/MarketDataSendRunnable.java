package com.iona.ps.demos;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarketDataSendRunnable implements Runnable
{
    private static final Logger log = LoggerFactory.getLogger(MarketDataSendRunnable.class);

    private volatile FeedEnum rateDropOffFeed;
    private volatile boolean isShutdown;
    private Random random = new Random();
    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = "tcp://localhost:61616";
    private boolean transacted = false;
    private String subject = "EventStreamQueue";
    private boolean persistent = true;
    
    public MarketDataSendRunnable()
    {
    }

    public void run()
    {
        log.info(".call Thread " + Thread.currentThread() + " starting");
     
        try
        {
            Connection connection = null;
            Destination destination = null;
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
     		connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(subject);
            MessageProducer producer = session.createProducer(destination);
            if (persistent) {
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            }
            while(!isShutdown)
            {
                int nextFeed = Math.abs(random.nextInt() % 2);
                FeedEnum feed = FeedEnum.values()[nextFeed];
                if (rateDropOffFeed != feed)
                {
                	ObjectMessage message = session.createObjectMessage();
                	message.setObject(new MarketDataEvent("SYM", feed));
           			producer.send(message);
                }
            }
            producer.close();
            session.close();
            connection.close();
        }
        catch (RuntimeException ex)
        {
            log.error("Error in send loop", ex);
        } catch (JMSException e) {
			e.printStackTrace();
		}

        log.info(".call Thread " + Thread.currentThread() + " done");
    }

    public void setRateDropOffFeed(FeedEnum feedToDrop)
    {
        rateDropOffFeed = feedToDrop;
    }

    public void setShutdown()
    {
        isShutdown = true;
    }
}
