/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.db4o;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

/**
 * @version $Revision$
 */
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
