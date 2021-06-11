package testcases;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.freemium.base.BaseClass;
import com.freemium.base.Constant;
import com.freemium.pageactions.PageActions;
import com.freemium.pagelocators.PageLocators;
import com.freemium.utilities.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;

public class SignUp_TestCase extends BaseClass {
	Common_Login common_Login = new Common_Login();
	private String sTestCaseName;
	protected PageLocators Locators;
	protected PageActions PageActions;

	@BeforeClass
	public void signUpSetUp() throws IOException {
		try {
			sTestCaseName = this.toString();
			sTestCaseName = getTestCaseName(this.toString());
			extentTest = onStart().startTest(sTestCaseName);
			driver.manage().deleteAllCookies();
			driver.get(properties.getProperty("freemiumDevSignUPUrl"));
			logger.info("Url Launched :" + properties.getProperty("freemiumDevSignUPUrl"));
			PageActions = new PageActions();
		} catch (Exception e) {
			captureScreen(driver, "SignUp_TestCase");
			Assert.assertFalse(true);
		}
		logger.info("This is Before Class");
	}

	@BeforeTest
	public void sartReport() throws Exception {
		common_Login.gettingNewUser();
	}

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
			PageActions.clickONSubmit();
			if (validationErrorMsgSignUp()) {
				logger.info("validation of Error Msg for Signup is verified  :: "
						+ PageLocators.SignNameErrorMsg.getText() + PageLocators.SignUpEmailErrorMsg.getText()
						+ PageLocators.SignUpPasswordErrorMsg.getText() + PageLocators.SignCheckErrorMsg.getText());
				extentTest.log(LogStatus.PASS, "validation of Error Msg for Signup is verified  :: "
						+ PageLocators.SignNameErrorMsg.getText());
				extentTest.log(LogStatus.PASS, "validation of SignUp EmailErrorMsg is verified  :: "
						+ PageLocators.SignUpEmailErrorMsg.getText());
				extentTest.log(LogStatus.PASS, "validation of SignUp PasswordErrorMsg is verified  :: "
						+ PageLocators.SignUpPasswordErrorMsg.getText());
				extentTest.log(LogStatus.PASS, "validation of SignUp CheckErrorMsg is verified  :: "
						+ PageLocators.SignCheckErrorMsg.getText());
			} else {
				extentTest.log(LogStatus.FAIL, "Signup Validation Fail");
				captureScreen(driver, sTestCaseName);
				Assert.assertFalse(validationErrorMsgSignUp());
			}

