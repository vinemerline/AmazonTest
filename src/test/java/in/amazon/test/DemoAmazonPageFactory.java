package in.amazon.test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;
import designPattern.AmazonPOM2Style;
import in.amazon.pages.AmazonHomePage;

public class DemoAmazonPageFactory {
	
	 CommonDriver cmnDriver;
	 String url = "https://www.amazon.in/";
	 AmazonHomePage homepage;
	 WebDriver driver;
	 
	 
	
	@BeforeClass
	public void invokeBrowser() throws Exception{
		cmnDriver = new CommonDriver("chrome");
		cmnDriver.setPageloadTimeout(30);
		cmnDriver.setElementDetectionTimeout(5);
		cmnDriver.navigateToFirstUrl(url);
		driver = cmnDriver.getDriver();
		homepage = new AmazonHomePage(driver);
		
	}
	
	@Test
	public void searchProduct() throws Exception {
	homepage.searchProduct("Apple watch", "Electronics");
	String result =homepage.getResult();
	System.out.println(result);
	homepage.GetAllProduct();
	
	}

	@AfterClass(enabled = false)
	public void closeBrowser() throws Exception{
	cmnDriver.closeAllBrowsers();
}
}
