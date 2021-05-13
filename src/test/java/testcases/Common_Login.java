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
			Thread.sleep(2000);
			PageActions.clickOnSignInButton();
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
