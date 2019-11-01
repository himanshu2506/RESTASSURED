package com.RestAssured.testCases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PUT_1_Test extends test_base
{
	
	@Test
	void put_1() throws InvalidFormatException, IOException, InterruptedException 
	{
		setUp();
		logger.info("***** Started PUT Request of lists *****");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		start();
		
		ExtentTest test = extent.createTest("PUT_1", "Check  PUT Request in lists");
		
		logger.info("***** DATA OF PUT REQUEST for lists*****");
		
		response=httpReq.request(Method.GET,"/lists");
		JsonPath i=response.jsonPath();
		int revision=i.get("[2].revision");
		String t="Ram";
		JSONObject json = new JSONObject();
		json.put("title", t);
		json.put("revision",revision);
		
		httpReq.queryParams(json);
		httpReq.pathParam("id", 409041278);
		httpReq.header("Content-Type", "application/json");
		httpReq.body(json.toString());
	    response = httpReq.request(Method.PUT, "/lists/{id}");
	    response.prettyPrint();
	    
		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);
		test.log(Status.INFO, "Status code of PUT Request from lists");
		
		logger.info("***** Finished PUT Request in lists*****");
	}
	public void tearDown()
	{
		
	}
	
}
