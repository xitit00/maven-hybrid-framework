package com.nopcommerce.user;

import java.lang.reflect.Method;
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

import com.aventstack.extentreports.Status;
import com.nopcomerce.data.UserData;
import com.nopcomerce.data.UserDataMapper;

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
import reportConfig.ExtentTestManagerV5;

public class Level_21_Multiple_Environment extends BaseTest {
	
	private WebDriver driver;
	private String email;

	
	private String firstName;
	private String lastName;
	private String password;
	
	private String date = "10";
	private String month = "8";
	private String year = "1998";
	
	private UserLoginPageObject loginPageObject;
	private UserHomePageObject homePageObject;
	private UserRegisterPageObject registerPageObject;
	private UserCustomerInfoPageObject customerInfoPageObject;
	
	UserDataMapper userData;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {

		System.out.println("URL: " + url);
		
		driver = getBrowserDriverOnlyOneUrl(browserName, url);
//		
		userData = UserDataMapper.getUserData();
//	
//		//  use data test from UserData by JSON
		email =  userData.getEmailAddress() + generateFakeNumber() + "@fakemail.com";
		System.out.println("Email Add: " + email);
		lastName = userData.getLastName();
		System.out.println("Last Name: " + lastName);
		firstName = userData.getFirstName();
		System.out.println("First Name: " + firstName);
		password = userData.getPassword();
		System.out.println("Password: " + password);
		date = userData.getDate();
		month = userData.getMonth();
		year = userData.getYear();
		
		// open URL -> Home : khoi tao Home 
		homePageObject = PageGeneratorManager.getUserHomePage();
		homePageObject.setDriver(driver);		

		
		// Array Json 
		String subjectName = userData.getSubjects().get(0).getSubjectName();
		String subjectName1 = userData.getSubjects().get(1).getSubjectName();
		String subjectName2 = userData.getSubjects().get(2).getSubjectName();
		
		String subjectPoint = userData.getSubjects().get(0).getSubjectPoint();
		String subjectPoint1 = userData.getSubjects().get(1).getSubjectPoint();
		String subjectPoint2 = userData.getSubjects().get(2).getSubjectPoint();
		
		System.out.println("Subject : " + subjectName + subjectPoint);
		System.out.println("Subject1 : " + subjectName1 + subjectPoint1);
		System.out.println("Subject2 : " + subjectName2 + subjectPoint2);
		
		
		
	}

	@Test
	public void User_01_Register(Method method) {

//		ExtentTestManagerV5.startTest(method.getName(), "User_01_Register");
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Navigate to 'Register' page");
		log.info("Register - Step 01: Navigate to 'Register' page");
		registerPageObject = homePageObject.clickToRegisterLink();
		
		log.info("Register - Step 02: Click to Radio Button");
		registerPageObject.clickToRadioButtonByLabel(driver, "Male");

		log.info("Register - Step 03: Enter to Firstname textbox with value is '" + firstName + "'");
		//registerPageObject.inputFirstName(firstName);
		registerPageObject.inputToTextboxByID(driver, "FirstName", firstName);
		
		log.info("Register - Step 04: Enter to Lastname textbox with value is '" + lastName + "'");
		//registerPageObject.inputLastName(lastName);
		registerPageObject.inputToTextboxByID(driver, "LastName", lastName);
		
		log.info("Register - Step 05: Select to dropdown DateOfBirthDay with value is '" + date + "'");
		registerPageObject.selectToDropdownByName(driver, "DateOfBirthDay", date);
		log.info("Register - Step 06: Select to dropdown DateOfBirthMonth with value is '" + month + "'");
		registerPageObject.selectToDropdownByName(driver, "DateOfBirthMonth", month);
		log.info("Register - Step 07: Select to dropdown DateOfBirthYear with value is '" + year + "'");
		registerPageObject.selectToDropdownByName(driver, "DateOfBirthYear", year);
		
		log.info("Register - Step 08: Enter to Email textbox with value is '" + email + "'");
		//registerPageObject.inputEmail(email);
		registerPageObject.inputToTextboxByID(driver, "Email", email);
		
		log.info("Register - Step 09: Enter to Password textbox with value is '" + password + "'");
		//registerPageObject.inputPassword(password);
		registerPageObject.inputToTextboxByID(driver, "Password", password);
		
		log.info("Register - Step 10: Enter to Confirm Password textbox with value is '" + password + "'");
		//registerPageObject.inputConfirmPassword(password);
		registerPageObject.inputToTextboxByID(driver, "ConfirmPassword", password);
		
		log.info("Register - Step 11: Click to Check Box");
		registerPageObject.clickToCheckBoxByLabel(driver, "Newsletter:");
		
		// sleep 10s để check data hiển thị trên các element 
		registerPageObject.sleepInSecond(10);

		// Click to register button
		log.info("Register - Step 12: Click to 'Register' button");
		//registerPageObject.clickToRegisterButton();
		registerPageObject.clickToButtonByText(driver,"Register");

		// Verify success message displayed
		log.info("Register - Step 13: Verify register success message is displayed");
		Assert.assertEquals(registerPageObject.getTextRegisterSuccessMess(), "Your registration completed");
		
		System.out.println("Username: " + userData.getLoginUsername());
		System.out.println("Password: " + userData.getLoginUserPassword());

	}
	
