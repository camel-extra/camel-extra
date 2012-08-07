/**************************************************************************************
 http://code.google.com/a/apache-extras.org/p/camel-extra

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
package org.apachextras.camel.component.esper;

import java.util.Map;

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
    private EPServiceProvider esperService;
    private EPRuntime esperRuntime;

    protected Endpoint createEndpoint(String uri, String remaining, Map parameters) throws Exception {
        return new EsperEndpoint(uri, this, remaining);
    }

    public EPServiceProvider getEsperService() {
        if (esperService == null) {
            esperService = EPServiceProviderManager.getDefaultProvider();
        }
        return esperService;
    }

    public void setEsperService(EPServiceProvider esperService) {
        this.esperService = esperService;
    }

    public EPRuntime getEsperRuntime() {
        if (esperRuntime == null) {
            esperRuntime = getEsperService().getEPRuntime();
        }
        return esperRuntime;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();

        // lets force lazy creation
        getEsperRuntime();
    }

}
