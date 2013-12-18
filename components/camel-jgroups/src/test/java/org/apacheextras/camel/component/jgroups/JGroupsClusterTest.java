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
package org.apacheextras.camel.component.jgroups;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apacheextras.camel.component.jgroups.JGroupsFilters.dropNonCoordinatorViews;


public class JGroupsClusterTest extends Assert {

    // Routing fixtures

    DefaultCamelContext firstCamelContext;

    DefaultCamelContext secondCamelContext;

    String master;

    int nominationCount;

    class Builder extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("jgroups:cluster?enableViewMessages=true").
                    filter(dropNonCoordinatorViews()).
                    process(new Processor() {
                        @Override
                        public void process(Exchange exchange) throws Exception {
                            String camelContextName = exchange.getContext().getName();
                            if (!camelContextName.equals(master)) {
                                master = camelContextName;
                                nominationCount++;
                            }
                        }
                    });
        }
    }

    @Before
    public void setUp() throws Exception {
        firstCamelContext = new DefaultCamelContext();
        firstCamelContext.setName("firstNode");
        firstCamelContext.addRoutes(new Builder());

        secondCamelContext = new DefaultCamelContext();
        secondCamelContext.setName("secondNode");
        secondCamelContext.addRoutes(new Builder());
    }

    // Tests

    @Test
    public void shouldElectSecondNode() throws Exception {
        // When
        firstCamelContext.start();
        String firstMaster = master;
        secondCamelContext.start();
        firstCamelContext.stop();
        String secondMaster = master;
        secondCamelContext.stop();

        // Then
        assertEquals(firstCamelContext.getName(), firstMaster);
        assertEquals(secondCamelContext.getName(), secondMaster);
        assertEquals(2, nominationCount);
    }

    @Test
    public void shouldKeepMaster() throws Exception {
        // When
        firstCamelContext.start();
        String firstMaster = master;
        secondCamelContext.start();
        secondCamelContext.stop();
        String secondMaster = master;
        firstCamelContext.stop();

        // Then
        assertEquals(firstCamelContext.getName(), firstMaster);
        assertEquals(firstCamelContext.getName(), secondMaster);
        assertEquals(1, nominationCount);
    }

    @Test
    public void shouldElectSecondNodeAndReturnToFirst() throws Exception {
        // When
        firstCamelContext.start();
        String firstMaster = master;
        secondCamelContext.start();
        firstCamelContext.stop();
        String secondMaster = master;
        firstCamelContext.start();
        String masterAfterRestartOfFirstNode = master;
        secondCamelContext.stop();
        String finalMaster = master;
        firstCamelContext.stop();

        // Then
        assertEquals(firstCamelContext.getName(), firstMaster);
        assertEquals(secondCamelContext.getName(), secondMaster);
        assertEquals(secondCamelContext.getName(), masterAfterRestartOfFirstNode);
        assertEquals(firstCamelContext.getName(), finalMaster);
        assertEquals(3, nominationCount);
    }

}
