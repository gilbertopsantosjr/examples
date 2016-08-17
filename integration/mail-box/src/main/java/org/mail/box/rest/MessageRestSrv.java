package org.mail.box.rest;

import static org.mail.box.share.useful.BasicMessageRestServices.CONNECT;
import static org.mail.box.share.useful.BasicMessageRestServices.DISCONNECT;
import static org.mail.box.share.useful.BasicMessageRestServices.SERVICE;
import static org.mail.box.share.useful.BasicMessageRestServices.SHOW_ALL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.mail.box.config.SpringBeansFactory;
import org.mail.box.dao.MyMessageProviderDao;
import org.mail.box.share.model.MyMessage;
import org.mail.box.share.model.MyMessageProvider;
import org.mail.box.share.srv.EmailReceiverSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Santos, Gilberto
 */
@RestController(SERVICE)
public class MessageRestSrv {
	
	private static final Logger logger = Logger.getLogger(MessageRestSrv.class.getCanonicalName());
	
	@Autowired
	private SpringBeansFactory springBeansFactory;
	
	@Autowired
	private MyMessageProviderDao myMessageProviderDao;
	
	@Autowired
	private EmailReceiverSrv emailReceiverSrv;

	@ResponseBody
	@RequestMapping(value = CONNECT, method = RequestMethod.POST)
	public MyMessageProvider connect(@RequestBody MyMessageProvider _myMessageProvider) {
		logger.info("connect for: " + _myMessageProvider.getnNumber() );
		MyMessageProvider saved = myMessageProviderDao.exchangeState( _myMessageProvider, true);
		springBeansFactory.createMyChannel(saved);
		return saved;
	}

	@RequestMapping(value = DISCONNECT, method = RequestMethod.POST)
	public MyMessageProvider disconnect(@RequestBody MyMessageProvider _myMessageProvider) {
		logger.info(" remove MyMessageProviderr for:" + _myMessageProvider.getnNumber());
		MyMessageProvider saved = myMessageProviderDao.exchangeState( _myMessageProvider, false);
		springBeansFactory.removeChannel(saved);
		return saved;
	}
	
	@RequestMapping(value = SHOW_ALL, method = RequestMethod.GET)
	public Collection<MyMessage> showAllMessageInBox(@PathVariable("nNumber") String _nNumber) {
		logger.info(" try to collect all mail inbox fro:" + _nNumber );
		List<MyMessage> inbox = Collections.emptyList();
		try {
			Collection<MyMessage> read = emailReceiverSrv.getInbox(_nNumber);
			if(read != null){
				logger.info(read.size() + " found ! ");
				inbox = new ArrayList<MyMessage>(read);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		} finally {
			Collections.sort(inbox);
		}		
		return inbox;
	}

}
