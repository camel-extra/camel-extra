/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.db4o;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

@ContextConfiguration
public class Db4oSpringTest extends AbstractJUnit38SpringContextTests {

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Autowired
    protected ProducerTemplate template;

    public void testProducerInsertsIntoDatabaseThenConsumerFiresMessageExchange() throws Exception {
        String name = "foo";
        template.sendBody(new PersonToStore(name));

        resultEndpoint.expectedMessageCount(1);
        resultEndpoint.assertIsSatisfied();
        PersonToStore message = (PersonToStore) resultEndpoint.getReceivedExchanges().get(0).getIn().getBody();
        assertEquals(name, message.name);
    }
}
