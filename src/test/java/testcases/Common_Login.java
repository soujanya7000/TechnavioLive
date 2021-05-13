package testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.spi.LoggerRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.freemium.base.BaseClass;
import com.freemium.pageactions.PageActions;
import com.freemium.pagelocators.PageLocators;
import com.freemium.utilities.Utilities;

public class Common_Login extends BaseClass {

	@BeforeTest
	public void urlSetUp() throws IOException {
		driver.manage().deleteAllCookies();
		driver.get(properties.getProperty("freemiumDevUrl"));
	}

	@Test
	public void freemiumUserlogin() throws Exception {
		try {
			PageActions actions = new PageActions();

			waitState();
			/*driver.manage().deleteAllCookies();
			logger.info("Deleted all cookies");
			hardRefresh();
			logger.info("Hard refresh");*/
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.userId)));
			actions.userNameParameter(getFreemiumUserName());
			logger.info("waiting for next button login page in 10 sec");
			// Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.nextButtonXpath)));
			PageActions.clickOnNextButton();
			logger.info("waiting for password passing sso page in 10 sec");
			/*
			 * hardRefresh(); logger.info("Hard refresh");
			 */
			// Thread.sleep(10000);
			// driver.manage().deleteAllCookies();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.passwordId)));
			PageActions.passwordParameter(getFremiumPassword());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.signInButtonId)));
			logger.info("waiting for sign button sso page in 6 sec");
			Thread.sleep(6000);
			// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			logger.info("wait completed for sso page");
			PageActions.clickOnSignInButton();
			logger.info("clickOnSignInButton");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PageLocators.adminIconClassName)));
			PageActions.adminIcon();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PageLocators.logOutIconClassName)));
			PageActions.logOut();
		} catch (Exception e) {
			throw e;
		}
	}

	/*@AfterClass
	public void logoutPage() throws Exception {
		try {
			Thread.sleep(3000);
			logger.info("waiting for admin icon");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PageLocators.adminIconClassName)));
			PageActions.adminIcon();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PageLocators.logOutIconClassName)));
			PageActions.logOut();

		} catch (Exception e) {
			throw e;
		}
	}*/

}
