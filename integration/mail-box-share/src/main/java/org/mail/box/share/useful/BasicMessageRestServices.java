package org.mail.box.share.useful;

/**
 * @author Santos, Gilberto
 *
 */
public interface BasicMessageRestServices {

	public static final String SERVICE = "/mail";
	
	public static final String CONNECT = SERVICE + "/connect";
	
	public static final String DISCONNECT = SERVICE + "/disconnect";
	
	public static final String SHOW_ALL = SERVICE + "/show/in/box/{nNumber}";
	
}
