package com.test;

//import java.util.HashMap;
//import java.util.Map;
import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import junit.framework.Assert;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test_POST {
	
	@Test
	void test_post_01() {
		
		//Map<String,Object> map = new HashMap<String,Object>();
			
		//map.put("name", "Leena");
		//map.put("job", "QA Engineer");
		
		//System.out.println(map);
		
		//JSONObject request = new JSONObject(map);
		JSONObject request = new JSONObject();
		
		request.put("name", "Leena");
		request.put("job", "QA Engineer");
		System.out.println(request);
		System.out.println(request.toJSONString());
		
		given()
		.header("Content-Type","application/json")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post("https://reqres.in/api/users")
		.then()
		.assertThat().body("name",equalTo("MS Dhoni"))
		.statusCode(201)
		.log().all();
				
	}
	
	//@Test
	void test_put_02() {
		
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Leena");
		request.put("job", "SDET");
		System.out.println(request);
		System.out.println(request.toJSONString());
		
		given()
		.header("Content-Type","application/json")
		.contentType(ContentType.JSON)
		//.auth().oauth2(accessToken)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.put("https://reqres.in/api/users/651")
		.then()
		.statusCode(200)
		.log().all();
				
	}
	
	@Test
	void test_delete_03() {
		
		when()
		.delete("https://reqres.in/api/users/651")
		.then()
		.statusCode(204)
		.log().all();
				
	}


}
