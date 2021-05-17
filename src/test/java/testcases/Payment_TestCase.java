package testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
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

public class Payment_TestCase extends BaseClass {
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
	String technavioSubscriptionPaln = null;
	String reportName = null;
	String technavioOfflineAddress1 = null;
	String technavioOfflineAddress2 = null;
	String paymentMode = null;

	@BeforeClass
	public void homePageSetUp() throws IOException {
		try {
			common_Login.freemiumUserlogin();
			sTestCaseName = this.toString();
			sTestCaseName = getTestCaseName(this.toString());
			ExcelReader.setExcelFile(getExcelPath() + getTestDataFileName(), "Payment");
			logger.info("succesfully invoke excel file path");
			int iTestCaseRow = ExcelReader.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
			//extentTest = onStart().startTest(sTestCaseName);
		} catch (NoSuchElementException ne) {
			logger.info("NoSuchElementException :: " + ne);
			captureScreen(driver, "Home_TestCase");
			Assert.assertFalse(true);
		} catch (TimeoutException te) {
			logger.info("TimeoutException :: " + te);
			captureScreen(driver, "Home_TestCase");
			Assert.assertFalse(true);
		} catch (StaleElementReferenceException se) {
			logger.info("StaleElementReferenceException :: " + se);
			captureScreen(driver, "Home_TestCase");
			Assert.assertFalse(true);
		}catch (Exception e) {
			captureScreen(driver, "Home_TestCase");
			logger.info(" Logins Fails :: " + e );
			extentTest.log(LogStatus.FAIL, " Logins Fails :: " + e);
			Assert.assertFalse(true);
		}
		logger.info("This is Before Class");
	}

	@BeforeTest
	public void urlSetUp() throws IOException {
		driver.manage().deleteAllCookies();
		extentTest = onStart().startTest(sTestCaseName);
		driver.get(properties.getProperty("freemiumDevUrl"));
	}

