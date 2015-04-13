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
package org.apacheextras.camel.examples.rcode.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Calculates monthly sales figures.
 */
public class MonthlySalesFigureCalculator {

  /**
   * Provides some level of logging information.
   */
  private final static Logger LOGGER = LoggerFactory.getLogger(MonthlySalesFigureCalculator.class);

  /**
   * {@inheritDoc}
   * <p>Executes the calculation of daily sales data into monthly values to run
   * the forecast</p>
   * @param salesDay
   * @return sales as String
   */
  public String summarize(List<List> salesDay) {
    Map<String, Map<String, String>> dailySalesCalendar = new LinkedHashMap<String, Map<String, String>>();
    
    // Get the sales data and convert it into an object where we can run the summary
    for (List salesDate : salesDay) {
      LOGGER.debug("Sales date: {} and value: {}", salesDate.get(0), salesDate.get(1));
      setSalesValue(dailySalesCalendar,getMonthAndYearOfDate(salesDate.get(0).toString()),
              salesDate.get(0).toString(), salesDate.get(1).toString());
    }
    // Create an R vector from the monthly sales values
    return toRVector(summarizeMonthlyValues(dailySalesCalendar));
  }
  
  /**
   * Creates an R vector by a given array of monthly sales.
   */
  private String toRVector(int[] monthlySales) {
    // Create the R vector as string
    final StringBuilder rVector = new StringBuilder();
    // Iterate via the monthly sales and buildup the string
    for (int i = 0; i < monthlySales.length; i++) {
      rVector.append(monthlySales[i]);
      if ((i + 1) != monthlySales.length) {
        rVector.append(',');
      }
    }
    // Return the vector as string
    return rVector.toString();
  }
  
  /**
   * Summarize all daily values by month.
   */
  private int[] summarizeMonthlyValues(Map<String, Map<String, String>> dailySalesCalendar) {
    final Set<String> monthAndYears = dailySalesCalendar.keySet();
    final int[] sum = new int[monthAndYears.size()];
    int i = 0;
    for (String monthAndYear : monthAndYears) {
      for (String value : dailySalesCalendar.get(monthAndYear).values()) {
        sum[i] = sum[i] + Integer.parseInt(value);
      }
      i++;
    }
    return sum;
  }
  
  /**
   * Set's the sales value to the calendar layout.
   */
  private void setSalesValue(Map<String, Map<String, String>> dailySalesCalendar,String monthAndYear, String date, String value) {
    if (null == dailySalesCalendar.get(monthAndYear)) {
      // Initialize the month if not available
      dailySalesCalendar.put(monthAndYear, new LinkedHashMap<String, String>());
    } else {
      if (null != dailySalesCalendar.get(monthAndYear).get(date)) {
        // Remove the existing value to replace with the lates information
        dailySalesCalendar.get(monthAndYear).remove(date);
      }
    }
    // Set the date to the calendar map
    dailySalesCalendar.get(monthAndYear).put(date, value);
  }
  
  /**
   * Converting the date object into a year and month string to be able running
   * a sum calculation on every month per year
   */
  private String getMonthAndYearOfDate(String strDate) {
    LOGGER.trace("Mapping date '{}' to the apropriate month.", strDate);
    // Format the dates
    final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    // Get calendar instance
    final Calendar cal = Calendar.getInstance();
    // Intermediate date to convert the string into a calendar like object
    Date date;
    // Month value
    int month = -1;
    // Year value
    int year = -1;
    // Parse the date and retrieve month and day values
    try {
      date = sdf.parse(strDate);
      cal.setTime(date);
      month = cal.get(Calendar.MONTH);
      year = cal.get(Calendar.YEAR);
    } catch (ParseException ex) {
      LOGGER.error("Could not parse the given date: {}", ex);
    }
    // Return month and day as string objects
    return String.valueOf(year) + '-' + month;
  }
}