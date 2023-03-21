package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.nopCommerce.user.BasePageNopCommerceUI;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	
	public WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
	
		this.driver = driver;
	}

	public boolean checkDisplayCustomerInfoPage() {
		
	
		waitForElementVisible(driver,UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		return isElementDisplayed(driver,UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		
	}

}
