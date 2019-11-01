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

public class DELETE_2_Test extends test_base
{
	@Test
	public void Delete_2() throws InterruptedException
	{
		setUp();
		logger.info("***** Started DELETE Request  *****");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		start();
		
		ExtentTest test = extent.createTest("DELETE_2", "Check DELETE Request ");	

		logger.info("***** Checking DELETE Request  *****");

		response=httpReq.request(Method.GET,"/lists");
		JsonPath x=response.jsonPath();
		int y=x.get("[23].id");
		int re=x.get("[23].revision");

		JSONObject req = new JSONObject();
		req.put("revision",re);
		httpReq.queryParams(req);
		httpReq.pathParam("id",y);

		Response response =httpReq.request(Method.DELETE,"/lists/{id}");
		response.prettyPrint();

		int cod = response.getStatusCode();
		Assert.assertEquals(cod, 204);
		test.log(Status.INFO, "Status code of DELETE Request");
		
		logger.info("***** Finished DELETE Request *****");
	}
	
}
