package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.freemium.base.BaseClass;
import com.freemium.base.Constant;
import com.freemium.pageactions.PageActions;
import com.freemium.utilities.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;

public class SignUp_TestCase extends BaseClass {
	Common_Login common_Login = new Common_Login();
	PageActions PageActions = new PageActions();
	private String sTestCaseName;

	@BeforeClass
	public void homePageSetUp() throws IOException {
		try {
			sTestCaseName = this.toString();
			sTestCaseName = getTestCaseName(this.toString());
			ExcelReader.setExcelFile(getExcelPath() + getTestDataFileName(), "Payment");
			int iTestCaseRow = ExcelReader.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
			extentTest = onStart().startTest(sTestCaseName);
			driver.get(properties.getProperty("freemiumTestSignUPUrl"));
			logger.info("Url Launched :" + properties.getProperty("freemiumTestSignUPUrl"));
		} catch (Exception e) {
			captureScreen(driver, "Home_TestCase");
			Assert.assertFalse(true);
		}
		logger.info("This is Before Class");
	}

	@AfterMethod
	public void geneareReport(ITestResult iTestResult) throws IOException {
		try {
			endReport(iTestResult);
		} catch (IOException e) {
			captureScreen(driver, sTestCaseName);
		}
	}
	@AfterClass
	public void logOut() throws Exception {
		common_Login.logoutPage();
		logger.info("Succesfully logged out");
		extentTest.log(LogStatus.INFO, "Succesfully logged out");
	}
	@SuppressWarnings("static-access")
	@Test
	public void validationForsignUp() throws Exception {
		
		
	}

}
