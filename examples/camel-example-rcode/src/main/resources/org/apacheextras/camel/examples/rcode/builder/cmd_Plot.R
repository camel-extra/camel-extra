graph <- plotHoltWintersForecast(demand, n.ahead = 24, error.ribbon = "red");
graph <- graph + ggtitle("A forecast example based on Holt-Winters") + theme(plot.title = element_text(lineheight=.8, face="bold"));
graph <- graph + scale_x_continuous(breaks = seq(2011, 2015));
graph <- graph + ylab("Demand (Pieces)");
plot(graph);