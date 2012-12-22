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
package org.apacheextras.camel.component.hibernate;

import org.apacheextras.camel.examples.SendEmail;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.connection.DriverManagerConnectionProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueryBuilderIntegrationTest extends Assert {

    // Fixtures

    String queryString = "FROM SendEmail where address = ?";
    String address = "address";

    // Collaborators

    Session session;

    // Fxiture setup

    @Before
    public void setUp() {
        SessionFactory sessionFactory =
                new Configuration().
                        setProperty(Environment.DIALECT,"org.hibernate.dialect.DerbyDialect").
                        setProperty(Environment.CONNECTION_PROVIDER, DriverManagerConnectionProvider.class.getName()).
                        setProperty(Environment.URL, "jdbc:derby:target/testdb;create=true").
                        setProperty(Environment.USER, "").
                        setProperty(Environment.PASS, "").
                        setProperty(Environment.HBM2DDL_AUTO, "create").
                        addResource("org/apacheextras/camel/examples/SendEmail.hbm.xml").
                        buildSessionFactory();
        session = sessionFactory.openSession();

        session.save(new SendEmail(address));
    }

    @After
    public void tearDown() {
        session.close();
    }

    // Tests

    @Test
    public void shouldPopulateQueryWithParameter() {
        // Given
        Query query = QueryBuilder.
                query(queryString).parameters(address).
                createQuery(session);

        // When
        SendEmail sendEmail = (SendEmail) query.uniqueResult();

        // Then
        assertEquals(address, sendEmail.getAddress());
    }

}
