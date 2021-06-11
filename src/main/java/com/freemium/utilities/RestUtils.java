/*package com.freemium.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtils {
	public static Logger logger;
	public Properties properties;
	public ExtentReports extent;
	public ExtentTest extentTest;
	RequestSpecification httpRequest;
	Response response;

	private final String propertyFilePath = (System.getProperty("user.dir")
			+ "\\src\\test\\resources\\properties\\automationrepository.properties");

	public RestUtils() {
		PropertyConfigurator.configure("Log4j.properties");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("automationrepository.properties not found at " + propertyFilePath);
		}

	}




	public static String getTestCaseName(String sTestCase) throws Exception {
		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			throw (e);
		}
	}

	// Reports
	public ExtentReports onStart() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String reportName = "Technavio_ExtentReport-" + timeStamp + ".html";
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/" + reportName, true);
		extent.addSystemInfo("Host Name", "Technavio Live Phalcon");
		extent.addSystemInfo("User Name", "QA Automation");
		extent.addSystemInfo("Environment", "Test server");
		return extent;
	}

	public void endReport(ITestResult result) throws IOException {
		logger.info("@AfterMethod");
		if (result.getStatus() == ITestResult.FAILURE) {

			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
			//String screenshotPath = captureScreen(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture("screenshotPath")); // to add screenshot in extent
																							// report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}

		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

	}
	// Properties Read

	public String getBrowserName() {
		String browserName = properties.getProperty("browserName");
		if (browserName != null)
			return browserName;
		else
			throw new RuntimeException("browser name not specified in the automationrepository.properties file.");
	}

	public String getExcelPath() {
		String excelPath = properties.getProperty("freemiumExcelPath");
		if (excelPath != null)
			return excelPath;
		else
			throw new RuntimeException("browser name not specified in the automationrepository.properties file.");
	}

	public String getSiteUrl() {
		String freemiumSiteUrl = properties.getProperty("freemiumDevUrl");
		if (freemiumSiteUrl != null)
			return freemiumSiteUrl;
		else
			throw new RuntimeException(" Freemium Url not specified in the automationrepository.properties file.");
	}

	public String getTestDataFileName() {
		String freemiumTestDataFileName = properties.getProperty("freemiumTestDataFileName");
		if (freemiumTestDataFileName != null)
			return freemiumTestDataFileName;
		else
			throw new RuntimeException(
					" freemiumTestDataFileName not specified in the automationrepository.properties file.");
	}

	public String getFreemiumUserName() {
		String freemiumUserName = properties.getProperty("userName");
		if (freemiumUserName != null)
			return freemiumUserName;
		else
			throw new RuntimeException(" freemium userName not specified in the automationrepository.properties file.");
	}

	public String getFremiumPassword() {
		String freemiumPassword = properties.getProperty("password");
		if (freemiumPassword != null)
			return freemiumPassword;
		else
			throw new RuntimeException(" freemium password not specified in the automationrepository.properties file.");
	}
	public String getURI() {
		String freemiumPassword = properties.getProperty("freemiumRestURI");
		if (freemiumPassword != null)
			return freemiumPassword;
		else
			throw new RuntimeException(" freemium password not specified in the automationrepository.properties file.");
	}

}
*/