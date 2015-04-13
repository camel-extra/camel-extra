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
package org.apacheextras.camel.component.hibernate;

import static org.junit.Assert.assertEquals;

import org.apacheextras.camel.examples.SendEmail;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueryBuilderHibernateTest {

    // Fixtures

    String queryString = "FROM SendEmail where address = ?";
    String address = "address";

    // Collaborators

    Session session;

    // Fixture setup

    @Before
    public void setUp() {
        Configuration configuration
            = new Configuration().
                setProperty(Environment.DIALECT,"org.hibernate.dialect.DerbyDialect").
                setProperty(Environment.URL, "jdbc:derby:target/testdb;create=true").
                setProperty(Environment.USER, "").
                setProperty(Environment.PASS, "").
                setProperty(Environment.HBM2DDL_AUTO, "create").
                addResource("org/apacheextras/camel/examples/SendEmail.hbm.xml");
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
            .applySettings(configuration.getProperties())
            .buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
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
