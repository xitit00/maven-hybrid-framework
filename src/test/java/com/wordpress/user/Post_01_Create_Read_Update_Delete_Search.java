package com.wordpress.user;

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

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {
	
	private WebDriver driver;
	private String email;

	
	private String firstName  = "anh";
	private String lastName = "BTC";
	private String password = "123456";
	
	private String date  = "10";
	private String month = "8";
	private String year = "1998";
	
	private UserLoginPageObject loginPageObject;
	private UserHomePageObject homePageObject;
	private UserRegisterPageObject registerPageObject;
	private UserCustomerInfoPageObject customerInfoPageObject;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {

		driver = getBrowserDriverOnlyOneUrl(browserName, url);	

	}

	@Test
	public void Post_01_Create_New_Post() {

		log.info("Register - Step 01: Navigate to 'Register' page");

	}
	
	@Test
	public void Post_02_Search_Post() {

	
	}
	
	@Test
	public void Post_03_View_Post() {
		
		
		
	}
	
	@Test
	public void Post_04_Edit_Post() {
		
		
	}
	
	@Test
	public void Post_05_Delete_Post() {
		
		
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
