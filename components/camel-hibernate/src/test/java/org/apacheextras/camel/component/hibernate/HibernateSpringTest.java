/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

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
package org.apacheextras.camel.component.hibernate;


import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apacheextras.camel.examples.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;
import org.junit.Ignore;

import java.util.List;

@ContextConfiguration
@Ignore
public class HibernateSpringTest extends AbstractJUnit38SpringContextTests {
    private static final transient Logger LOG = LoggerFactory.getLogger(HibernateSpringTest.class);

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Autowired
    protected ProducerTemplate template;


    public void testProducerInsertsIntoDatabaseThenConsumerFiresMessageExchange() throws Exception {
        template.sendBody(new SendEmail("foo@bar.com"));

        resultEndpoint.expectedMessageCount(1);
        resultEndpoint.assertIsSatisfied();
        List<Exchange> list = resultEndpoint.getReceivedExchanges();
        System.out.println("Received: " + list);
    }

}
