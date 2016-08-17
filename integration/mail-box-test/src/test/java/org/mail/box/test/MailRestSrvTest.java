package org.mail.box.test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mail.box.share.useful.BasicMessageRestServices.CONNECT;
import static org.mail.box.share.useful.BasicMessageRestServices.DISCONNECT;
import static org.mail.box.share.useful.BasicMessageRestServices.SHOW_ALL;

import java.util.Collection;

import org.apache.commons.mail.SimpleEmail;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mail.box.share.model.MyMessageProvider;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MailRestSrvTest extends AbstractTestWs {
	
	private static MyMessageProvider myMessageProvider;
	
	@BeforeClass
	public static void getMailBoxProvider(){
		myMessageProvider = new MyMessageProvider();
		myMessageProvider.setnNumber("demo");
		myMessageProvider.setPassword("123456");
	}
	
	@Test
	public void a_shouldConnectinWithMailBox() {
		String json = given()
			        .contentType(ContentType.JSON)
			        .accept(ContentType.JSON)
			        	.body(myMessageProvider)
			        .expect()
			         	.statusCode(OK)
			         		.log().ifError()
			            .post(CONNECT)
			            	.andReturn()
			            		.asString();
	 
		myMessageProvider = given(json).getObject("", MyMessageProvider.class);
		 
		 assertThat( myMessageProvider.isConnected(), is(true) );
	}
	
	private void enviaEmailSimples() throws RuntimeException {  
		try {
			SimpleEmail email = new SimpleEmail();  
	        email.setHostName("127.0.0.1");   
	        email.addTo("demo@localhost.com", "Gilberto");   
	        email.setFrom("demo@localhost.com", "I'm");  
	        email.setSubject("Test -> Email simples");  
	        email.setMsg("Test of Email by using commons-email");   
	        email.setAuthentication("demo", "123456");  
	        //email.setSmtpPort(465);  
	        //email.setSSL(false);  
	        //email.setTLS(false);  
	        email.send();
	        // after sent email wait for a pool of connection by spring integration
	        Thread.sleep(20000);
	        
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }  
	
	@Test
	public void b_shouldListAllMailInBox() {
		
		enviaEmailSimples();
		
		final String json = given()
				                .contentType(ContentType.JSON)
				                .accept(ContentType.JSON)
				                .pathParam("nNumber", myMessageProvider.getnNumber())
				            .expect()
				                .statusCode(OK)
				                .log().ifError()
				            .when()
				                .get(SHOW_ALL)
				            .asString();

		JsonPath jp = new JsonPath(json);
        final Collection<?> inbox = jp.get("");
        
        assertThat( inbox, is(not((nullValue())) ) );
	 
	}
	
	@Test
	public void b_shouldListMoreThanOneMailInBox() {
		
		for (int i = 0; i < 2; i++) {
			enviaEmailSimples();	
		}
		
		final String json = given()
				                .contentType(ContentType.JSON)
				                .accept(ContentType.JSON)
				                .pathParam("nNumber", myMessageProvider.getnNumber())
				            .expect()
				                .statusCode(OK)
				                .log().ifError()
				            .when()
				                .get(SHOW_ALL)
				            .asString();

		JsonPath jp = new JsonPath(json);
        final Collection<?> inbox = jp.get("");
        
        assertThat( inbox.size(), greaterThan(1) );
	 
	}
	
	@Test
	public void c_shouldDisconnectinWithMailBox() {
		String json = given()
			        .contentType(ContentType.JSON)
			        .accept(ContentType.JSON)
			        	.body(myMessageProvider)
			        .expect()
			         	.statusCode(OK)
			         		.log().ifError()
			            .post(DISCONNECT)
			            	.andReturn()
			            		.asString();
	 
		myMessageProvider = given(json).getObject("", MyMessageProvider.class);
		 
		 assertThat( myMessageProvider.isConnected(), is(false) );
	}
	
}
