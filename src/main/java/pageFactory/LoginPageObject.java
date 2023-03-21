package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import pageUIs.nopCommerce.user.UserHomePageUI;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class LoginPageObject extends BasePage {
	
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String LOGIN_BUTTON = "//button[@class='button-1 login-button']";
	public static final String EMAIL_ERR = "//span[@id='Email-error']";
	public static final String EMAIL_LOGIN_ERR = "//div[contains(@class,'message-error')]";
	
	@FindBy(how = How.XPATH, using = "//input[@id='Email']")
	private WebElement emailTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='Password']")
	private WebElement passwordTextBox;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//button[@class='button-1 login-button']")
	private WebElement loginButton;
	
	@FindBy(how = How.XPATH, using = "//span[@id='Email-error']")
	private WebElement emailError;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'message-error')]")
	private WebElement emailLoginError;
	
	
	// quản lý các hàm trong LoginPageObject , chứa những actions mình cần trong page Login 

	public void clickToLoginButton() {
		
		waitForElementClickableByElement(driver,loginButton);
		clickToElementByElement(loginButton);
		
	}

	public String getErrMessageEmailTextbox() {
		
		return getElementTextByElement(emailError);
	}

	public void inputEmail(String textValue) {
	
		sendkeyToElementByElement(emailTextBox, textValue);
		
	}

	public String getErrMessageUnsuccessful() {
		
		return getElementTextByElement(emailLoginError);
	}

	public void inputPassword(String textValue) {
		
		sendkeyToElementByElement(passwordTextBox, textValue);
	}
	
	
}
