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
graph <- plotHoltWintersForecast(demand, n.ahead = 24, error.ribbon = "red");
graph <- graph + ggtitle("A forecast example based on Holt-Winters") + theme(plot.title = element_text(lineheight=.8, face="bold"));
graph <- graph + scale_x_continuous(breaks = seq(2011, 2015));
graph <- graph + ylab("Demand (Pieces)");
plot(graph);