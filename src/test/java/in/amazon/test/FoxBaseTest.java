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
import in.amazon.pages.FoxHomePage;
import in.amazon.utils.ConfigReader;

//screenshot and config

public class FoxBaseTest {
	
	CommonDriver cmnDriver;
	String url;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReport;
	ExtentTest extentTest;
	FoxHomePage homepage;
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
		configPropertiesFilename = String.format("%s/config/FoxConfig.properties", CurrentworkingDirectory);
	}
	

	@BeforeSuite
	public void preSetup() throws Exception{
		initializeConfigProperties();
		initializeExtentReport();
	}
	private void initializeExtentReport() {
		extentReport= new ExtentReports(); //an object is created for the class ExtendReports
		reportName = String.format("/%s/reports/Fox_%s.html", CurrentworkingDirectory,executionStartTime);//path or location of the report is stored in rerpotName
		htmlReporter = new ExtentHtmlReporter(reportName);//an obj for extenthtmlreporter is created by passing the report location via constructor
		extentReport.attachReporter(htmlReporter);// html reporter is attached using attachreporter method from the extend report obj
		extentTest=extentReport.createTest("Pre-Setup:: Initialized Expent Report");//createTest is a method for creating test cases on the html report
		
	}
	private void initializeConfigProperties() throws Exception{
		configProperty = ConfigReader.readPropertiesFile(configPropertiesFilename);

	}	

	@BeforeClass
	public void invokeBrowser() throws Exception {
		extentTest = extentReport.createTest("Setup ::Browser invoker");
		String browserType = configProperty.getProperty("browserType");
		extentTest.log(Status.INFO, "Browser Selected : " + browserType);// log method is used for logging the status of the test in the html report
		cmnDriver = new CommonDriver(browserType); 
		int pageLoadTimeout = Integer.parseInt(configProperty.getProperty("pageLoadTimeout"));//the config properties file are all stored in string value. so get the value in integer format intparse is used

		extentTest.log(Status.INFO, "Page load timeout set is : " + pageLoadTimeout);
		cmnDriver.setPageloadTimeout(pageLoadTimeout);
		
		int elementDetectionTimeOut = Integer.parseInt(configProperty.getProperty("elementDetectionTime"));
		extentTest.log(Status.INFO, "Implicit wait is : " + elementDetectionTimeOut);
		cmnDriver.setElementDetectionTimeout(elementDetectionTimeOut);
		
		url= configProperty.getProperty("baseUrl");
		extentTest.log(Status.INFO, "URL : " + url);
		cmnDriver.navigateToFirstUrl(url);
		driver= cmnDriver.getDriver();
		camera= new ScreenshotControl(driver);//
		homepage= new FoxHomePage(driver);
	

		
	}

}
