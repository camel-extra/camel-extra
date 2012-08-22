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
package org.apache.camel.component.jgroups;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jgroups.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CamelJGroupsReceiverTest {

    // Fixtures

    @InjectMocks
    CamelJGroupsReceiver receiver;

    @Mock
    JGroupsEndpoint jGroupsEndpoint;

    @Mock
    Processor processor;

    // Tests

    @Test(expected = JGroupsException.class)
    public void shouldHandleProcessingException() throws Exception {
        // Given
        willThrow(Exception.class).given(processor).process(any(Exchange.class));

        // When
        receiver.receive(new Message(null, null, "someMessage"));
    }

}
