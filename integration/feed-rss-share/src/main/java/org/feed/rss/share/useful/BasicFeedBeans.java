package org.feed.rss.share.useful;

/**
 * @author Santos, Gilberto
 */
public enum BasicFeedBeans {

	FEED_CHANNEL_ADAPTER ( "feedChannelAdapter_"),
	
	FEED_CHANNEL ( "feedChannel_"),
	
	 MY_FEED_CHANNEL ( "myfeedChannel_");
	
	private String label;
	
	BasicFeedBeans(String _label){
		this.label = _label;
	}

	public String getLabel() {
		return label;
	}
	
	
}
