package org.feed.rss;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.given;
import static org.feed.rss.share.useful.BasicFeedQueryParam.N_NUMBER;
import static org.feed.rss.share.useful.BasicFeedQueryParam.SEQ;
import static org.feed.rss.share.useful.BasicFeedRestServices.CREATE;
import static org.feed.rss.share.useful.BasicFeedRestServices.DELETE;
import static org.feed.rss.share.useful.BasicFeedRestServices.DELETE_ALL;
import static org.feed.rss.share.useful.BasicFeedRestServices.SHOW_ALL;
import static org.feed.rss.share.useful.BasicFeedRestServices.SHOW_PROVIDERS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.Collection;

import org.feed.rss.share.model.FeedProvider;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedRestSrvTest extends AbstractTestWs {
	
	private static FeedProvider feedProvider;
	
	@BeforeClass
	public static void getFeedProvider(){
		feedProvider = new FeedProvider(RandomFeedProvider.getNNumber(), RandomFeedProvider.getOneFeed());
	}
	
	@Test
	public void a_shouldCreateAFeed() {
		String json = given()
			        .contentType(ContentType.JSON)
			        .accept(ContentType.JSON)
			        	.body(feedProvider)
			        .expect()
			         	.statusCode(OK)
			         		.log().ifError()
			            .post(CREATE)
			            	.andReturn()
			            		.asString();
	 
		 feedProvider = given(json).getObject("", FeedProvider.class);
		 
		 assertThat( feedProvider.getSeq().intValue(), greaterThan(0) );
	}
	
	@Test
	public void b_shouldNotCreateAFeed(){
		given()
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	            .body(feedProvider)
	        .expect()
	            .statusCode(OK)
	            .log().ifError()
        .post(CREATE);
	}
	
	@Test
	public void c_shouldListAllFeeds() {
		final String json = given()
				                .contentType(ContentType.JSON)
				                .accept(ContentType.JSON)
				                .pathParam(N_NUMBER, feedProvider.getnNumber())        		
				                .pathParam(SEQ, feedProvider.getSeq())
				            .expect()
				                .statusCode(OK)
				                .log().ifError()
				            .when()
				                .get(SHOW_ALL)
				            .asString();

		JsonPath jp = new JsonPath(json);
        final Collection<?> feeds = jp.get("");
        
        assertThat( feeds, is(not((nullValue())) ) );
	 
	}

	@Test
	public void d_shouldDeleteAFeed() {
		given()
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
		        .pathParam(N_NUMBER, feedProvider.getnNumber())        		
	            .pathParam(SEQ, feedProvider.getSeq())
	        .expect()
	        	.statusCode(OK)
	            	.log().ifError()
        .delete(DELETE);
	}
	
	@Test
	public void e_shouldNotDeleteAFeed(){
		
		final FeedProvider _feedProvider = new FeedProvider("123456", "http://hatunamatata.com");
		_feedProvider.setSeq(0L);
		
		given()
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
            .pathParam(N_NUMBER, _feedProvider.getnNumber())        		
            .pathParam(SEQ, _feedProvider.getSeq())
        .expect()
            .statusCode(NOT_FOUND)
            	.log().ifError()
        .delete(DELETE);
	}
	
	@Test
	public void f_shouldBeValidInputs(){
		final FeedProvider _feedProvider = new FeedProvider("asfsd56", "http://hatunama#$%$%^&");
		
		given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        	.body(_feedProvider)
	    .expect()
	     	.statusCode(NOT_ACCEPTABLE)
	     		.log().ifError()
	        .post(CREATE);
	}
	
	@Test
	public void g_shouldListAllFeedProvidersByUser(){
		final String json = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam(N_NUMBER, feedProvider.getnNumber())        		
            .expect()
                .statusCode(OK)
                .log().ifError()
            .when()
                .get(SHOW_PROVIDERS)
		            .asString();
		
		JsonPath jp = new JsonPath(json);
		final Collection<?> feeds = jp.get("");
		
		assertThat( feeds, is(not((nullValue())) ) );
	}
	
	
	@Test
	public void h_shouldCreateANewFeedForTheSameNNumber() {
		
		final FeedProvider _feedProvider = new FeedProvider(feedProvider.getnNumber(), RandomFeedProvider.getOneFeed());
		
		String json = given()
			        .contentType(ContentType.JSON)
			        .accept(ContentType.JSON)
			        	.body(_feedProvider)
			        .expect()
			         	.statusCode(OK)
			         		.log().ifError()
			            .post(CREATE)
			            	.andReturn()
			            		.asString();
	 
		 feedProvider = given(json).getObject("", FeedProvider.class);
		 
		 assertThat( feedProvider.getSeq().intValue(), greaterThanOrEqualTo(1) );
	}
	
	@Test
	public void i_shouldDeleteAllFeedProviderByNNumber(){
			given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam(N_NUMBER, feedProvider.getnNumber())         		
            .expect()
                .statusCode(OK)
                .log().ifError()
            .when()
                .delete(DELETE_ALL)
		            .asString();
	}
	

	
}
