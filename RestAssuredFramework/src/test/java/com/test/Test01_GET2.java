package com.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import junit.framework.Assert;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Test01_GET2 extends BaseClass{
	
	@Test
	void test_01() {
		Response resp = get("https://reqres.in/api/users?page=2");
		
		System.out.println(resp.asString());
		System.out.println(resp.getBody());
		System.out.println(resp.statusCode());
		System.out.println(resp.getHeader("content-type"));
		
		int statusCode = resp.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
    @Test
    void test_02() {
    	given()
    	//.header("Content-Type", "application/json").and().header("channelId", "CARE")
    	//.param("parameterName","parameterValues")
    	.get("https://reqres.in/api/users?page=2")
    	.then()
    	.statusCode(200)
    	.body("data.id[0]",equalTo(7))
    	.body("data.first_name", hasItems("Michael","Lindsay"))
    	.log().all();
    }

	
	
		
		
	

}

	