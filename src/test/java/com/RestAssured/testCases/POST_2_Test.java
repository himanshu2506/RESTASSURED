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

public class POST_2_Test extends test_base
{
	ExcelLib exceldata=new ExcelLib();
	@Test
	void post_2() throws InterruptedException, InvalidFormatException, IOException
	{
		setUp();
		logger.info("***** Started POST Request *****");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		start();
		
		ExtentTest test = extent.createTest("POST_2", "Check second POST Request");

		logger.info("***** DATA OF SECOND POST REQUEST *****");
		JSONObject json = new JSONObject();
		String d11=exceldata.getExcelData("POST1", 2, 1);
		String d22=exceldata.getExcelData("POST1",2,2);
		String d33=exceldata.getExcelData("POST1", 2, 3);
		String d44=exceldata.getExcelData("POST1", 2, 4);
		String d55=exceldata.getExcelData("POST1", 2, 5);
		String d66= exceldata.getExcelData("POST1", 2, 6);


		json.put("id", "123456");
		json.put("title", d11);
		json.put("owner_type", d22);
		json.put("owner_id", d33);
		json.put("list_type", d44);
		json.put("revision", d55);
		json.put("type", d66);

		httpReq.body(json.toString());
		response = httpReq.post("https://a.wunderlist.com/api/v1/lists");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 201);
		test.log(Status.INFO, "Status code of POST_2 Request");
		
		logger.info("***** Finished POST Request  *****");
}
}
