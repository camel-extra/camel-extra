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
package org.apacheextras.camel.examples;

import org.apacheextras.camel.component.hibernate.Consumed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a task which has multiple steps so that it can move from stage to stage
 * with the method annotated with {@link @Consumed} being invoked when the Camel consumer
 * has processed the entity bean
 *
 */
//@NamedQuery(name = "step1", query = "select x from MultiSteps x where x.step = 1")
public class MultiSteps {
    private static final transient Logger LOG = LoggerFactory.getLogger(MultiSteps.class);
    private Long id;
    private String address;
    private int step;

    public MultiSteps() {
    }

    public MultiSteps(String address) {
        setAddress(address);
        setStep(1);
    }

    @Override
    public String toString() {
        return "MultiSteps[id: " + getId() + " step: " + getStep() + " address: " + getAddress() + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    /**
     * This method is invoked after the entity bean is processed successfully by a Camel endpoint
     */
    @Consumed
    public void goToNextStep() {
        setStep(getStep() + 1);

        LOG.info("Invoked the completion complete method. Now updated the step to: " + getStep());
    }
}
