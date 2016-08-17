/**
 * 
 */
package org.mail.box.dao;

import java.util.Collection;

import org.mail.box.share.model.MyMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Santos, Gilberto
 */
@Repository
public class MyMessageProviderDaoImp implements MyMessageProviderDao {

	@Autowired
	private MongoOperations mongoOperation;

	@Override
	public MyMessageProvider exchangeState(MyMessageProvider _myMessageProvider, boolean connect) {

		MyMessageProvider saved = find(_myMessageProvider);
		
		if(saved == null)
			saved = save(_myMessageProvider);
		
		Update update = new Update();
		update.set("connected", connect);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		MyMessageProvider changed = mongoOperation.findAndModify( getQueryToFindByNNumberAndPassword(saved), update, options, MyMessageProvider.class);
		
		return changed;
	}

	private MyMessageProvider save(MyMessageProvider _myMessageProvider) {
		mongoOperation.save(_myMessageProvider);
		return find(_myMessageProvider);
	}

	@Override
	public MyMessageProvider find(MyMessageProvider _myMessageProvider) {
		return mongoOperation.findOne( getQueryToFindByNNumberAndPassword(_myMessageProvider), MyMessageProvider.class);
	}
	
	
	private Query getQueryToFindByNNumberAndPassword(MyMessageProvider _myMessageProvider){
		return new Query(Criteria.where("nNumber").is(_myMessageProvider.getnNumber()).andOperator( Criteria.where("password").is(_myMessageProvider.getPassword() ) ) );
	}

	@Override
	public Collection<MyMessageProvider> findAll() {
		return mongoOperation.findAll(MyMessageProvider.class);
	}

}
