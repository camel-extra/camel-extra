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
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.TestSupport;
import org.apache.camel.util.StopWatch;
import org.slf4j.Logger;

/**
 *
 */
public final class PerformanceHelper extends TestSupport {

    private PerformanceHelper() {
        // util class
    }

    public static void createDataFile(Logger log, int size) throws Exception {
        deleteDirectory("target/data");
        createDirectory("target/data");

        log.info("Creating data file ...");

        File file = new File("target/data/data.xml");
        FileOutputStream fos = new FileOutputStream(file, true);
        fos.write("<orders>\n".getBytes());

        for (int i = 0; i < size; i++) {
            fos.write("<order>\n".getBytes());
            fos.write(("  <id>" + i + "</id>\n").getBytes());
            int num = i % 10;
            if (num >= 0 && num <= 3) {
                fos.write(("  <amount>3</amount>\n").getBytes());
                fos.write(("  <customerId>333</customerId>\n").getBytes());
            } else if (num >= 4 && num <= 5) {
                fos.write(("  <amount>44</amount>\n").getBytes());
                fos.write(("  <customerId>444</customerId>\n").getBytes());
            } else if (num >= 6 && num <= 8) {
                fos.write(("  <amount>88</amount>\n").getBytes());
                fos.write(("  <customerId>888</customerId>\n").getBytes());
            } else {
                fos.write(("  <amount>123</amount>\n").getBytes());
                fos.write(("  <customerId>123123</customerId>\n").getBytes());
            }
            fos.write("  <description>bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla</description>\n".getBytes());
            fos.write("</order>\n".getBytes());
        }

        fos.write("</orders>".getBytes());
        fos.close();

        log.info("Creating data file done.");
    }

    public static RouteBuilder createRouteBuilder(final String language, final StopWatch watch, final AtomicInteger tiny, final AtomicInteger small, final AtomicInteger med,
                                                  final AtomicInteger large) throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:target/data?noop=true").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        log.info("Starting to process file");
                        watch.restart();
                    }
                }).split().language(language, "/orders/order").streaming().choice().when().language(language, "/order/amount < 10").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String xml = exchange.getIn().getBody(String.class);
                        assertTrue(xml, xml.contains("<amount>3</amount>"));

                        int num = tiny.incrementAndGet();
                        if (num % 100 == 0) {
                            log.info("Processed " + num + " tiny messages");
                            log.debug(xml);
                        }
                    }
                }).when().language(language, "/order/amount < 50").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String xml = exchange.getIn().getBody(String.class);
                        assertTrue(xml, xml.contains("<amount>44</amount>"));

                        int num = small.incrementAndGet();
                        if (num % 100 == 0) {
                            log.info("Processed " + num + " small messages: " + xml);
                            log.debug(xml);
                        }
                    }
                }).when().language(language, "/order/amount < 100").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String xml = exchange.getIn().getBody(String.class);
                        assertTrue(xml, xml.contains("<amount>88</amount>"));

                        int num = med.incrementAndGet();
                        if (num % 100 == 0) {
                            log.info("Processed " + num + " medium messages");
                            log.debug(xml);
                        }
                    }
                }).otherwise().process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String xml = exchange.getIn().getBody(String.class);
                        assertTrue(xml, xml.contains("<amount>123</amount>"));

                        int num = large.incrementAndGet();
                        if (num % 100 == 0) {
                            log.info("Processed " + num + " large messages");
                            log.debug(xml);
                        }
                    }
                }).end() // choice
                    .end(); // split
            }
        };
    }

}
