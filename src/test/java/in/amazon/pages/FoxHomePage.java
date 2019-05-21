package in.amazon.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;
import commonLibs.implementation.MouseControl;
import in.amazon.utils.DataDriven;

public class FoxHomePage {
	
	@FindBy(xpath ="//button[@aria-label='sign up or login to profile']")
	private WebElement ProfileSignIn;
	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div/div[4]/button[1]")
	private WebElement SignUp;
	@FindBy(xpath ="//input[@name='signupFirstName']")
	private WebElement FName;
	@FindBy(xpath = "//input[@name='signupLastName']")
	private WebElement LName;
	@FindBy(xpath="//input[@name='signupEmail']")
	private WebElement Email;
	@FindBy(xpath="//input[@name='signupPassword']")
	private WebElement Password;
	@FindBy(xpath ="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/form/div[7]/div[1]/div/div[1]/div/a")
	private WebElement Gender;
	@FindBy(xpath = "//*[@data-test='close-icon']")
	private WebElement CloseIcon;
	@FindBy(xpath="/html/body/div[1]/div/header/div/div/div[2]/div/div/div[2]/div/a[1]")
	private WebElement HomeLink;
	@FindBy(xpath="//div[@class='slick-slider slick-initialized']/div/div/div")
	private List<WebElement> allShows;

	MouseControl mouseControl;
	ElementControl elementControl;
	DropdownControl dropdownControl;
	
	public FoxHomePage(WebDriver driver) throws Exception {
		PageFactory.initElements(driver, this);
		elementControl = new ElementControl();
		mouseControl = new MouseControl(driver);
		dropdownControl = new DropdownControl();
		
	}
		
	
	public void ProfileSignUp() throws Exception{
		elementControl.clickElement(ProfileSignIn);
		elementControl.clickElement(SignUp);
		elementControl.setText(FName, "Vine");
		elementControl.setText(LName, "Baskaran");
		elementControl.setText(Email, "vinemerlin@gmail.com");
		elementControl.setText(Password, "password");
		//elementControl.clickElement(Gender);
		elementControl.clickElement(CloseIcon);
		
	}
	
	public void HomeLinkShows() throws Exception {
		
		elementControl.clickElement(HomeLink);
		System.out.println("Total no of shows are : " + allShows.size());
		DataDriven dataDriven = new DataDriven();
		String sheetname = "ShowsOnFox";
		String filename ="C:/Users/Vine/workspace/MavenExample1/inputFiles/ShowsOnFox.xlsx";
		dataDriven.createWorkbook(filename);
		dataDriven.openWorkbook(filename);
		dataDriven.createSheet(sheetname);
		for (int i = 0; i < allShows.size(); i++) {

			mouseControl.moveToElement(allShows.get(i));
			System.out.println("..................................");
			String result =elementControl.getText(allShows.get(i));
		    System.out.println(elementControl.getText(allShows.get(i)));
			dataDriven.setCellData(sheetname , i, 0, result);
		}
		dataDriven.saveFile();
		dataDriven.closeWorkbook();

		
		
	}
	
	
		
		//dropdownControl.selectViaVisibleText(selectCatergory, category);
	

}