	@Test
	public void User_02_Login() {

		// Register click log out to Home -> qua trang Home -> khởi tạo Home
		log.info("Login - Step 01: Navigate to 'Login' page");
//		homePageObject = registerPageObject.clickToLogout();
		loginPageObject = registerPageObject.clickToLoginLink();

		// Input to required fields
		log.info("Login - Step 02: Enter to Email textbox with value is '" + email + "'");
		//loginPageObject.inputEmail(email);
		loginPageObject.inputToTextboxByID(driver, "Email", email);
		
		log.info("Login - Step 03: Enter to Password textbox with value is '" + password + "'");
		//loginPageObject.inputPassword(password);
		loginPageObject.inputToTextboxByID(driver, "Password", password);

		// Login Sucessfull -> Home
		log.info("Login - Step 04: Click to 'Login' button");
		//homePageObject = loginPageObject.clickToLoginButton();
		loginPageObject.clickToButtonByText(driver, "Log in");
		// switch page sau khi click button vẫn che dấu đc việc khởi tạo 
		homePageObject = PageGeneratorManager.getUserHomePage();
		homePageObject.setDriver(driver);	
		
		// Verify success message displayed
		log.info("Login - Step 05: Verify login success message is displayed");
		Assert.assertTrue(homePageObject.checkDisplayMyAccountLink());
	}
	
	@Test
	public void User_03_My_Account() {
		
		log.info("My Account - Step 01: Navigate to 'My Account' page");
		customerInfoPageObject = homePageObject.openMyAccountPage();
		
		log.info("My Account - Step 02: Verify 'Customer Info' page is displayed");
		Assert.assertTrue(customerInfoPageObject.checkDisplayCustomerInfoPage());
		
		log.info("My Account- Step 03: Verify 'First Name' value is correctly");
		Assert.assertEquals(customerInfoPageObject.getTextboxAttributeValueByID(driver, "FirstName"), firstName);
		
		log.info("My Account- Step 04: Verify 'Last Name' value is correctly");
		Assert.assertEquals(customerInfoPageObject.getTextboxAttributeValueByID(driver, "LastName"), lastName);
		
		log.info("My Account- Step 05: Verify 'Email' value is correctly");
		Assert.assertEquals(customerInfoPageObject.getTextboxAttributeValueByID(driver, "Email"), email);
		
	}
	
	public void sleepInSecond(long timeoutInSec){
		
		try {
			
			Thread.sleep(timeoutInSec * 1000);
			
		}catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		
		closeBrowserAndDriver();
	}

}
