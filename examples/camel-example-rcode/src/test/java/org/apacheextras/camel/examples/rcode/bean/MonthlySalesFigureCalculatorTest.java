/**
 * ************************************************************************************
 * https://camel-extra.github.io
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 * http://www.gnu.org/licenses/gpl-2.0-standalone.html
 **************************************************************************************
 */
package org.apacheextras.camel.examples.rcode.bean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class MonthlySalesFigureCalculatorTest
 */
public class MonthlySalesFigureCalculatorTest extends Assert {

    private MonthlySalesFigureCalculator salesCalculator = new MonthlySalesFigureCalculator();

    private List<List> salesDate = new ArrayList<List>();

    @Before
    public void init() {
        salesDate.clear();
    }

    @Test
    public void summarizeWrongDate() {

        final List date = new ArrayList<String>();
        date.add(new StringBuilder().append(1).toString());
        date.add(new StringBuilder().append(100).toString());
        salesDate.add(date);

        try {
            salesCalculator.summarize(salesDate);
        } catch (Exception ex) {
            assertTrue("Did not receive an apropriate ", (ex instanceof ParseException));
        }
    }

    @Test
    public void summarizeValidDate() {
        for (int i = 0; i < 10; i++) {
            List date = new ArrayList<String>();
            date.add(new StringBuilder().append('0').append(i).append("/01/2014").toString());
            date.add(new StringBuilder().append(i * 100).toString());
            salesDate.add(date);
        }

        String rCode = salesCalculator.summarize(salesDate);
        assertEquals("Comma separated values not received", "0,100,200,300,400,500,600,700,800,900", rCode);
    }
}
