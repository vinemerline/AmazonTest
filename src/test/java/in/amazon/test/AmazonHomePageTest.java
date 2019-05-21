package in.amazon.test;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import commonLibs.implementation.CommonDriver;
import in.amazon.pages.AmazonHomePage;

public class AmazonHomePageTest {

	CommonDriver cmnDriver;
	String url = "https://www.amazon.in/";
	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReport;
	ExtentTest extentTest;
	AmazonHomePage homepage;
	WebDriver driver;
	static String CurrentworkingDirectory;
	static String executionStartTime;
	String reportName;

	static {
		CurrentworkingDirectory = System.getProperty("user.dir");
		Date date = new Date();
		executionStartTime = String.valueOf(date.getTime());
	}

	@BeforeSuite
	public void preSetup() {
		extentReport = new ExtentReports();
		reportName = String.format("%s/reports/amazon_%s.html", CurrentworkingDirectory, executionStartTime);
		htmlReporter = new ExtentHtmlReporter(reportName);
		extentReport.attachReporter(htmlReporter);
		extentTest = extentReport.createTest("Pre-Setup ::Initialized extend Report");
	}

	@BeforeClass
	public void invokeBrowser() throws Exception {
		extentTest = extentReport.createTest("Setup ::Browser invoker");
		String browserType = "chrome";
		extentTest.log(Status.INFO, "Browser Selected : " + browserType);
		cmnDriver = new CommonDriver(browserType);

		int pageLoadTimeout = 30;
		extentTest.log(Status.INFO, "Page load timeout set is : " + pageLoadTimeout);
		cmnDriver.setPageloadTimeout(pageLoadTimeout);

		int elementDetectionTimeOut = 20;
		extentTest.log(Status.INFO, "Implicit wait is : " + elementDetectionTimeOut);
		cmnDriver.setElementDetectionTimeout(elementDetectionTimeOut);

		extentTest.log(Status.INFO, "URL : " + url);
		cmnDriver.navigateToFirstUrl(url);

		driver = cmnDriver.getDriver();
		homepage = new AmazonHomePage(driver);

	}

	@Test(priority = 100)
	public void VerifyTitleOfThePage() throws Exception {
		String expectedTitle = "Amazon Home Page";
		String actualTitle = cmnDriver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 1000)
	public void searchProduct() throws Exception {
		extentTest = extentReport.createTest("TestCase-01: Search Product on Amazon");
		String product = "Apple watch";
		String category = "Electronics";
		extentTest.log(Status.INFO, "Product: " + product);
		homepage.searchProduct(product, category);
		String result = homepage.getResult();
		extentTest.log(Status.INFO, "Result of the search : " + result);

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		String methodName = result.getName();
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, "Test Step failed in method -  " + methodName);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "All steps passed in method" + methodName);
		} else {
			extentTest.log(Status.SKIP, "All steps skipped in method" + methodName);
		}

	}

	@AfterClass(enabled = false)
	public void closeBrowser() throws Exception {
		extentTest = extentReport.createTest("clean up: closing the browser");
		cmnDriver.closeAllBrowsers();
	}

	@AfterSuite

	public void postCleanup() {

		extentReport.flush();
	}

}
