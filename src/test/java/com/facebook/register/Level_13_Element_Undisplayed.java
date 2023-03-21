package com.facebook.register;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcomerce.data.UserData.Login;

import common.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;


public class Level_13_Element_Undisplayed extends BaseTest {
	
	private WebDriver driver;
	private LoginPageObject loginPage;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appurl) {
		
		driver = getBrowserDriverOnlyOneUrl(browserName, appurl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}
	
	@Test
	public void TC_01_Verify_Element_Displayed() {
		
		loginPage.clickToCreateNewAccountButton();
		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());
		
	}
	
	@Test
	public void TC_02_Verify_Undisplayed_In_DOM() {
		
		// Ở TH , UI ko hiển thị , vẫn có trong DOM thì mình sẽ có 2 cách làm :
		
		//1/ Verify True - cho hàm trả về Undisplayed - check việc ko hiển thị là đúng = hàm isElementUnDisplayed ở BasePage
		//verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
		
		
		//2/ Verify False - cho hàm trả về Displayed - check việc hiển thị là sai = hàm isElementDisplayed ở Base Page
//		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		// ------------------------ //
		
		// Nhưng mà nên dùng 1 cái để làm thôi cho dễ hiểu , dễ nắm bắt
		
		// Nếu như mình mong đợi 1 cái hàm vừa verify displayed / undisplayed thì ko đc kết hợp wait , 
		// vì tuỳ lúc nó sẽ là visible hoặc invisible tuỳ 
		
		// Verify True - mong đợi Confirm Email displayed (true) 
		loginPage.enterToEmailAddressTextbox("automationfc@gmail.com");
		sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		// Verify False - mong đợi Confirm Email Undisplayed (false) 
		loginPage.enterToEmailAddressTextbox("");
		sleepInSecond(3);
		
		// cách làm bình thường verify false vs isDisplay = false => false vs false => Đúng 
//		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		// cách làm tối ưu gom lại dành cho case Undisplayed 
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	}
	
	@Test
	public void TC_03_Verify_Undisplayed_Not_In_DOM() {
		
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(3);
		
		// Mình sẽ thử Verify False - mong đợi Confirm Email displayed (false) => false vs false => Đúng 
		// isDisplayed: bản chất ko thể kiểm tra 1 element ko có trong DOM được ,
		// vì elemet ko có trong DOM thì ko thể nào findElemnt để làm các hành động tiếp theo : wait , click , sendkey , check , ...
		// verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed()); 
		
		// Khi close cái form register đi thì Confirm Email ko còn trong DOM nữa 
		// Nên hàm findElement sẽ bị fail vì ko tìm thấy element 
		// 1 - Nó sẽ chờ hết time out của implicit : 30s
		// 2 - Nó sẽ đánh fail test case tại đúng step này luôn 
		// 3 - Ko có chạy các step còn lại nữa 
		
		// Mong đợi Confirm Email Undisplayed (true)
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
		
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
