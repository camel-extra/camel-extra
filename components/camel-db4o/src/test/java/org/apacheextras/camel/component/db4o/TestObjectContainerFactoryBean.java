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
package org.apacheextras.camel.component.db4o;

import java.io.File;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;

public class TestObjectContainerFactoryBean implements FactoryBean<ObjectContainer>, DisposableBean {

    private ObjectContainer objectContainer;

    public ObjectContainer getObject() throws Exception {
        File randomDatabase = File.createTempFile(getClass().getName(), "tmp");
        randomDatabase.deleteOnExit();
        objectContainer = Db4oEmbedded.openFile(randomDatabase.getAbsolutePath());
        return objectContainer;
    }

    public Class<?> getObjectType() {
        return ObjectContainer.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void destroy() throws Exception {
        objectContainer.close();
    }

}
