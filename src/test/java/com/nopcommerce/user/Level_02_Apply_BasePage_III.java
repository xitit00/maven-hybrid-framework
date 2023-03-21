package com.nopcommerce.user;

import java.util.List;
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

public class Level_02_Apply_BasePage_III extends BasePage {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email;
	
	@BeforeClass
	public void beforeClass() {
		
		// Mo trinh duyet Firefox
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		
		// Driver sau khi khoi tao thi se co 1 cai ID dc gen ra , dung de gan vao method cua BasePage se ko bi error
		driver = new FirefoxDriver();
		
		
		// Set timeout tim element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		email = "anhBTC" + generateFakeNumber() + "@gmail.com"; 
		
		driver.get("https://demo.nopcommerce.com/");
		

	}

	@Test
	public void TC_01_Register_Empty_Data() {
		
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver,"//a[@class='ico-register']");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver,"//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
		
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		
		driver.navigate().refresh();
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "anh");
		sendkeyToElement(driver, "//input[@id='LastName']", "BTC");
		sendkeyToElement(driver, "//input[@id='Email']", "1234.345");
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

				
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver,"//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	
		
	}
	
	@Test
	public void TC_03_Register_Success() {
		
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver,"//a[@class='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "anh");
		sendkeyToElement(driver, "//input[@id='LastName']", "BTC");
		sendkeyToElement(driver, "//input[@id='Email']", email);
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver,"//button[@id='register-button']");
		
		
		waitForElementVisible(driver, "//div[@class='result']");
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		
		
	}
	@Test
	public void TC_04_Register_Existing_Email() {
		
		backToPage(driver);
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "anh");
		sendkeyToElement(driver, "//input[@id='LastName']", "BTC");
		sendkeyToElement(driver, "//input[@id='Email']", email);
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver,"//button[@id='register-button']");
		
		
		//div[contains(@class,'message-error')]
		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']//li"), "The specified email already exists");
		
	}
	@Test
	public void TC_05_Register_Password_6_Than_Chars() {
		
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver,"//a[@class='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "anh");
		sendkeyToElement(driver, "//input[@id='LastName']", "BTC");
		sendkeyToElement(driver, "//input[@id='Email']", "anhBTC" + generateFakeNumber() +"@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']", "123");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
		
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver,"//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");

		
	}
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver,"//a[@class='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "anh");
		sendkeyToElement(driver, "//input[@id='LastName']", "BTC");
		sendkeyToElement(driver, "//input[@id='Email']", "anhBTC" + generateFakeNumber() +"@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123567");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver,"//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
		
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
