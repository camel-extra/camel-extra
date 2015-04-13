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
package org.apacheextras.camel.examples.hibernate;

import java.util.Map;
import java.util.Random;

/**
 * Bean that generates and process orders.
 */
public class OrderBean {

    private int counter;
    private Random ran = new Random();

    /**
     * Generates a new order structured as a {@link Map}
     */
    public Order generateOrder() {
    	counter++;
    	
        Order answer = new Order();
        answer.setItem(counter % 2 == 0 ? 111 : 222);
        answer.setAmount(ran.nextInt(10) + 1);
        answer.setDescription(counter % 2 == 0 ? "Camel in Action" : "ActiveMQ in Action");
        return answer;
    }

    /**
     * Processes the order
     *
     * @param order  the order
     * @return the transformed order
     */
    public String processOrder(Order order) {
        return "Processed order id " + order.getId() + " item " + order.getItem() + " of " + order.getAmount() + " copies of " + order.getDescription();
    }
}
