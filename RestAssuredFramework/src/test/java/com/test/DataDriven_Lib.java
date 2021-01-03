package com.test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utils.ExcelDataProvider;
import config.PropertiesFile;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class DataDriven_Lib  extends ExcelDataProvider{
	 ExtentReports extent;
	 ExtentSparkReporter spark;
	 ExtentTest test;
	 public static String env = null;
	 public static String url = null;
	
	@BeforeSuite
	public void report_setup(){
		extent = new ExtentReports();
        spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);
        
      //configuration items to change the look and feel
        //add content, manage tests etc
        
        spark.config().setDocumentTitle("Extent Report Demo");
        spark.config().setReportName("Api Test Report"); 
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        
	}
	
    @BeforeClass
	public String setup() {
		
		String accessToken = AuthenticationDemo.setup();

		return accessToken;
		
	}
	
	//Set the url based on environment
	@BeforeTest
	public void env_setup() {
		
		PropertiesFile.getProperties();
		
		if(env.equalsIgnoreCase("DLAB")) {
			url = "https://reqres.in";
		}else if(env.equalsIgnoreCase("QLAB")) {
			url= "https://reqres.in";
		}
		
	}
	
	@Test(dataProvider="testData1")
	void test_post_01(String testCase_ID, String description,String name, String job,String statusCode) {
	    test =extent.createTest(testCase_ID,description);
        
		
		JSONObject request = new JSONObject();
		
		request.put("name", name);
		request.put("job", job);
		
		//System.out.println(request);
		System.out.println(request.toJSONString());
		baseURI = url;
		String accessToken = setup();
		System.out.println("this is baseURL "+baseURI);
				
		Response res = 
		given()
		.header("Content-Type","application/json")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.auth().oauth2(accessToken)
		.body(request.toJSONString())
		.when()
		.post("/api/users")
		.then()
		
		.assertThat().body("name",equalTo(name))
		.log().all()
		.extract().response();
		
		String id= res.jsonPath().getString("id");// get data from response parameter
		//System.out.println("User id is :"+id);
		
		
		String jsonString = res.asString();
		System.out.println(jsonString);
		Assert.assertEquals(jsonString.contains("createdAt"), true);
		
		int code= res.getStatusCode();
		Assert.assertEquals(code, Integer.parseInt(statusCode));
		
				
	}
	
	@Test(groups={"smoke"})
	void test_getByUserID() {
		
		test =extent.createTest("TC-2 Get valid response","Test Passed");
		
		baseURI = url;
		when()
		.get("/api/users/2")
		.then()
		.statusCode(200)
		.body("data.first_name",equalTo("Janet"))
		.log().body();
		
	}

	//@Parameters({434})
	//@Test
	public void test_delete(int userId) {
		test =extent.createTest("TC-3 Delete the user","Test Passed");
		
		System.out.println("Value of userId: "+userId);
		baseURI = "https://reqres.in";
		
		when()
		.delete("/api/users/"+userId)
		.then()
		.statusCode(204);
		
	}
	
	 @AfterMethod 
	    public void getResult(ITestResult result) {
		//create labels like 'PASSED' / 'FAILED' and also to give colors to the different status in the test report
	        if(result.getStatus() == ITestResult.FAILURE) {
	            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
	            test.fail(result.getThrowable());
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS) {
	            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
	        }
	        else {
	            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
	            test.skip(result.getThrowable());
	        }
	    }
	
	 @AfterTest
	    public void tearDown() {
	    	//to write or update test information to reporter
	        extent.flush();
	 }

}
