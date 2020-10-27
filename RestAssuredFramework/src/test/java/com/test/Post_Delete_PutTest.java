package com.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post_Delete_PutTest {
	
	@Test
	public void test1() {
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type","application/json");
		
		JSONObject json = new JSONObject();
		json.put("email", "eve.holt@reqres.in");
		json.put("password", "cityslicka");
		
		//POST the request
		request.body(json.toJSONString());
		Response body = request.post("https://reqres.in/api/login");
		
		//Delete the request 
		//Response body = request.delete("https://reqres.in/api/login");
		
		//PUT request
		//Response body = request.put("https://reqres.in/api/login");
		
		int code = body.getStatusCode();
		Assert.assertEquals(code, 200);
	}
	

}
