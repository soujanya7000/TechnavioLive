package com.freemium.pageactions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.freemium.pagelocators.PageLocators;
import com.freemium.utilities.Utilities;

public class PageActions extends Utilities {public PageLocators pageLocator;
final static Logger log = Logger.getLogger(PageActions.class);

public PageActions() {
      this.pageLocator = new PageLocators();
      PageFactory.initElements(driver, this.pageLocator);
}

      //username field
public static void clickOnNextButton() throws Exception {
      PageLocators.useridnext.click();
      log.info("Click On Next Button");
}
public static void clickSignInButton() {
    WebElement element=PageLocators.pwdnext;
    JavascriptExecutor js =(JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", element);
}
public static void clickOnNextButton1() {
    WebElement element=PageLocators.useridnext;
    JavascriptExecutor js =(JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", element);
}
public static void  passwordParameter(String ssoPassword) {
    WebElement element1 =PageLocators.pwid;
    JavascriptExecutor java =(JavascriptExecutor) driver;
    java.executeScript("arguments[0].value"+"="+"'"+ ssoPassword +"'"+";",element1);
}

public static void  userNameParameter(String ssoPassword) {
    WebElement element1 =PageLocators.userid;
    JavascriptExecutor java =(JavascriptExecutor) driver;
    java.executeScript("arguments[0].value"+"="+"'"+ ssoPassword +"'"+";",element1);
}

public static void clickOnAdminIcon() {
    WebElement element=PageLocators.adminicon;
    JavascriptExecutor js =(JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", element);
}
public static void clickOnLogout() {
    WebElement clickOnelement=PageLocators.logout;
    JavascriptExecutor js =(JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", clickOnelement);
}


public static void userFieldClear() {
      PageLocators.userid.clear();
      log.info(" Freemium User Name clear");
}
//password field
public static void clickOnSignInButton() {
      PageLocators.pwdnext.click();
      log.info(" Click on Sign-In Button");
}


public static void passwordFieldclear() {
      PageLocators.pwid.clear();
      log.info("Freemium  Password Clear");
}

//homePage WebElements
public static List<WebElement> latestReports;
public  List<WebElement> reports() {
      log.info("Click On latestReports");
      return latestReports;
}
public static List<WebElement> favoriteIcons;
public  List<WebElement> favoriteIcons() {
      log.info("Click On favoriteIcons");
      return favoriteIcons;
}
public static List<WebElement> cartIcons;
public  List<WebElement> cartIcons() {
      log.info("Click On cartIcons");
      return cartIcons;
}
public static List<WebElement> quickVeiwIcons;
public  List<WebElement> quickVeiwIcons() {
      log.info("Click On quickVeiwIcons");
      return quickVeiwIcons;
}
public static List<WebElement> homeTabIcons;
public  List<WebElement> homeTabIcons() {
      log.info("Click On homeTabIcons");
      return homeTabIcons;
}
public static void filterIcon() {
      PageLocators.filterIcon.click();
      log.info("Click On filterIcon");
}
public static void adminIcon() {
      PageLocators.adminicon.click();
      log.info("Click On admin Icon");
}
public static void logOut() {
      PageLocators.logout.click();
      log.info("Logout Sucessfully");
}
public static List<WebElement> favoritetabReports;
public  List<WebElement> favoritetabReports() {
      log.info("Click On favoritetabReports");
      return favoritetabReports;
}
public  void clickOnFirstReport() {
      PageLocators.latestFirstReports.click();
      log.info("Click On First Report");
}

public static void clickFirstCartIcon() {
      PageLocators.latestReportsFirstCartIcon.click();
      log.info("Click On latestReports FirstCart Icon");
}
//go to Upgrade plans

public static WebElement goToLitePlanPage() {
      PageLocators.lite.click();
      log.info("Click On goToLitePlanPage");
      return null;
}
public static WebElement goToBasicPlanPage() {
      PageLocators.basic.click();
      log.info("Click On goToBasicPlanPage");
      return null;
}
public static WebElement goToTeamsPlanPage() {
      PageLocators.teams.click();
      log.info("Click On goToTeamsPlanPage");
      return null;
}



//go to cart icon
public static void goToCartPage() {
      PageLocators.goToIcon.click();
      log.info("Click On goToIcon");
}
public  void goToHome() {
      PageLocators.goToHomePage.click();
      log.info("Click On goToHomePage");
}
public static void clickOnGoToCartButton() {
      PageLocators.goToCart.click();
      log.info("Click On goToCheckout Page");
}

public static void clickOnUpgradeButton() {
      PageLocators.upgrade.click();
      log.info("Click On Upgrade button");
}
public void clickOnUpgradeButton1() {
      WebElement element=PageLocators.upgrade;
      JavascriptExecutor js =(JavascriptExecutor) driver;
      js.executeScript("arguments[0].click();", element);
}
public void clickOnUpgradeButton2() {
      WebElement element=PageLocators.upgrade;
      Actions obj=new Actions(driver);
      obj.moveToElement(element).click().perform();
}

//check Out Cart Page
public static void addressParameter(String addressParameter) {
      PageLocators.Address.sendKeys(addressParameter);
      log.info("Entering addressParameter ");
}
public static void cityParameter(String cityParameter) {
      PageLocators.city.sendKeys(cityParameter);
      log.info("Entering cityParameter ");
}

public static void ClickOnCountryDropDown() {
      PageLocators.DropDown.click();
      log.info("Click On ClickOnCountryDropDown ");
}
public static void ClickOnProvinceDropDown() {
      PageLocators.provinceDropDown.click();
      log.info("Click On ClickOnProvinceDropDown ");
}

public static void cardHolderName(String cardHolderName) {
      PageLocators.CardName.sendKeys(cardHolderName);
      log.info("Entering cardHolderName ");
}

public static void cardHoldNumber(String cardHoldNumber) {
      PageLocators.cardNumber.sendKeys(cardHoldNumber);
      log.info("Entering cardHoldNumber ");
}
public static void cardHoldCvv(String cardHoldCvv) {
      PageLocators.cardCvv.sendKeys(cardHoldCvv);
      log.info("Entering cardHoldCvv ");
}
public static void cardHoldMonth() {
      PageLocators.cardCvv.click();
      log.info("Click On cardHoldMonth ");
}
public static void cardHoldYear() {
      PageLocators.cardYear.click();
      log.info("Click On cardHoldYear ");
}

public static void toAgreeCheck() {
      PageLocators.agreeCheckBox.click();
      log.info("Click On to AgreeCheck ");
}
public static void ClickOnUserSubscription() {
      PageLocators.userSubscription.click();
      log.info("Click On UserSubscription ");
}
public static void ClickOnProceed() {
      PageLocators.proceedRadioIcon.click();
      log.info("Click On clickOnRadioIcon ");
}

public static void ClickOnMyAccountDetails() {
      PageLocators.myAccountDetails.click();
      log.info("Click On MyAccountDetails ");
}

public static void ClickOnSubcriptionHistory() {
      PageLocators.subscriptionHistory.click();
      log.info("Click On SubcriptionHistory ");
}
public static void ClickOnPurchaseHistory() {
      PageLocators.purchaseHistory.click();
      log.info("Click On Purchase History ");
}
public static void clickOnContinueStep2() {
      PageLocators.ContinueStep2.click();
      log.info("Click On ContinueStep2");
}
public static void clickOnContinueStep1() {
      PageLocators.ContinueStep1.click();
      log.info("Click On ContinueStep1 ");
}

public static void clickOnMenuBar() {
      PageLocators.menuBar.click();
      log.info("Click On MenuBar ");
}
public static void clickOnCheckOut() {
      PageLocators.checkOut.click();
      log.info("Click On CheckOut ");
}
public static void clickOnpaymentHistoryText() {
      PageLocators.paymentHistoryText.click();
      log.info("Click On click On paymentHistory Text ");
}
public static void clickOnpaymentHistory() {
      PageLocators.paymentHistoryClassName.click();
      log.info("Click On payment History ");
}
public static void clickOnpaymentHistory1() {
      WebElement element=PageLocators.paymentHistoryClassName;
      JavascriptExecutor js =(JavascriptExecutor) driver;
      js.executeScript("arguments[0].click();", element);
      log.info("Click On payment History ");
}
public static void clickOnProceedToAgree() {
      PageLocators.proceedToChoose.click();
      log.info("Click On proceed To Choose ");
}
public static void clickOnAgreeToChoose() {
      PageLocators.agreeToChoose.click();
      log.info("Click On Agree To Choose ");
}
public static void clickOnProceedToBuy() {
      WebElement element=PageLocators.proceedToBuy;
      JavascriptExecutor js =(JavascriptExecutor) driver;
      js.executeScript("arguments[0].click();", element);
      log.info("Click On Proceed To Buy ");
}
public static void adressBox1(String addressBox1) {
      PageLocators.addressBox1.sendKeys(addressBox1);
      log.info("Entering addressBox1 ");
}
public static void adressBox2(String addressBox2) {
      PageLocators.addressBox2.sendKeys(addressBox2);
      log.info("Entering addressBox2 ");
}
//sign up field
      public static void nameParameter(String Name) throws Exception {
           PageLocators.firstName.sendKeys(Name);
           log.info("Freemium Name Passed");
      }
      public  void emailParameter(String email) throws Exception {
           PageLocators.email.sendKeys(email);
           log.info(" Freemium User email Passed");
      }
      public  void passwordFieldParameter(String passwordField) throws Exception {
           PageLocators.password.sendKeys(passwordField);
           log.info(" Freemium password Passed");
      }
      public static void clickONToAgree() throws Exception {
           PageLocators.toAgree.click();
           log.info("Click On Next Button");
      }
      public static void clickONSubmit() throws Exception {
           PageLocators.submit.click();
           log.info("Click On Next Button");
      }
      public static void clickONinviteUser() throws Exception {
           PageLocators.inviteUser.click();
           log.info("Click On invite User Link");
      }
      public  void inviteUserNameParameter(String inviteUserName) throws Exception {
           PageLocators.inviteUserName.sendKeys(inviteUserName);
           log.info(" Freemium Invite User Name Passed");
      }
      public  void inviteEmailParameter(String inviteEmail) throws Exception {
           PageLocators.inviteEmail.sendKeys(inviteEmail);
           log.info(" Freemium invite Email Passed");
      }
      public static void clickOnRequestInvite() throws Exception {
           PageLocators.requestInvite.click();
           log.info("Click On Request Inviter Link");
      }
}
