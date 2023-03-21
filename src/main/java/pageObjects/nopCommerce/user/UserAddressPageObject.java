package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.BasePage;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;

public class UserAddressPageObject extends BasePage {
	
	public WebDriver driver;
	public WebDriverWait explicitWait;
	public boolean isPageExist;

	public UserAddressPageObject(WebDriver driver) {
	
		this.driver = driver;
	}
	
	public UserAddressPageObject(WebDriver driver, WebDriverWait explicitWait) {
		
		this.driver = driver;
		this.explicitWait = explicitWait;
	}
	
	public UserAddressPageObject(WebDriver driver, boolean isPageExist) {
		
		this.driver = driver;
		this.isPageExist = isPageExist;
	}
	
	

}
