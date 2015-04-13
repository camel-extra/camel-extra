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
 * *************************************************************************************
 */
package org.apacheextras.camel.component.esper;

import com.espertech.esper.client.EventBean;
import org.apache.camel.impl.DefaultMessage;

/**
 * {@inheritDoc} Class EsperMessage wraps two EventBeans into a single
 * DefaultMessage object
 */
public class EsperMessage extends DefaultMessage {

  private final EventBean newEvent;
  private final EventBean oldEvent;

  /**
   * Creates a new instance of an EsperMessage.
   *
   * @param newEvent EventBean
   * @param oldEvent EventBean
   */
  public EsperMessage(EventBean newEvent, EventBean oldEvent) {
    this.newEvent = newEvent;
    this.oldEvent = oldEvent;
    // use new event as the default body
    setBody(newEvent);
  }

  /**
   * Returns the new EventBean.
   *
   * @return newEvent
   */
  public EventBean getNewEvent() {
    return newEvent;
  }

  /**
   * Returns the old EventBean.
   *
   * @return oldEvent
   */
  public EventBean getOldEvent() {
    return oldEvent;
  }
  
  /**
   * {@inheritDoc}
   * Returns the EsperMessage as DefaultMessage.
   * 
   * @return EsperMessage
   */
  @Override
  public DefaultMessage newInstance() {
    return new EsperMessage(newEvent, oldEvent);
  }
}
