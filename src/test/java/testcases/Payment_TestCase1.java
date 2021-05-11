package testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.util.ContinuedFraction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.freemium.base.BaseClass;
import com.freemium.base.Constant;
import com.freemium.pageactions.PageActions;
import com.freemium.pagelocators.PageLocators;
import com.freemium.utilities.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;

public class Payment_TestCase1 extends BaseClass {
	Common_Login common_Login = new Common_Login();
	PageActions PageActions = new PageActions();
	private String sTestCaseName;
	private int iTestCaseRow;
	int rowCount;
	String technavioCardAddress = null;
	String technavioCardCity = null;
	String technavioCardNumber = null;
	String technavioCardCvv;
	String technavioCardNumCriteria = null;
	String technavioCardCvvCriteria = null;
	String technavioCardName = null;

	@BeforeClass
	public void homePageSetUp() throws IOException {
		try {
			common_Login.freemiumUserlogin();
			sTestCaseName = this.toString();
			sTestCaseName = getTestCaseName(this.toString());
			ExcelReader.setExcelFile(getExcelPath() + getTestDataFileName(), "Payment");
			int iTestCaseRow = ExcelReader.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
			extentTest = onStart().startTest(sTestCaseName);
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
	public void validationForSubscriptionPayment() throws Exception {
		try {
			extentTest = extent.startTest("validationForPaymentSection");
			waitState();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitFRorLatestTabReports)));
			PageActions.clickOnFirstReport();
			logger.info("Click on FirstReport");
			extentTest.log(LogStatus.INFO, "Click on FirstReport");
			Set<String> window = driver.getWindowHandles();
			ArrayList handle = new ArrayList(window);
			driver.switchTo().window((String) handle.get(1));
			logger.info("Switch To NewTab");
			extentTest.log(LogStatus.INFO, "Switch To NewTab");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForNewTabRandomReport)));
			Thread.sleep(4000);
			PageActions.clickOnGoToCartButton();
			logger.info("clickOnGoToCartButton");
			extentTest.log(LogStatus.INFO, "clickOnGoToCartButton");
			// get Excel data
			iTestCaseRow = ExcelReader.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
			// technavioCardCriteria = ExcelReader.getCellData(iTestCaseRow,
			// Constant.Col_Password_Criteria);
			rowCount = ExcelReader.getRowUsed();
			if (rowCount != 0) {
				technavioCardAddress = ExcelReader.getCellData(1, Constant.Col_CardAddress);
				technavioCardCity = ExcelReader.getCellData(1, Constant.Col_CardCity);
			}

			// first time passing parameters in billing info
			Thread.sleep(3000);
			scrollToElementContinueStep2();
			PageActions.clickOnContinueStep2();
			logger.info("Click On Billing info Continue");
			extentTest.log(LogStatus.INFO, "Click On Billing info Continue");
			if (!validateBillingsection()) {
				setBillingInformation(technavioCardAddress, technavioCardCity);
				PageActions.clickOnContinueStep2();
			} else {
				extentTest.log(LogStatus.FAIL, " Expected Error Message Not Found In Billing Section  ");
				Assert.assertTrue(false);
				captureScreen(driver, sTestCaseName);

			}

			PageActions.ClickOnUserSubscription();
			logger.info("Click On UserSubscription");
			extentTest.log(LogStatus.INFO, "Click On UserSubscription");
			Thread.sleep(3000);
			scrollToElementContinueStep2();
			logger.info("Scrolling to Continue button");
			extentTest.log(LogStatus.INFO, "Scrolling to Continue button");
			PageActions.clickOnContinueStep2();
			logger.info("Click On Billing info Continue");
			extentTest.log(LogStatus.INFO, "Click On Billing info Continue");
			Thread.sleep(3000);
			scrollToElementContinueStep1();
			PageActions.clickOnContinueStep1();
			logger.info("Click On Subscription Continue");
			extentTest.log(LogStatus.INFO, "Click On Subscription Continue");
			// second time passing parameters in billing info
			setBillingInformation(technavioCardAddress, technavioCardCity);
			Thread.sleep(3000);
			scrollToElementContinueStep2();
			PageActions.clickOnContinueStep2();
			// PageActions.clickOnContinueStep2();
			logger.info("Click On Billing info Continue");
			extentTest.log(LogStatus.INFO, "Click On Billing info Continue");
			// PageActions.clickOnCheckOut();
			// Entering parameters in Payment via Credit/Debit Card
			if (paymentSection()) {
				logger.info("Click On CheckOut");
				extentTest.log(LogStatus.INFO, "Click On CheckOut");
				Thread.sleep(10000);
				if (PageLocators.thankYouMessage.getText().equalsIgnoreCase("Thank you for your payment! ")) {
					PageActions.adminIcon();
					PageActions.ClickOnMyAccountDetails();
					logger.info("Click On MyAccountDetails");
					extentTest.log(LogStatus.INFO, "Click On MyAccountDetails");
					Thread.sleep(5000);
					PageActions.ClickOnSubcriptionHistory();
					List<WebElement> subcriptionHistoryList = PageLocators.subcriptionHistoryList;
					if (subcriptionHistoryList.size() >= 1 && !PageLocators.upgradeButton.isDisplayed()) {

					} else {
						// fails condition
						extentTest.log(LogStatus.FAIL, "subcription not completly done No Records Found  ");
						Assert.assertTrue(false);
						captureScreen(driver, sTestCaseName);
						throw new RuntimeException("subcription not completly done No Records Found  ");
					}

				} else {
					// fails condition
					extentTest.log(LogStatus.FAIL, "Successfull Message not Found  ");
					Assert.assertTrue(false);
					captureScreen(driver, sTestCaseName);

				}
			}

		} catch (Exception e) {
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		}

	}

	private boolean validateBillingsection() {
		if (PageLocators.addressErrorMsg.isDisplayed() && PageLocators.cityErrorMsg.isDisplayed()
				&& PageLocators.countryErrorMsg.isDisplayed() && PageLocators.proceedToChooseErrorMsg.isDisplayed()) {
			return false;
		}
		return true;
	}

	private void setBillingInformation(String technavioCardAddress, String technavioCardCity)
			throws InterruptedException {
		Thread.sleep(3000);
		PageActions.addressParameter(technavioCardAddress);
		logger.info("Entering to address field");
		extentTest.log(LogStatus.INFO, "Entering to address field");
		PageActions.cityParameter(technavioCardCity);
		logger.info("Entering to City field");
		extentTest.log(LogStatus.INFO, "Entering to City field");
		List<WebElement> list = PageLocators.countries;
		list.get(4).click();

	}

	private boolean paymentSection() throws Exception {
		rowCount = ExcelReader.getRowUsed();
		if (rowCount != 0) {

			for (iTestCaseRow = 1; iTestCaseRow <= rowCount; iTestCaseRow++) {
				PageLocators.cardNumber.clear();
				PageLocators.cardCvv.clear();
				PageLocators.CardName.clear();
				if (iTestCaseRow != 1) {
					PageActions.toAgreeCheck();
				}

				technavioCardNumber = ExcelReader.getCellData(iTestCaseRow, Constant.Col_CardNumber);
				/*technavioCardCvv = ExcelReader.getCellData(iTestCaseRow, Constant.Col_CardCvv);*/
				technavioCardCvv ="123";
				technavioCardNumCriteria = ExcelReader.getCellData(iTestCaseRow, Constant.Col_CardNumCriteria);
				technavioCardCvvCriteria = ExcelReader.getCellData(iTestCaseRow, Constant.Col_CvvCriteria);
				technavioCardName = ExcelReader.getCellData(iTestCaseRow, Constant.Col_CardName);
				PageActions.cardHolderName(technavioCardName);
				logger.info("Entering cardHolderName");
				extentTest.log(LogStatus.INFO, "Entering cardHolderName");
				PageActions.cardHoldNumber(technavioCardNumber);
				logger.info("Entering cardHoldNumber");
				extentTest.log(LogStatus.INFO, "Entering cardHoldNumber");
				PageActions.cardHoldCvv(technavioCardCvv);
				logger.info("Entering cardHoldCvv");
				extentTest.log(LogStatus.INFO, "Entering cardHoldCvv");
				List<WebElement> listOfMonths = PageLocators.cardMonthOptions;
				listOfMonths.get(4).click();
				List<WebElement> listOfYears = PageLocators.cardYearOptions;
				listOfYears.get(4).click();
				PageActions.toAgreeCheck();
				logger.info("Click to AgreeCheck");
				extentTest.log(LogStatus.INFO, "Click to AgreeCheck");
				//PageActions.clickOnCheckOut();
                     Thread.sleep(3000);
				if (PageLocators.paymentErrorMsg.isDisplayed()) {
					List<String> errorMsgsList = errorMsgsList();
					String actualMsg = PageLocators.paymentErrorMsg.getText();
					if (errorMsgsList.contains(actualMsg)) {
						logger.info(PageLocators.paymentErrorMsg.getText());
						continue;
					} else {
						extentTest.log(LogStatus.FAIL, "Un-Relavent Error Message Displayed : " + actualMsg);
						Assert.assertTrue(false);
						captureScreen(driver, sTestCaseName);
					}

				} else {
					logger.info("Return True");
					PageActions.clickOnCheckOut();
					return true;
				}

			}
		}
		return false;

	}

	private List<String> errorMsgsList() {
		List<String> errorMsgsList = new ArrayList();
		errorMsgsList.add("Please provide card number");
		errorMsgsList.add("Card number cannot be less than 14 digit");
		errorMsgsList.add("Please provide CVV details");
		errorMsgsList.add("Please Enter Card Holder Name");
		errorMsgsList.add("Please agree to proceed!");
		errorMsgsList.add("Your card number is incorrect.");
		errorMsgsList.add("Your card's expiration month is invalid.");
		errorMsgsList.add("This payment has already been processed");
		return errorMsgsList;

	}

}
