package com.test;

import static io.restassured.RestAssured.get;

import org.testng.Assert;
import org.testng.annotations.Test;


public class GetData {
	
	@Test
	public void testResponseCode() {
		//Response resp = RestAssured.get("https://reqres.in/api/users/2");
		//int code= resp.getStatusCode();
		int code = get("https://reqres.in/api/users/2").getStatusCode();
		
		System.out.println("Actual Status code: "+code);
		Assert.assertEquals(code, 400);
	}
	
	

	@Test
	public void testBody() {
		//Response resp = RestAssured.get("https://reqres.in/api/users/2");
		//String dataBody= resp.asString();
		String dataBody = get("https://reqres.in/api/users/2").asString();
		long time = get("https://reqres.in/api/users/2").getTime();
		
		System.out.println("Response body : "+dataBody);
		System.out.println("Response time : "+time);
		
		
	}

}
