package commonLibs.implementation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import commonLibs.contract.IDriver;

public class CommonDriver implements IDriver{

	
	private WebDriver driver;
	private int pageloadTimeout;
	private int elementDetectionTimeout;
	
	

	public WebDriver getDriver()
	{
		return driver;
	}
	
	public void setPageloadTimeout(int pageloadTimeout) {
		this.pageloadTimeout = pageloadTimeout;
	}
	
	
	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}
	
	
	public CommonDriver(String browserType) throws Exception
	{
		pageloadTimeout= 20;
		elementDetectionTimeout=10;
		browserType=browserType.trim();
		
		if(browserType.equalsIgnoreCase("chrome")) {
			
		
			System.setProperty("webdriver.chrome.driver", "C:/Users/Vine/workspace/chromedriver_win32/chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserType.equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:/Users/Vine/Downloads/MicrosoftWebDriver.exe");// setting the path at system level ..path of the exe file 
			driver= new EdgeDriver();
		}
		else if(browserType.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C://Users/Vine/Downloads/geckodriver-v0.24.0-win32/geckodriver.exe");// setting the path at system level ..path of the exe file 
			driver =new FirefoxDriver();
		}
		else {
			throw new Exception("invalid browser");
		}
	}
	

	@Override
	public void navigateToFirstUrl(String url) throws Exception {
		url = url.trim();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(pageloadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
		driver.get(url);
	}

	

	@Override
	public String getTitle() throws Exception {
		return driver.getTitle();
	}

	@Override
	public String getCurrentUrl() throws Exception {
		return driver.getCurrentUrl();
	}

	@Override
	public String getPageSource() throws Exception {
		return driver.getPageSource();
	}

	@Override
	public void navigateToUrl(String url) throws Exception {
    url = url.trim();
    driver.navigate().to(url);
	}

	@Override
	public void navigateForward() throws Exception {
		driver.navigate().forward();
	}

	@Override
	public void navigateBackward() throws Exception {
		driver.navigate().back();
	}

	@Override
	public void refresh() throws Exception {
		driver.navigate().refresh();
	}

	@Override
	public void closeBrowser() throws Exception {
		if(driver != null){
			driver.close();
		}
		
	}

	@Override
	public void closeAllBrowsers() throws Exception {
     if(driver!=null) {
    	 driver.quit();
     }
	}
}
