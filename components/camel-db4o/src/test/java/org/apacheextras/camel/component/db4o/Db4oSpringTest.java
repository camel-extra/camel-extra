/**************************************************************************************
 * https://camel-extra.github.io
 * <p/>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 * <p/>
 * http://www.gnu.org/licenses/gpl-2.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.db4o;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;

@ContextConfiguration
public class Db4oSpringTest extends AbstractJUnit4SpringContextTests {

  @EndpointInject(uri = "mock:result")
  protected MockEndpoint resultEndpoint;

  @Autowired
  protected ProducerTemplate template;

  @Test
  public void testProducerInsertsIntoDatabaseThenConsumerFiresMessageExchange() throws Exception {
    String name = "foo";
    template.sendBody(new PersonToStore(name));

    resultEndpoint.expectedMessageCount(1);
    resultEndpoint.assertIsSatisfied();
    PersonToStore message = (PersonToStore) resultEndpoint.getReceivedExchanges().get(0).getIn().getBody();
    assertEquals(name, message.name);
  }
}
