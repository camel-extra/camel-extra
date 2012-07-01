/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.esper;

public class MyEvent {
    private String foo;
    private int bar = 0;

    public MyEvent() {
    }

    public MyEvent(String foo, int bar) {
        this.foo = foo;
        this.bar = bar;
    }

    @Override
    public String toString() {
        return "MyEvent[foo: " + foo + " bar: " + bar + "]";
    }

    public int getBar() {
        return bar;
    }

    public void setBar(int bar) {
        this.bar = bar;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }
}
