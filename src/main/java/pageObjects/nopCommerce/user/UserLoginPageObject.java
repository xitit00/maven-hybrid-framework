package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import common.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserHomePageUI;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		
		this.driver = driver;
	}
	
	// quản lý các hàm trong LoginPageObject , chứa những actions mình cần trong page Login 

	public UserHomePageObject clickToLoginButton() {
		
		waitForElementClickable(driver,UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver,UserLoginPageUI.LOGIN_BUTTON);
		
		UserHomePageObject h = PageGeneratorManager.getUserHomePage();
		h.setDriver(driver);
		
		return h;
	}

	public String getErrMessageEmailTextbox() {
		
		return getElementText(driver, UserLoginPageUI.EMAIL_ERR);
	}

	public void inputEmail(String textValue) {
	
		sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, textValue);
		
	}

	public String getErrMessageUnsuccessful() {
		
		return getElementText(driver, UserLoginPageUI.EMAIL_LOGIN_ERR);
	}

	public void inputPassword(String textValue) {
		
		sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, textValue);
	}

	public UserHomePageObject loginAsUser(String email , String password) {
		
		inputEmail(email);
		inputPassword(password);
		UserHomePageObject h = clickToLoginButton();
		return h;
	}
	
	
}
