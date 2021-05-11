package testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.freemium.base.BaseClass;
import com.freemium.pageactions.PageActions;
import com.freemium.pagelocators.PageLocators;
import com.relevantcodes.extentreports.LogStatus;

public class Home_TestCase extends BaseClass {
	Common_Login common_Login = new Common_Login();
	PageActions PageActions = new PageActions();
	private String sTestCaseName;

	@BeforeClass
	public void homePageSetUp() throws IOException {
		try {
			common_Login.freemiumUserlogin();
			sTestCaseName = this.toString();
			sTestCaseName = getTestCaseName(this.toString());
			extentTest = onStart().startTest(sTestCaseName);
		} catch (Exception e) {
			captureScreen(driver, "Home_TestCase");
			Assert.assertFalse(true);
		}
		logger.info("This is Before Class");
	}

	@Test
	public void checkingfavoriteIcons() throws Exception {
		extentTest=extent.startTest("checkingfavoriteIcons");
		//driver.navigate().refresh();
		//extentTest.log(LogStatus.INFO, "Navigate to Product Page");
		extentTest.log(LogStatus.INFO, "verify for the availability of favorite Icons");
		String favoriteAddErrorMessage = "Report is added to Favorite";
		String actualMsg = null;
		String reportName = null;
		List<WebElement> homeTabicons = PageLocators.homeTabIcons;
		waitState();
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsfaIcon)));
			List<WebElement> favoriteIcons = PageLocators.latestReportsfavoriteIcons;
			for (int i = 0; i < favoriteIcons.size(); i+=10) {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsfaIcon)));
				List<WebElement> aciveIconElementList = PageLocators.latestReportsfavoriteIcons;
				String aciveIconElement = aciveIconElementList.get(i).getAttribute("class");
				reportName = PageLocators.latestReports.get(i).getText();
				extentTest.log(LogStatus.INFO, " Get report Name :: " + reportName);
				if (!aciveIconElement.equalsIgnoreCase("fa fa-heart active")) {
					logger.info("Verify for the status Favorite icon of an Report is not added to Favorite List");
					extentTest.log(LogStatus.INFO, "Verify for the status Favorite icon of an Report is not added to Favorite List");
					logger.info(" Click On Favorite Icon ");
					extentTest.log(LogStatus.INFO, " Click On Favorite Icon ");
					favoriteIcons.get(i).click();
					logger.info(" Get report Name in Latest Tab  :: " + reportName);
					extentTest.log(LogStatus.INFO, " Get reportName in Latest Tab  :: " + reportName);
					//logger.info("Fav Icon Not Active");
					//extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + );
					isFavPopupMsgDisplayed(favoriteAddErrorMessage, reportName, homeTabicons, aciveIconElement);
				} else {
					logger.info(" Get report Name in Latest Tab  :: " + reportName);
					//extentTest.log(LogStatus.INFO, " Get reportName in Latest Tab  :: " + reportName);
					favoriteIcons.get(i).click();
					logger.info("Verify for the status Favorite icon of an Report is added to Favorite List");
					extentTest.log(LogStatus.PASS, "Verify for the status Favorite icon of an Report is added to Favorite List");
					logger.info(" Click On Fav Active Icon ");
					isFavPopupMsgDisplayed(favoriteAddErrorMessage, reportName, homeTabicons, aciveIconElement);
				}
			}
		} catch (TimeoutException e) {
			extentTest.log(LogStatus.FAIL, " Taking more time to Load Page (TimeoutException ) ");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);

		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, " Test Failed");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		}
	}

	/**
	 * @param favoriteAddErrorMessage
	 * @param reportName
	 * @param homeTabicons
	 * @param aciveIconElement
	 * @throws Exception
	 * @throws IOException
	 */
	private void isFavPopupMsgDisplayed(String favoriteAddErrorMessage, String reportName,
			List<WebElement> homeTabicons, String aciveIconElement) throws Exception, IOException {
		String actualMsg;
		if (!aciveIconElement.equalsIgnoreCase("fa fa-heart active") && isFavMessageDisplay()) {
			logger.info("Favorite Add Msg Displayed");
			homeTabicons.get(0).click();
			logger.info("Click On Fav Tab");
			extentTest.log(LogStatus.INFO, "Click On Fav Tab");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForfavTabReports)));
			
			isReportNamePresent(homeTabicons, reportName, aciveIconElement);

		} else if (aciveIconElement.equalsIgnoreCase("fa fa-heart active") && isFavRemoveMessageDisplay()) {
			homeTabicons.get(0).click();
			extentTest.log(LogStatus.INFO, "Click On Fav Tab");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForfavTabReports)));
			isReportNamePresent(homeTabicons, reportName, aciveIconElement);
		} else {
			actualMsg = PageLocators.favoriteIconadded.getText();
			logger.info("actualMsg - Else " + actualMsg);
			captureScreen(driver, sTestCaseName);
			extentTest.log(LogStatus.FAIL,
					"Expected Message " + favoriteAddErrorMessage + " Actual Message is " + actualMsg);
			Assert.assertEquals(actualMsg, favoriteAddErrorMessage);
		}
	}

	@Test(priority = 3,enabled=false)
	public void checkingCartIcons() throws Exception {
		extentTest = extent.startTest("checkingCart Icons");
		extentTest.log(LogStatus.INFO, "verify for the availability of checkingCart Icons");
		//driver.navigate().refresh();
		waitState();
		String actualMsg = null;
		String reportName = null;
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
		List<WebElement> homeTabicons = PageLocators.homeTabIcons;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
			List<WebElement> cartIcons = PageLocators.latestReportsCartIcons;
			WebElement cartIcon = PageLocators.goToIcon;
			for (int i = 0; i < cartIcons.size(); i += 10) {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
				String cartIconStatus = cartIconStatus(i);
				reportName = PageLocators.latestReports.get(i).getText();
				if (!cartIconStatus.equalsIgnoreCase("fa fa-shopping-cart add-to-cart  cart-added")) {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
					logger.info("Verify for the status Cart icon of an Report is not added to Cart Page");
					extentTest.log(LogStatus.INFO, "Verify for the status Cart icon of an Report is not added to Cart Page");
					logger.info(" Click On Cart Icon ");
					extentTest.log(LogStatus.INFO, " Click On Cart Icon ");
					cartIcons.get(i).click();
					logger.info("Get report Name  :  : " + reportName);
					extentTest.log(LogStatus.INFO, "Get report Name  :  : " + reportName);
					if (isCartMessageDisplay()) {
						/*actualMsg = PageLocators.productAddedcart.getText();
						logger.info("actualMsg -  " + actualMsg);*/
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath(PageLocators.waitForCartAddErrorMsg)));
						logger.info("waitForlatestTabGoToCartIcon  Completed");
						logger.info(" Click On Cart Page Icon ");
						extentTest.log(LogStatus.INFO, " Click On Cart Page Icon ");
						PageActions.goToCartPage();
						if (isReportNamePresentCartPage(reportName)) {
							//Thread.sleep(5000);
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
							validateCartIconStatus(i);
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
					extentTest.log(LogStatus.INFO, " Click On Cart Page Icon ");
					wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForReportsCart)));
					if (isReportNamePresentCartPage(reportName)) {
						validateCartIconStatus(i);
					}
				}
			}

		} catch (TimeoutException e) {
			extentTest.log(LogStatus.FAIL, " Taking more time to Load Page (TimeoutException ) ");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, " Test Failed");
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
		}
	}

	@Test(priority = 1,enabled=false)
	private void checkingViewIcons() throws Exception {
		extentTest = extent.startTest("checkingViewIcons");
		extentTest.log(LogStatus.INFO, "verify for the availability of Eye Icons");
		waitState();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsfaIcon)));
		List<WebElement> ReportsViewIcons = PageLocators.latestReportsViewIcons;
		scrollToElement();
		logger.info("Scroll to Visibility of Element");
		extentTest.log(LogStatus.INFO, "Scroll to Visibility of Element");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.latestReportsQuickViewIcon)));
		// ReportName Get
		String actualReportName = PageLocators.latestFirstReports.getText();
		extentTest.log(LogStatus.INFO, "Get Report Name :: " + actualReportName);
		logger.info("Click on Quick view icon");
		extentTest.log(LogStatus.INFO, "Click on Quick view icon");
		ReportsViewIcons.get(0).click();
		logger.info("Quick view Popup opened");
		extentTest.log(LogStatus.INFO, "Quick view Popup opened");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForPassageText)));
		if (PageLocators.quickViewText.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForAllAbstaractLink)));
			logger.info("Click on ViewallAbstract Link Text");
			extentTest.log(LogStatus.INFO, "Click on ViewallAbstract Link Text");
			PageLocators.allViewAbstract.get(0).click();
			Set<String> window = driver.getWindowHandles();
			ArrayList handle = new ArrayList(window);
			driver.switchTo().window((String) handle.get(1));
			logger.info("Switch To NewTab");
			extentTest.log(LogStatus.INFO, "Switch To NewTab");
			if(PageLocators.waitForNewTabRandomReport!=" ") {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForNewTabRandomReport)));
			}
			String expectedReportName = PageLocators.newReportTab.getText();
			if (actualReportName.equals(expectedReportName)) {
				logger.info("Expeted Report Matched ::  " + expectedReportName );
				extentTest.log(LogStatus.INFO, "Expeted Report Matched ::  " + expectedReportName);
				Assert.assertEquals(actualReportName, expectedReportName);
				logger.info("Switch to Latest Report Tab");
				extentTest.log(LogStatus.INFO, "Switch to Latest Report Tab");
				driver.switchTo().window((String) handle.get(0));
				logger.info(" Switch To Latest Report Tab");
			} else {
				logger.info("Expeted Report Name " + expectedReportName + " Not Found");
				captureScreen(driver, sTestCaseName);
				extentTest.log(LogStatus.FAIL, "Expeted Report Name " + expectedReportName + " Not Found");
				Assert.assertEquals(actualReportName, expectedReportName);
			}

		} else {
			captureScreen(driver, sTestCaseName);
			extentTest.log(LogStatus.FAIL, "Expected Report is view icon text invisible" + PageLocators.passageText);
			Assert.assertEquals(PageLocators.allViewAbstract, "Expected Report is view icon text invisible");
		}
		
	}

	private void validateCartIconStatus(int i) throws Exception {
		// Thread.sleep(5000);
		String cartIconStatus;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForlatestTabReportsCartIcon)));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForLastCartIcon)));
		logger.info("Wait for Last Cart icon");
		cartIconStatus = cartIconStatus(i);
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

	private String cartIconStatus(int i) throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForLastCartIcon)));
		logger.info("Wait for cartIconStatus completed");
		List<WebElement> aciveIconElementList = PageLocators.latestReportsCartIconsClassName;
		String aciveIconElement = aciveIconElementList.get(i).getAttribute("class");
		return aciveIconElement;
	}

	private void isReportNamePresent(List<WebElement> homeTabicons, String reportName, String aciveIconElement)
			throws Exception {
		if (!aciveIconElement.equalsIgnoreCase("fa fa-heart active") && isReportPresentInFavTab(reportName)) {
			logger.info(" Report is Availble ");
			homeTabicons.get(1).click();
			logger.info(" Click On Latest Tab ");
			extentTest.log(LogStatus.INFO, " Click On Latest Tab ");

		} else if (aciveIconElement.equalsIgnoreCase("fa fa-heart active") && isReportNotPresentInFavTab(reportName)) {
			logger.info(" Report is Not Availble As expected in favorite tab");
			extentTest.log(LogStatus.PASS, " Report is Not Availble As expected in favorite tab");
			homeTabicons.get(1).click();
			logger.info(" Click On Latest Tab ");
			extentTest.log(LogStatus.INFO, " Click On Latest Tab ");
		} else {
			extentTest.log(LogStatus.FAIL, "  Expected Report Not Availble ");
			Assert.assertTrue(false);
			throw new RuntimeException(" Expected Report Not Avaible ");
		}
	}

	private boolean isFavMessageDisplay() throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForFavoritAddErrorMsg)));
			logger.info("wait completed For Add Msg Displayed ");
			if (PageLocators.favoriteIconadded.isDisplayed()) {
				String actualMsg = PageLocators.favoriteIconadded.getText();
				logger.info("actualMsg -  " + actualMsg);
				extentTest.log(LogStatus.INFO, "actualMsg -  "  + actualMsg);
				
				logger.info("return true For Add Msg ");
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
		}

		catch (Exception e) {
			logger.info("return Exception");
			throw e;
		}
	}

	private boolean isFavRemoveMessageDisplay() throws Exception {
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(PageLocators.waitForFavoritRemovedErrorMsg)));
			logger.info("wait completed");
			if (PageLocators.favoriteIconremoved.isDisplayed()) {
				logger.info("return true");
				String actualMsg = PageLocators.favoriteIconremoved.getText();
				logger.info("actualMsg -  " + actualMsg);
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
		}
		catch (Exception e) {
			logger.info("return Exception");
			throw e;
		}
	}

	public boolean isReportPresentInFavTab(String homeReportName) throws Exception {
		// driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		//Thread.sleep(6000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PageLocators.waitForFAvIconErrorMsg)));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForfavTabReports)));
		logger.info("Wait For Load the Fav Tab Add Reports");
		List<WebElement> favoritetabReports = PageLocators.favoritetabReports;
		for (int i = 0; i < favoritetabReports.size(); i++) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated((By)
			// PageLocators.waitForFavTabReports.get(i)));
			String actualFavReportName = PageLocators.favoritetabReports.get(i).getText();
			if (homeReportName.equalsIgnoreCase(actualFavReportName)) {
				extentTest.log(LogStatus.INFO, "Comparing latest tab reports in favorite tab reports");
				Assert.assertEquals(actualFavReportName, homeReportName);
				extentTest.log(LogStatus.PASS, homeReportName + "  :: Expected Report Matched");
				logger.info(homeReportName + " :: Expected Report Matched");
				logger.info("Return True for Add Report ");
				return true;
			}
		}
		logger.info("Return false for Add Report ");
		return false;
	}

	public boolean isReportNotPresentInFavTab(String homeReportName) throws Exception {
		// driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		//Thread.sleep(6000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PageLocators.waitForFAvIconErrorMsg)));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForfavTabReports)));
		logger.info("Wait For Load the Fav Tab Remove Reports");
		List<WebElement> favoritetabReports = PageLocators.favoritetabReports;
		for (int i = 0; i < favoritetabReports.size(); i++) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated((By)
			// PageLocators.waitForFavTabReports.get(i)));
			String actualFavReportName = PageLocators.favoritetabReports.get(i).getText();
			if (homeReportName.equalsIgnoreCase(actualFavReportName)) {
				extentTest.log(LogStatus.INFO, "Comparing latest tab reports in favorite tab reports");
				Assert.assertEquals(actualFavReportName, homeReportName);
				extentTest.log(LogStatus.INFO, homeReportName + " :: Expected Report Should Not Matched");
				logger.info(homeReportName + " :: Expected Report Should Not Matched");
				return false;
			}
		}
		logger.info("Report are available in favorite tab");
		return true;
	}

	private boolean isCartMessageDisplay() throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForCartAddErrorMsg)));
			logger.info("wait completed");
			if (PageLocators.productAddedcart.isDisplayed()) {
				String actualMsg = PageLocators.productAddedcart.getText();
				logger.info("actualMsg -  " + actualMsg);
				extentTest.log(LogStatus.INFO, "actualMsg -  "  + actualMsg);

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
		}
		catch (Exception e) {
			logger.info("return Exception");
			throw e;
		}
	}
	private boolean isReportNamePresentCartPage(String reportName) throws Exception {
		if (isReportPresentInCartTab(reportName)) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForHomeIcon)));
			PageActions.goToHome();
			extentTest.log(LogStatus.INFO, "Click on Home button ");
			logger.info(" Sucessfully Navigate to Home Lastest Report page");
			return true;
		} else {
			extentTest.log(LogStatus.FAIL, "  Expected Report Not Avaible ");
			Assert.assertTrue(false);
			throw new RuntimeException(" Expected Report Not Avaible ");
		}
	}

	public boolean isReportPresentInCartTab(String homeReportName) throws Exception {
		// Thread.sleep(3000);
		logger.info(" Wait For Cart Tab ");
		logger.info(" Enter into isReportPresentInCartTab   : : ");
		List<WebElement> CartTabReports = PageLocators.cartTabReports;
		extentTest.log(LogStatus.INFO, "Camparing the reports in checkout cart page");
		for (int i = 0; i < CartTabReports.size(); i++) {
			logger.info(" isReportPresentInCartTab Enter ForLoop : : " + i);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForcartReport)));
			String actualCartReportName = PageLocators.cartTabReports.get(i).getText();
			/*logger.info(" actualCartReportName  : : " + actualCartReportName);
			logger.info(" homeReportName  :  : " + homeReportName);
			extentTest.log(LogStatus.INFO, " actualCartReportName  : : " + actualCartReportName);
			extentTest.log(LogStatus.INFO, " homeReportName  :  : " + homeReportName);*/
			// Thread.sleep(3000);
			if (homeReportName.equalsIgnoreCase(actualCartReportName)) {
				extentTest.log(LogStatus.INFO,actualCartReportName + " :: Expected Report are matched");
				logger.info(homeReportName + " Expected Report is Active State  : : " + actualCartReportName);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(PageLocators.waitForReportPageContinue)));
				logger.info(" after completing wait state for continue button");
				extentTest.log(LogStatus.INFO, "click on Remove link :: " + actualCartReportName);
				PageLocators.cartRemoveLink.get(i).click();
				logger.info(" second after completing wait state for continue button");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.waitForReportPageContinue)));
				logger.info("Excepected Report is Removed");
				Assert.assertEquals(actualCartReportName, homeReportName);
				//extentTest.log(LogStatus.INFO, homeReportName + " :: Expected Report Matched");
				return true;
			}
		}
		logger.info(" isReportPresentInCartTab return false : : ");
		return false;
	}

	@AfterClass
	public void logOut() throws IOException {

		try {
			/*
			 * if (PageLocators.waitForFavoritAddErrorMsg.
			 * equalsIgnoreCase("Report is added to Favorite") ||
			 * PageLocators.waitForFavoritRemovedErrorMsg
			 * .equalsIgnoreCase("Report is removed from Favorites")) { if
			 * (PageLocators.waitForFavoritAddErrorMsg.
			 * equalsIgnoreCase("Report is added to Favorite")) { //
			 * common_Login.logoutPage(); } else if
			 * (PageLocators.waitForFavoritRemovedErrorMsg
			 * .equalsIgnoreCase("Report is removed from Favorites")) {
			 * wait.until(ExpectedConditions
			 * .invisibilityOfElementLocated(By.xpath(PageLocators.waitForCartRemoveErrorMsg
			 * ))); common_Login.logoutPage(); } } else
			 */ if (PageLocators.waitForCartAddErrorMsg.equalsIgnoreCase("Product is added to Cart.")
					|| PageLocators.waitForCartRemoveMsg.equalsIgnoreCase("Product is deleted from Cart")) {
				if (PageLocators.waitForCartAddErrorMsg.equalsIgnoreCase("Product is added to Cart.")) { // common_Login.logoutPage();
				} else if (PageLocators.waitForCartRemoveMsg.equalsIgnoreCase("Product is deleted from Cart")) {
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath(PageLocators.waitForCartRemoveMsg)));
					common_Login.logoutPage();
					logger.info("Succesfully logged out");
					extentTest.log(LogStatus.INFO, "Succesfully logged out");
					
				}
			} else {
				common_Login.logoutPage();
				logger.info("Succesfully logged out");
				extentTest.log(LogStatus.INFO, "Succesfully logged out");
			}
		} catch (Exception e) {
			captureScreen(driver, sTestCaseName);
			Assert.assertFalse(true);
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
	

}
