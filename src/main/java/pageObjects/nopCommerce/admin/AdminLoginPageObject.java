package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import common.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {

	public WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
	
		
		this.driver = driver;
	}
	
	public void inputEmail(String textValue) {
		
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, textValue);
	}
	
	public void inputPassword(String textValue) {
		
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, textValue);
	}
	
	public AdminDashboardPageObject clickToLoginButton() {
		
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		
		AdminDashboardPageObject a = PageGeneratorManager.getAdminDashboardPage(driver);
		return a;
	}
	
	public AdminDashboardPageObject loginAsAdmin (String email, String password) {
		
		inputEmail(email);
		inputPassword(password);
		
		return clickToLoginButton();
	}
	
}
