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
import common.GlobalConstants;
import common.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_08_Switch_Role extends BaseTest {
	
	private WebDriver driver;
	private String userEmail;
	private String userFirstName  = "anh";
	private String userLastName = "BTC";
	private String userPassword = "123456";
	
	private String adminEmail = "admin@yourstore.com";
	private String adminPassword = "admin";
	
	private UserCustomerInfoPageObject userCustomerInfoPageObject;
	private UserLoginPageObject userLoginPageObject;
	private UserHomePageObject userHomePageObject;
	private UserRegisterPageObject userRegisterPageObject;
	private AdminLoginPageObject adminLoginPageObject;
	private AdminDashboardPageObject adminDashboardPageObject;
	
	
	@Parameters({"browser","environment"})
	@BeforeClass
	public void beforeClass(String browserName, String environment) {

		// chạy url với multiple browser và trả về driver đã đc khởi tạo bên BaseTest
		driver = getBrowserDriver(browserName, environment);
		
		userEmail = "anhBTC" + generateFakeNumber() + "@gmail.com"; 

		// open URL -> Home : khoi tao Home 
		userHomePageObject = PageGeneratorManager.getUserHomePage();
		userHomePageObject.setDriver(driver);		
		
		// Home click Register Link -> qua trang Register -> khởi tạo Register
		userRegisterPageObject = userHomePageObject.clickToRegisterLink();

		// Input to required fields
		userRegisterPageObject.inputFirstName(userFirstName);
		userRegisterPageObject.inputLastName(userLastName);
		userRegisterPageObject.inputEmail(userEmail);
		userRegisterPageObject.inputPassword(userPassword);
		userRegisterPageObject.inputConfirmPassword(userPassword);

		// Click to register button
		userRegisterPageObject.clickToRegisterButton();

		// Verify success message displayed
		Assert.assertEquals(userRegisterPageObject.getTextRegisterSuccessMess(), "Your registration completed");

		// Register click log out to Home -> qua trang Home -> khởi tạo Home
		userHomePageObject = userRegisterPageObject.clickToLogout();

	}

	@Test
	public void Role_01_User_To_Admin() {

		// Home click Login Link -> qua trang Login -> khởi tạo login lại
		userLoginPageObject = userHomePageObject.clickToLoginLink();
		
		// vì hàm loginAsUser , nó ko dùng ở trang khác đc , mà chỉ có mình Login Page gọi đc nó , cho nên mình sẽ KO viết hàm này ở BasePage dùng chung , để ý kỹ chỗ này nhé
		// Wrapper input Email , Password , clickToLoginBtn lại 1 hàm xử lý tất cả bên trong pageObjects => loginAsUser 
		userHomePageObject = userLoginPageObject.loginAsUser(userEmail,userPassword);

		// verify err confirm password
		Assert.assertTrue(userHomePageObject.checkDisplayMyAccountLink());
		
		// HomePage -> Customer Info 
		userCustomerInfoPageObject = userHomePageObject.openMyAccountPage();
		
		// CustomerInfo -> click logOut -> Home 
		userCustomerInfoPageObject.clickToLogoutLinkAtUserPage(driver);
	
		// Để User role qua Admin thì :
		// Ở Home Cần 1 hàm để mở ra trang Admin 
		userHomePageObject.openPageUrl(driver, GlobalConstants.DEV_ADMIN_PAGE_URL);
		
		// Sau khi gọi url thì sẽ mở ra Admin Login 
		adminLoginPageObject = PageGeneratorManager.getAdminLoginPage(driver);
		
		// Sau khi login xong thì sẽ trả về Admin Dashboard
		adminDashboardPageObject = adminLoginPageObject.loginAsAdmin(adminEmail, adminPassword);
		
		// check dashboard display
		Assert.assertTrue(adminDashboardPageObject.checkDashBoardDisplay());
		
		// Dashboard Page -> Click Logout -> Login Page(Admin)
		adminLoginPageObject = adminDashboardPageObject.clickToLogoutLinkAtAdminPage(driver);
		
	}

	@Test
	public void Role_02_Admin_To_User() {
		
		// Để Admin role qua User lại thì : 
		
		// Ở Dashboard cần 1 hàm để mở ra trang User 
		adminDashboardPageObject.openPageUrl(driver, GlobalConstants.DEV_PORTAL_PAGE_URL);
		
		// Sau khi gọi url thì sẽ mở HomePage
		userHomePageObject = PageGeneratorManager.getUserHomePage();
		userHomePageObject.setDriver(driver);
		
		// HomePage -> Login Page 
		userLoginPageObject = userHomePageObject.clickToLoginLink();
		
		// Login as User role 
		userHomePageObject = userLoginPageObject.loginAsUser(userEmail, userPassword);
	
		Assert.assertTrue(userHomePageObject.checkDisplayMyAccountLink());
		
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
