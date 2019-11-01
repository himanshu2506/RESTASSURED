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

public class GET_1_Test extends test_base
{
	
	@Test
	public void checkGET() throws InterruptedException
	{
		setUp();
		logger.info("***** Started GET Request in list_positions *****");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		start();
		
		ExtentTest test = extent.createTest("GET_1", "Check GET Request in list_positions");
		
		logger.info("***** Checking GET Request in list_positions *****");
		response =httpReq.request(Method.GET,"/list_positions");
		String responseBody=response.prettyPrint();
		logger.info("Response Body==>"+responseBody);
		
		Assert.assertTrue(responseBody!=null);
		test.log(Status.INFO, "Response Body of GET Request from list_position");
		
		int code=response.getStatusCode();
		logger.info("Status Code==>"+code);
		
		Assert.assertEquals(code, 200);
		test.log(Status.INFO, "Status code of GET Request from list_position");
		
		logger.info("***** Finished GET Request in list_positions*****");
	}
	public void tearDown()
	{
		
	}
}
