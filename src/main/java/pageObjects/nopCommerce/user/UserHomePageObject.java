package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import common.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	
	private WebDriver driver;
	
	public static UserHomePageObject getHomePageObject() {
		
		UserHomePageObject hPageObject = new UserHomePageObject();
		return hPageObject;
	}
	
	public void setDriver(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public UserRegisterPageObject clickToRegisterLink() {
		
		waitForElementClickable(driver,UserHomePageUI.REGISTER_LINK);
		clickToElement(driver, UserHomePageUI.REGISTER_LINK);
		
		UserRegisterPageObject r =  PageGeneratorManager.getUserRegisterPage(driver);
		return r;
		
	}

	public UserLoginPageObject clickToLoginLink() {
		
		waitForElementClickable(driver,UserHomePageUI.LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.LOGIN_LINK);
		
		UserLoginPageObject l = PageGeneratorManager.getUserLoginPage(driver);
		return l;
	}

	public boolean checkDisplayMyAccountLink() {
		
		waitForElementVisible(driver,UserHomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver,UserHomePageUI.MY_ACCOUNT_LINK);
		
	}

	public boolean checkDisplayLogout() {
		
		waitForElementVisible(driver,UserHomePageUI.LOG_OUT);
		return isElementDisplayed(driver, UserHomePageUI.LOG_OUT);
	}

	public void clickToMyAccountLink() {
		
		waitForElementClickable(driver,UserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		
	}
	
	public UserCustomerInfoPageObject openMyAccountPage() {
		
		waitForElementClickable(driver,UserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		
		UserCustomerInfoPageObject c = PageGeneratorManager.getUserCustomerInfoPage(driver);
		
		return c;
		
	}

	public void clickToComputersLink() {
		
		waitForElementClickable(driver,UserHomePageUI.COMPUTER_LINK);
		clickToElement(driver, UserHomePageUI.COMPUTER_LINK);
		
	}


}
