package com.RestAssured.testCases;

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

public class DELETE_1_Test extends test_base
{
	@Test
	public void Delete_1() throws InterruptedException
	{
		setUp();
		logger.info("***** Started DELETE Request in lists *****");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		start();
		
		ExtentTest test = extent.createTest("DELETE_1", "Check DELETE Request in lists");	

		logger.info("***** Checking DELETE Request in lists *****");

		response=httpReq.request(Method.GET,"/lists");
		JsonPath i=response.jsonPath();
		int id=i.get("[22].id");
		int rev=i.get("[22].revision");

		JSONObject request = new JSONObject();
		request.put("revision",rev);
		httpReq.queryParams(request);
		httpReq.pathParam("id",id);

		Response response =httpReq.request(Method.DELETE,"/lists/{id}");
		response.prettyPrint();

		int code = response.getStatusCode();
		Assert.assertEquals(code, 204);
		test.log(Status.INFO, "Status code of DELETE Request from lists");
		
		logger.info("***** Finished DELETE Request in lists *****");
	}
	
}
