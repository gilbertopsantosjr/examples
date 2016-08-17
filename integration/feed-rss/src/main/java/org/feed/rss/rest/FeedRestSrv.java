package org.feed.rss.rest;

import static org.feed.rss.share.useful.BasicFeedBeans.FEED_CHANNEL;
import static org.feed.rss.share.useful.BasicFeedQueryParam.N_NUMBER;
import static org.feed.rss.share.useful.BasicFeedQueryParam.SEQ;
import static org.feed.rss.share.useful.BasicFeedRestServices.CREATE;
import static org.feed.rss.share.useful.BasicFeedRestServices.DELETE;
import static org.feed.rss.share.useful.BasicFeedRestServices.DELETE_ALL;
import static org.feed.rss.share.useful.BasicFeedRestServices.SERVICE;
import static org.feed.rss.share.useful.BasicFeedRestServices.SHOW_ALL;
import static org.feed.rss.share.useful.BasicFeedRestServices.SHOW_PROVIDERS;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.feed.rss.config.SpringBeansFactory;
import org.feed.rss.dao.FeedProviderDao;
import org.feed.rss.share.exceptions.FeedFoundException;
import org.feed.rss.share.exceptions.FeedNotFoundException;
import org.feed.rss.share.model.Feed;
import org.feed.rss.share.model.FeedProvider;
import org.feed.rss.useful.FeedProviderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rometools.rome.feed.synd.SyndEntry;

/**
 * @author Santos, Gilberto
 */
@RestController(SERVICE)
public class FeedRestSrv {
	
	private static final Logger logger = Logger.getLogger(FeedRestSrv.class.getCanonicalName());
	
	@Autowired
	private FeedProviderDao feedProviderDao;
	
	@Autowired
	private SpringBeansFactory springBeansFactory;

	@ResponseBody
	@RequestMapping(value = CREATE, method = RequestMethod.POST)
	public FeedProvider create(@RequestBody FeedProvider _feedProvider) {
		logger.info("call create ");
		FeedProvider saved = null;
		try {
			FeedProviderValidator.validate(_feedProvider);
			final FeedProvider found = find(_feedProvider);
			if(found != null){
				saved = found;
				throw new FeedFoundException();
			}
		} catch (FeedFoundException fe) {
			
		} catch (FeedNotFoundException e) {
			saved = feedProviderDao.save(_feedProvider);
			
		} finally {
			springBeansFactory.createMyFeedChannel(saved);
		}
		return saved;
	}

	@RequestMapping(value = DELETE_ALL, method = RequestMethod.DELETE)
	public boolean deleteAll(@PathVariable(N_NUMBER) String NNumber) {
		logger.info(" remove all feeds by feed Provider for:" + NNumber);
		final FeedProvider found = find(new FeedProvider(NNumber, 0L));
		return removeChannel(found);
	}

	@RequestMapping(value = DELETE, method = RequestMethod.DELETE)
	public boolean delete(@PathVariable(N_NUMBER) String NNumber, @PathVariable(SEQ) long seq) {
		final FeedProvider found = find(new FeedProvider(NNumber, seq));
		logger.info(" remove a feed provider for:" + found.getnNumber()  + " feed " +  found.getUrl() );
		feedProviderDao.remove(found.getUrl());
		return removeChannel(found);
	}
	
	@RequestMapping(value = SHOW_PROVIDERS, method = RequestMethod.GET)
	public Collection<FeedProvider> showAlLProvider(@PathVariable(N_NUMBER) String NNumber) {
		return feedProviderDao.findAll(new FeedProvider(NNumber, ""));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = SHOW_ALL, method = RequestMethod.GET)
	public Collection<Feed> showAllFeedChannel(@PathVariable(N_NUMBER) String NNumber, @PathVariable(SEQ) long seq) {
		Set<Feed> myfeeds = null;
		final FeedProvider found = find(new FeedProvider(NNumber, seq));
		final String beanName = springBeansFactory.getBeanUserName(FEED_CHANNEL, found);
		
		if(springBeansFactory.isThereBean(beanName)){
			logger.info(" found channel read on database retrieve a feedProvider for:" + NNumber);
			PollableChannel feedChannel = (PollableChannel) springBeansFactory.getBean(beanName);
			logger.info("retrieve a feedChannel:" + found.getnNumber() );
			
			for (int i = 0; i < 10; i++) {
				Message<SyndEntry> message = (Message<SyndEntry>) feedChannel.receive(1000);
				if (message != null) {
					if(myfeeds == null)
						myfeeds = new HashSet<Feed>();
					SyndEntry entry = message.getPayload();
					myfeeds.add(Feed.newFeed(entry.getPublishedDate(), entry.getTitle(), entry.getLink()) );
				}
			}
		}
		
		if(myfeeds == null)
			myfeeds = Collections.EMPTY_SET; 
			
		return myfeeds;
	}
	
	private FeedProvider find(FeedProvider _feedProvider){
		FeedProvider feedProvider = feedProviderDao.find(_feedProvider);
		if(feedProvider == null)
			throw new FeedNotFoundException();
		return feedProvider;
	}
	
	private boolean removeChannel(FeedProvider found){
		springBeansFactory.removeMyFeedChannel(found);
		return true;
	}
	

}
