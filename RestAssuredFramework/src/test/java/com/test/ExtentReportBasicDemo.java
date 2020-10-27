package com.test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import junit.framework.Assert;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.response.Response;
import junit.framework.Assert;

public class ExtentReportBasicDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);
        
        extent.createTest("ScreenCapture")
        .addScreenCaptureFromPath("extent.png")
        .pass(MediaEntityBuilder.createScreenCaptureFromPath("extent.png").build());
        
        ExtentTest test1= extent.createTest("Api Testing for get user","Get the user details");
        Response resp = get("https://reqres.in/api/users?page=2");
        test1.log(Status.INFO, "Sent the get request ");
		
		System.out.println(resp.asString());
		System.out.println(resp.getBody());
		System.out.println(resp.statusCode());
		System.out.println(resp.getHeader("content-type"));
		
		
		int statusCode = resp.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		test1.log(Status.INFO, "Test ended ");
		extent.flush();
	}

}
