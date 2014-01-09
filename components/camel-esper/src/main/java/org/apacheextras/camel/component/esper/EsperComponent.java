/**
 * ************************************************************************************
 * http://code.google.com/a/apache-extras.org/p/camel-extra
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

import java.util.Map;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

/**
 * A component for working with <a href="http//esper.codehaus.org/">Esper</a>
 *
 * @version $Revision: 1.1 $
 */
public class EsperComponent extends DefaultComponent {

  /**
   * Event processing service provider.
   */
  private EPServiceProvider esperService;

  /**
   * Esper runtime environment.
   */
  private EPRuntime esperRuntime;

  /**
   * Creates an Esper endpoint. {@inheritDoc}
   *
   * @param uri
   * @param remaining
   * @param parameters
   * @return Endpoint
   * @throws java.lang.Exception
   */
  @Override
  protected Endpoint createEndpoint(String uri, String remaining, Map parameters) throws Exception {
    return new EsperEndpoint(uri, this, remaining);
  }

  /**
   * Returns the event processing service provider. If the provider is null an
   * instance will be created via the EPServiceProviderManager.
   *
   * @return event processing service provider
   */
  public EPServiceProvider getEsperService() {
    if (esperService == null) {
      Configuration configuration = new Configuration();
      configuration.configure();
      //esperService = EPServiceProviderManager.getDefaultProvider();
      esperService = EPServiceProviderManager.getProvider("EPServiceProvider", configuration);
    }
    return esperService;
  }

  /**
   * Sets the Esper service provider.
   *
   * @param esperService
   */
  public void setEsperService(EPServiceProvider esperService) {
    this.esperService = esperService;
  }

  /**
   * Returns the Esper runtime object.
   *
   * @return EPRuntime
   */
  public EPRuntime getEsperRuntime() {
    if (esperRuntime == null) {
      esperRuntime = getEsperService().getEPRuntime();
    }
    return esperRuntime;
  }

  /**
   * {@inheritDoc}
   *
   * @throws java.lang.Exception
   */
  @Override
  protected void doStart() throws Exception {
    super.doStart();

    // let's force lazy creation
    getEsperRuntime();
  }

}
