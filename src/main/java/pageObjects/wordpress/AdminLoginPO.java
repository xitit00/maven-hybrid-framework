package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.wordpress.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
	
	public WebDriver driver;

	// dùng get , set để khởi tạo class và set Driver
//	public static AdminLoginPO getAdminLoginPageObject() {
//		
//		return new AdminLoginPO();
//	}
//	
//	public void setDriver(WebDriver driver) {
//		
//		this.driver = driver;
//	}
	
	public AdminLoginPO(WebDriver driver) {
	
		this.driver = driver;
	}
	

	public void enterToUsernameTextbox(String adminUsername) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.USERNAME_TEXTBOX, adminUsername);
	}


	public void enterToPasswordTextbox(String adminPassword) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
	}

	//c1 chuyển page
//	public void clickToLoginButton() {
//		// TODO Auto-generated method stub
//		
//		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
//		clickToElement(driver,  AdminLoginPageUI.LOGIN_BUTTON);
//		
//	}
	
	//c2 chuyển page 
	public AdminDashboardPO clickToLoginButton() {
		
		AdminDashboardPO adminDashboardPO = PageGeneratorManager.getAdminDashboardPO(driver);
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver,  AdminLoginPageUI.LOGIN_BUTTON);
		return adminDashboardPO;
		
	}

}
