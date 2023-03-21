package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.nopCommerce.user.UserMyAccountPageUI;

public class UserMyAccountPageObject extends BasePage {

	private WebDriver driver;

	public UserMyAccountPageObject(WebDriver driver) {
		
		
		this.driver = driver;
	}

	public void clickToGender() {
		
		waitForElementClickable(driver,UserMyAccountPageUI.GENDER_RADIO_BUTTON);
		clickToElement(driver, UserMyAccountPageUI.GENDER_RADIO_BUTTON);
		
	}

	public void inputLastName(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.LAST_NAME_TEXTBOX, textValue);
		
	}

	public void inputFirstName(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.FIRST_NAME_TEXTBOX, textValue);
		
	}

	public void clickToDayOfBirth(String textValue) {
		
		waitForAllElementPresence(driver, UserMyAccountPageUI.BIRTHDAY_DROPDOWN);
		selectItemByValueInDefaultDropdown(driver, UserMyAccountPageUI.BIRTHDAY_DROPDOWN, textValue);
	}

	public void clickToMonthOfBirth(String textValue) {
		
		waitForAllElementPresence(driver, UserMyAccountPageUI.BIRTHMONTH_DROPDOWN);
		selectItemByValueInDefaultDropdown(driver, UserMyAccountPageUI.BIRTHMONTH_DROPDOWN, textValue);
		
	}

	public void clickToYearOfBirth(String textValue) {
		
		waitForAllElementPresence(driver, UserMyAccountPageUI.BIRTHYEAR_DROPDOWN);
		selectItemByValueInDefaultDropdown(driver, UserMyAccountPageUI.BIRTHYEAR_DROPDOWN, textValue);
		
	}

	public void inputEmail(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.EMAIL_TEXTBOX, textValue);
		
	}

	public void inputCompanyName(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.COMPANY_TEXTBOX, textValue);
	}

	public void clickToSaveInfoButton() {
		
		waitForElementClickable(driver,UserMyAccountPageUI.SAVE_INFO_BUTTON);
		clickToElement(driver, UserMyAccountPageUI.SAVE_INFO_BUTTON);
		
	}

	public boolean checkGenderSelected() {
	
		
		return isElementSelected(driver, UserMyAccountPageUI.GENDER_RADIO_BUTTON);
	}

	public String getValueTextFirstName() {
		
		return getElementAttribute(driver, UserMyAccountPageUI.FIRST_NAME_TEXTBOX, "value");
	}

	public String getValueTextLastName() {
		
		return getElementAttribute(driver, UserMyAccountPageUI.LAST_NAME_TEXTBOX, "value");
	}

	public String getValueSelectedDayOfBirth() {
		
		return getSelectedItemDefaultDropdown(driver, UserMyAccountPageUI.BIRTHDAY_DROPDOWN);
	}

	public String getValueSelectedMonthOfBirth() {

		return getSelectedItemDefaultDropdown(driver, UserMyAccountPageUI.BIRTHMONTH_DROPDOWN);
	}

	public String getValueSelectedYearOfBirth() {
		
		return getSelectedItemDefaultDropdown(driver, UserMyAccountPageUI.BIRTHYEAR_DROPDOWN);
	}

	public String getValueTextEmail() {
		
		return getElementAttribute(driver, UserMyAccountPageUI.EMAIL_TEXTBOX, "value");
	}

	public String getValueTextCompanyName() {
		
		
		return getElementAttribute(driver, UserMyAccountPageUI.COMPANY_TEXTBOX, "value");
	}

	public void clickToAddressLink() {
		
		waitForElementClickable(driver,UserMyAccountPageUI.ADDRESSES_LINK);
		clickToElement(driver, UserMyAccountPageUI.ADDRESSES_LINK);
	}

	public void clickToAddNewBtn() {
		
		waitForElementClickable(driver,UserMyAccountPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, UserMyAccountPageUI.ADD_NEW_BUTTON);
		
	}

	public void inputAddressFirstName(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_FIRST_NAME_TEXTBOX, textValue);
	}

	public void inputAddressLastName(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_LAST_NAME_TEXTBOX, textValue);
		
	}

	public void inputAddressEmail(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_EMAIL_TEXTBOX, textValue);
		
	}

	public void selectCountry(String textValue) {
		
		waitForAllElementPresence(driver, UserMyAccountPageUI.ADDRESS_COUNTRY_DROPDOWN);
		selectItemByTextInDefaultDropdown(driver, UserMyAccountPageUI.ADDRESS_COUNTRY_DROPDOWN, textValue);
		
	}

	public void inputAddressCity(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_CITY_TEXTBOX, textValue);
		
	}

	public void inputAddress1(String textValue) {
	
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_1_TEXTBOX, textValue);
		
	}

	public void inputZipCode(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_ZIPCODE_TEXTBOX, textValue);
		
	}

	public void inputAddressPhoneNumber(String textValue) {
	
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_PHONE_TEXTBOX, textValue);
		
	}

	public void clickToSaveAddressButton() {
		
		waitForElementClickable(driver,UserMyAccountPageUI.SAVE_ADDRESS_BUTTON);
		clickToElement(driver, UserMyAccountPageUI.SAVE_ADDRESS_BUTTON);
		
	}

	public String getTextAddressName() {
		
		waitForElementVisible(driver, UserMyAccountPageUI.ADDRESS_NAME);
		return getElementText(driver, UserMyAccountPageUI.ADDRESS_NAME);
	}

	public String getTextAddressEmail() {
	
		waitForElementVisible(driver, UserMyAccountPageUI.ADDRESS_EMAIL);
		return getElementText(driver, UserMyAccountPageUI.ADDRESS_EMAIL);
	}

	public String getTextAddressPhone() {
		
		waitForElementVisible(driver, UserMyAccountPageUI.ADDRESS_PHONE);
		return getElementText(driver, UserMyAccountPageUI.ADDRESS_PHONE);
	}

	public String getTextAddress1() {
		
		waitForElementVisible(driver, UserMyAccountPageUI.ADDRESS_1);
		return getElementText(driver, UserMyAccountPageUI.ADDRESS_1);
	}

	public String getTextAddressCountry() {
		
		waitForElementVisible(driver, UserMyAccountPageUI.ADDRESS_COUNTRY);
		return getElementText(driver, UserMyAccountPageUI.ADDRESS_COUNTRY);
	}

	public String getTextAddressCityAndZipCode() {
		
		waitForElementVisible(driver, UserMyAccountPageUI.ADDRESS_CITY_ZIP);
		return getElementText(driver, UserMyAccountPageUI.ADDRESS_CITY_ZIP);
	}

	public void clickToChangePasswordLink() {
		
		waitForElementClickable(driver,UserMyAccountPageUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, UserMyAccountPageUI.CHANGE_PASSWORD_LINK);
	}

	public void inputOldPassword(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.OLD_PASSWORD_TEXTBOX, textValue);
		
	}

	public void inputNewPassword(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.NEW_PASSWORD_TEXTBOX, textValue);
		
	}

	public void inputConfirmPassword(String textValue) {
		
		sendkeyToElement(driver, UserMyAccountPageUI.CONFIRM_PASSWORD_TEXTBOX, textValue);
	}

	public void clickToChangePasswordButton() {
		
		waitForElementClickable(driver,UserMyAccountPageUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(driver, UserMyAccountPageUI.CHANGE_PASSWORD_BUTTON);
		
	}

	public boolean checkPasswordChangedDisplay() {
		
		waitForElementVisible(driver, UserMyAccountPageUI.CHANGED_PASSWORD_SUCCESSFULL_MESSAGE);
		return isElementDisplayed(driver, UserMyAccountPageUI.CHANGED_PASSWORD_SUCCESSFULL_MESSAGE);
	}

	public void clickToCloseNotification() {
		
		waitForElementClickable(driver,UserMyAccountPageUI.CHANGED_PASSWORD_CLOSE_BUTTON);
		clickToElement(driver, UserMyAccountPageUI.CHANGED_PASSWORD_CLOSE_BUTTON);
		
	}

	public void clickToMyProductReviewsLink() {
		
		waitForElementClickable(driver,UserMyAccountPageUI.MY_PRODUCT_REVIEWS_LINK);
		clickToElement(driver, UserMyAccountPageUI.MY_PRODUCT_REVIEWS_LINK);
		
	}


	
}
