package pageObjects.saucelab;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.saucelab.LoginPageUI;

public class LoginPageObject extends BasePage {
	
	public WebDriver driver;

	public LoginPageObject(WebDriver driver) {
	
		this.driver = driver;
	}

	public void enterToUsernameTextbox(String strUserName) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, strUserName);
		
	}

	public void enterToPasswordTextbox(String strPassword) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, strPassword);
	}

	public ProductPageObject clickToLoginButton() {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		
		return PageGeneratorManager.getProductPage(driver);		
		
	}
	
	
	
	
	

}
