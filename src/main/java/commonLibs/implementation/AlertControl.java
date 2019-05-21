package commonLibs.implementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonLibs.contract.IAlerts;

public class AlertControl implements IAlerts{
	private WebDriver driver;
	
	public  AlertControl(WebDriver driver) {
		this.driver =driver;
	}
	@Override
	public void acceptAlert() throws Exception {
		driver.switchTo().alert().accept();;
	}

	@Override
	public void rejectAlert() throws Exception {
		driver.switchTo().alert().dismiss();
	}

	@Override
	public String getMessageFromAlert() throws Exception {
		return driver.switchTo().alert().getText();
	}

	@Override
	public boolean checkAlertPresent(int timeoutInseconds) throws Exception {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver,timeoutInseconds);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
			}catch(Exception e) {
				return false;
	}

}
}
