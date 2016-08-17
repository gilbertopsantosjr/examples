package org.mail.box.share.useful;

/**
 * @author Santos, Gilberto
 */
public enum BasicMessageBeans {

	MESSAGE_CHANNEL_ADAPTER ( "myMessageChannelAdapter_"),
	
	MESSAGE_CHANNEL ( "myMessageChannel_"),
	
	POP_CHANNEL ( "myPopMessageChannel_"),
	
	SERVICE_ACTIVATOR ( "myServiceActivator_");
	
	private String label;
	
	BasicMessageBeans(String _label){
		this.label = _label;
	}

	public String getLabel() {
		return label;
	}
	
	
}
