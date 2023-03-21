package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static HomePageObject getHomePageObject(WebDriver driver) {
		
		return new HomePageObject(driver);
		
	}

	public static AdminLoginPageObject getAdminLoginPageObject(WebDriver driver) {
		
		return new AdminLoginPageObject(driver);
		
	}
}
