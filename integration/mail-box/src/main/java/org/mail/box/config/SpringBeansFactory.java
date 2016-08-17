package org.mail.box.config;

import static org.mail.box.share.useful.BasicMessageBeans.MESSAGE_CHANNEL_ADAPTER;
import static org.mail.box.share.useful.BasicMessageBeans.POP_CHANNEL;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.mail.box.share.exceptions.MessageProviderInvalidException;
import org.mail.box.share.model.MyMessageProvider;
import org.mail.box.share.useful.BasicMessageBeans;
import org.mail.box.share.useful.MessageProviderValidator;
import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.SourcePollingChannelAdapterFactoryBean;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.integration.mail.MailReceivingMessageSource;
import org.springframework.integration.mail.Pop3MailReceiver;

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

	public boolean createMyChannel(MyMessageProvider myMessageProvider) {
		try {
			String bean = getBeanUserName(MESSAGE_CHANNEL_ADAPTER, myMessageProvider);
			boolean thereIsBean = getInstance().isThereBean(bean);
			if (!thereIsBean) {
				logger.info(" try create a new bean of spring integration with myMessageProvider for: " + bean);
				getInstance().createMessageChannelAdapter(myMessageProvider);
				thereIsBean = getInstance().isThereBean(bean);
			}
			return thereIsBean;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param MyMessageProvider
	 * @throws Exception
	 */
	private void createMessageChannelAdapter(MyMessageProvider mmp) throws Exception {
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.pop3.port", 110); // TODO verify if that is the standart port at LIT
		//javaMailProperties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//javaMailProperties.put("mail.pop3.socketFactory.fallback", false );
		javaMailProperties.put("mail.debug", false);
		
		Pop3MailReceiver pop3 = new Pop3MailReceiver("pop3://" + mmp.getnNumber() + ":" + mmp.getPassword() + "@localhost:110/INBOX");
		pop3.setApplicationContext(getInstance().applicationContext);
		pop3.setJavaMailProperties(javaMailProperties);
		pop3.setBeanFactory(beanFactory);
		pop3.setShouldDeleteMessages(false);
		pop3.setBeanName( getBeanUserName(POP_CHANNEL, mmp) );
		pop3.afterPropertiesSet();
		
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MailReceivingMessageSource.class);
		builder.addConstructorArgValue(pop3);
		
		BeanDefinitionBuilder adapterBuilder = BeanDefinitionBuilder.genericBeanDefinition(SourcePollingChannelAdapterFactoryBean.class);

		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("source", ((BeanMetadataElement) builder.getBeanDefinition()) );
		mpv.add("outputChannel", (DirectChannel) getBean("receiveChannel") );
		mpv.add("pollerMetadata", getBean("mypoller"));
		mpv.add("autoStartup", Boolean.FALSE);
		
		AbstractBeanDefinition beanDefinition = adapterBuilder.getBeanDefinition();
		beanDefinition.setPropertyValues(mpv);
		
		registry.registerBeanDefinition( getBeanUserName(MESSAGE_CHANNEL_ADAPTER, mmp), beanDefinition );
		
		((SourcePollingChannelAdapter) getBean( getBeanUserName(MESSAGE_CHANNEL_ADAPTER, mmp) ) ).start();
		 
	}
	/**
	 * prepare the name of bean to Spring register it
	 * @param basic
	 * @param _myMessageProvider
	 * @return
	 * @throws MessageProviderInvalidException
	 */
	public static String getBeanUserName(BasicMessageBeans basic , MyMessageProvider _myMessageProvider) throws MessageProviderInvalidException{
		MessageProviderValidator.validate(_myMessageProvider);
		return basic.getLabel() + _myMessageProvider.getnNumber();
	}
	
	/**
	 * relase beans channel of the memory 
	 * @param mmp
	 */
	public void removeChannel(MyMessageProvider mmp){
		((SourcePollingChannelAdapter) getBean( getBeanUserName(MESSAGE_CHANNEL_ADAPTER, mmp) ) ).stop();
		removeBean( getBeanUserName(MESSAGE_CHANNEL_ADAPTER, mmp) );
	}

}
