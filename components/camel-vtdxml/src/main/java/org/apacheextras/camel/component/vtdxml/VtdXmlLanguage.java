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

import org.apache.camel.Expression;
import org.apache.camel.IsSingleton;
import org.apache.camel.Predicate;
import org.apache.camel.spi.Language;

/**
 *
 */
public class VtdXmlLanguage implements Language, IsSingleton {

    @Override
    public Predicate createPredicate(String expression) {
        VtdXmlXPathBuilder builder = VtdXmlXPathBuilder.xpath(expression);
        configureBuilder(builder);
        return builder;
    }

    @Override
    public Expression createExpression(String expression) {
        VtdXmlXPathBuilder builder = VtdXmlXPathBuilder.xpath(expression);
        configureBuilder(builder);
        return builder;
    }

    protected void configureBuilder(VtdXmlXPathBuilder builder) {
        // noop
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
