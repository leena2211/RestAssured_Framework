package com.test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Test01_GET {
	
	@Test
	void test_01() {
		Response resp = RestAssured.get("https://reqres.in/api/users?page=2");
		
		System.out.println(resp.asString());
		System.out.println(resp.getBody());
		System.out.println(resp.statusCode());
		System.out.println(resp.getHeader("content-type"));
		
		int statusCode = resp.getStatusCode();
		Assert.assertEquals(statusCode, 400);
		
	}

}
	