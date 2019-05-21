package commonLibs.implementation;

import javax.swing.JSeparator;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import commonLibs.contract.IJavaScript;

public class JavascriptControl implements IJavaScript{
	
	WebDriver driver;
	
	public  JavascriptControl(WebDriver driver) {
		this.driver =driver;
	}
	
	public JavascriptExecutor jsEngine() {
		
		JavascriptExecutor jsEngine;
		jsEngine =(JavascriptExecutor) driver;
		return jsEngine;
	}
	

	@Override
	public void executeJavaScript(String scriptToExecute) throws Exception {
		jsEngine().executeScript(scriptToExecute);
	}

	@Override
	public void scrollDown(int x, int y) throws Exception {
		
		
		String jsCommand = String.format("window.scrollBy(%d,%d)",x,y);
				jsEngine().executeScript(jsCommand);
		
	}

	@Override
	public String executeJavaScriptWithReturnValue(String scriptToExecute) throws Exception {
		return jsEngine().executeScript(scriptToExecute).toString();
	}

}
