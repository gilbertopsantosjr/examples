package org.feed.rss.useful;

import java.util.Collection;

import org.feed.rss.config.SpringBeansFactory;
import org.feed.rss.dao.FeedProviderDao;
import org.feed.rss.share.model.FeedProvider;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Santos, Gilberto
 */
public class OnStartUp implements ApplicationListener<ContextRefreshedEvent> {

	private SpringBeansFactory springBeansFactory;
	private FeedProviderDao feedProviderDao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		springBeansFactory = (SpringBeansFactory) event.getApplicationContext().getBean("springBeansFactory");
		feedProviderDao = (FeedProviderDao) event.getApplicationContext().getBean("feedProviderDaoImpl");
		
		if(feedProviderDao != null){
			Collection<FeedProvider> feedProviders = feedProviderDao.findAll();
			for (FeedProvider feedProvider : feedProviders) {
				try {
					FeedProviderValidator.validate(feedProvider);
					springBeansFactory.createMyFeedChannel(feedProvider);
				} catch (Exception e) {
					// do nothing 
				}
			}
		}
	}
}
