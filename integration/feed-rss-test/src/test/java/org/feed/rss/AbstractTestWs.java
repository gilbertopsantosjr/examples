package org.feed.rss;

import org.junit.BeforeClass;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class AbstractTestWs {
	
	public static final int OK = 200;
	public static final int NOT_FOUND = 404;
	public static final int CONFLIT = 409;
	public static final int FOUND = 302;
	public static final int NOT_ACCEPTABLE = 406;
	
	@BeforeClass
    public static void init(){
		RestAssured.baseURI = "http://localhost:8080/";
        JsonPath.config = new JsonPathConfig("UTF-8");
    }


}
