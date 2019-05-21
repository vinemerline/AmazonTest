package in.amazon.test;

import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.ScreenshotControl;
import in.amazon.pages.AmazonHomePage;
import in.amazon.utils.ConfigReader;

//screenshot and config

public class BaseTest {
	
	CommonDriver cmnDriver;
	String url;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReport;
	ExtentTest extentTest;
	AmazonHomePage homepage;
	WebDriver driver;
	static String CurrentworkingDirectory;
	static String configPropertiesFilename;
	static String executionStartTime;
	String reportName;
	static String configPropertiesName;
	static Properties configProperty;
	static String screenshotfilename;
	static  ScreenshotControl camera;

	static {
		CurrentworkingDirectory = System.getProperty("user.dir");
		Date date = new Date();
		executionStartTime = String.valueOf(date.getTime());
		configPropertiesFilename = String.format("%s/config/amazonConfig.properties", CurrentworkingDirectory);
	}
	

	@BeforeSuite
	public void preSetup() throws Exception{
		initializeConfigProperties();
		initializeExtentReport();
	}
	private void initializeExtentReport() {
		extentReport= new ExtentReports();
		reportName = String.format("/%s/reports/amazon_%s.html", CurrentworkingDirectory,executionStartTime);
		htmlReporter = new ExtentHtmlReporter(reportName);
		extentReport.attachReporter(htmlReporter);
		extentTest=extentReport.createTest("Pre-Setup:: Initialized Expent Report");
		
	}
	private void initializeConfigProperties() throws Exception{
		configProperty = ConfigReader.readPropertiesFile(configPropertiesFilename);

	}	

	@BeforeClass
	public void invokeBrowser() throws Exception {
		extentTest = extentReport.createTest("Setup ::Browser invoker");
		String browserType = configProperty.getProperty("browserType");
		extentTest.log(Status.INFO, "Browser Selected : " + browserType);
		cmnDriver = new CommonDriver(browserType); 
		int pageLoadTimeout = Integer.parseInt(configProperty.getProperty("pageLoadTimeout"));

		extentTest.log(Status.INFO, "Page load timeout set is : " + pageLoadTimeout);
		cmnDriver.setPageloadTimeout(pageLoadTimeout);
		
		int elementDetectionTimeOut = Integer.parseInt(configProperty.getProperty("elementDetectionTime"));
		extentTest.log(Status.INFO, "Implicit wait is : " + elementDetectionTimeOut);
		cmnDriver.setElementDetectionTimeout(elementDetectionTimeOut);
		
		url= configProperty.getProperty("baseUrl");
		extentTest.log(Status.INFO, "URL : " + url);
		cmnDriver.navigateToFirstUrl(url);
		driver= cmnDriver.getDriver();
		camera= new ScreenshotControl(driver);
		homepage= new AmazonHomePage(driver);
	

		
	}

}
