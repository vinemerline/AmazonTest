package in.amazon.pages;

import java.util.List;

//stale element reference exception occurs we used the page model refractory.. it means the element no longer appears on the DOM of the page
//good for dynamic elements and much enhanced for cachefactory	
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;
import commonLibs.implementation.MouseControl;

public class AmazonHomePage1 {

	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBox;
	@FindBy(xpath = "//input[@value ='Go']")
	private WebElement searchButton;
	@FindBy(id = "searchDropdownBox")
	private WebElement selectCatergory;
	@FindBy(xpath = "//span[@data-component-type='s-result-info-bar']")
	private WebElement result;
	@FindBy(xpath = "//span[@data-component-type='s-search-results']/div[1]/div")
	private List<WebElement> allProduct;

	MouseControl mouseControl;
	ElementControl elementControl;
	DropdownControl dropdownControl;

	public AmazonHomePage1(WebDriver driver) {
		PageFactory.initElements(driver, this);
		elementControl = new ElementControl();
		mouseControl = new MouseControl(driver);
		dropdownControl = new DropdownControl();
	}

	public String getResult() throws Exception {

		return elementControl.getText(result);
	}

	public void searchProduct(String product, String category) throws Exception {

		elementControl.setText(searchBox, product);
		dropdownControl.selectViaVisibleText(selectCatergory, category);
		elementControl.clickElement(searchButton);

	}

	public void NthProduct() {

	}

	public void GetAllProduct() throws Exception {
		System.out.println("Total no of products are : " + allProduct.size());

		for (int i = 0; i < allProduct.size(); i++) {

			mouseControl.moveToElement(allProduct.get(i));
			System.out.println("..................................");
			System.out.println(elementControl.getText(allProduct.get(i)));

		}
		
	}

	}

