package com.iona.ps.demos;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

public class MyRouteBuilder extends RouteBuilder {

	public static void main(String... args) throws Exception {
		Main.main(args);
	}

	public void configure() {

		from("activemq:EventStreamQueue").to("esper://feed");
		from("esper://feed?eql=insert into TicksPerSecond select feed, count(*) as cnt from com.iona.ps.demos.MarketDataEvent.win:time_batch(1 sec) group by feed")
				.to("esper://feed");
		from("esper://feed?eql=select feed, avg(cnt) as avgCnt, cnt as feedCnt from TicksPerSecond.win:time(10 sec) group by feed + having cnt < avg(cnt) * 0.75")
				.process(new Processor() {
					@SuppressWarnings("unchecked")
					public void process(Exchange arg0) throws Exception {
						com.espertech.esper.event.map.MapEventBean ev = (com.espertech.esper.event.map.MapEventBean) arg0
								.getIn().getBody();
						Map map = (Map) ev.getUnderlying();
						System.out.println(map);
					}
				});
		
	}
}
