package designPattern;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class AmazonPOM2Style {
		public WebElement searchBox;
		public WebElement searchButton;
		public WebElement selectCatergory;
		
		ElementControl elementControl;
		DropdownControl dropdownControl;
		
		public AmazonPOM2Style(WebDriver driver)
		{
			
			 searchBox = driver.findElement(By.id("twotabsearchtextbox"));
			 searchButton = driver.findElement(By.xpath("//input[@value ='Go']"));
			 selectCatergory = driver.findElement(By.id("searchDropdownBox"));
			 elementControl = new ElementControl();
			 dropdownControl = new DropdownControl();
		}
		public void searchProduct(String product,String category) throws Exception {
			
			elementControl.setText(searchBox, product);
			dropdownControl.selectViaVisibleText(selectCatergory, category);
			elementControl.clickElement(searchButton);
			
		}
}
