/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.examples;

import org.apachextras.camel.component.hibernate.Consumed;
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
