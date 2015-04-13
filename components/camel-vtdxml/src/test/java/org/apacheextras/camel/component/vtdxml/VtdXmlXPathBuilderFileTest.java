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
package org.apacheextras.camel.component.vtdxml;

import java.io.File;
import java.util.Iterator;

import org.apache.camel.test.junit4.ExchangeTestSupport;
import org.apache.camel.util.ObjectHelper;
import org.junit.Test;

/**
 *
 */
public class VtdXmlXPathBuilderFileTest extends ExchangeTestSupport {

    @Test
    public void testVtdXmlXPathBuilderEvaluate() throws Exception {
        exchange.getIn().setBody(new File("src/test/data/persons.xml"));

        VtdXmlXPathBuilder xpath = new VtdXmlXPathBuilder("/persons/person");
        Object result = xpath.evaluate(exchange, Object.class);
        assertNotNull(result);

        Iterator it = ObjectHelper.createIterator(result);
        while (it.hasNext()) {
            Object next = it.next();
            String s = exchange.getContext().getTypeConverter().convertTo(String.class, next);
            log.info("Next:" + s);
        }
    }

    @Test
    public void testVtdXmlXPathBuilderMatches() throws Exception {
        exchange.getIn().setBody(new File("src/test/data/persons.xml"));

        VtdXmlXPathBuilder xpath = new VtdXmlXPathBuilder("/persons/person/name = 'James Strachan'");
        boolean matches = xpath.matches(exchange);
        assertTrue("Should match", matches);

        xpath = new VtdXmlXPathBuilder("/persons/person/name = 'Doald Duck'");
        matches = xpath.matches(exchange);
        assertFalse("Should not match", matches);
    }

}
