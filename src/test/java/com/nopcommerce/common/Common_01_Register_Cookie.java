package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import common.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register_Cookie extends BaseTest {
	
	private WebDriver driver;
	private String email;
	
	private UserHomePageObject homePageObject;
	private UserRegisterPageObject registerPageObject;
	private UserLoginPageObject loginPageObject;
	
	private String firstName  = "anh";
	private String lastName = "BTC";
	private String password = "123456";
	
	public static Set<Cookie> loggedCookies;
	
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void User_01_Register(String browserName, String url) {
		
		driver = getBrowserDriverOnlyOneUrl(browserName, url);
		
		email = "anhBTC" + generateFakeNumber() + "@gmail.com"; 

		// open URL -> Home : khoi tao Home 
		homePageObject = PageGeneratorManager.getUserHomePage();
		homePageObject.setDriver(driver);		

		registerPageObject = homePageObject.clickToRegisterLink();

		registerPageObject.inputFirstName(firstName);
		registerPageObject.inputLastName(lastName);
		registerPageObject.inputEmail(email);
		registerPageObject.inputPassword(password);
		registerPageObject.inputConfirmPassword(password);

		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getTextRegisterSuccessMess(), "Your registration completed");
		
		homePageObject = registerPageObject.clickToLogout();
		loginPageObject = homePageObject.clickToLoginLink();

		loginPageObject.inputEmail(email);
		loginPageObject.inputPassword(password);

		homePageObject = loginPageObject.clickToLoginButton();
		
		// lấy cookies
		
		loggedCookies = homePageObject.getAllCookies(driver);
		
		for (Cookie cookie : loggedCookies) {
			
			System.out.println("Cookie at Common class: " + cookie);
		}
	}
	

	@AfterTest
	public void afterTest() {
		
		System.out.println("Run After Test");
		driver.quit();

	}

	@BeforeClass
	public void beforeClass() {
		

		System.out.println("Run Before Class");
	}
	
	@AfterClass
	public void afterClass() {
		
		System.out.println("Run After Class");

	}

	@BeforeSuite
	public void beforeSuite() {
		
		System.out.println("Run Before Suite");

	}

	@AfterSuite
	public void afterSuite() {
		
		System.out.println("Run After Suite");

	}
}
