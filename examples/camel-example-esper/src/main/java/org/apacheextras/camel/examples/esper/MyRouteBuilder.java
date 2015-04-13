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
package org.apacheextras.camel.examples.esper;

import java.util.Map;


import com.espertech.esper.client.EventBean;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRouteBuilder extends RouteBuilder {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyRouteBuilder.class);

    public static void main(String... args) throws Exception {
        Main.main(args);
    }

    @Override
    public void configure() {

        from("activemq:EventStreamQueue").to("esper://feed");

        from("esper://feed?eql=insert into TicksPerSecond select feed, count(*) as cnt from org.apacheextras.camel.examples.esper.MarketDataEvent.win:time_batch(1 sec) group by feed")
                .to("esper://feed");

        from("esper://feed?eql=select feed, avg(cnt) as avgCnt, cnt as feedCnt from TicksPerSecond.win:time(10 sec) group by feed + having cnt < avg(cnt) * 0.75")
                .process(new Processor() {
                    @SuppressWarnings("unchecked")
                    public void process(Exchange exchange) throws Exception {
                        EventBean event = exchange.getIn().getBody(EventBean.class);
                        Map map = (Map) event.getUnderlying();
                        LOGGER.info("Event map := {}", map);
                    }
                });

    }
}
