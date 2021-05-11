package apitestcases;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.freemium.base.Constant;
import com.freemium.base.RestBaseClass;
import com.freemium.base.RestConstans;
import com.freemium.utilities.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_POST_Request extends RestBaseClass {
	String sTestCaseName;
	int iTestCaseRow;
	RequestSpecification httpRequest;
	Response response;
	// RequestSpecification httpRequst;

	@BeforeClass
	void signUp() throws Exception {
		sTestCaseName = this.toString();
		sTestCaseName = getTestCaseName(this.toString());
		extentTest = onStart().startTest("Post Request ");
		RestAssured.baseURI = getURI();
		httpRequest = RestAssured.given();

	}

	@AfterMethod
	public void endGeneareReport(ITestResult iTestResult) throws IOException {
		try {

			endReport(iTestResult);
			extent.endTest(extentTest);

		} catch (IOException e) {
			Assert.fail();
		}
	}

	@SuppressWarnings("unused")
	@Test
	void validationOfPostRequest() throws Exception {
		// extentTest = extent.startTest("validationForStatusCode");

		ExcelReader.setExcelFile(getExcelPath() + getTestDataFileName(), "PostData");
		iTestCaseRow = ExcelReader.getRowContains(sTestCaseName, RestConstans.Col_TestCaseName);
		String UserName = ExcelReader.getCellData(1, RestConstans.Col_UserName);
		String Email = ExcelReader.getCellData(1, RestConstans.Col_Email);
		String password = ExcelReader.getCellData(1, RestConstans.Col_Password);
		String password1 = ExcelReader.getCellData(1, RestConstans.Col_Password1);
		String fname = ExcelReader.getCellData(1, RestConstans.Col_fname);
		String fnameq = ExcelReader.getCellData(1, RestConstans.Col_fname1);

		JSONObject requestParams = new JSONObject();
		requestParams.put(UserName, Email);
		requestParams.put(password, password1);
		requestParams.put(fname, fnameq);

		// attach data to the request
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.POST, RestConstans.signup);
		Thread.sleep(2000);
		String responseBody = response.getBody().asPrettyString();
		logger.info("Response Body  ::  " + responseBody);
		
		// String responseEmail = response.jsonPath().get("email");
		try {
			boolean responseData = response.jsonPath().get("error");
			if (responseData == false) {
				String responseEmail = response.jsonPath().get("email");
				logger.info("Response Body Email  ::  " + responseEmail);
				Assert.assertEquals(response.jsonPath().get("email"), responseEmail);
				extentTest.log(LogStatus.PASS, "New Email is registered  :: " + responseEmail);

			} else if (responseData == true) {
				int customerID = response.jsonPath().get("customerid");
				logger.info("Response Customer ID  ::  " + customerID);
				extentTest.log(LogStatus.PASS, "Customer ID  :: " + customerID);
				LinkedHashMap responseDatas = response.jsonPath().get("error_text");
				logger.info("Email is alredy registered :: " + responseDatas);
				extentTest.log(LogStatus.PASS, "Email is alredy registered :: " + responseDatas);
			} else {
				Assert.fail();
			}
		} catch (Exception e) {
			// TODO: handle exception
			Assert.fail();
		}

		// ResponseValidation();
		statusCode();
		checkContentType();
		checkContentEncoding();
		checkTimeOut();

	}

	private void collectAllHeaders() {
		// collecting all headers
		Headers getAllHeaders = response.headers();
		for (Header getHeaders : getAllHeaders) {
			logger.info(getHeaders.getName() + "   " + getHeaders.getValue());
		}
	}

	void ResponseValidation() throws Exception {

		try {
			boolean responseData = response.jsonPath().get("error");
			logger.info("Response Body  ::  " + responseData);
			if (responseData == false) {
				Assert.assertEquals(false, responseData);
				logger.info("New Email is registered" + responseData);
				extentTest.log(LogStatus.PASS, "New Email is registered  :: " + responseData);
			} else if (responseData == true) {
				Assert.assertEquals(true, responseData);
				logger.info("Email is alredy registered :: " + responseData);
				extentTest.log(LogStatus.PASS, "Email is alredy registered :: " + responseData);
			} else {
				Assert.fail();
				extentTest.log(LogStatus.FAIL, "Response Validation Fail" + responseData);
			}
		} catch (Exception e) {
			Assert.fail();
			extentTest.log(LogStatus.FAIL, "Response Validation Fail");
		}
		// extent.endTest(extentTest);

	}

	void statusCode() throws Exception {
		// extentTest = extent.startTest("validationForStatusCode");
		int statusCode = 0;
		try {
			statusCode = response.getStatusCode();
			if (statusCode == 200) {
				logger.info("Status Code Matched :: " + statusCode);
				extentTest.log(LogStatus.INFO, "Status Code Matched:: " + statusCode);
			} else {
				Assert.assertEquals(200, statusCode);
				logger.info("Status Code Not Matched  ::  " + statusCode);
				extentTest.log(LogStatus.FAIL, "Status Code Not Matched  ::  " + statusCode);
			}
		} catch (Exception e) {
			Assert.assertEquals(200, statusCode);
			logger.info("Status Code Not Matched  ::  " + statusCode);
			extentTest.log(LogStatus.FAIL, "Status Code Not Matched  ::  " + statusCode);
		}
		// extent.endTest(extentTest);

	}

	void checkContentType() throws Exception {
		// extentTest = extent.startTest("validationForContentType");
		String contentType = contentType = response.header("Content-Type");

		try {
			if (contentType != null) {
				// capture deatils Headers from Response
				logger.info("Content-Type Matched  ::" + contentType);
				extentTest.log(LogStatus.INFO, "Content-Type Matched ::" + contentType);
			} else {
				Assert.assertEquals(contentType, "application/json");
				logger.info("Content-Type Not Matched  ::  " + contentType);
				extentTest.log(LogStatus.FAIL, "Content-Type Not Matched  ::  " + contentType);
			}

		} catch (Exception e) {
			Assert.assertEquals(contentType, "application/json");
			logger.info("Content-Type Not Matched  ::  " + contentType);
			extentTest.log(LogStatus.FAIL, "Content-Type Not Matched  ::  " + contentType);
		}

	}

	void checkContentEncoding() throws Exception {
		// extentTest = extent.startTest("validationForContentEncoding");
		String contentEncoding = contentEncoding = response.header("Content-Encoding");
		;
		try {
			if (contentEncoding != null) {
				// capture deatils Headers from Response
				logger.info("Content Encoding Matched  ::" + contentEncoding);
				extentTest.log(LogStatus.INFO, "Content Encoding Matched ::" + contentEncoding);
			} else {
				Assert.assertEquals(contentEncoding, "gzip");
				logger.info("Content Encoding not Matched  ::" + contentEncoding);
				extentTest.log(LogStatus.FAIL, "Content-Encoding Not Matched  ::  " + contentEncoding);
			}
		} catch (Exception e) {
			Assert.assertEquals(contentEncoding, "gzip");
			logger.info("Content-Encoding Not Matched  ::  " + contentEncoding);
			extentTest.log(LogStatus.FAIL, "Content-Encoding Not Matched  ::  " + contentEncoding);
		}

	}

	void checkContentLength() throws Exception {
		// extentTest = extent.startTest("validationForContentLength");
		String contentLength = response.header("Content-Length");
		;
		try {
			if (contentLength != null) {
				// capture deatils Headers from Response
				Assert.assertEquals(contentLength, "105");
				logger.info("Content Length Matched  ::" + contentLength);
				extentTest.log(LogStatus.INFO, "Content Length Matched  ::" + contentLength);
			} else {
				Assert.assertEquals(contentLength, "105");
				logger.info("Content Length not Matched  ::" + contentLength);
				extentTest.log(LogStatus.FAIL, "Content Length not Matched  ::" + contentLength);
			}
		} catch (Exception e) {
			Assert.assertEquals(contentLength, "105");
			logger.info("Content Length not Matched  ::" + contentLength);
			extentTest.log(LogStatus.FAIL, "Content Length not Matched  ::" + contentLength);
		}

	}

	void checkTimeOut() throws Exception {
		// extentTest = extent.startTest("validationForContentLength");
		String timeout = response.header("Keep-Alive");
		;
		try {
			if (timeout != null) {
				// capture deatils Headers from Response
				Assert.assertEquals(timeout, "timeout=5, max=100");
				logger.info("timeout Matched  ::" + timeout);
				extentTest.log(LogStatus.INFO, "timeout Matched  ::" + timeout);
			} else {
				Assert.assertEquals(timeout, "timeout=5, max=100");
				logger.info("timeout not Matched  ::" + timeout);
				extentTest.log(LogStatus.FAIL, "timeout not Matched  ::" + timeout);
			}
		} catch (Exception e) {
			Assert.assertEquals(timeout, "timeout=5, max=100");
			logger.info("timeout not Matched  ::" + timeout);
			extentTest.log(LogStatus.FAIL, "timeout not Matched  ::" + timeout);
		}

	}

}
