package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class HomePageObject extends BasePage {
	
	private WebDriver driver;
	

	public HomePageObject(WebDriver driver) {
		
		this.driver = driver;
		// driver sử dụng để findElement 
		// this đại diện cho 1 page ( HomePageObject) , như là 1 Object page truyền vô PageFactory.initElement dùng để khởi tạo page 
		// luồng đi : HomePageObject -> constructor -> initElement driver đã đc khởi tạo từ testcase và this nhầm khẳng định 
		// chính xác là page này tao cần khởi tạo , việc define @FindBy bên dưới chỉ là define ra thôi , chứ thực sự nó chỉ findElement 
		// khi các action bên trong method đc gọi thì nó mới đi findELement đc define bên trên.
		// => các FindBy chỉ là khai báo , khi nào mình cần dùng thì nó sẽ tự đi tìm cho mình.
		PageFactory.initElements(driver, this);
	}


	@FindBy(how = How.XPATH, using = "//a[@class='ico-register']")
	private WebElement registerLink;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//a[@class='ico-account']")
	private WebElement myAccountLink;
	
	@FindBy(how = How.XPATH, using = "//a[@class='ico-login']")
	private WebElement loginLink;
	
	
	public void clickToRegisterLink() {
		
		waitForElementClickableByElement(driver,registerLink);
		clickToElementByElement(registerLink);
	
	}

	public void clickToLoginLink() {
		
		waitForElementClickableByElement(driver,loginLink);
		clickToElementByElement(loginLink);
	}

	public boolean checkDisplayMyAccount() {
		
		waitForElementVisibleByElement(driver,myAccountLink);
		return isElementDisplayedByElement(myAccountLink);
		
	}

	public void clickToMyAccountLink() {
		
		waitForElementClickableByElement(driver,myAccountLink);
		clickToElementByElement(myAccountLink);
		
	}


}
