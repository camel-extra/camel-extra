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
 **************************************************************************************
 */
package org.apacheextras.camel.examples.rcode.types;

import java.util.Date;

/**
 * Forecast document.
 */
public class ForecastDocument {

  private String title = "";
  private String path = "";
  private Date date = null;
  private byte[] jpegGraph = null;

  /**
   *
   */
  public ForecastDocument() {
  }

  /**
   * @param jpegGraph
   */
  public ForecastDocument(byte[] jpegGraph) {
    setJpegGraph(jpegGraph);
  }

  /**
   * @param title
   * @param path
   * @param date
   * @param jpegGraph
   */
  public ForecastDocument(String title, String path, Date date, byte[] jpegGraph) {
    this.title = title;
    this.path = path;
    setDate(date);
    setJpegGraph(jpegGraph);
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * @param path the path to set
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * @return the date
   */
  public Date getDate() {
    return (Date)date.clone();
  }

  /**
   * @param date the date to set
   */
  public void setDate(Date date) {
    this.date = new Date();
    if(null != date) {
      this.date.setTime(date.getTime());
    } else {
      this.date = new Date();
    }
  }

  /**
   * @return the jpegGraph
   */
  public byte[] getJpegGraph() {
    return (byte[])jpegGraph.clone();
  }

  /**
   * @param jpegGraph the jpegGraph to set
   */
  public void setJpegGraph(byte[] jpegGraph) {
    // Avoid malicious code vulnerability and create a copy
    this.jpegGraph = new byte[jpegGraph.length];
    for (int i = 0; i < jpegGraph.length; i++) {
      this.jpegGraph[i] = jpegGraph[i];
    }
  }
}
