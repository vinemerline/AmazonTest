package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.amazon.utils.TestDataReader;

public class DemoTestReader {
	
	

	ChromeDriver driver;
@BeforeClass
	public void invokeBrowser() {

	System.setProperty("webdriver.chrome.driver", "C:/Users/Vine/workspace/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();
		driver.get("http://demo.guru99.com/v4/");

	}


	@Test(dataProvider="getData",dataProviderClass = TestDataReader.class)
	public void login(String sUserId, String sPassword) {

		WebElement userId;

		userId = driver.findElement(By.name("uid"));
		userId.sendKeys(sUserId);

		driver.findElement(By.name("password")).sendKeys(sPassword);

		driver.findElement(By.name("btnLogin")).click();
	}

	
//	@Test(dataProvider="getData",dataProviderClass = TestDataReader.class)
//	public void printTestData(String userId, String password)
//	{
//		
//		System.out.println("User Id :" + userId);
//		System.out.println("Password :" + password);
//
//}
}
