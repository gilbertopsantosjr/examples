/**
 * 
 */
package org.mail.box.srv;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.mail.box.share.model.MyMessage;
import org.mail.box.share.srv.EmailReceiverSrv;

/**
 * @author Santos, Gilberto
 */
public class EmailReceiverSrvImpl implements EmailReceiverSrv {

	private static final Logger logger = Logger.getLogger(EmailReceiverSrvImpl.class.getCanonicalName());
	
	private static final int NUMBER_OF_LATEST_MESSAGE = 10;

	private static final Map<String, Collection<MyMessage>> group = new ConcurrentHashMap<String, Collection<MyMessage>>();
	private static List<MyMessage> inbox = new ArrayList<MyMessage>();

	public void receive(MimeMessage mimeMessage) throws MessagingException {
		try {
			// ensure the la test 10 email inbox
			if (inbox.size() >= NUMBER_OF_LATEST_MESSAGE)
				inbox.remove(inbox.size() - 1);
			
			String nNumber = ""; 
			// group messages by nNumber
			Enumeration<?> e = mimeMessage.getAllHeaders();
			while (e.hasMoreElements()) {
				javax.mail.Header header = (javax.mail.Header) e.nextElement();
				if (header.getName().equals("To"))
					nNumber = header.getValue().split("@")[0].replace("<", "");
			}

			MyMessage m = new MyMessage();
			
			if(mimeMessage.getSubject() != null)
				m.setSubject(mimeMessage.getSubject().toString());
			
			if(mimeMessage.getFrom().length > 0)
				m.setFrom(mimeMessage.getFrom()[0].toString());
			
			if(mimeMessage.getContent() != null)
				m.setContent(mimeMessage.getContent());
			
			if(mimeMessage.getReceivedDate() != null)
				m.setWhen(mimeMessage.getReceivedDate());
			else 
				m.setWhen(new Date());
			
			m.setFace(nNumber.concat(".jpeg"));
			m.setWho(nNumber);
			m.setId(mimeMessage.getMessageID());
			
			inbox.add(m);
			
			logger.info("inbox size:" + inbox.size());
					
			// put in the list to export as microservice
			group.put(nNumber, inbox);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Collection<MyMessage> getInbox(String nNumber) {
		return group.get(nNumber);
	}
	

}
