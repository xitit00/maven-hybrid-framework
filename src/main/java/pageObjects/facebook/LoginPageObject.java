package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
	
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		
		this.driver = driver;
	}

	public void clickToCreateNewAccountButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
		
	}

	public boolean isEmailAddressTextboxDisplayed() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		return isElementDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
	}

	public boolean isConfirmEmailAddressTextboxUndisplayed() {
		// TODO Auto-generated method stub
//		waitForElementInVisible(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
		waitForElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
		return isElementUndisplayed(driver,  LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
	}

	public boolean isConfirmEmailAddressTextboxDisplayed() {
		// TODO Auto-generated method stub
		
//		waitForElementInVisible(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
		return isElementDisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
	}

	public void enterToEmailAddressTextbox(String emailAddress) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
	}

	public void clickCloseIconAtRegisterForm() {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, LoginPageUI.CLOSE_ICON);
		clickToElement(driver, LoginPageUI.CLOSE_ICON);
	}

}
