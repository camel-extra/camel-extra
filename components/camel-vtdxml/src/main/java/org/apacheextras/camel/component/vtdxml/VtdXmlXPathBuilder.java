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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.ximpleware.AutoPilot;
import com.ximpleware.ModifyException;
import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Predicate;
import org.apache.camel.RuntimeExchangeException;
import org.apache.camel.RuntimeExpressionException;
import org.apache.camel.Service;
import org.apache.camel.spi.NamespaceAware;
import org.apache.camel.util.IOHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class VtdXmlXPathBuilder implements Expression, Predicate, NamespaceAware, Service {

    private static final transient Logger LOG = LoggerFactory.getLogger(VtdXmlXPathBuilder.class);
    private final String text;
    private Map<String, String> namespaces;

    public VtdXmlXPathBuilder(String text) {
        this.text = text;
    }

    public static VtdXmlXPathBuilder xpath(String text) {
        return new VtdXmlXPathBuilder(text);
    }

    @Override
    public String toString() {
        return "VTDXPath: " + text;
    }

    @Override
    public void setNamespaces(Map<String, String> namespaces) {
        this.namespaces = namespaces;
    }

    /**
     * Registers the namespace prefix and URI with the builder so that the
     * prefix can be used in XPath expressions
     *
     * @param prefix is the namespace prefix that can be used in the XPath
     *                expressions
     * @param uri is the namespace URI to which the prefix refers
     * @return the current builder
     */
    public VtdXmlXPathBuilder namespace(String prefix, String uri) {
        if (namespaces == null) {
            namespaces = new HashMap<String, String>();
        }
        namespaces.put(prefix, uri);
        return this;
    }

    @Override
    public boolean matches(Exchange exchange) {
        try {
            Object body = exchange.getIn().getMandatoryBody();
            if (body instanceof File) {
                // optimize for file
                File file = (File) body;
                return matchesFile(exchange, file);
            } else {
                // should be a byte[] array
                byte[] bytes = exchange.getIn().getBody(byte[].class);
                if (bytes == null) {
                    // go from input stream to byte array
                    InputStream is = exchange.getIn().getMandatoryBody(InputStream.class);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    IOHelper.copyAndCloseInput(is, bos);
                    bytes = bos.toByteArray();
                    IOHelper.close(bos);
                }
                return matchesBody(exchange, bytes);
            }
        } catch (Exception e) {
            throw new RuntimeExchangeException("Cannot process xpath " + text, exchange, e);
        }
    }

    private boolean matchesFile(Exchange exchange, File file) throws Exception {
        VTDGen vg = new VTDGen();
        vg.parseFile(file.getAbsolutePath(), true);
        VTDNav vn = vg.getNav();
        AutoPilot ap = new AutoPilot(vn);
        configureXPath(ap);
        return ap.evalXPathToBoolean();
    }

    private boolean matchesBody(Exchange exchange, byte[] body) throws Exception {
        VTDGen vg = new VTDGen();
        vg.setDoc(body);
        vg.parse(true);
        VTDNav vn = vg.getNav();
        AutoPilot ap = new AutoPilot(vn);
        configureXPath(ap);
        return ap.evalXPathToBoolean();
    }

    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {
        Object result;
        try {
            // optimize for file
            Object body = exchange.getIn().getMandatoryBody();
            if (body instanceof File) {
                // optimize for file
                File file = (File) body;
                result = evaluateFile(exchange, file);
            } else {
                // should be a byte[] array
                byte[] bytes = exchange.getIn().getBody(byte[].class);
                if (bytes == null) {
                    // go from input stream to byte array
                    InputStream is = exchange.getIn().getMandatoryBody(InputStream.class);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    IOHelper.copyAndCloseInput(is, bos);
                    try {
                        bytes = bos.toByteArray();
                    } finally {
                        IOHelper.close(bos, "vtdxml", LOG);
                    }
                }
                result = evaluateBody(exchange, bytes);
            }
        } catch (Exception e) {
            throw new RuntimeExpressionException("Cannot evaluate xpath " + text, e);
        }
        return exchange.getContext().getTypeConverter().convertTo(type, result);
    }

    private Object evaluateFile(Exchange exchange, File file) throws Exception {
        VTDGen vg = new VTDGen();
        vg.parseFile(file.getAbsolutePath(), true);
        VTDNav vn = vg.getNav();

        AutoPilot ap = new AutoPilot(vn);
        configureXPath(ap);
        return new VtdXmlIterator(vn, ap);
    }

    private Object evaluateBody(Exchange exchange, byte[] body) throws Exception {
        VTDGen vg = new VTDGen();
        vg.setDoc(body);
        vg.parse(true);
        VTDNav vn = vg.getNav();

        AutoPilot ap = new AutoPilot(vn);
        configureXPath(ap);
        return new VtdXmlIterator(vn, ap);
    }

    protected void configureXPath(AutoPilot ap) throws XPathParseException {
        // set namespaces
        if (namespaces != null) {
            for (Map.Entry<String, String> entry : namespaces.entrySet()) {
                if (LOG.isTraceEnabled()) {
                    LOG.trace("Using namespace {} -> {}", entry.getKey(), entry.getValue());
                }
                ap.declareXPathNameSpace(entry.getKey(), entry.getValue());
            }
        }

        LOG.trace("SelectXPath {}", text);
        ap.selectXPath(text);
    }

    /**
     * Iterator to be used for XPath expressions
     */
    private final class VtdXmlIterator implements Iterator {

        private final VTDNav vn;
        private final AutoPilot ap;
        private int index = -1;
        private volatile boolean nextCalled = true;

        private VtdXmlIterator(VTDNav vn, AutoPilot ap) throws ModifyException {
            this.vn = vn;
            this.ap = ap;
        }

        @Override
        public boolean hasNext() {
            if (nextCalled) {
                try {
                    index = ap.evalXPath();
                } catch (XPathEvalException e) {
                    throw new RuntimeExpressionException("Cannot evaluate xpath " + text, e);
                } catch (NavException e) {
                    throw new RuntimeExpressionException("Cannot navigate xpath " + text, e);
                }
            }

            LOG.trace("hasNext index {}", index);
            nextCalled = false;
            return index != -1;
        }

        @Override
        public Object next() {
            nextCalled = true;
            try {
                // the code below can grab the namespace, to be included, but its much slower
                // ElementFragmentNs ns = vn.getElementFragmentNs();
                // return ns.toBytes();

                // grab the next element, based on offset positioning
                long l = vn.getElementFragment();
                if (l != -1) {
                    int offset = (int) l;
                    int len = (int) (l >> 32);
                    return vn.toRawString(offset, len);
                } else {
                    return null;
                }
            } catch (Exception e) {
                throw new RuntimeException("Cannot extract String from index: " + index, e);
            }
        }

        @Override
        public void remove() {
            // noop
        }
    }

    @Override
    public void start() throws Exception {
        // noop
    }

    @Override
    public void stop() throws Exception {
        // noop
    }

}
