package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.freemium.pageactions.PageActions;
import com.freemium.pagelocators.PageLocators;
import com.freemium.utilities.Utilities;

public class Common_Login extends Utilities {

	public void freemiumUserlogin() throws Exception {
		try {
			PageActions actions = new PageActions();

			waitState();
			/*
			 * driver.manage().deleteAllCookies(); logger.info("Deleted all cookies");
			 * hardRefresh(); logger.info("Hard refresh");
			 */
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.userId)));
			actions.userNameParameter(getFreemiumUserName());
			logger.info("waiting for next button login page in 1min");
			Thread.sleep(100000);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.nextButtonXpath)));
			PageActions.clickOnNextButton();
			/*
			 * if (PageLocators.useriderr.isDisplayed()) {
			 * 
			 * }
			 */
			// logger.info("after hard refresh waiting for password passing sso page in
			// 1min");
			logger.info("waiting for next button login page in 5 sec");
			Thread.sleep(5000);
			// driver.manage().deleteAllCookies();
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.passwordId)));
			if (PageLocators.useriderr.isDisplayed()) {
				logger.info(PageLocators.useriderr.getText());
			} else {
				logger.info("waiting for next button login page in 2min");
				Thread.sleep(20000);
				if (PageLocators.ssoPage.isDisplayed()) {
					logger.info("sso page visible :: " + PageLocators.ssoPage.getText());
					String pwd = getFremiumPassword();
					logger.info("passing pwd :: " + pwd);
					PageActions.passwordParameter(pwd);
					// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.signInButtonId)));
					logger.info("waiting for sign button sso page in 6 sec");
					Thread.sleep(100000);
					// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					logger.info("wait completed for sso page");
					PageActions.clickOnSignInButton();
					logger.info("clickOnSignInButton");
				}

			}
			/*
			 * String pwd =getFremiumPassword(); logger.info("passing pwd :: "+pwd);
			 * PageActions.passwordParameter(pwd); //
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.
			 * signInButtonId))); logger.info("waiting for sign button sso page in 6 sec");
			 * Thread.sleep(100000); // driver.manage().timeouts().implicitlyWait(20,
			 * TimeUnit.SECONDS); logger.info("wait completed for sso page");
			 * PageActions.clickOnSignInButton(); logger.info("clickOnSignInButton");
			 */
		} catch (NoSuchElementException ne) {
			logger.info("NoSuchElementException :: " + ne);
			throw ne;
		} catch (TimeoutException te) {
			logger.info("TimeoutException :: " + te);
			throw te;
		} catch (StaleElementReferenceException se) {
			logger.info("StaleElementReferenceException :: " + se);
			throw se;
		} catch (Exception e) {
			throw e;
		}
	}

	public void logoutPage() throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PageLocators.adminIconClassName)));
			PageActions.adminIcon();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PageLocators.logOutIconClassName)));
			PageActions.logOut();

		} catch (Exception e) {
			throw e;
		}
	}

}
