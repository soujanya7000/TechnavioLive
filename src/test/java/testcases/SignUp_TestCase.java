package testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.freemium.base.BaseClass;
import com.freemium.pageactions.PageActions;
import com.freemium.pagelocators.PageLocators;
import com.relevantcodes.extentreports.LogStatus;

public class SignUp_TestCase extends BaseClass {
	Common_Login common_Login = new Common_Login();
	private String sTestCaseName;
	protected PageLocators Locators;
	protected PageActions PageActions;

	@BeforeClass
	public void signUpSetUp() throws IOException {
		try {
			//signup code
			sTestCaseName = this.toString();
			sTestCaseName = getTestCaseName(this.toString());
			extentTest = onStart().startTest(sTestCaseName);
			driver.get(properties.getProperty("freemiumDevSignUPUrl"));
			logger.info("Url Launched :" + properties.getProperty("freemiumDevSignUPUrl"));
			PageActions = new PageActions();
		} catch (Exception e) {
			captureScreen(driver, "SignUp_TestCase");
			Assert.assertFalse(true);
		}
		logger.info("This is Befor Class");
	}
	/*
	 * @BeforeTest public void sartReport() throws IOException { extentTest =
	 * onStart().startTest(sTestCaseName); extentTest =
	 * extent.startTest("validationForSignup"); }
	 */

	@AfterMethod
	public void geneareReport(ITestResult iTestResult) throws IOException {
		try {
			endReport(iTestResult);
		} catch (IOException e) {
			captureScreen(driver, sTestCaseName);
		}
	}

	/*
	 * @AfterClass public void logOut() throws Exception {
	 * common_Login.logoutPage(); logger.info("Succesfully logged out");
	 * extentTest.log(LogStatus.INFO, "Succesfully logged out"); }
	 */
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void validationForSignUp() throws Exception {
		// Locators =new PageLocators();
		extentTest = extent.startTest("validation For Signup");
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			// PageActions.clickONSubmit();
			PageActions.nameParameter(getFremiumName());
			logger.info("Entering the User Name");
			extentTest.log(LogStatus.INFO, "Username passed    " + getFremiumName());
			PageActions.emailParameter(getFremiumEmail());
			logger.info("Entering the email");
			extentTest.log(LogStatus.INFO, "email passed    " + getFremiumEmail());
			PageActions.passwordFieldParameter(getFremiumPasswordField());
			logger.info("Entering the Password Name");
			extentTest.log(LogStatus.INFO, "Password passed    " + getFremiumPasswordField());
			if (validationPassword()) {
				logger.info("validation of Password is verified");
				extentTest.log(LogStatus.PASS, "validation of Password is verified");
			} else {
				extentTest.log(LogStatus.FAIL, "Password Validation Fail");
				captureScreen(driver, sTestCaseName);
				Assert.assertFalse(validationPassword());
			}
			PageActions.clickONToAgree();
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "SignUp Validation Fail");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void validationForInviteUser() throws Exception {
		extentTest = extent.startTest("validation For InviteUser");
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			PageActions.clickONinviteUser();
			extentTest.log(LogStatus.INFO, "click on invite Link user for Invite");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			PageActions.inviteUserNameParameter(getFremiuminviteUserName());
			extentTest.log(LogStatus.INFO, "Entering New User :: " + getFremiuminviteUserName());
			PageActions.inviteEmailParameter(getFremiuminviteEmail());
			extentTest.log(LogStatus.INFO, "Entering New Email :: " + getFremiuminviteEmail());
			String actualUser = getFremiuminviteUserName();
			String actualEmail = getFremiuminviteEmail();
			PageActions.clickOnRequestInvite();
			String successMsg = "Congratulations! You can enter a password of your choice and sign-up right away!";
			if (PageLocators.sucessInvite.getText().equalsIgnoreCase(successMsg)) {
				if (actualUser.equalsIgnoreCase(getFremiuminviteUserName())
						&& actualEmail.equalsIgnoreCase(getFremiuminviteEmail())) {
					extentTest.log(LogStatus.PASS, "successMsg Validation Passed");
				}
			} else {
				extentTest.log(LogStatus.FAIL, "successMsg Validation Fail");
				captureScreen(driver, sTestCaseName);
				Assert.assertFalse(true);
			}
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Invite User Validation Fail");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		}
	}

	/**
	 * @return
	 */
	private boolean validationPassword() {
		return PageLocators.lowerCase.getAttribute("class").equalsIgnoreCase("pass")
				&& PageLocators.upperCase.getAttribute("class").equalsIgnoreCase("pass")
				&& PageLocators.oneNumber.getAttribute("class").equalsIgnoreCase("pass")
				&& PageLocators.symbols.getAttribute("class").equalsIgnoreCase("pass")
				&& PageLocators.length.getAttribute("class").equalsIgnoreCase("pass");
	}
}
