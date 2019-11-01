package com.RestAssured.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.RestAssured.base.test_base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GET_2_Test extends test_base
{
	@Test
	public void checkGETbyID() throws InterruptedException
	{
		setUp();
		logger.info("***** Started GET Request in lists by ID *****");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		start();
		
		ExtentTest test = extent.createTest("GET_2", "Check GET Request in lists by ID");
		
		logger.info("***** Checking  GET Request in lists By ID *****");
		response =httpReq.request(Method.GET,"/lists/409041278");
		String responseBody=response.prettyPrint();
		logger.info("Response Body==>"+responseBody);
		
		Assert.assertEquals(responseBody.contains("409041278"),true);
		test.log(Status.INFO, "Response Body of GET Request from lists by ID");
		
		int code=response.getStatusCode();
		logger.info("Status Code==>"+code);
		
		Assert.assertEquals(code, 200);
		test.log(Status.INFO, "Status Code of GET Request from lists by ID");
		
		logger.info("***** Finished GET Request in lists by ID *****");
		
	}
	
}
