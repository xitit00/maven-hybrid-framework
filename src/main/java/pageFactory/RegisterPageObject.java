package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class RegisterPageObject extends BasePage {
	
	private WebDriver driver;
	
	
	// hàm constructor : khi class này đc gọi thì hàm này sẽ đc gọi vào đầu tiên , truyền tham số driver vào map trước , sau đó mới 
	// chạy đến những hàm bên dưới thì lúc này driver đã map đã có giá trị r nhé.
	public RegisterPageObject(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static final String FIRST_NAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LAST_NAME_TEXTBOX = "//input[@id='LastName']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String CONFIRM_PASSWORD = "//input[@id='ConfirmPassword']";
	public static final String REGISTER_BUTTON = "//button[@id='register-button']";
	public static final String LOG_OUT = "//a[@class='ico-logout']";
	public static final String REGISTER_SUCCESS_MESSAGE = "//div[@class='result']";
	
	@FindBy(how = How.XPATH, using = "//input[@id='FirstName']")
	private WebElement firstNameTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='LastName']")
	private WebElement lastNameTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='Email']")
	private WebElement emailTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='Password']")
	private WebElement passwordTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='ConfirmPassword']")
	private WebElement confirmPassword;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//button[@id='register-button']")
	private WebElement registerButton;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//a[@class='ico-logout']")
	private WebElement logOut;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//div[@class='result']")
	private WebElement registerSuccessMessage;
	
	
	
	public void inputFirstName(String textValue) {
		
		sendkeyToElementByElement(firstNameTextBox, textValue);
	}
	
	public void inputLastName(String textValue) {
		
		sendkeyToElementByElement(lastNameTextBox, textValue);

	}
	
	public void inputEmail(String textValue) {
		
		sendkeyToElementByElement(emailTextBox, textValue);
	}
	
	public void inputPassword(String textValue) {
		
		sendkeyToElementByElement(passwordTextBox, textValue);
	}
	
	public void inputConfirmPassword(String textValue) {
		
		sendkeyToElementByElement(confirmPassword, textValue);
	}
	
	public void clickToRegisterButton() {
		
		waitForElementClickableByElement(driver, registerButton);
		clickToElementByElement(registerButton);
	}
	
	public void clickToLogout() {
		
		waitForElementClickableByElement(driver, logOut);
		clickToElementByElement(logOut);
	}
	
	public String getTextRegisterSuccessMess() {
		
		waitForElementVisibleByElement(driver, registerSuccessMessage);
		return getElementTextByElement(registerSuccessMessage);
	}

	public void clickToTheMoon() {
		// TODO Auto-generated method stub
		
	}
}
