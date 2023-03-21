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
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_06_Page_Generator_Manager_II extends BaseTest {
	
	private WebDriver driver;
	private String emailExisting;
	private String emailInvalid = "anh.1234";
	private String emailNotFound;
	
	private String firstName  = "anh";
	private String lastName = "BTC";
	private String password = "123456";
	
	
	private UserLoginPageObject loginPageObject;
	private UserHomePageObject homePageObject;
	private UserRegisterPageObject registerPageObject;
	
	@Parameters({"browser","environment"})
	@BeforeClass
	public void beforeClass(String browserName, String environment) {

		driver = getBrowserDriver(browserName, environment);
		
		// Set timeout tim element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// open URL 
		driver.get("https://demo.nopcommerce.com/");
		
		emailExisting = "anhBTC" + generateFakeNumber() + "@gmail.com"; 
		emailNotFound = "anhBTC" + generateFakeNumber() + "@mail.com";

		// open URL -> Home : khoi tao Home 
		homePageObject = UserHomePageObject.getHomePageObject();
		homePageObject.setDriver(driver);
		
		//Click to register link
		homePageObject.clickToRegisterLink();
		
		//Home click Register Link -> qua trang Register -> khởi tạo Register
		registerPageObject = new UserRegisterPageObject(driver);
		
		//Input to required fields
		registerPageObject.inputFirstName(firstName);
		registerPageObject.inputLastName(lastName);
		registerPageObject.inputEmail(emailExisting);
		registerPageObject.inputPassword(password);
		registerPageObject.inputConfirmPassword(password);
		
		//Click to register button
		registerPageObject.clickToRegisterButton();
		
		//Verify success message displayed
		Assert.assertEquals(registerPageObject.getTextRegisterSuccessMess(), "Your registration completed");
		
		//Click to log out
		registerPageObject.clickToLogout();

		//Register click log out to Home -> qua trang Home -> khởi tạo Home
		homePageObject = UserHomePageObject.getHomePageObject();
		homePageObject.setDriver(driver);
		
		
	}
	

	@Test
	public void Login_01_Empty_Data() {
		

		//Click to login link
		homePageObject.clickToLoginLink();
		
		loginPageObject = new UserLoginPageObject(driver);
		
		//Click to login button
		loginPageObject.clickToLoginButton();
		
		//Verify err message display
		Assert.assertEquals(loginPageObject.getErrMessageEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		
		//Click to login link
		homePageObject.clickToLoginLink();
		
		//Home click Login Link -> qua trang Login -> khởi tạo login lại 
		loginPageObject = new UserLoginPageObject(driver);
		
		//Input to required fields
		loginPageObject.inputEmail(emailInvalid);
	
		//Click to login button
		loginPageObject.clickToLoginButton();
		
		//Verify err message display
		Assert.assertEquals(loginPageObject.getErrMessageEmailTextbox() , "Wrong email");
	
	}
	
	@Test
	public void Login_03_Email_Not_Found() {
		
		//Click to login link
		homePageObject.clickToLoginLink();
		
		//Home click Login Link -> qua trang Login -> khởi tạo login lại 
		loginPageObject = new UserLoginPageObject(driver);
		
		//Input to required fields
		loginPageObject.inputEmail(emailNotFound);
		
		//Click to login button
		loginPageObject.clickToLoginButton();
		
		//Verify success message displayed
		Assert.assertEquals(loginPageObject.getErrMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
		
	}
	
	@Test
	public void Login_04_Existing_Email_And_Empty_Password() {
		
		//Click to login link
		homePageObject.clickToLoginLink();
		
		//Home click Login Link -> qua trang Login -> khởi tạo login lại 
		loginPageObject = new UserLoginPageObject(driver);
		
		//Input to required fields
		loginPageObject.inputEmail(emailExisting);
		loginPageObject.inputPassword("");
		
		//Click to login button
		loginPageObject.clickToLoginButton();
		
		//verify email exists 
		Assert.assertEquals(loginPageObject.getErrMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
	}
	
	@Test
	public void Login_05_Existings_Email_And_Wrong_Password() {
		
		//Click to login link
		homePageObject.clickToLoginLink();
		
		//Home click Login Link -> qua trang Login -> khởi tạo login lại 
		loginPageObject = new UserLoginPageObject(driver);
		
		//Input to required fields
		loginPageObject.inputEmail(emailExisting);
		loginPageObject.inputPassword("123");
		
		//Click to register button
		loginPageObject.clickToLoginButton();
		
		//verify err password
		Assert.assertEquals(loginPageObject.getErrMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		
	}
	
	@Test
	public void Login_06_Email_Password_Successfully() {
		
		// Click to login link
		homePageObject.clickToLoginLink();

		// Home click Login Link -> qua trang Login -> khởi tạo login lại
		loginPageObject = new UserLoginPageObject(driver);
		
		// Input to required fields
		loginPageObject.inputEmail(emailExisting);
		loginPageObject.inputPassword(password);

		//Click to register button
		loginPageObject.clickToLoginButton();
		
		//Login Sucessfull -> Home
		homePageObject = UserHomePageObject.getHomePageObject();
		homePageObject.setDriver(driver);
		
		//verify err confirm password
		Assert.assertTrue(homePageObject.checkDisplayMyAccountLink());
		Assert.assertTrue(homePageObject.checkDisplayLogout());
		
		
	}
	
	public int generateFakeNumber() {
		
		Random random = new Random();
		return random.nextInt(99999);
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
