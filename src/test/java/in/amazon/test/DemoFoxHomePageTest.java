package in.amazon.test;

import org.openqa.selenium.remote.server.handler.GetTitle;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonLibs.implementation.ElementControl;

//screenshot and config

public class DemoFoxHomePageTest extends FoxBaseTest {

	@Test(priority = 100)
	public void VerifyTitleOfThePage() throws Exception {
		String expectedTitle = "Stream and Watch Full Episodes of Your Favorite TV Shows Online | FOX";
		String actualTitle = cmnDriver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		
		
		
	}
	
	@Test(priority =1000)
	public void SignUp() throws Exception
	
	{
		homepage.ProfileSignUp();
		
	}
	
	@Test(priority=2000)
	public void Home() throws Exception
	{
		homepage.HomeLinkShows();
	}

	/*
	 * @Test(priority = 10000) public void searchProduct() throws Exception {
	 * extentTest =
	 * extentReport.createTest("TestCase-01: Search Product on Amazon"); String
	 * product = "Apple watch"; String category = "Electronics";
	 * extentTest.log(Status.INFO, "Product: " + product);
	 * homepage.searchProduct(product, category); String result =
	 * homepage.getResult(); extentTest.log(Status.INFO, "Result of the search : " +
	 * result);
	 */
	

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {

		String methodName = result.getName();
		if (result.getStatus() == ITestResult.FAILURE) {
			screenshotfilename = String.format("%s/screenshots/%s_%s.jpeg", CurrentworkingDirectory, methodName,
					executionStartTime);
			camera.captureAndSaveScreenshot(screenshotfilename);

			extentTest.log(Status.FAIL, "Test Step failed in method -  " + methodName);
			extentTest.addScreenCaptureFromPath(screenshotfilename);

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "All steps passed in method" + methodName);
		} else {
			extentTest.log(Status.SKIP, "All steps skipped in method" + methodName);
		}

	}

	@AfterClass
	public void closeBrowser() throws Exception {
		extentTest = extentReport.createTest("clean up: closing the browser");
		cmnDriver.closeAllBrowsers();
	}

	@AfterSuite

	public void postCleanup() {

		extentReport.flush();
	}

}