			PageActions.nameParameter(getFremiumName());
			logger.info("Entering the User Name1");
			extentTest.log(LogStatus.INFO, "Username passed    " + getFremiumName());
			PageActions.emailParameter(getFremiumEmail());
			logger.info("Entering the email");
			extentTest.log(LogStatus.INFO, "email passed    " + getFremiumEmail());
			PageActions.passwordFieldParameter(getFremiumPasswordField());
			logger.info("Entering the Password Name");
			extentTest.log(LogStatus.INFO, "Password passed    " + getFremiumPasswordField());
			// PageActions.clickONSubmit();
			if (validationPassword()) {
				logger.info("validation of Password is verified");
				extentTest.log(LogStatus.PASS, "validation of Password is verified");
			} else {
				extentTest.log(LogStatus.FAIL, "Password Validation Fail");
				captureScreen(driver, sTestCaseName);
				Assert.assertFalse(validationPassword());
			}
			PageActions.clickONToAgree();
			logger.info("click ON To Agree");
			extentTest.log(LogStatus.PASS, "click ON To Agree");
			PageActions.clickONSubmit();
			logger.info("click ON Submit");
			extentTest.log(LogStatus.PASS, "click ON Submit");
			Thread.sleep(20000);
			if (PageLocators.selectPreferrence.getText().equalsIgnoreCase("Select your preferred industries")) {
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForExploreButton)));
				if (PageLocators.inActiveExplore.isDisplayed()) {
					// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForDoItLater)));
					cliclOnIndustriesName();
					logger.info("click on Industies Name");
					extentTest.log(LogStatus.INFO, "click on Industies Name");
					cliclOnindustriesL2Media();
					logger.info("click on Industies Media");
					extentTest.log(LogStatus.INFO, "click on Industies Media");
					cliclOnActiveExplore();
					logger.info("click on Explore Button");
					extentTest.log(LogStatus.INFO, "click on Explore Button");
					Thread.sleep(2000);
					if (PageLocators.prefferedIndustriesActive.isDisplayed()) {
						logger.info("Preffered Industry tab is active");
						extentTest.log(LogStatus.PASS, "preffered Industries tab is Active");
						if (PageLocators.visibilityOfL2Dropdown.getText().equalsIgnoreCase("Media & Entertainment")) {
							logger.info("Selected Industries are shown in Dropdown");
							extentTest.log(LogStatus.PASS, "Selected Industries are shown in Dropdown");
							List<WebElement> preferredIndustries = PageLocators.listOfPrefferedReports;

							if (preferredIndustries.containsAll(PageLocators.listOfPrefferedReports)) {
								logger.info("selected Industries Reports are Visible in Preferred Tab page");
								extentTest.log(LogStatus.PASS,
										"selected Industries Reports are Visible in Preferred Tab page");
								Assert.assertTrue(true);
								Thread.sleep(3000);
								clickOnAdminIcon();
								Thread.sleep(3000);
								javaClickOnMyAccount();
								Thread.sleep(3000);
								String emailId = PageLocators.MyAccountUserEMail.getText();

								if (emailId.trim().equalsIgnoreCase(properties.getProperty("signUpEmail"))) {
									logger.info("MyAccount User EMail is Matched");
									extentTest.log(LogStatus.PASS, "MyAccount User EMail is Matched :: "
											+ PageLocators.MyAccountUserEMail.getText());
								} else {
									logger.info("MyAccount User EMail is not Matched");
									extentTest.log(LogStatus.FAIL, "MyAccount User EMail is not Matched");
									captureScreen(driver, sTestCaseName);
									Assert.assertFalse(true);

								}

							} else {
								logger.info("selected Industries Reports are not showing");
								extentTest.log(LogStatus.FAIL, "selected Industries Reports are not showing");
								captureScreen(driver, sTestCaseName);
								Assert.assertFalse(true);
							}

						} else {
							logger.info("Not showing selected Industries in dropDown");
							extentTest.log(LogStatus.FAIL, "Not showing selected Industries in dropDown");
							captureScreen(driver, sTestCaseName);
							Assert.assertFalse(true);
						}
					} else {
						logger.info("Preffered Industry tab is not active");
						extentTest.log(LogStatus.FAIL, "Preffered Industry tab is not active");
						captureScreen(driver, sTestCaseName);
						Assert.assertFalse(true);
					}

				} else {
					extentTest.log(LogStatus.FAIL, "My Preferrence Validation Fail");
					captureScreen(driver, sTestCaseName);
					Assert.assertFalse(validationPassword());
				}

			} else if (validationErrorSignUp()) {
				logger.info("SignUp Validation Fail :: " + PageLocators.SignUpEmailErrorMsg.getText());
				extentTest.log(LogStatus.FAIL,
						"SignUp Validation Fail :: " + PageLocators.SignUpEmailErrorMsg.getText());
				captureScreen(driver, sTestCaseName);
			} else {
				extentTest.log(LogStatus.FAIL, "SignUp Process failed due to Validation");
				captureScreen(driver, sTestCaseName);
				Assert.assertFalse(validationPassword());
			}

		} catch (ElementClickInterceptedException e) {
			extentTest.log(LogStatus.FAIL, "Element not clickable");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		} catch (ElementNotInteractableException e) {
			extentTest.log(LogStatus.FAIL, "Element not Interactable");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		} catch (StaleElementReferenceException e) {
			extentTest.log(LogStatus.FAIL, "Stale Element");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		} catch (NoSuchElementException e) {
			extentTest.log(LogStatus.FAIL, "Element not available in the page");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		} catch (TimeoutException e) {
			extentTest.log(LogStatus.FAIL, "Taking More Time for Page Load");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "SignUp Process failed due to Validation");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		}
	}

	@Test(dependsOnMethods = { "validationForSignUp" })
	public void logOut() throws Exception {
		Thread.sleep(5000);
		logger.info("wait for 5 sec");
		common_Login.logoutPage();
		extentTest.log(LogStatus.PASS, "Logout successfully done");
		Assert.assertTrue(true);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void validationForInviteUser() throws Exception {
		extentTest = extent.startTest("validation For InviteUser");
		try {
			driver.navigate().to(properties.getProperty("freemiumDevSignUPUrl"));
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

	private boolean validationErrorMsgSignUp() {
		return PageLocators.SignNameErrorMsg.getText().equalsIgnoreCase("Please enter a valid name!")
				&& PageLocators.SignUpEmailErrorMsg.getText().equalsIgnoreCase("Please enter a valid email!")
				&& PageLocators.SignUpPasswordErrorMsg.getText()
						.equalsIgnoreCase("Please satisfy the criteria mentioned below!")
				&& PageLocators.SignCheckErrorMsg.getText().equalsIgnoreCase("Please agree to proceed!");

	}

	private boolean validationErrorSignUp() {
		return PageLocators.SignUpEmailErrorMsg.getText().equalsIgnoreCase("E-Mail Address is already registered!")
				|| PageLocators.SignUpEmailErrorMsg.getText()
						.equalsIgnoreCase("Cannot Signup, please request an Invite!")
				|| PageLocators.SignUpEmailErrorMsg.getText().equalsIgnoreCase(
						"Oops! Cannot complete the sign up process. Please contact support@technavio.com for assistance!")
				|| PageLocators.SignUpEmailErrorMsg.getText()
						.equalsIgnoreCase("Cannot Signup, please request an Invite!")
				|| PageLocators.CaptchaError.getText().equalsIgnoreCase("Recaptcha token empty");

	}

}
