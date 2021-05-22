package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
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

public class Login_TestCase extends BaseClass {
	private String sTestCaseName;
	private int iTestCaseRow;
	protected String technavioPassword;
	protected PageLocators Locators;
	protected PageActions LocatorsAction;

	@BeforeClass
	public void setUpLogin() {

		try {
			sTestCaseName = this.toString();
			sTestCaseName = getTestCaseName(this.toString());
			ExcelReader.setExcelFile(getExcelPath() + getTestDataFileName(), "Login");
			iTestCaseRow = ExcelReader.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
			//driver.get(properties.getProperty("freemiumDevUrl"));
			extentTest = onStart().startTest(sTestCaseName);
			 extentTest=extent.startTest("loginValidations");
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

	}
	@BeforeTest
	public void urlSetUp() throws IOException {
		driver.manage().deleteAllCookies();
		driver.get(properties.getProperty("freemiumDevUrl"));
	}
	@AfterMethod
	public void geneareReport(ITestResult iTestResult) throws IOException {
		try {
			endReport(iTestResult);
		} catch (IOException e) {
			captureScreen(driver, sTestCaseName);
		}
	}

	@Test
	public void loginValidations() throws Exception {
        extentTest=extent.startTest("loginValidations");
		PageActions actions =new PageActions();
		String technavioUserName = null;
		String technavioUserNameCriteria = null;
		String userNameErrorMessage1 = "Sorry! We couldn't find a Technavio account linked to this email.";
		String userNameErrorMessage2 = "Invalid Email Address!";
		String userNameErrorMessage3 = "There are currently multiple sessions logged in with this username and password. Please logout other sessions or try after sometime.";
		// String passwordErrorMessage4 = "Sorry! Your session expired. Please log in
		// again.";
		LocatorsAction = new PageActions();
		waitState();
		int rowCount;
		rowCount = ExcelReader.getRowUsed();

		if (rowCount != 0) {

			for (iTestCaseRow = 1; iTestCaseRow <= rowCount; iTestCaseRow++) {
				try {
					technavioUserName = ExcelReader.getCellData(iTestCaseRow, Constant.Col_UserName);
					technavioPassword = ExcelReader.getCellData(iTestCaseRow, Constant.Col_Password);
					technavioUserNameCriteria = ExcelReader.getCellData(iTestCaseRow, Constant.Col_UserName_Criteria);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.userId)));
					actions.userNameParameter(technavioUserName);
					extentTest.log(LogStatus.INFO, "Username passed    " + technavioUserName);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.nextButtonXpath)));
					PageActions.clickOnNextButton();
					extentTest.log(LogStatus.INFO, "Click on Next Button");
					if (!"".equalsIgnoreCase(technavioUserNameCriteria)
							&& "invalid".equalsIgnoreCase(technavioUserNameCriteria)) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.userNameError)));
						if (isUserErrorMessageDisplayed()) {
							if (PageLocators.useriderr.getText().equals(userNameErrorMessage1)) {
								userNameInvalidPass(LocatorsAction, userNameErrorMessage1);
								Reporter.log(PageLocators.useriderr.getText() + " Expected Message");
								extentTest.log(LogStatus.INFO, "User Error Message is visible" + userNameErrorMessage1);
							} else if (PageLocators.useriderr.getText().equals(userNameErrorMessage2)) {
								userNameInvalidPass(LocatorsAction, userNameErrorMessage2);
								extentTest.log(LogStatus.INFO, "User Error is Message" + userNameErrorMessage2);
								Reporter.log(PageLocators.useriderr.getText() + " Expected Message");
							} else if (PageLocators.useriderr.getText().equals(userNameErrorMessage3)) {
								userNameInvalidPass(LocatorsAction, userNameErrorMessage3);
								extentTest.log(LogStatus.INFO, "User Error is Message" + userNameErrorMessage3);
							} else {

								userNameFail(LocatorsAction);
							}

						} else if ("".equalsIgnoreCase(technavioUserName)) {
							userNameInvalidPass(LocatorsAction, "");
							extentTest.log(LogStatus.INFO, "Blank User Name passed");
							

						} else {
							userNameFail(LocatorsAction);
						}
					} else if (!"".equalsIgnoreCase(technavioUserNameCriteria)
							&& "valid".equalsIgnoreCase(technavioUserNameCriteria)) {

						if (isUserErrorMessageDisplayed()) {

							userNameValidPass(LocatorsAction);
							extentTest.log(LogStatus.INFO, "Valid User Name passed" + LocatorsAction);
							continue;
						}

						else if (!validatePassword(sTestCaseName)) {
							continue;
						}
					} else {
						ExcelReader.setCellData("Fail", iTestCaseRow, Constant.Col_Status);
						Assert.assertFalse(true);
					}
				} catch (Exception e) {
					userNameFail(LocatorsAction);
					Assert.assertFalse(true);
				}
			}
		} else {
			logger.info("No Row are avible in Excel Sheet");
		}

	}

	private boolean isUserErrorMessageDisplayed() {

		try {
			if (PageLocators.useriderr.isDisplayed()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

	}

	private boolean isPasswordErrorMessageDisplayed() {

		try {
			if (PageLocators.pwderr.isDisplayed()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

	}

	private boolean validatePassword(String sTestCaseName) throws Exception {
		String passwordErrorMessage1 = "Your password is correct";
		String passwordErrorMessage2 = "Please enter your passwords";
		String passwordErrorMessage3 = "Your account is temporarily locked to prevent unauthorized use. Try again later.";
		String technavioPasswordCriteria = null;
		technavioPasswordCriteria = ExcelReader.getCellData(iTestCaseRow, Constant.Col_Password_Criteria);

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.passwordId)));
			PageActions.passwordParameter(technavioPassword);
			logger.info(" Password : " + technavioPasswordCriteria);
			extentTest.log(LogStatus.INFO, "password passed      " + technavioPassword  );
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.signInButtonId)));
			PageActions.clickOnSignInButton();
			extentTest.log(LogStatus.INFO, "Click on SignIn Button");

			if (!"".equalsIgnoreCase(technavioPasswordCriteria)
					&& "invalid".equalsIgnoreCase(technavioPasswordCriteria)) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.passwordError)));
				if (isPasswordErrorMessageDisplayed()) {

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.passwordId)));
					if (PageLocators.pwderr.getText().contains(passwordErrorMessage1)) {
						navigateUserNamePage(passwordErrorMessage1);
						extentTest.log(LogStatus.INFO, "Password Error Message present   " + passwordErrorMessage1);
						// Reporter.log(PageLocators.useriderr.getText() + " Expected Message");
					} else if (PageLocators.pwderr.getText().contains(passwordErrorMessage2)) {
						navigateUserNamePage(passwordErrorMessage2);
						extentTest.log(LogStatus.INFO, "Password Error Message present   " + passwordErrorMessage2);
					} else if (PageLocators.pwderr.getText().contains(passwordErrorMessage3)) {
						navigateUserNamePage(passwordErrorMessage3);
						extentTest.log(LogStatus.INFO, "Password Error Message present   " + passwordErrorMessage3);
					} else {
						passwordFail(LocatorsAction);
					}

				} else {
					passwordFail(LocatorsAction);
				}
			} else if (!"".equalsIgnoreCase(technavioPasswordCriteria)
					&& "valid".equalsIgnoreCase(technavioPasswordCriteria)) {
				if (logoutPage()) {
					logOutPass();
				} else {
					ExcelReader.setCellData("Fail", iTestCaseRow, Constant.Col_Status);
				}
				return false;

			} else {
				ExcelReader.setCellData("Fail", iTestCaseRow, Constant.Col_Status);
			}
			return false;
		} catch (Exception e) {
			// passwordFail(LocatorsAction);
			return false;
		}

	}

	private void navigateUserNamePage(String passwordErrorMessage1) throws Exception {
		passWordInvalidPass(LocatorsAction, passwordErrorMessage1);
		driver.navigate().to(getSiteUrl());
	}

	private void passwordFail(PageActions LocatorsAction) throws Exception {
		ExcelReader.setCellData("Fail ", iTestCaseRow, Constant.Col_Status);
		ExcelReader.setCellData("Password Test Failed  ", iTestCaseRow, Constant.Col_Comments);
		PageActions.passwordFieldclear();
		/* Assert.assertFalse(true, PageLocators.pwderr.getText()); */
		logger.info("Invalid Message Displyed :" + PageLocators.pwderr.getText());
		extentTest.log(LogStatus.FAIL, "Invalid Message Displyed :" + PageLocators.pwderr.getText());
		Assert.assertFalse(true, PageLocators.pwderr.getText());
		driver.navigate().to(getSiteUrl());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.userId)));

	}

	private void passWordInvalidPass(PageActions LocatorsAction, String validationMessage) throws Exception {
		ExcelReader.setCellData("Pass ", iTestCaseRow, Constant.Col_Status);
		ExcelReader.setCellData(PageLocators.pwderr.getText(), iTestCaseRow, Constant.Col_Error_Msg);
		PageActions.passwordFieldclear();
		Assert.assertEquals(PageLocators.pwderr.getText(), validationMessage);
		logger.info("Invalid Message Displyed :" + PageLocators.pwderr.getText());

	}

	private void logOutPass() throws Exception {
		ExcelReader.setCellData("Pass ", iTestCaseRow, Constant.Col_Status);
		ExcelReader.setCellData("LogOut Successfully ", iTestCaseRow, Constant.Col_Comments);
		Assert.assertTrue(true);
		logger.info("LogOut Successfully ");

	}

	private void userNameInvalidPass(PageActions LocatorsAction, String validationMessage) throws Exception {
		ExcelReader.setCellData("Pass ", iTestCaseRow, Constant.Col_Status);
		ExcelReader.setCellData(PageLocators.useriderr.getText(), iTestCaseRow, Constant.Col_Error_Msg);
		PageActions.userFieldClear();
		extentTest.log(LogStatus.INFO, "Username field clear");
		Assert.assertEquals(PageLocators.useriderr.getText(), validationMessage);
		logger.info("Invalid Message Displyed :" + PageLocators.useriderr.getText());
	}

	private void userNameValidPass(PageActions LocatorsAction) throws Exception {
		ExcelReader.setCellData("Pass ", iTestCaseRow, Constant.Col_Status);
		ExcelReader.setCellData(PageLocators.useriderr.getText(), iTestCaseRow, Constant.Col_Error_Msg);
		PageActions.userFieldClear();
		extentTest.log(LogStatus.INFO, "Username field clear");
		Assert.assertTrue(true);
		logger.info("Invalid Message Displyed :" + PageLocators.useriderr.getText());

	}

	private void userNameFail(PageActions LocatorsAction) throws Exception {
		ExcelReader.setCellData("Fail ", iTestCaseRow, Constant.Col_Status);
		ExcelReader.setCellData("UserName Test Failed  ", iTestCaseRow, Constant.Col_Comments);
		PageActions.userFieldClear();
		extentTest.log(LogStatus.INFO, "Username field clear");
		logger.info("Invalid Message Displyed :" + PageLocators.useriderr.getText());
		extentTest.log(LogStatus.FAIL, "Invalid Message Displyed" + PageLocators.useriderr.getText());
		Assert.assertFalse(true, PageLocators.useriderr.getText());

	}

	public boolean logoutPage() throws Exception {

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PageLocators.adminIconClassName)));
			PageActions.clickOnAdminIcon();
			extentTest.log(LogStatus.INFO, "Click on Admin Icon");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PageLocators.logOutIconClassName)));
			PageActions.clickOnLogout();
			extentTest.log(LogStatus.INFO, "Successfull LogOut");
			return true;

		} catch (Exception e) {
			ExcelReader.setCellData("Fail ", iTestCaseRow, Constant.Col_Status);
			captureScreen(driver, sTestCaseName);
			logger.info("LogOut failed");
			extentTest.log(LogStatus.FAIL, "LogOut failed");
			Assert.assertTrue(false);
			return false;

		}
	}

}
