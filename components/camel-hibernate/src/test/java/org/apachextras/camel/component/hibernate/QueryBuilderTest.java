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
package org.apachextras.camel.component.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;

import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QueryBuilderTest {

    // Fixtures

    String queryString = "FROM Foo where bar = ?";
    String parameterValue = "parameterValue";

    // Collaborators

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Session session;

    // Tests

    @Test
    public void shouldPopulateQueryWithParameter() {
        // Given
        QueryBuilder builder = QueryBuilder.query(queryString).parameters(parameterValue);

        // When
        Query query = builder.createQuery(session);

        // Then
        verify(query).setParameter(0, parameterValue);
    }

}
