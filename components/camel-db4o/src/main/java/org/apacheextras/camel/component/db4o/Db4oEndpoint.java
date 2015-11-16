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

import com.db4o.ObjectContainer;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.ScheduledPollEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.util.ObjectHelper;

/**
 * @version $Revision$
 */
@UriEndpoint(scheme = "db4o", title = "DB4O", syntax = "db4o://className", consumerClass = Db4oConsumer.class)
public class Db4oEndpoint extends ScheduledPollEndpoint {
    private ObjectContainer objectContainer;
    private Class storedClass;
    private boolean consumeDelete = true;

    public Db4oEndpoint(String uri, Class storedClass, Db4oComponent component) {
        super(uri, component);
        this.setObjectContainer(component.getObjectContainer());
        this.setStoredClass(storedClass);
    }

    @Override
    public Producer createProducer() throws Exception {
        ObjectHelper.notNull(getObjectContainer(), "object container property");
        return new Db4oProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        ObjectHelper.notNull(getObjectContainer(), "object container property");
        Db4oConsumer consumer = new Db4oConsumer(this, processor);
        configureConsumer(consumer);
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public ObjectContainer getObjectContainer() {
        return objectContainer;
    }

    public void setObjectContainer(ObjectContainer objectContainer) {
        this.objectContainer = objectContainer;
    }

    public Class getStoredClass() {
        return storedClass;
    }

    public void setStoredClass(Class storedClass) {
        this.storedClass = storedClass;
    }

    public boolean isConsumeDelete() {
        return consumeDelete;
    }

    public void setConsumeDelete(boolean consumeDelete) {
        this.consumeDelete = consumeDelete;
    }

}
