/*package com.freemium.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.freemium.base.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;





public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
		
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		//htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location of the report
		htmlReporter=new ExtentHtmlReporter("C:\\Users\\Gurudeesh\\eclipse-workspace\\com.technavio\\test-output"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "\\src\\test\\resources\\extentconfig\\extent-config.xml");
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","Nellore Gurudeesh");
		
		htmlReporter.config().setDocumentTitle("Technavio Test Project"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath); 
		
		if(f.exists())
		{
		try {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			} 
		catch (IOException e) 
				{
				e.printStackTrace();
				}
		}
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}


public class Reporting{
	
	public RemoteWebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String reportName="Technavio_ExtentReport-"+timeStamp+".html";
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/"+reportName, true);
		extent.addSystemInfo("Host Name", "Technavio Live Phalcon");
		extent.addSystemInfo("User Name", "QA Automation");
		extent.addSystemInfo("Environment", "Test server");
		
	}
	
	
	
	public void onTestSuccess(ITestResult tr) 
	{
		extentTest = extent.startTest(tr.getName());
		extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + tr.getName());
	}
	
	public void onTestFailure(String testCaseName) throws IOException  {
		extentTest = extent.startTest(testCaseName);
		extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+testCaseName); //to add name in extent report
		//extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+tr.getThrowable()); //to add error/exception in extent report
		
		String screenshotPath;
		// String screenShot = (System.getProperty("user.dir")+"/Screenshots/UserName120210226095253.png");
			screenshotPath = BaseClass.captureScreen(driver, testCaseName);
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); 
		} 
		//to add screenshot in extent report
		//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
	

	public void onTestSkipped(ITestResult tr)
	{
		extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + tr.getName());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.endTest(extentTest);
		extent.flush();
		extent.close();
	}
		
}*/