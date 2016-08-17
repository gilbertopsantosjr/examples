package org.feed.rss.dao;

import java.util.Collection;

import org.feed.rss.share.model.FeedProvider;

/**
 * @author Santos, Gilberto
 */
public interface FeedProviderDao {

	/**
	 * 
	 * @param key
	 * @return the next auto increment id
	 */
	public long getNextSequenceId(String key);
	
	/**
	 * 
	 * @param feedProvider
	 * @return true if the document are save in database
	 */
	public FeedProvider save(FeedProvider feedProvider);
	
	/**
	 * 
	 * @param id
	 * @return true if all the document are remove in database
	 */
	public boolean remove(FeedProvider feedProvider);
	
	
	/**
	 * 
	 * @param id
	 * @return true if a document with id == key are remove in database
	 */
	public boolean remove(String key);
	
	
	
	/**
	 * return a feedProvider instance if there is on database 
	 * @param feedProvider
	 * @return FeedProvider
	 */
	public FeedProvider find(FeedProvider feedProvider);
	
	
	/**
	 * return all feedProvider by user 
	 * @param feedProvider
	 * @return Collection<FeedProvider>
	 */
	public Collection<FeedProvider> findAll(FeedProvider feedProvider);
	
	
	/**
	 * return all feedProvider
	 * @return Collection<FeedProvider>
	 */
	public Collection<FeedProvider> findAll();
}