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

public class PUT_2_Test extends test_base
{
	@Test
	void put_2() throws InvalidFormatException, IOException, InterruptedException 
	{
		setUp();
		logger.info("***** Started PUT Request in memberships *****");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		start();
		
		ExtentTest test = extent.createTest("PUT_2", "Check PUT Request in memberships");

		logger.info("***** DATA OF PUT REQUEST for memberships *****");

		response=httpReq.request(Method.GET,"/memberships");
		JsonPath i=response.jsonPath();
		int revision=i.get("[2].revision");
		String t="accepted";
		JSONObject json = new JSONObject();
		json.put("state", t);
		json.put("revision",revision);

		int id=i.get("[2].id");
		httpReq.queryParams(json);
		httpReq.pathParam("id", id);
		httpReq.header("Content-Type", "application/json");
		httpReq.body(json.toString());
		response = httpReq.request(Method.PUT, "/memberships/{id}");
		response.prettyPrint();

		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);
		test.log(Status.INFO, "Status code of PUT Request from memberships");
		
		logger.info("***** Finished PUT Request in memberships*****");

	}
	
}
