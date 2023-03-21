package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BasePage;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object_01_Register {
	
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String email;
	
	private String firstName = "anh";
	private String lastName = "BTC";
	private String password = "123456";
	
	
	private UserHomePageObject homePageObject;
	private UserRegisterPageObject registerPageObject;
	
	@BeforeClass
	public void beforeClass() {
		
		// Mo trinh duyet Firefox
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		
		// Driver sau khi khoi tao thi se co 1 cai ID dc gen ra , dung de gan vao method cua BasePage se ko bi error
		driver = new FirefoxDriver();
		
	
		// khoi tao homePageObject = cach gan ngc lai doi tuong class da dc khoi tao truoc ben trong class , sau do set gia tri 
		// cho driver
		homePageObject = UserHomePageObject.getHomePageObject();
		homePageObject.setDriver(driver);
		
		// khoi tao registerPageObject = cach override ham constructor
		registerPageObject = new UserRegisterPageObject(driver);
		
		// Set timeout tim element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		email = "anhBTC" + generateFakeNumber() + "@gmail.com"; 
		
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test
	public void Register_01_Empty_Data() {
		

		//Click to register link
		homePageObject.clickToRegisterLink();;
		
		//Click to register button
		registerPageObject.clickToRegisterButton();
		
		//Verify err message display
		Assert.assertEquals(registerPageObject.getTextFirstNameErr(), "First name is required.");
		Assert.assertEquals(registerPageObject.getTextLastNameErr(), "Last name is required.");
		Assert.assertEquals(registerPageObject.getTextEmailErr(), "Email is required.");
		Assert.assertEquals(registerPageObject.getTextPasswordErr(), "Password is required.");
		Assert.assertEquals(registerPageObject.getTextConfirmPasswordErr(), "Password is required.");
		
	}

	@Test
	public void Register_02_Invalid_Email() {
		
		//Click to register link
		homePageObject.clickToRegisterLink();;
		
		//Input to required fields
		registerPageObject.inputFirstName(firstName);
		registerPageObject.inputLastName(lastName);
		registerPageObject.inputEmail("1234.345");
		registerPageObject.inputPassword(password);
		registerPageObject.inputConfirmPassword(password);

		//Click to register button
		registerPageObject.clickToRegisterButton();
		
		//Verify err message display
		Assert.assertEquals(registerPageObject.getTextEmailErr() , "Wrong email");
	
	}
	
	@Test
	public void Register_03_Success() {
		
		
		//Click to register link
		homePageObject.clickToRegisterLink();;
		
		//Input to required fields
		registerPageObject.inputFirstName(firstName);
		registerPageObject.inputLastName(lastName);
		registerPageObject.inputEmail(email);
		registerPageObject.inputPassword(password);
		registerPageObject.inputConfirmPassword(password);
		
		//Click to register button
		registerPageObject.clickToRegisterButton();
		
		//Verify success message displayed
		Assert.assertEquals(registerPageObject.getTextRegisterSuccessMess(), "Your registration completed");
		
		//Click to log out
		registerPageObject.clickToLogout();
		
	}
	@Test
	public void Register_04_Existing_Email() {
		
		//Click to register link
		homePageObject.clickToRegisterLink();;
		
		//Input to required fields
		registerPageObject.inputFirstName(firstName);
		registerPageObject.inputLastName(lastName);
		registerPageObject.inputEmail(email);
		registerPageObject.inputPassword(password);
		registerPageObject.inputConfirmPassword(password);
		
		
		//Click to register button
		registerPageObject.clickToRegisterButton();
		
		//verify email exists 
		Assert.assertEquals(registerPageObject.getTextExistingsEmailErr(), "The specified email already exists");
		
	}
	@Test
	public void Register_05__Password_6_Than_Chars() {
		

		//Click to register link
		homePageObject.clickToRegisterLink();;
		
		//Input to required fields
		registerPageObject.inputFirstName(firstName);
		registerPageObject.inputLastName(lastName);
		registerPageObject.inputEmail("anhBTC" + generateFakeNumber() +"@gmail.com");
		registerPageObject.inputPassword("123");
		registerPageObject.inputConfirmPassword("123");
		
		//Click to register button
		registerPageObject.clickToRegisterButton();
		
		//verify err password
		Assert.assertEquals(registerPageObject.getTextPasswordErr(), "Password must meet the following rules:\nmust have at least 6 characters");

		
	}
	@Test
	public void Register_06__Invalid_Confirm_Password() {
		
		//Click to register link
		homePageObject.clickToRegisterLink();;
		
		//Input to required fields
		registerPageObject.inputFirstName(firstName);
		registerPageObject.inputLastName(lastName);
		registerPageObject.inputEmail("anhBTC" + generateFakeNumber() +"@gmail.com");
		registerPageObject.inputPassword(password);
		registerPageObject.inputConfirmPassword("123567");
		
		//Click to register button
		registerPageObject.clickToRegisterButton();
		
		//verify err confirm password
		Assert.assertEquals(registerPageObject.getTextConfirmPasswordErr(), "The password and confirmation password do not match.");
		
		
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
