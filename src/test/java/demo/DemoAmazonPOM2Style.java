package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;
import designPattern.AmazonPOM2Style;







public class DemoAmazonPOM2Style {
	
	 CommonDriver cmnDriver;
	 String url = "https://www.amazon.in/";
//	 ElementControl elementControl;
//	 DropdownControl dropdownControl;
	 AmazonPOM2Style homepage;
	 WebDriver driver;
	 
	 
	
	@BeforeClass
	public void invokeBrowser() throws Exception{
		cmnDriver = new CommonDriver("chrome");
		cmnDriver.setPageloadTimeout(30);
		cmnDriver.setElementDetectionTimeout(5);
		cmnDriver.navigateToFirstUrl(url);
		driver = cmnDriver.getDriver();
//		elementControl = new ElementControl();
//		dropdownControl = new DropdownControl();
		homepage = new AmazonPOM2Style(driver);
		
	}
	
	@Test
	public void searchProduct() throws Exception {
	homepage.searchProduct("Apple watch", "Electronics");
	}

	@AfterClass(enabled = false)
	public void closeBrowser() throws Exception{
	cmnDriver.closeAllBrowsers();
}
}
