package com.liveguru;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import pageObjects.liveguru.AdminLoginPageObject;
import pageObjects.liveguru.HomePageObject;
import pageObjects.liveguru.PageGeneratorManager;

public class HW_Handle_DataTable_DataGrid extends BaseTest {

	private WebDriver driver;
	private HomePageObject homePage;
	private AdminLoginPageObject adminLoginPage;
	
	String firstName = "anh";
	String lastName = "nguyen";
	String emailAddress;
	String passWord = "123456";
	String confirmPassWord = "123456";
	
	String adminUserName = "user01";
	String adminPassWord = "guru99com";
	
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appurl) {
		
		emailAddress = "autotest" + getRandomNumber() + "@gmail.com";

		driver = getBrowserDriverOnlyOneUrl(browserName, appurl);
		homePage = PageGeneratorManager.getHomePageObject(driver);
	}

	@Test
	public void TC_01_Verify_Account_Registered_Successfully() {
		
		homePage.clickMyAccountLink();
		
		homePage.clickCreateAccountButton();
		
		homePage.enterFirstNameTextbox("firstname",firstName);
		homePage.enterLastNameTextbox("lastname",lastName);
		homePage.enterEmailAddressTextbox("email_address",emailAddress);
		homePage.enterPassWordTextbox("password",passWord);
		homePage.enterConfirmPasswordTextbox("confirmation",confirmPassWord);
		
		sleepInSecond(2);
		
		homePage.clickRegisterButton();
		
		verifyTrue(homePage.isRegisterSuccessfulTextDisplay());
		

	}
	
	@Test
	public void TC_02_Switch_User_To_Admin() {
		
		homePage.openPageUrl(driver, "http://live.techpanda.org/index.php/backendlogin/customer/");
		
		adminLoginPage = PageGeneratorManager.getAdminLoginPageObject(driver);
		
		adminLoginPage.enterUserNameTextbox("username",adminUserName);
		adminLoginPage.enterPassWordTextbox("password",adminPassWord);
		
		adminLoginPage.clickToLoginButton();
		
	}
	
	@Test
	public void TC_03_Close_Pop_Up() {
		
		verifyTrue(adminLoginPage.isPopupDisplayed());
		
		adminLoginPage.closePopUp();
	}
	
	@Test 
	public void TC_04_Verify_Name_And_Email_Displayed() {
		
		String fullName = firstName + " " + lastName;
		
		System.out.println("Full name: ");
		
		adminLoginPage.enterFullName("name",fullName);
		adminLoginPage.enterEmail("email",emailAddress);
		
		sleepInSecond(2);
		
		adminLoginPage.clickBtnSearch();
		
		verifyTrue(adminLoginPage.isNameAndEmailDisplayed(fullName, emailAddress));
	}
	
	@Test
	public void TC_05_Delete_Account_Registered() {
		
		sleepInSecond(3);
		
		adminLoginPage.clickToCheckbox(emailAddress);
		verifyTrue(adminLoginPage.isCheckBoxSelected(emailAddress));
		
		adminLoginPage.selectDeleteInDropdown("delete");
		verifyEquals(adminLoginPage.getSelectedText(), "Delete");
		
		adminLoginPage.clickBtnSubmit();
		adminLoginPage.clickAcceptAlert();
		
		verifyTrue(adminLoginPage.isDeletedAccountSuccessful());
		
	}
	
	@Test 
	public void TC_06_Switch_Admin_To_User() {
		
		adminLoginPage.openPageUrl(driver, "http://live.guru99.com");
		
//		homePage = PageGeneratorManager.getHomePageObject(driver);
		
//		// nhớ log out vì khi log out ra thì nó sẽ đi vào như rule ban đầu
//		homePage.clickAccountLink();
//		homePage.clickLogoutLink();
		
		homePage.clickMyAccountLink();
		
		homePage.enterEmailLoginTextbox("email",emailAddress);
		homePage.enterPasswordLoginTextbox("pass",passWord);
		
		sleepInSecond(2);
		
		homePage.clickBtnLogin();
		verifyTrue(homePage.isResultTextDisplayed());
		
	}
	
	// Random Num
	public int getRandomNumber() {

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
