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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.util.StopWatch;
import org.apache.camel.util.TimeUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Uses regular Camel XPath
 */
public class XPathRoutePerformanceTest extends CamelTestSupport {

    private int size = 100 * 1000;
    private final AtomicInteger tiny = new AtomicInteger();
    private final AtomicInteger small = new AtomicInteger();
    private final AtomicInteger med = new AtomicInteger();
    private final AtomicInteger large = new AtomicInteger();
    private final StopWatch watch = new StopWatch();

    @Override
    public void setUp() throws Exception {
        PerformanceHelper.createDataFile(log, size);
        super.setUp();
    }

    @Test
    @Ignore("This is a manual test")
    public void testXPatPerformanceRoute() throws Exception {
        NotifyBuilder notify = new NotifyBuilder(context).whenDone(size).create();

        boolean matches = notify.matches(5, TimeUnit.MINUTES);
        log.info("Processed file with " + size + " elements in: " + TimeUtils.printDuration(watch.stop()));

        log.info("Processed " + tiny.get() + " tiny messages");
        log.info("Processed " + small.get() + " small messages");
        log.info("Processed " + med.get() + " medium messages");
        log.info("Processed " + large.get() + " large messages");

        assertEquals((size / 10) * 4, tiny.get());
        assertEquals((size / 10) * 2, small.get());
        assertEquals((size / 10) * 3, med.get());
        assertEquals((size / 10) * 1, large.get());

        assertTrue("Should complete route", matches);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return PerformanceHelper.createRouteBuilder("xpath", watch, tiny, small, med, large);
    }

}
