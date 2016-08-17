/**
 * 
 */
package org.feed.rss.dao;

import static org.feed.rss.share.useful.BasicFeedQueryParam.N_NUMBER;
import static org.feed.rss.share.useful.BasicFeedQueryParam.SEQ;
import static org.feed.rss.share.useful.BasicFeedQueryParam.URL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.feed.rss.share.model.FeedProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Santos, Gilberto
 */
@Repository
public class FeedProviderDaoImpl implements FeedProviderDao {

	@Autowired
	private MongoOperations mongoOperation;

	@Override
	public long getNextSequenceId(String nNumber) {
		Query query = new Query(Criteria.where(N_NUMBER).is(nNumber));
		query.with(new Sort(Sort.Direction.DESC, SEQ)).limit(1);

		FeedProvider seqId = mongoOperation.findOne(query, FeedProvider.class);
		
		if (seqId == null || seqId.getSeq().intValue() == 0 ) {
			seqId = new FeedProvider();
			seqId.setSeq(1L);
		} else  {
			seqId.setSeq( seqId.getSeq() + 1 );
		}

		return seqId.getSeq();
	}

	@Override
	public FeedProvider save(FeedProvider feedProvider) {
		feedProvider.setSeq(getNextSequenceId(feedProvider.getnNumber()));
		mongoOperation.save(feedProvider);
		return find(feedProvider);
	}

	@Override
	public boolean remove(FeedProvider feedProvider) {
		Query query = getFeedProvider(feedProvider);
		mongoOperation.findAndRemove(query, FeedProvider.class);
		List<FeedProvider> all = new ArrayList<FeedProvider>( mongoOperation.find(query, FeedProvider.class) );
		return all.isEmpty();
	}

	@Override
	public boolean remove(String url) {
		Query query = new Query(Criteria.where(URL).is(url));
		mongoOperation.findAndRemove(query, FeedProvider.class);
		return mongoOperation.find(query, FeedProvider.class) == null;
	}

	@Override
	public FeedProvider find(FeedProvider feedProvider) {
		return mongoOperation.findOne( getFeedProvider(feedProvider), FeedProvider.class);
	}

	private Query getFeedProvider(FeedProvider feedProvider){
		Query query = null;
		if(feedProvider.getSeq() != null )
			if(feedProvider.getSeq().intValue() > 0 )
				query = new Query( Criteria.where(N_NUMBER).is(feedProvider.getnNumber()).andOperator( Criteria.where(SEQ).is(feedProvider.getSeq()) ) );
			else
				query = new Query( Criteria.where(N_NUMBER).is(feedProvider.getnNumber()) );
		else
			query = new Query( Criteria.where(N_NUMBER).is(feedProvider.getnNumber()).andOperator( Criteria.where(URL).is(feedProvider.getUrl()) ) );
		return query;
		
	}

	@Override
	public Collection<FeedProvider> findAll(FeedProvider feedProvider) {
		Query query = new Query( Criteria.where(N_NUMBER).is( feedProvider.getnNumber() ) );
		return mongoOperation.find(query, FeedProvider.class);
	}

	@Override
	public Collection<FeedProvider> findAll() {
		Query query = new Query( Criteria.where(SEQ).gte(0) );
		return mongoOperation.find(query, FeedProvider.class);
	}
}
