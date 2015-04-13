# https://camel-extra.github.io
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 2
# of the License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
# 02110-1301, USA.
#
# http://www.gnu.org/licenses/gpl-2.0-standalone.html
##
# Function 'plotHoltWintersForecast' generates a forecast plot based on the Holt-Winters function
# within the 'forecast' package using 'ggplot2' functionality.
# It takes an incoming time-series object, some forecast parameters as well as some graph configs.
#
# @param ts_object    - time-series object containing historical data.
# @param n.ahead      - number of future periods to predict, default=4.
# @param CI           - confidence level for the prediction interval, default=.95.
# @param error.ribbon - color definition of the prediction error rate, default='green'.
# @param line.size    - set's the line size of the output graph, default=1.
# @return plot        - ggplot object of library ggplot2.
# #
plotHoltWintersForecast <- function(ts_object, n.ahead=4, CI=.95, error.ribbon='green', line.size=1) {
  # Calculate a forecast based on the Holt-Winters algorithm
  hw_object <- HoltWinters(ts_object);
  forecast <- predict(hw_object, n.ahead=n.ahead, prediction.interval=T, level=CI);
  
  # Retrieve forecast, fitted and actual values
  for_values    <- data.frame(time=round(time(forecast), 3),
                              value_forecast=as.data.frame(forecast)$fit,
                              dev=as.data.frame(forecast)$upr-as.data.frame(forecast)$fit);
  fitted_values <- data.frame(time=round(time(hw_object$fitted),3),
                              value_fitted=as.data.frame(hw_object$fitted)$xhat);
  actual_values <- data.frame(time=round(time(hw_object$x), 3),
                              Actual=c(hw_object$x));
  
  # Generate graphset to plot the values
  graphset <- merge(actual_values,  fitted_values,  by='time',  all=TRUE);
  graphset <- merge(graphset,  for_values,  all=TRUE,  by='time');
  graphset[is.na(graphset$dev),  ]$dev<-0;
  graphset$Fitted <- c(rep(NA,  NROW(graphset)-(NROW(for_values) + NROW(fitted_values))),
                       fitted_values$value_fitted,  for_values$value_forecast);
  graphset.melt <- melt(graphset[, c('time', 'Actual', 'Fitted')], id='time');
  
  # Create plot object and return it
  plot <- ggplot(graphset.melt, aes(x=time,  y=value)) + geom_ribbon(data=graphset, aes(x=time, y=Fitted, ymin=Fitted-dev,  ymax=Fitted + dev),  alpha=.2,  fill=error.ribbon) + geom_line(aes(colour=variable), size=line.size) + geom_vline(x=max(actual_values$time),  lty=2) + xlab('Time') + ylab('Value') + theme(legend.position='bottom') + scale_colour_hue('');
  return(plot);
}