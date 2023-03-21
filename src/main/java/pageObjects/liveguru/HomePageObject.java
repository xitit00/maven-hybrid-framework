package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.liveguru.user.HomePageUI;

public class HomePageObject extends BasePage {
	
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		
		this.driver = driver;
	}

	public void clickMyAccountLink() {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public void clickCreateAccountButton() {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, HomePageUI.CREATE_ACCOUNT_BUTTON);
		clickToElement(driver, HomePageUI.CREATE_ACCOUNT_BUTTON);
		
	}

	public void enterFirstNameTextbox(String textID, String key) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, HomePageUI.CREATE_ACCOUNT_TEXTBOX, textID);
		sendkeyToElement(driver, HomePageUI.CREATE_ACCOUNT_TEXTBOX, key, textID);
		
	}

	public void enterLastNameTextbox(String textID, String key) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.CREATE_ACCOUNT_TEXTBOX, textID);
		sendkeyToElement(driver, HomePageUI.CREATE_ACCOUNT_TEXTBOX, key, textID);
	}

	public void enterEmailAddressTextbox(String textID, String key) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.CREATE_ACCOUNT_TEXTBOX, textID);
		sendkeyToElement(driver, HomePageUI.CREATE_ACCOUNT_TEXTBOX, key, textID);
	}

	public void enterPassWordTextbox(String textID, String key) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.CREATE_ACCOUNT_TEXTBOX, textID);
		sendkeyToElement(driver, HomePageUI.CREATE_ACCOUNT_TEXTBOX, key, textID);
	}

	public void enterConfirmPasswordTextbox(String textID, String key) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.CREATE_ACCOUNT_TEXTBOX, textID);
		sendkeyToElement(driver, HomePageUI.CREATE_ACCOUNT_TEXTBOX, key, textID);
	}

	public void clickRegisterButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.REGISTER_BUTTON);
		clickToElement(driver, HomePageUI.REGISTER_BUTTON);
	}

	public boolean isRegisterSuccessfulTextDisplay() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.REGISTER_SUCCESSFUL_TEXTBOX);
		return isElementDisplayed(driver, HomePageUI.REGISTER_SUCCESSFUL_TEXTBOX);
	}

	public void enterEmailLoginTextbox(String emailValue, String emailAddress) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, HomePageUI.LOGIN_ACCOUNT_TEXTBOX, emailValue);
		sendkeyToElement(driver, HomePageUI.LOGIN_ACCOUNT_TEXTBOX, emailAddress, emailValue);
	}

	public void enterPasswordLoginTextbox(String passValue, String passWord) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, HomePageUI.LOGIN_ACCOUNT_TEXTBOX, passValue);
		sendkeyToElement(driver, HomePageUI.LOGIN_ACCOUNT_TEXTBOX, passWord, passValue);
	}

	public void clickBtnLogin() {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, HomePageUI.LOGIN_BUTTON);
		clickToElement(driver, HomePageUI.LOGIN_BUTTON);
	}

	public boolean isResultTextDisplayed() {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, HomePageUI.LOGIN_RESULT_TEXT);
		return isElementDisplayed(driver, HomePageUI.LOGIN_RESULT_TEXT);

	}

	public void clickAccountLink() {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, HomePageUI.ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.ACCOUNT_LINK);
	}

	public void clickLogoutLink() {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, HomePageUI.LOGOUT_LINK);
		clickToElement(driver, HomePageUI.LOGOUT_LINK);
		
	}
	

}
