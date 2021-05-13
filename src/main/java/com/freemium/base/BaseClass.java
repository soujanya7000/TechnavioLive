package com.freemium.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.freemium.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass extends Utilities {

	@BeforeSuite
	public void setUp() throws Exception {
		// extentTest = onStart().startTest("Freemium");
		logger = Logger.getLogger(this.getClass());
		logger.info(" @BeforeSuite Calling");
		driver = OpenBrowser();
		
	}
	/*@BeforeSuite
	public void startsetUp() throws Exception {
		
	}*/
	/*@AfterTest
	protected void endsetUp() throws Exception {
		extent.flush();
		extent.close();
	}*/
	
	
	@AfterSuite
	public void closeBrowser() throws Exception {
		//driver.quit();
	/*	extent.flush();
		extent.close();*/
		logger.info(" Closing Browser ");
		//extentTest.log(LogStatus.INFO, " Closing Browser");
		logger.info(" @AfterSuite  ");
	}

}
