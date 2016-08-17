package org.mail.box.share.srv;

import java.util.Collection;

import org.mail.box.share.model.MyMessage;

public interface EmailReceiverSrv  {
	
	Collection<MyMessage> getInbox(String nNumber);

}
