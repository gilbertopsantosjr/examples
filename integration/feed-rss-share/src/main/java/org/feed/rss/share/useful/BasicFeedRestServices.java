package org.feed.rss.share.useful;

public interface BasicFeedRestServices {

	public static final String SERVICE = "/feed";
	
	public static final String CREATE = SERVICE + "/create";
	
	public static final String DELETE = SERVICE + "/delete/{nNumber}/{seq}";
	
	public static final String DELETE_ALL = SERVICE + "/delete/all/{nNumber}";
	
	public static final String SHOW_ALL = SERVICE + "/show/all/{nNumber}/{seq}";
	
	public static final String SHOW_PROVIDERS = SERVICE + "/show/all/{nNumber}";
	
}
