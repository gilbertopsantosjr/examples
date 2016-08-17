package org.feed.rss.config;

import static org.feed.rss.share.useful.BasicFeedBeans.FEED_CHANNEL;
import static org.feed.rss.share.useful.BasicFeedBeans.FEED_CHANNEL_ADAPTER;
import static org.feed.rss.share.useful.BasicFeedBeans.MY_FEED_CHANNEL;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.feed.rss.share.exceptions.FeedInvalidException;
import org.feed.rss.share.model.FeedProvider;
import org.feed.rss.share.useful.BasicFeedBeans;
import org.feed.rss.useful.FeedProviderValidator;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.SourcePollingChannelAdapterFactoryBean;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;

/**
 * @author Santos, Gilberto
 */
@Configuration
public class SpringBeansFactory {

	private static final Logger logger = Logger.getLogger(SpringBeansFactory.class.getCanonicalName());

	@Autowired
	private ApplicationContext applicationContext;
	private ConfigurableListableBeanFactory beanFactory;
	private BeanDefinitionRegistry registry;
	
	@PostConstruct
	public void init() {
		beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
		registry = ((BeanDefinitionRegistry) beanFactory);
	}
	
	@Bean(name = "myspringBeansFactory")
    public SpringBeansFactory createSpringBeansFactory() {
        return new SpringBeansFactory();
    }

	@SuppressWarnings("unchecked")
	public <T extends Object> T getBean(String bean) {
		return (T) applicationContext.getBean(bean);
	}
	
	public SpringBeansFactory getInstance() {
		return (SpringBeansFactory) applicationContext.getBean("myspringBeansFactory");
	}

	/**
	 * 
	 * @param feedProvider
	 * @return try create a new bean of spring integration with feed provider
	 */
	public boolean createMyFeedChannel(FeedProvider feedProvider) {
		try {
			String bean = getBeanUserName(FEED_CHANNEL, feedProvider);
			boolean thereIsBean = getInstance().isThereBean(bean);
			if (!thereIsBean) {
				logger.info(" try create a new bean of spring integration with feed provider for: " + bean);
				getInstance().createFeedChannelAdapter(feedProvider);
				thereIsBean = getInstance().isThereBean(bean);
			}
			return thereIsBean;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Remove all beans to provide a feed service from spring integration
	 * @param feedProvider
	 */
	public void removeMyFeedChannel(FeedProvider feedProvider) {
		for (BasicFeedBeans feedBeans : BasicFeedBeans.values()) {
			String bean = getBeanUserName( feedBeans, feedProvider );
			boolean thereIsBean = getInstance().isThereBean(bean);
			if (thereIsBean){
				logger.info(" try remove a bean of spring integration with feed provider for : " + bean );
				getInstance().removeBean(bean);
			}
		}
	}

	/**
	 * Remove bean programmatically
	 * 
	 * @param bean
	 */
	private void removeBean(String bean) {
		((BeanDefinitionRegistry) getInstance().beanFactory).removeBeanDefinition(bean);
	}

	/**
	 * 
	 * @param feedProvider
	 * @return verify if there is a bean with nNumber
	 */
	public boolean isThereBean(String bean) {
		return getInstance().applicationContext.containsBean(bean);
	}

	/**
	 * 
	 * @param feedProvider
	 * @throws Exception
	 */
	private void createFeedChannelAdapter(FeedProvider feedProvider) throws Exception {
		createFeedChannel(feedProvider);
		createMessageSource(feedProvider);

		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("source", (FeedEntryMessageSource) getBean( getBeanUserName(FEED_CHANNEL_ADAPTER, feedProvider) ) );
		mpv.add("outputChannel", (QueueChannel) getBean( getBeanUserName(FEED_CHANNEL, feedProvider) ) );
		mpv.add("pollerMetadata", getBean("mypoller"));
		mpv.add("autoStartup", Boolean.FALSE);

		GenericBeanDefinition beanDefinition = getTemplateGenericBeanDefinition( new GenericBeanDefinition() );
		beanDefinition.setBeanClass(SourcePollingChannelAdapterFactoryBean.class);
		beanDefinition.setPropertyValues(mpv);
		
		registry.registerBeanDefinition( getBeanUserName(MY_FEED_CHANNEL, feedProvider), beanDefinition);

		((SourcePollingChannelAdapter) getBean( getBeanUserName(MY_FEED_CHANNEL, feedProvider) )).start();

	}

	/**
	 * 
	 * @param feedProvider
	 * @throws Exception
	 */
	private void createMessageSource(FeedProvider feedProvider) throws Exception {

		ConstructorArgumentValues ca = new ConstructorArgumentValues();
		ca.addIndexedArgumentValue(0, new URL(feedProvider.getUrl()));
		ca.addIndexedArgumentValue(1, "");

		GenericBeanDefinition beanDefinition = getTemplateGenericBeanDefinition( new GenericBeanDefinition() );
		beanDefinition.setBeanClass(FeedEntryMessageSource.class);
		beanDefinition.setConstructorArgumentValues(ca);

		registry.registerBeanDefinition( getBeanUserName(FEED_CHANNEL_ADAPTER, feedProvider), beanDefinition);
	}

	/**
	 * 
	 * @param feedProvider
	 */
	private void createFeedChannel(FeedProvider feedProvider) {
		
		ConstructorArgumentValues ca = new ConstructorArgumentValues();
		ca.addIndexedArgumentValue(0, 10);

		GenericBeanDefinition beanDefinition = getTemplateGenericBeanDefinition( new GenericBeanDefinition() );
		beanDefinition.setBeanClass(QueueChannel.class);
		beanDefinition.setConstructorArgumentValues(ca);

		registry.registerBeanDefinition( getBeanUserName(FEED_CHANNEL, feedProvider)  , beanDefinition);

	}
	
	public static String getBeanUserName(BasicFeedBeans basic , FeedProvider _feed) throws FeedInvalidException{
		FeedProviderValidator.validate(_feed);
		return basic.getLabel() + _feed.getnNumber() + "_" + _feed.getSeq();
	}
	
	private GenericBeanDefinition getTemplateGenericBeanDefinition(GenericBeanDefinition beanDefinition){
		beanDefinition.setLazyInit(false);
		beanDefinition.setAbstract(false);
		beanDefinition.setAutowireCandidate(true);
		beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
		return beanDefinition;
	}

}
