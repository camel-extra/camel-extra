/**************************************************************************************
 * Copyright (C) 2008 - 2012 Camel Extra Team. All rights reserved.                   *
 * http://code.google.com/a/apache-extras.org/p/camel-extra/                          *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package com.iona.ps.demos;

import java.io.Serializable;

public class MarketDataEvent implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5171705971847030315L;
	private String symbol;
    private FeedEnum feed;

    public MarketDataEvent(String symbol, FeedEnum feed) {
        this.symbol = symbol;
        this.feed = feed;
    }

    public String getSymbol() {
        return symbol;
    }

    public FeedEnum getFeed() {
        return feed;
    }
}
