package com.freemium.pagelocators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageLocators {// During Wait state locators
    public static String nextButtonXpath = "//*[@type=\"submit\"]";
    public static String userId = "user";
    public static String userNameError = "//*[contains(text(),'Sorry! We could')] | //*[@class=\"alert alert-danger p-1\"]";
    public static String passwordId = "password";
    public static String signInButtonId = "next";
    public static String passwordError = "(//*[text()=\"Please enter your password\"]) | (//*[text()=\"Your password is incorrect\"])";
    public static String adminIconClassName = "admin-avatar";
    public static String logOutIconClassName = "drop-bottom";
    public static String waitForFavoritAddErrorMsg = "//*[text()='Report is added to Favorite']";
    public static String waitForFavoritRemovedErrorMsg = "//*[text()='Report is removed from Favorites']";
    public static String waitForCartAddErrorMsg ="//*[text()='Product is added to Cart.']";
    public static String latestFavoritIcon = "(((//div[@class=\\\"tab-meta\\\"])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[2]";
    public static String favRportTabLoc = "//*[@class=\"favorites-tab active show\"]";
    public static String waitForfavTabReports = "(//div[@class='tab-meta '])[1]/child::div[contains(@class,'new-product-card ')]";
    public static String latestReportsfavoriteIcon = "(//*[@title='Add to Favorites'])[1]";
    public static String waitForlatestTabReportsfaIcon = "(//*[@title='Add to Favorites'])[25]";
    public static String waitForlatestTabReportsCartIcon = "(//*[@title='Add to Cart'])[10]";
    public static String waitForReportsCartIcon = "(//*[@title='Add to Cart'])[4]";
    public static String waitForlatestTabGoToCartIcon = "(//*[@title='Go to cart'])[1]";
    public static String waitForReportsCart = "//*[@class='repname']/child::span";
    public static String inActiveFabIcon = "//*[@class='fa fa-heart in-active']";
    public static String inActiveCartIcon = "//*[@class='fa fa-shopping-cart add-to-cart  in-active']";
    public static String activeFabIcon = "//*[@class='fa fa-heart active']";
    public static String waitForHomeIcon ="//*[text()='Home']";
    public static String waitForCartRemoveErrorMsg="//*[text()='Report is removed from Favorites']";
    public static String waitForCartRemoveMsg="//*[text()='Product is deleted from Cart']";
    public static String waitForCartSubcriptionMsg = "(//*[@class='repname']/child::span)[24]";
    public static String waitForcartReport ="(//*[@class='repname']/child::span)[1]";
    public static String waitForLastCartIcon ="((((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[3]/span)[25]";
    public static String waitForReportCartPage="//*[@class='cartdelete']";
    public static String waitForRadioIcon="//*[@value='subscription']";
    public static String waitForPagination="(//*[text()='Next'])[2]";
    public static String latestReportsQuickViewIcon="(((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[1]/span[1]";
    public static String waitForAllAbstaractLink="(//*[@class='new-product-icons']/following::div/div/following::div/a)[1]";
    public static String waitFRorLatestTabReports="(//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')][25]";
    public static String waitForReportPageContinue="//a[contains(@class,'step_2_payment')]";
    public static String waitForPassageText="(//*[@class='loading-inc d-none']/following::p)[1]";
    public static String waitForNewTabLastestReport="//*[@class='product-detail-info']/h1";
    public static String waitForNewTabReport="//*[contains(text(),'Global Tyrosine Kinase')]";
    public static String waitForNewTabRandomReport="//*[@class='product-detail-info']/h1";
    public static String waitForFAvIconErrorMsg="//*[text()='Report is added to Favorite']|//*[text()='Report is removed from Favorites']";
    public static String waitForGoToCartButton="(//*[text()='Read Sample']/following::a)[1]";
    public static String waitForUpgradeButton="//*[@id='subscriptionTop']/a";
    public static String waitForLiteButton="(//*[contains(@class,'btn-st card_btn')]/span)[2]";
    public static String waitForBasicButton="(//*[contains(@class,'btn-st card_btn')]/span)[3]";
    public static String waitForTeamsButton="(//*[contains(@class,'btn-st card_btn')]/span)[4]";
    public static String waitForThanksMessage="//*[@class='search-found']";
    public static String waitForLoader="//*[@id='cover-spin']";
    public static String waitForSubscriptionHistory="(//*[text()=' Subscription History '])|//*[@class='plan_his']";
    public static String waitForContact="(//*[contains(@class,'btn-st card_btn')])[5]";
    public static String waitForStepButton1="//*[contains(@class,'step_1_payment')]|//*[@class=' text-right']";
    public static String waitForStepButton2="(//*[contains(@class,'step_2_payment')])|(//*[contains(@class,'col-md-12 t')])[1]";
    public static String waitForAddress="//*[@id='billing_address']";
    public static String waitForSubHistory="//*[@class='search-found']";
    public static String waitForFAQLink="//*[text()='FAQ ']";
    public static String waitForLoderScreen="//*[text()='Processing your request, this will take a moment. ']";
    public static String waitForThankYouMsg="//*[@class='search-found']/span";
    public static String firstIconStatus="((((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[3])[1]";
    public static String waitForReportPurchase="//*[@class='col-md-8']/h3";
    public static String waitForPaymentHistory="(//*[@class='success-msg'])/child::a";
    
    //public @FindBy (xpath="//*[@class='search-found']/span") WebElement waitForThankYouM;
    
    
    // Username Page Locators
    @FindBy(how = How.ID, using = "user")
    public static WebElement userid;
    @FindBy(how = How.XPATH, using = "//*[@type=\"submit\"]")
    public static WebElement useridnext;
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Sorry! We could')] | //*[@class=\"alert alert-danger p-1\"]")
    public static WebElement useriderr;
    
    // Password Page Locators
    @FindBy(how = How.ID, using = "password")
    public static WebElement pwid;
    @FindBy(how = How.XPATH, using = "(//*[text()=\"Please enter your password\"]) | (//*[text()=\"Your password is incorrect\"])")
    public static WebElement pwderr;
    @FindBy(how = How.ID, using = "next")
    public static WebElement pwdnext;
    
    
    // Logout Page Locators
    @FindBy(how = How.XPATH, using = "(//*[@class='fa fa-user-circle user_icon'])[1]")
    public static WebElement adminicon;
    @FindBy(how = How.XPATH, using = "(//*[@class='drop setting active'])[2]/child::span/a")
    public static WebElement logout;
    
    //Home page Locators
    //Latest reports
    @FindBy(how=How.XPATH,using="(//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')]")
    public static List<WebElement> latestReports;
    @FindBy(how=How.XPATH,using="((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])[1]")
    public static WebElement latestFirstReports;
    @FindBy(how=How.XPATH,using="(((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[1]")
    public static List<WebElement> latestReportsViewIcons;
    @FindBy(how=How.XPATH,using="(((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[2]/span")
    public static List<WebElement> latestReportsfavoriteIcons;
    @FindBy(how=How.XPATH,using="(((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[3]/span")
    public static List<WebElement> latestReportsCartIconsClassName;
    @FindBy(how=How.XPATH,using="(((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[3]")
    public static List<WebElement> latestReportsCartIcons;
    @FindBy(how = How.XPATH, using = "//*[@class='product-detail-info']/h1")
    public static WebElement newReportTab;
    @FindBy(how = How.XPATH, using = "((((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[3]/span)[1]")
    public static WebElement latestReportsFirstCartIcon;
    @FindBy(how = How.XPATH, using = "((((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[3])[1]")
    public static WebElement firstCartIcon;
    /*@FindBy(how = How.XPATH, using = "((((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[3])[1]")
    public static WebElement firstCartIconStatus;*/
    @FindBy(how = How.XPATH, using = "(((//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')])/div/div/ul)/li[3]/span")
    public static WebElement firstCartIconClassName;
    @FindBy(how=How.XPATH,using="//*[@title='Purchased']")
    public static List<WebElement> purchaseReportList;
    @FindBy(how=How.XPATH,using="(//div[@class='tab-meta '])[1]/child::div[contains(@class,'new-product-card ')]")
    public static List<WebElement> waitForFavTabReports;
    
    //Signup
    
    @FindBy(how = How.XPATH, using = "(//*[@class='alert alert-success alert-invite']/following::input)[1]")
    public static WebElement firstName;
    @FindBy(how = How.NAME, using = "email")
    public static WebElement email;
    @FindBy(how = How.NAME, using = "password")
    public static WebElement password;
    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'col-lg-6')]/ul/li)[1]")
    public static WebElement lowerCase;
    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'col-lg-6')]/ul/li)[2]")
    public static WebElement upperCase;
    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'col-lg-6')]/ul/li)[3]")
    public static WebElement oneNumber;
    @FindBy(how = How.XPATH, using = "((//*[contains(@class,'col-lg-6')]/ul)[2]/li)[1]")
    public static WebElement symbols;
    @FindBy(how = How.XPATH, using = "((//*[contains(@class,'col-lg-6')]/ul)[2]/li)[2]")
    public static WebElement length;
    @FindBy(how = How.ID, using = "checkbox")
    public static WebElement toAgree;
    @FindBy(how = How.CLASS_NAME, using = "submit")
    public static WebElement submit;
    @FindBy(how = How.XPATH, using = "//*[@class='not_you']/a")
    public static WebElement inviteUser;
    @FindBy(how = How.NAME, using = "signup_name")
    public static WebElement inviteUserName;
    @FindBy(how = How.NAME, using = "signup_email")
    public static WebElement inviteEmail;
    @FindBy(how = How.ID, using = "send_sign_up_invite")
    public static WebElement requestInvite;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'alert alert-success')]/p")
    public static WebElement sucessInvite;
    
    @FindBy(how = How.XPATH, using = "//*[text()='Sign In - SSO']")
    public static WebElement ssoPage;
    
    
  
    
    
    
    
    //favorite Reports
    @FindBy(how=How.XPATH,using="(//div[@class=\"tab-meta \"])[1]/child::div[contains(@class,'new-product-card ')]")
    public static List<WebElement> favoritetabReports;
    @FindBy(how=How.XPATH,using="//span[@title='Add to Favorites']")
    public static List<WebElement> fabIcons;
    
    @FindBy(how=How.XPATH,using="//*[@class='fa fa-heart active']")
    public static WebElement favoriteActivePathIcons;
    
    @FindBy(how=How.XPATH,using="(//*[@class=\"filter-ico\"]/ul/li)")
    public static List<WebElement> homeTabIcons;
    @FindBy(how = How.ID, using = "show-hidden-menu")
    public static WebElement filterIcon;

    //Cart icon
    @FindBy(how=How.XPATH,using="(//*[@title='Go to cart'])[1]")
    public static WebElement goToIcon;
    @FindBy(how=How.XPATH,using="//*[@class='repname']/child::span")
    public static List<WebElement> cartTabReports;
    @FindBy(how=How.XPATH,using="//*[@class='cartdelete']")
    public static List<WebElement> cartRemoveLink;
    
    //view eyeicon report text 
    @FindBy(how=How.XPATH,using="//*[contains(text(),' Below are some of the key')]")
    public static WebElement passageText;
    @FindBy(how=How.XPATH,using="(//*[@class='new-product-icons']/following::div/div/following::div/a)[1]")
    public static List<WebElement> allViewAbstract;
    @FindBy(how=How.XPATH,using="(//div[@class='tab-meta'])[2]/child::div[contains(@class,'new-product-card ')][2]")
    public static WebElement scrollToReport;
    
    //home Icon
    @FindBy(how=How.XPATH,using="//*[text()='Home']")
    public static WebElement goToHomePage;
    @FindBy(how=How.XPATH,using="//*[@id='subscriptionTop']/a")
    public static WebElement upgrade;
    
    //upgrade plan
    @FindBy(how=How.XPATH,using="(//*[contains(@class,'btn-st card_btn')]/span)[2]")
    public static WebElement lite;
    @FindBy(how=How.XPATH,using="(//*[contains(@class,'btn-st card_btn')]/span)[3]")
    public static WebElement basic;
    @FindBy(how=How.XPATH,using="(//*[contains(@class,'btn-st card_btn')]/span)[4]")
    public static WebElement teams;
    
    @FindBy(how=How.XPATH,using="//*[@id='cover-spin']")
    public static WebElement loader;
    @FindBy(how=How.XPATH,using="//*[text()='Lite']")
    public static WebElement litePlan;
    @FindBy(how=How.XPATH,using="//*[text()='Basic']")
    public static WebElement basicPlan;
    @FindBy(how=How.XPATH,using="//*[text()='Team']")
    public static WebElement teamsPlan;
    
    
    
    
    
    
    //Report Price
    @FindBy(how=How.XPATH,using="(//div[@class='report_justify'])/span")
    public static WebElement costOfReport;
    @FindBy(how=How.XPATH,using="(//*[text()='Read Sample']/following::a)[1]")
    public static WebElement goToCart;
    
    //check Out Cart Page
    @FindBy(how=How.ID,using="billing_address")
    public static WebElement Address;
    @FindBy(how=How.ID,using="billing_city")
    public static WebElement city;
    
    
    @FindBy(how=How.XPATH,using="(//*[@type='radio'])[3]")
    public static WebElement proceedRadioIcon;
    @FindBy(how=How.XPATH,using="(//*[@type='radio'])[4]")
    public static WebElement userSubscription;
    
    @FindBy(how=How.XPATH,using="(//*[@id='billing_country'])[1]")
    public static WebElement DropDown;
    
    //Collecting 254 countries
    @FindBy(how=How.XPATH,using="(//*[@id='billing_country'])[1]/option")
    public static List<WebElement> countries;
    @FindBy(how=How.ID,using="billing_province")
    public static WebElement provinceDropDown;
    
    //Collecting 16 countriesProvince
    @FindBy(how=How.XPATH,using="//*[@id='billing_province']/option")
    public static List<WebElement> countriesProvince;
    
    @FindBy(how=How.XPATH,using="//*[contains(@class,'step_2_payment')]")
    public static WebElement ContinueStep2;
    @FindBy(how=How.XPATH,using="//*[contains(@class,'step_1_payment')]")
    public static WebElement ContinueStep1;
    
    
    
    @FindBy(how=How.XPATH,using="//*[@placeholder='Enter your full name']")
    public static WebElement CardName;
    
    @FindBy(how=How.ID,using="cc-number")
    public static WebElement cardNumber;
    
    @FindBy(how=How.ID,using="cc-cvv")
    public static WebElement cardCvv;
    
    @FindBy(how=How.ID,using="cc-month")
    public static WebElement cardMonth;
    @FindBy(how=How.XPATH,using="//*[@id='cc-month']/option")
    public static List<WebElement> cardMonthOptions;
    
    @FindBy(how=How.ID,using="cc-year")
    public static WebElement cardYear;
    @FindBy(how=How.XPATH,using="//*[@id='cc-year']/option")
    public static List<WebElement> cardYearOptions;
    
    @FindBy(how=How.XPATH,using="(//*[@type='checkbox'])[2]")
    public static WebElement agreeCheckBox;
    
    @FindBy(how=How.XPATH,using="(//*[text()='My Account Details'])[1]")
    public static WebElement myAccountDetails;
    
    @FindBy(how=How.XPATH,using="//*[text()=' Subscription History ']")
    public static WebElement subscriptionHistory;
    @FindBy(how=How.XPATH,using="//*[text()=' SKU Purchase History ']")
    public static WebElement purchaseHistory;
    
    @FindBy(how=How.XPATH,using="//*[contains(@class,'ti-menu-alt')]")
    public static WebElement menuBar;
    
    
    @FindBy(how=How.ID,using="button-confirm")
    public static WebElement checkOut;
    
    @FindBy(how=How.XPATH,using="//*[@class='search-found']/span")
    public static WebElement thankYouMessage;
    @FindBy(how=How.XPATH,using="//*[@class='col-md-8']/h3")
    public static WebElement successfullPaymentReportName;
    
    
    @FindBy(how=How.XPATH,using="(//*[@role='row'])[2]")
    public static List<WebElement> subcriptionHistoryList;
