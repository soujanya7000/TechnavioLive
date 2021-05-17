package com.freemium.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.freemium.base.Constant;
import com.freemium.pagelocators.PageLocators;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Utilities {
	public static WebDriver driver;
	public static Logger logger;
	public static WebDriverWait wait;
	public static FluentWait fluentWait;
	public Properties properties;
	public ExtentReports extent;
	public ExtentTest extentTest;

	private final String propertyFilePath = (System.getProperty("user.dir")
			+ "\\src\\test\\resources\\properties\\automationrepository.properties");

	public Utilities() {
		PropertyConfigurator.configure("Log4j.properties");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("automationrepository.properties not found at " + propertyFilePath);
		}

	}

	public static String captureScreen(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots\\" + screenshotName
				+ dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

		logger.info("destination : " + destination);
		logger.info("Screen Shot Taken " + screenshotName);

		return destination;
	}

	public WebDriver OpenBrowser() throws Exception {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ChromeOptions coptions = new ChromeOptions();
			coptions.merge(dc);
			String browserName = getBrowserName();
			if (browserName.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + Constant.GeckoDriver_path);
				driver = new FirefoxDriver();
				logger.debug("Launching FireFox");
			} else if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + Constant.ChromeDriver_path);

				driver = new ChromeDriver(coptions);
				logger.debug("Launching chrome");
			} else if (browserName.equals("edge")) {
				System.setProperty("webdriver.Edge.driver", System.getProperty("user.dir") + Constant.EdgeDriver_path);

				driver = new EdgeDriver();
				logger.debug("Launching edge");
			}

			/*
			 * driver.get(properties.getProperty("freemiumDevUrl"));
			 * logger.info("Url Launched :" + properties.getProperty("freemiumDevUrl"));
			 */
			driver.manage().window().maximize();

		} catch (Exception e) {
			logger.error("Class Utils | Method OpenBrowser | Exception desc : " + e.getMessage());
		}
		return driver;
	}

	public void waitState() {
		wait = new WebDriverWait(driver, 30);
	}

	public void fluentWaitState() {
		fluentWait = new FluentWait(driver);
		fluentWait.pollingEvery(1, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(30, TimeUnit.SECONDS);
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void navigateForward() {
		driver.navigate().forward();
	}

	public void scrollToElement() {
		WebElement element = PageLocators.scrollToReport;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToElementContinueStep2() {
		WebElement element = PageLocators.ContinueStep2;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToElementContinueStep1() {
		WebElement element = PageLocators.ContinueStep1;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToProceedElement() {
		WebElement element = PageLocators.proceedToChoose;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void pwdJavaScript() {
		//WebElement pwd=PageLocators.pwid;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('password').setAttribute('value', 'Infi@123')");
		//jse.executeScript("arguments[0].'value'='Infi@123';",pwd);
	}
	public void clickJavaScript() {
		WebElement click=PageLocators.pwdnext;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();",click);
	}
	public void buttonToClick(WebElement buttons) {
		JavascriptExecutor java =(JavascriptExecutor) driver;
		java.executeScript("arguments[0].click();",buttons);
		}
	public void sendkeys(WebElement sendkeys) {
		JavascriptExecutor java =(JavascriptExecutor) driver;
		java.executeScript("arguments[0].value;",sendkeys);
		}
	public void actions() {
		Actions action = new Actions(driver);
	}
	public void javaClickGoToIcon() {
		WebElement ClickonIcon= PageLocators.goToIcon;
		JavascriptExecutor java =(JavascriptExecutor) driver;
		java.executeScript("arguments[0].click();",ClickonIcon);
		}
	public void javaClickOnUpgrade() {
		WebElement ClickonUpgrade=PageLocators.upgrade;
		JavascriptExecutor java =(JavascriptExecutor) driver;
		java.executeScript("arguments[0].click();",ClickonUpgrade);
		}

	public static void hardRefresh() throws Exception {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_SHIFT);
		r.keyPress(KeyEvent.VK_R);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_SHIFT);
		r.keyRelease(KeyEvent.VK_R);
	}

	public static String getTestCaseName(String sTestCase) throws Exception {
		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			throw (e);
		}
	}

	// Reports
	public ExtentReports onStart() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String reportName = "Technavio_ExtentReport-" + timeStamp + ".html";
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/" + reportName, true);
		extent.addSystemInfo("Host Name", "Technavio Live Phalcon");
		extent.addSystemInfo("User Name", "QA Automation");
		extent.addSystemInfo("Environment", "Test server");
		return extent;
	}

	protected void endReport(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {

			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
			String screenshotPath = captureScreen(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to add screenshot in extent
																							// report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}

		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

	}

	// Properties Read

	public String getBrowserName() {
		String browserName = properties.getProperty("browserName");
		if (browserName != null)
			return browserName;
		else
			throw new RuntimeException("browser name not specified in the automationrepository.properties file.");
	}

	public String getExcelPath() {
		String excelPath = properties.getProperty("freemiumExcelPath");
		if (excelPath != null)
			return excelPath;
		else
			throw new RuntimeException("browser name not specified in the automationrepository.properties file.");
	}

	public String getSiteUrl() {
		String freemiumSiteUrl = properties.getProperty("freemiumDevUrl");
		if (freemiumSiteUrl != null)
			return freemiumSiteUrl;
		else
			throw new RuntimeException(" Freemium Url not specified in the automationrepository.properties file.");
	}

	public String getTestDataFileName() {
		String freemiumTestDataFileName = properties.getProperty("freemiumTestDataFileName");
		if (freemiumTestDataFileName != null)
			return freemiumTestDataFileName;
		else
			throw new RuntimeException(
					" freemiumTestDataFileName not specified in the automationrepository.properties file.");
	}

	public String getFreemiumUserName() {
		String freemiumUserName = properties.getProperty("userName");
		if (freemiumUserName != null)
			return freemiumUserName;
		else
			throw new RuntimeException(" freemium userName not specified in the automationrepository.properties file.");
	}

	public String getFremiumPassword() {
		String freemiumPassword = properties.getProperty("password");
		if (freemiumPassword != null)
			return freemiumPassword;
		else
			throw new RuntimeException(" freemium password not specified in the automationrepository.properties file.");
	}

	public String getFremiumName() {
		String name = properties.getProperty("Name");
		if (name != null)
			return name;
		else
			throw new RuntimeException(" freemium Name not specified in the automationrepository.properties file.");
	}

	public String getFremiumEmail() {
		String email = properties.getProperty("email");
		if (email != null)
			return email;
		else
			throw new RuntimeException(" freemium Email not specified in the automationrepository.properties file.");
	}

	public String getFremiumPasswordField() {
		String passwordField = properties.getProperty("passwordField");
		if (passwordField != null)
			return passwordField;
		else
			throw new RuntimeException(" freemium password not specified in the automationrepository.properties file.");
	}

	public String getFremiuminviteUserName() {
		String passwordField = properties.getProperty("inviteUserName");
		if (passwordField != null)
			return passwordField;
		else
			throw new RuntimeException(
					" freemium invite UserName not specified in the automationrepository.properties file.");
	}

	public String getFremiuminviteEmail() {
		String passwordField = properties.getProperty("inviteEmail");
		if (passwordField != null)
			return passwordField;
		else
			throw new RuntimeException(
					" freemium invite Email not specified in the automationrepository.properties file.");
	}

}
