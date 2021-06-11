package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.freemium.pageactions.PageActions;
import com.freemium.pagelocators.PageLocators;
import com.freemium.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class Common_Login extends Utilities {

	public void freemiumUserlogin() throws Exception {
		try {
			PageActions actions = new PageActions();

			waitState();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.userId)));
			actions.userNameParameter(getFreemiumUserName());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.nextButtonXpath)));
			PageActions.clickOnNextButton();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.passwordId)));
			PageActions.passwordParameter(getFremiumPassword());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.signInButtonId)));
			PageActions.clickOnSignInButton();
		} catch (Exception e) {
			throw e;
		}
	}

	public void logoutPage() throws Exception {
		
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PageLocators.adminIconClassName)));
			clickOnAdminIcon();
			clickOnLogout();

		
	}
	public void gettingNewUser() throws Exception {
		try {
			PageActions actions = new PageActions();
			waitState();
			driver.get(properties.getProperty("tempMail"));
			Thread.sleep(3000);
			clickOnTrashIcon();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PageLocators.userId)));
			//actions.userNameParameter(getFreemiumUserName());
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageLocators.nextButtonXpath)));
			clickOnCopyIcon();
		} catch (Exception e) {
			throw e;
		}
	}

}
