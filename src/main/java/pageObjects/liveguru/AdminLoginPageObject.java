package pageObjects.liveguru;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import common.BasePage;
import pageUIs.liveguru.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
	
	WebDriver driver;
	Alert alert;

	public AdminLoginPageObject(WebDriver driver) {
		
		this.driver = driver;
	}

	public void enterUserNameTextbox(String textUserName, String key) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminLoginPageUI.LOGIN_TEXTBOX , textUserName);
		sendkeyToElement(driver, AdminLoginPageUI.LOGIN_TEXTBOX, key, textUserName);
	}

	public void enterPassWordTextbox(String textPassWord, String key) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminLoginPageUI.LOGIN_TEXTBOX, textPassWord);
		sendkeyToElement(driver, AdminLoginPageUI.LOGIN_TEXTBOX, key, textPassWord);
	}

	public void clickToLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		
	}

	public void closePopUp() {
		
		waitForElementClickable(driver, AdminLoginPageUI.CLOSE_BUTTON);
		clickToElement(driver, AdminLoginPageUI.CLOSE_BUTTON);
		
	}

	public boolean isPopupDisplayed() {

		waitForElementVisible(driver, AdminLoginPageUI.MESSAGE_POPUP);
		return isElementDisplayed(driver, AdminLoginPageUI.MESSAGE_POPUP);
	}

	public boolean isNameAndEmailDisplayed(String fullName, String emailAddress) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver,  AdminLoginPageUI.TABLE_ROW, fullName, emailAddress);
		return isElementDisplayed(driver, AdminLoginPageUI.TABLE_ROW, fullName, emailAddress);
		
	}

	public void enterFullName(String nameValue, String keyEnter) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, AdminLoginPageUI.COLUMN_TEXTBOX, nameValue);
		sendkeyToElement(driver, AdminLoginPageUI.COLUMN_TEXTBOX, keyEnter, nameValue);
	}

	public void enterEmail(String emailValue, String keyEnter) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminLoginPageUI.COLUMN_TEXTBOX, emailValue);
		sendkeyToElement(driver, AdminLoginPageUI.COLUMN_TEXTBOX, keyEnter, emailValue);
	}

	public void clickBtnSearch() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminLoginPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminLoginPageUI.SEARCH_BUTTON);
	}

	public void clickToCheckbox(String emailValue) {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, AdminLoginPageUI.COLUMN_CHECKBOX, emailValue);
		clickToElement(driver, AdminLoginPageUI.COLUMN_CHECKBOX, emailValue);
		
	}

	public boolean isCheckBoxSelected(String emailValue) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, AdminLoginPageUI.COLUMN_CHECKBOX, emailValue);
		return isElementSelected(driver, AdminLoginPageUI.COLUMN_CHECKBOX, emailValue);
	}

	public void selectDeleteInDropdown(String dropdownValue) {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, AdminLoginPageUI.SELECTED_ITEM_DROPDOWN);
		selectItemByValueInDefaultDropdown(driver, AdminLoginPageUI.SELECTED_ITEM_DROPDOWN, dropdownValue);
	}
	
	public String getSelectedText() {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, AdminLoginPageUI.SELECTED_ITEM_DROPDOWN);
		return getSelectedItemDefaultDropdown(driver, AdminLoginPageUI.SELECTED_ITEM_DROPDOWN);
	}

	public void clickBtnSubmit() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminLoginPageUI.SUBMIT_BUTTON);
		clickToElement(driver, AdminLoginPageUI.SUBMIT_BUTTON);
	}

	public void clickAcceptAlert() {
		// TODO Auto-generated method stub
		
		waitForAlertPresence(driver);
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "Are you sure?");
		alert.accept();
	}

	public boolean isDeletedAccountSuccessful() {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, AdminLoginPageUI.EMPTY_ROW);
		return isElementDisplayed(driver, AdminLoginPageUI.EMPTY_ROW);
	}
	
	

}
