package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.nopCommerce.user.UserProductPageUI;

public class UserProductPageObject extends BasePage  {
	
	private WebDriver driver;

	public UserProductPageObject(WebDriver driver) {
	
		this.driver = driver;
	}

	public void clickToProductReview() {
		
		waitForElementClickable(driver,UserProductPageUI.PRODUCT_REVIEW_LINK);
		clickToElement(driver,UserProductPageUI.PRODUCT_REVIEW_LINK);
		
	}
	
	
	

}
