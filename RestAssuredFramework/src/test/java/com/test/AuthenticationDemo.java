package com.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthenticationDemo {

	
	@BeforeClass
		public static String setup() {
			
//			Response res = given()
//			.param("grant_type", "client_credentials")
//			.accept("application/json")
//			.headers("Authorization", "Basic xyz12357789jksdj", "ChannelId","")
//			.auth().basic("userName", "password")
//			.contentType(ContentType.JSON)
//			.when()
//			//.get("http://restapi.demoqa.com/authentication/CheckForAuthentication")
//			.then()
//			//.statusCode(200)
//			.log().all()
//			.extract().response();
			
			String token = "FFG54546656DFDFDSF";
					//res.jsonPath().getString("access_token");
			
			return token;
			
			
		}
}
