package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
	
	public static AdminLoginPO getAdminLoginPageObject(WebDriver driver) {
		
		return new AdminLoginPO(driver);
	}
	
	public static AdminDashboardPO getAdminDashboardPO(WebDriver driver) {
		
		return new AdminDashboardPO(driver);
	}
	
	public static AdminPostAddNewPO getAdminPostAddNewPO(WebDriver driver) {
		
		return new AdminPostAddNewPO(driver);
	}
	
	public static AdminPostSearchPO getAdminPostSearchPO(WebDriver driver) {
		
		return new AdminPostSearchPO(driver);
	}
	
	public static UserHomePO getUserHomePO(WebDriver driver) {
		
		return new UserHomePO(driver);
	}
	
	public static UserPostSearchPO getUserPostSearchPO(WebDriver driver) {
		
		return new UserPostSearchPO(driver);
	}
	
	public static UserPostDetailPO getUserPostDetailPO(WebDriver driver) {
		
		return new UserPostDetailPO(driver);
	}

}
