package com.RestAssured.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

import com.RestAssured.utilities.ReportLib;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class test_base extends ReportLib
{

	public static RequestSpecification httpReq;
	public static Response response;
	public static String contentType="application/json";
	public static String accessToken="3b7e9045900544cbac1955e6d37d3c1e2b135d01c3d92767c5286e0c0171";
	public static String clientID="812bc83302a98a92eea5";
	
	
	public Logger logger;
	
	
	public void setUp()
	{
		logger=Logger.getLogger("RestAPI");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
	public  void start() throws InterruptedException
	{
		httpReq=RestAssured.given();
		httpReq.header("Content-Type",contentType);
		httpReq.header("X-Access-Token",accessToken);
		httpReq.header("X-Client-ID",clientID);
		httpReq.log().all();
		Thread.sleep(3000);	
	}
}
