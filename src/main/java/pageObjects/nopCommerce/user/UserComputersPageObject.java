package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.nopCommerce.user.UserComputersPageUI;

public class UserComputersPageObject extends BasePage  {
	
	private WebDriver driver;

	public UserComputersPageObject(WebDriver driver) {
	
		this.driver = driver;
	}

	public void clickToDesktopsLink() {
		
		
		waitForElementClickable(driver,UserComputersPageUI.DESKTOPS_LINK);
		clickToElement(driver,UserComputersPageUI.DESKTOPS_LINK);
	}
	
	
	

}
