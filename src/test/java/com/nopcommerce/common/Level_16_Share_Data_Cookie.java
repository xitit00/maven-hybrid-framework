package com.nopcommerce.common;

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

public class Level_16_Share_Data_Cookie extends BaseTest {
	
	private WebDriver driver;
	private String email;
	private String password;
	
	private UserLoginPageObject loginPageObject;
	private UserHomePageObject homePageObject;

	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriverOnlyOneUrl(browserName, url);

		// open URL -> Home : khoi tao Home 
		homePageObject = PageGeneratorManager.getUserHomePage();
		homePageObject.setDriver(driver);		
		
		// login 
		loginPageObject = homePageObject.clickToLoginLink();

		// ko lấy email , password như shareLogin , mà lấy cookies và reload Page , vì đã login sẵn ở trang common rồi 
		loginPageObject.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
		loginPageObject.refreshCurrentPage(driver);

		// verify my account link displayed 
		Assert.assertTrue(homePageObject.checkDisplayMyAccountLink());

	}
	
	@Test
	public void Search_01_Empty_Data() {
		
		
	}
	
	@Test
	public void Search_02_Empty_Data() {
		
		
	}
	
	@Test
	public void Search_03_Empty_Data() {
		
		
	}
	
	@Test
	public void Search_04_Empty_Data() {
		
		
	}
	
	@Test
	public void Search_05_Empty_Data() {
		
		
	}
	
	@Test
	public void Search_06_Empty_Data() {
		
		
	}

	// Bài này ko gọi Register , vì đã tách nó ra 1 hàm dùng chung ở Common_01_Register_Account
//	@Test
//	public void User_01_Register() {
//
//		registerPageObject = homePageObject.clickToRegisterLink();
//
//		registerPageObject.inputFirstName(firstName);
//		registerPageObject.inputLastName(lastName);
//		registerPageObject.inputEmail(email);
//		registerPageObject.inputPassword(password);
//		registerPageObject.inputConfirmPassword(password);
//
//		registerPageObject.clickToRegisterButton();
//
//		Assert.assertEquals(registerPageObject.getTextRegisterSuccessMess(), "Your registration completed");
//		
//		homePageObject = registerPageObject.clickToLogout();
//	}
	
	// Dời phần login lên @Before Class để 
//	@Test
//	public void User_02_Login() {
//	
//		loginPageObject = homePageObject.clickToLoginLink();
//
//		loginPageObject.inputEmail(email);
//		loginPageObject.inputPassword(password);
//
//		homePageObject = loginPageObject.clickToLoginButton();
//
//		Assert.assertTrue(homePageObject.checkDisplayMyAccountLink());
//		
//		customerInfoPageObject = homePageObject.openMyAccountPage();
//		
//		Assert.assertTrue(customerInfoPageObject.checkDisplayCustomerInfoPage());
//	}

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
