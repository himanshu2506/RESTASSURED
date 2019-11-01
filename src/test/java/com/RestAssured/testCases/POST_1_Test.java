package com.RestAssured.testCases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.RestAssured.base.test_base;
import com.RestAssured.utilities.ExcelLib;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;

public class POST_1_Test extends test_base
{
	ExcelLib exceldata=new ExcelLib();
	@Test
	void post_1() throws InterruptedException, InvalidFormatException, IOException
	{
		setUp();
		logger.info("***** Started POST Request in lists with different sets of data *****");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		start();
		
		ExtentTest test = extent.createTest("POST_1", "Check first POST Request");

		logger.info("***** DATA OF FIRST POST REQUEST *****");
		JSONObject json = new JSONObject();
		String d1=exceldata.getExcelData("POST1", 1, 1);
		String d2=exceldata.getExcelData("POST1",1,2);
		String d3=exceldata.getExcelData("POST1", 1, 3);
		String d4=exceldata.getExcelData("POST1", 1, 4);
		String d5=exceldata.getExcelData("POST1", 1, 5);
		String d6= exceldata.getExcelData("POST1", 1, 6);


		json.put("id", "234567");
		json.put("title", d1);
		json.put("owner_type", d2);
		json.put("owner_id", d3);
		json.put("list_type", d4);
		json.put("revision", d5);
		json.put("type", d6);

		httpReq.body(json.toString());
		response = httpReq.post("https://a.wunderlist.com/api/v1/lists");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 201);
		test.log(Status.INFO, "Status code of POST_1 Request");
		
		logger.info("***** Finished POST Request in lists with different sets of data *****");
	}
}
