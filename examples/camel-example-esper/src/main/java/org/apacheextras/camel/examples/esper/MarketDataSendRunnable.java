/**************************************************************************************
 https://camel-extra.github.io

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.


 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/gpl-2.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.examples.esper;

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

public class MarketDataSendRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarketDataSendRunnable.class);

    private volatile FeedEnum rateDropOffFeed;
    private volatile boolean isShutdown;
    private Random random = new Random();
    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = "tcp://localhost:61616";
    private boolean transacted = false;
    private String subject = "EventStreamQueue";
    private boolean persistent = true;

    public MarketDataSendRunnable() {
    }

    @Override
    public void run() {
        LOGGER.info(".call Thread " + Thread.currentThread() + " starting");

        try {
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
            while (!isShutdown) {
                int nextFeed = Math.abs(random.nextInt() % 2);
                FeedEnum feed = FeedEnum.values()[nextFeed];
                if (rateDropOffFeed != feed) {
                    ObjectMessage message = session.createObjectMessage();
                    message.setObject(new MarketDataEvent("SYM", feed));
                    producer.send(message);
                }
            }
            producer.close();
            session.close();
            connection.close();
        } catch (RuntimeException ex) {
            LOGGER.error("Error in send loop", ex);
        } catch (JMSException e) {
            LOGGER.error("Detected a JMS error '{}' caused by '{}'", e.getMessage(), e);
        }

        LOGGER.info(".call Thread " + Thread.currentThread() + " done");
    }

    public void setRateDropOffFeed(FeedEnum feedToDrop) {
        rateDropOffFeed = feedToDrop;
    }

    public void setShutdown() {
        isShutdown = true;
    }
}
