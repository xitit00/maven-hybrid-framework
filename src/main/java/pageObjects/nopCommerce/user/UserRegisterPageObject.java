package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import common.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserLoginPageUI;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	
	private WebDriver driver;
	
	
	// hàm constructor : khi class này đc gọi thì hàm này sẽ đc gọi vào đầu tiên , truyền tham số driver vào map trước , sau đó mới 
	// chạy đến những hàm bên dưới thì lúc này driver đã map đã có giá trị r nhé.
	public UserRegisterPageObject(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void inputFirstName(String textValue) {
		
		sendkeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, textValue);
	}
	
	public void inputLastName(String textValue) {
		
		sendkeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, textValue);
	}
	
	public void inputEmail(String textValue) {
		
		sendkeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, textValue);
	}
	
	public void inputPassword(String textValue) {
		
		sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, textValue);
	}
	
	public void inputConfirmPassword(String textValue) {
		
		sendkeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD, textValue);
	}
	
	public void clickToRegisterButton() {
		
		waitForElementClickable(driver,UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}
	
	public UserHomePageObject clickToLogout() {
		
		waitForElementClickable(driver,UserRegisterPageUI.LOG_OUT);
		clickToElement(driver, UserRegisterPageUI.LOG_OUT);
		
		UserHomePageObject h = PageGeneratorManager.getUserHomePage();
		h.setDriver(driver);
		
		return h;
	}
	
	public void clickToLogoutByJS() {
		
		waitForElementClickable(driver,UserRegisterPageUI.LOG_OUT);
		clickToElementByJS(driver, UserRegisterPageUI.LOG_OUT);
	}
	
	public String getTextFirstNameErr() {
		
		return getElementText(driver, UserRegisterPageUI.FIST_NAME_ERR_MESSAGE);
	}

	public String getTextLastNameErr() {

		return getElementText(driver, UserRegisterPageUI.LAST_NAME_ERR_MESSAGE);
	}

	public String getTextEmailErr() {

		return getElementText(driver, UserRegisterPageUI.EMAIL_ADDRESS_ERR_MESSAGE);
	}

	public String getTextPasswordErr() {

		return getElementText(driver, UserRegisterPageUI.PASSWORD_ERR_MESSAGE);
	}

	public String getTextConfirmPasswordErr() {

		return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERR_MESSAGE);
	}
	
	
	public String getTextRegisterSuccessMess() {
		
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}
	
	public String getTextExistingsEmailErr() {
		
		return getElementText(driver, UserRegisterPageUI.EXISTINGS_EMAIL_ERR_MESSAGE);
	}

	public void clickToTheMoon() {
		// TODO Auto-generated method stub
		
	}

	public UserLoginPageObject clickToLoginLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver,UserRegisterPageUI.LOG_IN);
		clickToElement(driver,UserRegisterPageUI.LOG_IN);
		
		return PageGeneratorManager.getUserLoginPage(driver);
	}
}
