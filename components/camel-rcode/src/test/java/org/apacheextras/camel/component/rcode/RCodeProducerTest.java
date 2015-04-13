/**************************************************************************************
 https://camel-extra.github.io

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation; either version 3
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.


 You should have received a copy of the GNU Lesser General Public
 License along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301, USA.

 http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ***************************************************************************************/
package org.apacheextras.camel.component.rcode;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RCodeProducerTest extends CamelTestSupport {

    final String user = "test";
    final String password = "test123";

    RConnection rConnection = mock(RConnection.class, RETURNS_DEEP_STUBS);

    @Before
    @Override
    public void setUp() throws Exception {
        // We supply a fake factory to mock the RConnection Instance.
        RConnectionFactory.SingletonHolder.instance = new RConnectionFactory() {
            @Override
            public RConnection createConnection(RCodeConfiguration rCodeConfiguration) throws RserveException {
                return rConnection;
            }
        };

        when(rConnection.needLogin()).thenReturn(Boolean.TRUE);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                return null;
            }
        }).when(rConnection).login(user, password);
        super.setUp();
    }
}
