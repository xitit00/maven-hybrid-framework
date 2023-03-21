package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.nopCommerce.user.UserDesktopsPageUI;

public class UserDesktopsPageObject extends BasePage  {
	
	private WebDriver driver;

	public UserDesktopsPageObject(WebDriver driver) {
	
		this.driver = driver;
	}

	public void clickToProductItem() {
	
		waitForElementClickable(driver,UserDesktopsPageUI.PRODUCT_LINK);
		clickToElement(driver,UserDesktopsPageUI.PRODUCT_LINK);
		
	}
	
	
	

}
