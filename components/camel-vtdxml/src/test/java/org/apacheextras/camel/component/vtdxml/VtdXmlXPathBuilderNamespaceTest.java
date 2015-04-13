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
public class VtdXmlXPathBuilderNamespaceTest extends ExchangeTestSupport {

    @Test
    public void testVtdXmlXPathBuilderEvaluate() throws Exception {
        String data = context.getTypeConverter().convertTo(String.class, new File("src/test/data/persons.xml"));
        exchange.getIn().setBody(data);

        VtdXmlXPathBuilder xpath = new VtdXmlXPathBuilder("//ns1:*/person");
        xpath.namespace("ns1", "http://acme.com");
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
        String data = context.getTypeConverter().convertTo(String.class, new File("src/test/data/persons.xml"));
        exchange.getIn().setBody(data);

        VtdXmlXPathBuilder xpath = new VtdXmlXPathBuilder("//ns1:*/person/name = 'James Strachan'");
        xpath.namespace("ns1", "http://acme.com");
        boolean matches = xpath.matches(exchange);
        assertTrue("Should match", matches);

        xpath = new VtdXmlXPathBuilder("/persons/person/name = 'Donald Duck'");
        matches = xpath.matches(exchange);
        assertFalse("Should not match", matches);
    }

}
