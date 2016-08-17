package org.mail.box.dao;

import java.util.Collection;

import org.mail.box.share.model.MyMessageProvider;

/**
 * @author Santos, Gilberto
 *
 */
public interface MyMessageProviderDao {
	
	/**
	 * @param connect 
	 * @param _myMessageProvider
	 * @return MyMessageProvider with connected attribute as true
	 */
	MyMessageProvider exchangeState(MyMessageProvider _myMessageProvider, boolean connect);
	
	
	/**
	 * 
	 * @param _myMessageProvider
	 * @return MyMessageProvider a complete instance
	 */
	MyMessageProvider find(MyMessageProvider _myMessageProvider);

	/**
	 * 
	 * @return Collection<MyMessageProvider>
	 */
	Collection<MyMessageProvider> findAll();

}
