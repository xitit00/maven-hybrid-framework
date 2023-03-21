package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.wordpress.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage {
	
	public WebDriver driver;
	
	public AdminDashboardPO(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public AdminPostSearchPO clickToPostMenuLink() {
		
		AdminPostSearchPO adminPostSearchPage = PageGeneratorManager.getAdminPostSearchPO(driver);
		
		waitForElementClickable(driver, AdminDashboardPageUI.POST_MENU_LINK);
		clickToElement(driver, AdminDashboardPageUI.POST_MENU_LINK);
		
		return adminPostSearchPage;
	}
	
	
}
