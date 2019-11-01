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

public class PATCH_1_Test extends test_base
{
	@Test
	void patch_1() throws InvalidFormatException, IOException, InterruptedException 
	{
		setUp();
		logger.info("***** Started PATCH Request in lists *****");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		start();
		
		ExtentTest test = extent.createTest("PATCH_1", "Check  PATCH Request in lists");
		
		logger.info("***** DATA OF PATCH REQUEST for lists*****");
		
		response=httpReq.request(Method.GET,"/lists");
		JsonPath i=response.jsonPath();
		int revision=i.get("[2].revision");
		String t="Raju";
		JSONObject json = new JSONObject();
		json.put("title", t);
		json.put("revision",revision);
		
		httpReq.queryParams(json);
		httpReq.pathParam("id", 409041278);
		httpReq.header("Content-Type", "application/json");
		httpReq.body(json.toString());
	    response = httpReq.request(Method.PATCH, "/lists/{id}");
	    response.prettyPrint();
	    
		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);
		test.log(Status.INFO, "Status code of PATCH Request from lists");
		
		logger.info("***** Finished PATCH Request in lists *****");
		
	}
	
}
