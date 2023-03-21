package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import common.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_07_Switch_Page extends BaseTest {
	
	private WebDriver driver;
	private String email;

	
	private String firstName  = "anh";
	private String lastName = "BTC";
	private String password = "123456";
	
	
	private UserLoginPageObject loginPageObject;
	private UserHomePageObject homePageObject;
	private UserRegisterPageObject registerPageObject;
	private UserCustomerInfoPageObject customerInfoPageObject;
	private UserAddressPageObject addressPageObject;
	private UserRewardPointPageObject rewardPointPageObject;
	private UserMyProductReviewPageObject myProductReviewPageObject;
	
	@Parameters({"browser","environment"})
	@BeforeClass
	public void beforeClass(String browserName, String environment) {

		driver = getBrowserDriver(browserName, environment);
		
		// Set timeout tim element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// open URL 
		driver.get("https://demo.nopcommerce.com/");
		
		email = "anhBTC" + generateFakeNumber() + "@gmail.com"; 
		

		// open URL -> Home : khoi tao Home 
		homePageObject = PageGeneratorManager.getUserHomePage();
		homePageObject.setDriver(driver);		

	}

	@Test
	public void User_01_Register() {

		// Home click Register Link -> qua trang Register -> khởi tạo Register
		registerPageObject = homePageObject.clickToRegisterLink();

		// Input to required fields
		registerPageObject.inputFirstName(firstName);
		registerPageObject.inputLastName(lastName);
		registerPageObject.inputEmail(email);
		registerPageObject.inputPassword(password);
		registerPageObject.inputConfirmPassword(password);

		// Click to register button
		registerPageObject.clickToRegisterButton();

		// Verify success message displayed
		Assert.assertEquals(registerPageObject.getTextRegisterSuccessMess(), "Your registration completed");

		// Register click log out to Home -> qua trang Home -> khởi tạo Home
		homePageObject = registerPageObject.clickToLogout();
		
	}

	@Test
	public void User_02_Login() {
		
		// Home click Login Link -> qua trang Login -> khởi tạo login lại
		loginPageObject = homePageObject.clickToLoginLink();

		// Input to required fields
		loginPageObject.inputEmail(email);
		loginPageObject.inputPassword(password);

		// Login Sucessfull -> Home
		homePageObject = loginPageObject.clickToLoginButton();
		
		//verify err confirm password
		Assert.assertTrue(homePageObject.checkDisplayMyAccountLink());
	}
	
	@Test
	public void User_03_My_Account() {
		
		customerInfoPageObject = homePageObject.openMyAccountPage();
		Assert.assertTrue(customerInfoPageObject.checkDisplayCustomerInfoPage());
	}
	
	@Test
	public void User_04_Switch_Page() {
		
		// Knowledge cua3 Page Object:
		// Một page A khi chuyển qua page B thì phải viết 1 hàm 
		// Thay vì tạo ra hàm lặp lại nhiều lần ở page đa chiều thì gom lại các hàm openPage viết ở 1 chỗ là BasePage để dễ quản lý 
		// sau đó ở testcase thì nhờ việc các pageObject đều kế thừa basePage , nên tại pageObject đang đứng có thể gọi thẳng ra pageObject 
		// muốn chuyển tới vd : Customer Info -> Address - addressPageObject = customerInfoPageObject.openAddressPage(driver);
		// (action : open/ click / ...: link / button / image / ... ) để mở page B đó lên
		
		// Customer Info -> Address
		addressPageObject = customerInfoPageObject.openAddressPage(driver);
		
		// Address -> My Product Review 
		myProductReviewPageObject = addressPageObject.openMyProductReviewPage(driver);
		
		// My Product Review -> Reward Point
		rewardPointPageObject = myProductReviewPageObject.openRewardPointPage(driver);
		
		// Reward Point -> Address
		addressPageObject = rewardPointPageObject.openAddressPage(driver);
		
		// Address -> Reward Point
		rewardPointPageObject = addressPageObject.openRewardPointPage(driver);
		
		// Reward Point -> My Product Preview
		myProductReviewPageObject = rewardPointPageObject.openMyProductReviewPage(driver);
	}
	
	@Test
	public void User_05_Switch_Role() {
		//Switch Role
		System.out.println("Switch Role");
	}

	

	public void sleepInSecond(long timeoutInSec){
		
		try {
			
			Thread.sleep(timeoutInSec * 1000);
			
		}catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		
		driver.quit();
	}

}