	@Test
	public void validatePaymentSection() throws IOException {

		try {
			logger.info("waiting for latest reports tab 1min");
			Thread.sleep(50000);
			logger.info("wait completed for latest reports tab 1min");
			rowCount = ExcelReader.getRowUsed();
			for (iTestCaseRow = 1; iTestCaseRow <= rowCount; iTestCaseRow++) {
				paymentMode = ExcelReader.getCellData(iTestCaseRow, Constant.Col_PaymentMode);
				if (paymentMode.equalsIgnoreCase("Online")) {
					validationForReportOnlinePurchase();

				} else if (paymentMode.equalsIgnoreCase("Offline")) {
					validationForOfflineReportPurchase();

				} else if (paymentMode.equalsIgnoreCase("Lite") || paymentMode.equalsIgnoreCase("Basic")
						|| paymentMode.equalsIgnoreCase("Team")) {
					validationForUpgradeSubscription();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			extentTest.log(LogStatus.FAIL, " Payment section Fails ");
			Assert.assertTrue(false);
			captureScreen(driver, sTestCaseName);
			e.printStackTrace();
		}

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

	public void validationForUpgradeSubscription() throws Exception {
		try {
			extentTest = extent.startTest("validationForPaymentSection");
			waitState();
			fluentWaitState();
			// Thread.sleep(20000);
			logger.info("wait for click upgrade button");
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsfaIcon)));
			Thread.sleep(1500);
			PageActions.clickOnUpgradeButton2();
			logger.info("wait complete for click upgrade button");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForFAQLink)));
			logger.info("wait complete for FAQ Link");
			Thread.sleep(1500);
			setPlan();
			// Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForStepButton1)));
			scrollToElementContinueStep1();
			PageActions.clickOnContinueStep1();
			logger.info("Click On Subscription Continue");
			extentTest.log(LogStatus.INFO, "Click On Subscription Continue");
			rowCount = ExcelReader.getRowUsed();
			if (rowCount != 0) {
				technavioCardAddress = ExcelReader.getCellData(1, Constant.Col_CardAddress);
				technavioCardCity = ExcelReader.getCellData(1, Constant.Col_CardCity);
			}
			// first time passing parameters in billing info
			// Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForStepButton2)));
			scrollToElementContinueStep2();
			PageActions.clickOnContinueStep2();
			logger.info("Click On Billing info Continue");
			extentTest.log(LogStatus.INFO, "Click On Billing info Continue");
			if (!validateBillingsection()) {
				setBillingInformation(technavioCardAddress, technavioCardCity);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForStepButton2)));
				scrollToElementContinueStep2();
				PageActions.clickOnContinueStep2();
				logger.info("Click On Billing info Continue");
				extentTest.log(LogStatus.INFO, "Click On Billing info Continue");
			} else {
				extentTest.log(LogStatus.FAIL, " Expected Error Message Not Found In Billing Section  ");
				Assert.assertTrue(false);
				captureScreen(driver, sTestCaseName);

			}
			if (paymentSection()) {

				// Thread.sleep(50000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PageLocators.waitForLoderScreen)));
				// String thankyouMsg="Thank You For Your Payment!\\n\\nYou will receive an
				// email shortly with the next steps to start using your account.";
				// Thread.sleep(7000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForThankYouMsg)));
				if (PageLocators.thankYouMessage.getText().equalsIgnoreCase("Thank You For Your Payment!")) {
					PageActions.adminIcon();
					PageActions.ClickOnMyAccountDetails();
					logger.info("Click On MyAccountDetails");
					extentTest.log(LogStatus.INFO, "Click On MyAccountDetails");
					// Thread.sleep(5000);
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(PageLocators.waitForSubscriptionHistory)));
					Thread.sleep(1500);
					PageActions.ClickOnSubcriptionHistory();
					logger.info("Click On Subcription History");
					extentTest.log(LogStatus.INFO, "Click On Subcription History");
					List<WebElement> subcriptionHistoryList = PageLocators.subcriptionHistoryList;
					if (subcriptionHistoryList.size() >= 1 && !PageLocators.upgradeButton.isDisplayed()) {
						logger.info("Expected Plan Visible");
						extentTest.log(LogStatus.INFO, "Expected Plan Visible");
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

	public void validationForReportOnlinePurchase() throws Exception {
		extentTest = extent.startTest("validationForPaymentSection");
		waitState();
		String actualMsg = null;
		// String reportName = null;
		/*
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * PageLocators.waitFRorLatestTabReports))); String cartIcons =
		 * PageLocators.firstIconStatus; String cartIconStatus =
		 * cartIconStatus(cartIcons); reportName =
		 * PageLocators.latestFirstReports.getText();
		 */
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
			List<WebElement> cartIcons = PageLocators.latestReportsCartIcons;
			List<WebElement> purchaseReportList = PageLocators.latestReportsCartIconsClassName;

			WebElement cartIcon = PageLocators.goToIcon;
			for (int i = 0; i < cartIcons.size(); i += 5) {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
				String cartIconStatus = cartIconStatus(i);
				reportName = PageLocators.latestReports.get(i).getText();
				String isPurchased = purchaseReportList.get(i).getText();
				if (isPurchased.equals("Purchased")) {
					continue;
				}

				if (!cartIconStatus.equalsIgnoreCase("fa fa-shopping-cart add-to-cart  cart-added")) {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
					Actions a=new Actions(driver);
					a.click(cartIcons.get(i)).perform();
					/*WebElement cartIconButton=cartIcons.get(i);
                    buttonToClick(cartIconButton);*/
                    logger.info("report cart icon clicked");
					//cartIcons.get(i).click();
					logger.info("reportName  :  : " + reportName);
					if (isCartMessageDisplay()) {
						actualMsg = PageLocators.productAddedcart.getText();
						logger.info("actualMsg -  " + actualMsg);
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath(PageLocators.waitForCartAddErrorMsg)));
						logger.info("waitForlatestTabGoToCartIcon  Completed");
						//WebElement ClickonIcon= PageLocators.goToIcon;
					//	a.click(ClickonIcon).perform();
						//buttonToClick(ClickonIcon);
						javaClickGoToIcon();
						logger.info(" Click on go to cart page");
						//PageActions.goToCartPage();
						logger.info("goToCartPage  Completed");
						if (isReportNamePresentCartPage(reportName)) {
						}
					} else {
						actualMsg = PageLocators.productAddedcart.getText();
						logger.info("actualMsg - Else " + actualMsg);
						captureScreen(driver, sTestCaseName);
						extentTest.log(LogStatus.FAIL,
								"Expected Message " + "CartAddErrorMessage" + " Actual Message is " + actualMsg);
						Assert.assertEquals(actualMsg, "CartAddErrorMessage");
					}
				} else {
					javaClickGoToIcon();
					logger.info(" Click on go to cart page");
					//PageActions.goToCartPage();
					wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForReportsCart)));
					if (isReportNamePresentCartPage(reportName)) {
					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		}
	}

	private String cartIconStatus(int i) throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForLastCartIcon)));
		logger.info("Wait for cartIconStatus completed");
		List<WebElement> aciveIconElementList = PageLocators.latestReportsCartIconsClassName;
		String aciveIconElement = aciveIconElementList.get(i).getAttribute("class");
		return aciveIconElement;
	}

	public void validationForOfflineReportPurchase() throws Exception {
		extentTest = extent.startTest("validationForPaymentSection");
		waitState();
		String actualMsg = null;
		// String reportName = null;
		
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
			List<WebElement> cartIcons = PageLocators.latestReportsCartIcons;
			List<WebElement> purchaseReportList = PageLocators.latestReportsCartIconsClassName;

			WebElement cartIcon = PageLocators.goToIcon;
			for (int i = 0; i < cartIcons.size(); i += 10) {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
				String cartIconStatus = cartIconStatus(i);
				reportName = PageLocators.latestReports.get(i).getText();
				String isPurchased = purchaseReportList.get(i).getText();
				if (isPurchased.equals("Purchased")) {
					continue;
				}

				if (!cartIconStatus.equalsIgnoreCase("fa fa-shopping-cart add-to-cart  cart-added")) {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
					WebElement cartIconButton=cartIcons.get(i);
                    buttonToClick(cartIconButton);
                    logger.info("report cart icon clicked");
					//cartIcons.get(i).click();
					logger.info("reportName  :  : " + reportName);
					if (isCartMessageDisplay()) {
						actualMsg = PageLocators.productAddedcart.getText();
						logger.info("actualMsg -  " + actualMsg);
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath(PageLocators.waitForCartAddErrorMsg)));
						logger.info("waitForlatestTabGoToCartIcon  Completed");
						PageActions.goToCartPage();
						logger.info("goToCartPage  Completed");
						if (isOfficialReportNameCartPage(reportName)) {
						}
					} else {
						actualMsg = PageLocators.productAddedcart.getText();
						logger.info("actualMsg - Else " + actualMsg);
						captureScreen(driver, sTestCaseName);
						extentTest.log(LogStatus.FAIL,
								"Expected Message " + "CartAddErrorMessage" + " Actual Message is " + actualMsg);
						Assert.assertEquals(actualMsg, "CartAddErrorMessage");
					}
				} else {
					PageActions.goToCartPage();
					wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForReportsCart)));
					if (isOfficialReportNameCartPage(reportName)) {
					}
				}

			}

		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Offline Payment Fails");
			Assert.assertTrue(false);
			captureScreen(driver, sTestCaseName);
			
		}
	}

	private void validateCartIconStatus(String cartIconStatusName) throws Exception {
		// Thread.sleep(5000);
		String cartIconStatus = null;
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
		logger.info("Wait for Last Cart icon");
		cartIconStatus = cartIconStatus(cartIconStatus);
		logger.info("comparing the cartIconStatus completed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForLastCartIcon)));
		logger.info("wait for cart status in active or active");
		if (cartIconStatus.equalsIgnoreCase("fa fa-shopping-cart add-to-cart  in-active")) {
			logger.info("entered cart status in active");
			extentTest.log(LogStatus.INFO, "Expected Report is InActive State" + cartIconStatus);
		} else {
			captureScreen(driver, sTestCaseName);
			extentTest.log(LogStatus.FAIL, "Expected Report is InActive State :" + cartIconStatus);
			Assert.assertEquals(cartIconStatus, "fa fa-shopping-cart add-to-cart  in-active");
		}
	}

	private String cartIconStatus(String cartIconStatus) throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForLastCartIcon)));
		logger.info("Wait for cartIconStatus completed");
		WebElement aciveIconElementList = PageLocators.firstCartIconClassName;
		String aciveIconElement = aciveIconElementList.getAttribute("class");
		return aciveIconElement;
	}

	private boolean isCartMessageDisplay() throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForCartAddErrorMsg)));
			logger.info("wait completed");
			if (PageLocators.productAddedcart.isDisplayed()) {

				logger.info("return true");
				return true;
			} else {
				logger.info("return false");
				return false;
			}
		} catch (NoSuchElementException e) {
			logger.info("NoSuchElementException");
			throw e;
		} catch (TimeoutException e) {
			logger.info("TimeoutException");
			throw e;
		} catch (StaleElementReferenceException e) {
			logger.info("StaleElementReferenceException");
			throw e;
		} catch (Exception e) {
			logger.info("return Exception");
			throw e;
		}
	}

	private boolean isReportNamePresentCartPage(String reportName) throws Exception {
		if (isReportPresentInCart(reportName)) {
		}
		return false;
	}

	private boolean isOfficialReportNameCartPage(String reportName) throws Exception {
		if (isOfficialReportInCart(reportName)) {
		}
		return false;
	}

	public boolean isReportPresentInCart(String homeReportName) throws Exception {
		// Thread.sleep(3000);
		logger.info(" Wait For Cart Tab ");
		logger.info(" Enter into isReportPresentInCartTab   : : ");
		List<WebElement> CartTabReports = PageLocators.cartTabReports;
		for (int i = 0; i < CartTabReports.size(); i++) {
			logger.info(" isReportPresentInCartTab Enter ForLoop : : " + i);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForcartReport)));
			String actualCartReportName = PageLocators.cartTabReports.get(i).getText();
			logger.info(" actualCartReportName  : : " + actualCartReportName);
			logger.info(" homeReportName  :  : " + homeReportName);
			extentTest.log(LogStatus.INFO, " actualCartReportName  : : " + actualCartReportName);
			extentTest.log(LogStatus.INFO, " homeReportName  :  : " + homeReportName);
			// Thread.sleep(3000);
			if (homeReportName.equalsIgnoreCase(actualCartReportName)) {
				logger.info(homeReportName + " Expected Report is Active State  : : " + actualCartReportName);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(PageLocators.waitForReportPageContinue)));
				logger.info(" after completing wait state for continue button");
				iTestCaseRow = ExcelReader.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
				rowCount = ExcelReader.getRowUsed();
				// get Excel data
				logger.info("Entering the card address");
				if (rowCount != 0) {
					technavioCardAddress = ExcelReader.getCellData(1, Constant.Col_CardAddress);
					technavioCardCity = ExcelReader.getCellData(1, Constant.Col_CardCity);
				}
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForStepButton2)));
				// Thread.sleep(3000);
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
				PageActions.ClickOnProceed();
				logger.info("Click On Proceed");
				extentTest.log(LogStatus.INFO, "Click On Proceed");
				Thread.sleep(3000);
				scrollToElementContinueStep2();
				logger.info("Scrolling to Continue button");
				extentTest.log(LogStatus.INFO, "Scrolling to Continue button");
				PageActions.clickOnContinueStep2();
				logger.info("Click On Billing info Continue");
				extentTest.log(LogStatus.INFO, "Click On Billing info Continue");
				Thread.sleep(3000);
				if (reportPaymentSection()) {
					// Thread.sleep(50000);
					wait.until(
							ExpectedConditions.invisibilityOfElementLocated(By.xpath(PageLocators.waitForLoderScreen)));
					// Thread.sleep(7000);
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(PageLocators.waitForReportPurchase)));
					if (PageLocators.successfullPaymentReportName.getText() != null) {
						// Thread.sleep(3000);
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath(PageLocators.waitForPaymentHistory)));
						PageActions.clickOnpaymentHistory1();
						List<WebElement> skuHistoryList = PageLocators.skuHistoryList;
						for (int j = 0; j < skuHistoryList.size(); j++) {
							logger.info(PageLocators.skuHistoryList.get(j).getText());
							if (PageLocators.skuHistoryList.get(j).getText().equals(reportName)) {
								logger.info(reportName + "Expected Report Name Visible");
								extentTest.log(LogStatus.INFO, reportName + "Expected Report Name Visible");
							} else {
								// fails condition
								extentTest.log(LogStatus.FAIL, "subcription not completly done No Records Found  ");
								Assert.assertTrue(false);
								captureScreen(driver, sTestCaseName);
								throw new RuntimeException("subcription not completly done No Records Found  ");
							}
						}

					} else {
						// fails condition
						extentTest.log(LogStatus.FAIL, "Successfull Message not Found  ");
						Assert.assertTrue(false);
						captureScreen(driver, sTestCaseName);

					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean isOfficialReportInCart(String homeReportName) throws Exception {
		// Thread.sleep(3000);
		logger.info(" Wait For Cart Tab ");
		logger.info(" Enter into isReportPresentInCartTab   : : ");
		List<WebElement> CartTabReports = PageLocators.cartTabReports;
		for (int i = 0; i < CartTabReports.size(); i++) {
			logger.info(" isReportPresentInCartTab Enter ForLoop : : " + i);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForcartReport)));
			String actualCartReportName = PageLocators.cartTabReports.get(i).getText();
			logger.info(" actualCartReportName  : : " + actualCartReportName);
			logger.info(" homeReportName  :  : " + homeReportName);
			extentTest.log(LogStatus.INFO, " actualCartReportName  : : " + actualCartReportName);
			extentTest.log(LogStatus.INFO, " homeReportName  :  : " + homeReportName);
			// Thread.sleep(3000);
			if (homeReportName.equalsIgnoreCase(actualCartReportName)) {
				logger.info(homeReportName + " Expected Report is Active State  : : " + actualCartReportName);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(PageLocators.waitForReportPageContinue)));
				logger.info(" after completing wait state for continue button");
				iTestCaseRow = ExcelReader.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
				rowCount = ExcelReader.getRowUsed();
				// get Excel data
				logger.info("Entering the card address");
				if (rowCount != 0) {
					technavioCardAddress = ExcelReader.getCellData(1, Constant.Col_CardAddress);
					technavioCardCity = ExcelReader.getCellData(1, Constant.Col_CardCity);
				}
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForStepButton2)));
				// Thread.sleep(3000);
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
				PageActions.ClickOnProceed();
				logger.info("Click On Proceed");
				extentTest.log(LogStatus.INFO, "Click On Proceed");
				Thread.sleep(3000);
				scrollToElementContinueStep2();
				logger.info("Scrolling to Continue button");
				extentTest.log(LogStatus.INFO, "Scrolling to Continue button");
				PageActions.clickOnContinueStep2();
				logger.info("Click On Billing info Continue");
				extentTest.log(LogStatus.INFO, "Click On Billing info Continue");
				Thread.sleep(3000);
				if (officalReportPayment()) {
					// Thread.sleep(50000);
					wait.until(
							ExpectedConditions.invisibilityOfElementLocated(By.xpath(PageLocators.waitForLoderScreen)));
					// Thread.sleep(7000);
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(PageLocators.waitForReportPurchase)));
					if (PageLocators.successfullPaymentReportName.getText() != null) {
						// Thread.sleep(3000);
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath(PageLocators.waitForPaymentHistory)));
						PageActions.clickOnpaymentHistory1();
						List<WebElement> skuHistoryList = PageLocators.skuHistoryList;
						for (int j = 0; j < skuHistoryList.size(); j++) {
							logger.info(PageLocators.skuHistoryList.get(j).getText());
							if (PageLocators.skuHistoryList.get(j).getText().equals(reportName)) {
								logger.info(reportName + "Expected Report Name Visible");
								extentTest.log(LogStatus.INFO, reportName + "Expected Report Name Visible");
							} else {
								// fails condition
								extentTest.log(LogStatus.FAIL, "subcription not completly done No Records Found  ");
								Assert.assertTrue(false);
								captureScreen(driver, sTestCaseName);
								throw new RuntimeException("subcription not completly done No Records Found  ");
							}
						}

					} else {
						// fails condition
						extentTest.log(LogStatus.FAIL, "Successfull Message not Found  ");
						Assert.assertTrue(false);
						captureScreen(driver, sTestCaseName);

					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("static-access")
	private void setPlan() throws Exception {
		rowCount = ExcelReader.getRowUsed();
		for (iTestCaseRow = 1; iTestCaseRow <= rowCount; iTestCaseRow++) {
			technavioSubscriptionPaln = ExcelReader.getCellData(iTestCaseRow, Constant.Col_PaymentMode);
			if (PageLocators.litePlan.getText().equals(technavioSubscriptionPaln)) {

				PageActions.goToLitePlanPage();
				logger.info("click on lite plan");
			} else if (PageLocators.basicPlan.getText().equals(technavioSubscriptionPaln)) {
				PageActions.goToBasicPlanPage();
				logger.info("click on Basic plan");
			} else if (PageLocators.teamsPlan.getText().equals(technavioSubscriptionPaln)) {
				PageActions.goToTeamsPlanPage();
				logger.info("click on Teams plan");
			}
		}
	}

	private boolean validateBillingsection() {
		if (PageLocators.addressErrorMsg.isDisplayed() && PageLocators.cityErrorMsg.isDisplayed()
				&& PageLocators.countryErrorMsg.isDisplayed()) {
			return false;
		}
		return true;
	}

	private boolean offlineBillingsection() {
		if (PageLocators.offlineProceedToChooseErrorMsg.isDisplayed()) {
			return false;
		}
		return true;
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	private void setBillingInformation(String technavioCardAddress, String technavioCardCity)
			throws InterruptedException {
		// Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForAddress)));
		PageActions.addressParameter(technavioCardAddress);
		logger.info("Entering to address field");
		extentTest.log(LogStatus.INFO, "Entering to address field");
		PageActions.cityParameter(technavioCardCity);
		logger.info("Entering to City field");
		extentTest.log(LogStatus.INFO, "Entering to City field");
		List<WebElement> list = PageLocators.countries;
		list.get(4).click();

	}

	@SuppressWarnings("static-access")
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
				/*
				 * technavioCardCvv = ExcelReader.getCellData(iTestCaseRow,
				 * Constant.Col_CardCvv);
				 */
				technavioCardCvv = "123";
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
				if (PageLocators.paymentErrorMsg.isDisplayed()) {
					List<String> errorMsgsList = errorMsgsList();
					String actualMsg = PageLocators.paymentErrorMsg.getText();
					if (errorMsgsList.contains(actualMsg)) {
						logger.info(PageLocators.paymentErrorMsg.getText());
						extentTest.log(LogStatus.FAIL, PageLocators.paymentErrorMsg.getText());
						Assert.assertTrue(false);
						captureScreen(driver, sTestCaseName);
						continue;
					} else {
						extentTest.log(LogStatus.FAIL, "Un-Relavent Error Message Displayed : " + actualMsg);
						Assert.assertTrue(false);
						captureScreen(driver, sTestCaseName);
					}

				} else {
					logger.info("Return True");
					PageActions.clickOnCheckOut();
					logger.info("Click On CheckOut");
					extentTest.log(LogStatus.INFO, "Click On CheckOut");
					return true;
				}

			}
		}
		return false;

	}

	private boolean reportPaymentSection() throws Exception {
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
				technavioCardCvv = "123";
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
				PageActions.clickOnCheckOut();
				logger.info("Click On CheckOut");
				extentTest.log(LogStatus.INFO, "Click On CheckOut");
				wait.until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath(PageLocators.waitForLoderScreen)));
				if (PageLocators.paymentErrorMsg.isDisplayed()) {
					List<String> errorMsgsList = errorMsgsList();
					String actualMsg = PageLocators.paymentErrorMsg.getText();
					if (errorMsgsList.contains(actualMsg)) {
						logger.info(PageLocators.paymentErrorMsg.getText());
						extentTest.log(LogStatus.FAIL, "Un-Relavent Error Message Displayed : ");
						Assert.assertTrue(false);
						continue;
					} else {
						extentTest.log(LogStatus.FAIL, "Un-Relavent Error Message Displayed : " + actualMsg);
						Assert.assertTrue(false);
						captureScreen(driver, sTestCaseName);
					}

				} else {
					/*logger.info("Return True");
					PageActions.clickOnCheckOut();
					logger.info("Click On CheckOut");*/
					logger.info("Return True");
					return true;
				}

			}
		}
		return false;

	}

	private boolean officalReportPayment() throws Exception {
		rowCount = ExcelReader.getRowUsed();
		if (rowCount != 0) {

			for (iTestCaseRow = 1; iTestCaseRow <= rowCount; iTestCaseRow++) {
				Thread.sleep(3000);
				scrollToProceedElement();
				PageActions.clickOnProceedToAgree();
				if (iTestCaseRow != 1) {
					PageActions.toAgreeCheck();
				}
				technavioOfflineAddress1 = ExcelReader.getCellData(iTestCaseRow, Constant.Col_addressBox1);
				technavioOfflineAddress2 = ExcelReader.getCellData(iTestCaseRow, Constant.Col_addressBox2);
				PageActions.clickOnProceedToBuy();
				if (!offlineBillingsection()) {

				} else {
					extentTest.log(LogStatus.FAIL, " Expected Error Message Not Found In Billing Section  ");
					Assert.assertTrue(false);
					captureScreen(driver, sTestCaseName);
				}
				//PageActions.clickOnProceedToAgree();
				PageActions.adressBox1(technavioOfflineAddress1);
				logger.info("Entering Address Line 1");
				extentTest.log(LogStatus.INFO, "Entering Address Line 1");
				PageActions.adressBox2(technavioOfflineAddress2);
				logger.info("Entering Address Line 2");
				extentTest.log(LogStatus.INFO, "Entering Address Line 2");
				PageActions.clickOnAgreeToChoose();
				PageActions.clickOnProceedToBuy();
				return true;
			}

		}
		return false;

	}

	@SuppressWarnings("rawtypes")
	private List<String> errorMsgsList() {
		@SuppressWarnings("unchecked")
		List<String> errorMsgsList = new ArrayList();
		errorMsgsList.add("Please provide card number");
		errorMsgsList.add("Card number cannot be less than 14 digit");
		errorMsgsList.add("Please provide CVV details");
		errorMsgsList.add("Please Enter Card Holder Name");
		errorMsgsList.add("Please agree to proceed!");
		errorMsgsList.add("Your card number is incorrect.");
		errorMsgsList.add("Your card's expiration month is invalid.");
		errorMsgsList.add("This payment has already been processed");
		errorMsgsList.add("Your card has expired.");
		errorMsgsList.add("Your card has insufficient funds.");
		errorMsgsList.add("Your card was declined.");
		errorMsgsList.add("Your card's security code is incorrect.");
		errorMsgsList.add("This value must be greater than or equal to 1.");
		

		return errorMsgsList;

	}
}
