package org.mail.box.config;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mail.box.dao.MyMessageProviderDao;
import org.mail.box.share.model.MyMessageProvider;
import org.mail.box.share.useful.MessageProviderValidator;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Santos, Gilberto
 *
 */
public class OnStartUp implements ApplicationListener<ContextRefreshedEvent> {
	
	private static final Logger logger = Logger.getLogger(OnStartUp.class.getCanonicalName());

	private SpringBeansFactory springBeansFactory;
	private MyMessageProviderDao myMessageProviderDao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		springBeansFactory = (SpringBeansFactory) event.getApplicationContext().getBean("springBeansFactory");
		myMessageProviderDao = (MyMessageProviderDao) event.getApplicationContext().getBean("myMessageProviderDaoImp");
		
		if(myMessageProviderDao != null){
			Collection<MyMessageProvider> all = myMessageProviderDao.findAll();
			for (MyMessageProvider myMessageProvider : all) {
				try {
					logger.info("MyMessageProvider bean found for:" + myMessageProvider.getnNumber() );
					MessageProviderValidator.validate(myMessageProvider);
					if(myMessageProvider.isConnected())
						springBeansFactory.createMyChannel(myMessageProvider);
				} catch (Exception e) {
					// do nothing 
				}
			}
		}
	}
	
}
