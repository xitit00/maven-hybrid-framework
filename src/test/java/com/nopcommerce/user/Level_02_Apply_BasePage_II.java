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

public class Level_02_Apply_BasePage_II {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email;
	BasePage basePage;
	
	@BeforeClass
	public void beforeClass() {
		
		// Mo trinh duyet Firefox
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		
		// Driver sau khi khoi tao thi se co 1 cai ID dc gen ra , dung de gan vao method cua BasePage se ko bi error
		driver = new FirefoxDriver();
		
		 //Khoi tao basePage don thuan
		//basePage = new BasePage();
		
		// Khoi tao basePage xai constructor trong BasePage
		// Che giau viec khoi tao cua 1 doi tuong
		basePage = BasePage.getBasePageObject();
		
		
		// Set timeout tim element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		email = "anhBTC" + generateFakeNumber() + "@gmail.com"; 
		
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test
	public void TC_01_Register_Empty_Data() {
		
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver,"//a[@class='ico-register']");
		
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver,"//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
		
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		
		driver.navigate().refresh();
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "anh");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "BTC");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", "1234.345");
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

				
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver,"//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	
		
	}
	
	@Test
	public void TC_03_Register_Success() {
		
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver,"//a[@class='ico-register']");
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "anh");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "BTC");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", email);
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver,"//button[@id='register-button']");
		
		
		basePage.waitForElementVisible(driver, "//div[@class='result']");
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
		
		
	}
	@Test
	public void TC_04_Register_Existing_Email() {
		
		basePage.backToPage(driver);
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "anh");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "BTC");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", email);
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver,"//button[@id='register-button']");
		
		
		//div[contains(@class,'message-error')]
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='message-error validation-summary-errors']//li"), "The specified email already exists");
		
	}
	@Test
	public void TC_05_Register_Password_6_Than_Chars() {
		
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver,"//a[@class='ico-register']");
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "anh");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "BTC");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", "anhBTC" + generateFakeNumber() +"@gmail.com");
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "123");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
		
		
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver,"//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");

		
	}
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver,"//a[@class='ico-register']");
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "anh");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "BTC");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", "anhBTC" + generateFakeNumber() +"@gmail.com");
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123567");
		
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver,"//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
		
		
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
