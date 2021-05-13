package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.freemium.pageactions.PageActions;
import com.freemium.pagelocators.PageLocators;
import com.freemium.utilities.Utilities;

public class Common_Login extends Utilities {

	public void freemiumUserlogin() throws Exception {
		try {
			PageActions actions = new PageActions();

			waitState();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.userId)));
			actions.userNameParameter(getFreemiumUserName());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.nextButtonXpath)));
			PageActions.clickOnNextButton();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.passwordId)));
			PageActions.passwordParameter(getFremiumPassword());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.signInButtonId)));
			logger.info("waiting for sso page in 6 sec");
			Thread.sleep(6000);
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			logger.info("wait completed for sso page");
			PageActions.clickOnSignInButton();
			logger.info("clickOnSignInButton");
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
