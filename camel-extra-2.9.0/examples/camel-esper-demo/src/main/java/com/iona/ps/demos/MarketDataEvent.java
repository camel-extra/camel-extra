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
