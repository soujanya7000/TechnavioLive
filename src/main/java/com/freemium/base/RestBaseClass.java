/*package com.freemium.base;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.freemium.utilities.RestUtils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestBaseClass extends RestUtils{
	public static RequestSpecification httpRequest;
	public static Response response;
	
	@BeforeSuite
	public void setUp() throws Exception {
		logger = Logger.getLogger(this.getClass());
		
		logger.info(" @BeforeSuite Calling");
		
	}
	@AfterTest
	public void endsetUp() throws Exception {
		//extent.endTest(extentTest);
		extent.flush();
		extent.close();
		//extent.endTest(extentTest);
	}

}
*/