@FindBy(how=How.XPATH,using="(((//*[@role='grid']))[1])/child::tbody/tr/td[2]")
    public static List<WebElement> skuHistoryList;
    
    @FindBy(how=How.ID,using="subscriptionTop")
    public static WebElement upgradeButton;
    
    //Billing Section page
    @FindBy(how=How.XPATH,using="//*[contains(@id,'address_error')]/span")
    public static WebElement addressErrorMsg;
    @FindBy(how=How.XPATH,using="//*[contains(@id,'city_error')]/span")
    public static WebElement cityErrorMsg;
    @FindBy(how=How.XPATH,using="//*[contains(@id,'country_error')]/span")
    public static WebElement countryErrorMsg;
    @FindBy(how=How.XPATH,using="//*[@class='col-sm-12']/p")
    public static WebElement proceedToChooseErrorMsg;
    
    @FindBy(how=How.XPATH,using="(//*[@role='alert'])[3]/child::span")
    public static WebElement offlineProceedToChooseErrorMsg;
    @FindBy(how=How.XPATH,using="(//*[@type='radio'])[2]")
    public static WebElement proceedToChoose;
    @FindBy(how=How.XPATH,using="(//*[@type='checkbox'])[3]")
    public static WebElement agreeToChoose;
    
    
    
    @FindBy(how=How.XPATH,using="//*[@class='btn-st grn-clr buy-now pull-right']")
    public static WebElement proceedToBuy;
    @FindBy(how=How.ID,using="address1")
    public static WebElement addressBox1;
    @FindBy(how=How.ID,using="address2")
    public static WebElement addressBox2;
    
    
    
    
    
    
    
    
    
    
    //Payment Section page
    @FindBy(how=How.XPATH,using="(((//*[@id='payment-errors'])[1]/following::*/i)/following-sibling::*)[1]")
    public static WebElement paymentErrorMsg;
    @FindBy(how=How.XPATH,using="//*[text()=' Purchase history']")
    public static WebElement paymentHistoryText;
    @FindBy(how=How.XPATH,using="(//*[@class='success-msg'])/child::a")
    public static WebElement paymentHistoryClassName;
    
    
    
    
    
    
    
    
    //Popup Error Message for reports
    @FindBy(how = How.XPATH, using = "//*[text()='Report is added to Favorite']")
    public static WebElement favoriteIconadded;
    @FindBy(how = How.XPATH, using = "//*[text()='Report is removed from Favorites']")
    public static WebElement favoriteIconremoved;
    @FindBy(how = How.XPATH, using = "//*[text()='Product is added to Cart.']")
    public static WebElement productAddedcart;
    @FindBy(how = How.XPATH, using = "//*[text()=\"Report is removed from Favorites\"]")
    public static WebElement productremovedcart;
    @FindBy(how = How.XPATH, using =  "//*[contains(text(),'Report ')]")
    public static WebElement favoritErrorMsg;
    @FindBy(how = How.XPATH, using = "(//*[@class='loading-inc d-none']/following::p)[1]")
    public static WebElement quickViewText;
}
