package com.jquery;

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

import common.BaseTest;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;


public class Level_17_Custom_Close_Driver extends BaseTest {
	
	private WebDriver driver;
	private HomePageObject homePageObject;
	
	
	// one File 
	String cSharpFileName = "CSharp.png";
	String javaFileName = "Java.png";
	String pythonFileName = "Python.png";
	String rubyFileName = "Ruby.png";
	
	// multiple File
	String[] multipleFile = {cSharpFileName,javaFileName,pythonFileName,rubyFileName};
	
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appurl) {

		driver = getBrowserDriverOnlyOneUrl(browserName, appurl);
		homePageObject = PageGeneratorManager.getHomePageObject(driver);
		log.info("Upload_File - Step 01: Load A File" + pythonFileName);
		homePageObject.uploadMultipleFiles(driver, pythonFileName);
		
		// đổi true -> false để làm cho fail tại before class luôn -> skip TC và @After class 
		log.info("Upload_File - Step 02: Verify A File Loaded Successfully" + pythonFileName);
		//Assert.assertTrue(homePageObject.isFileLoadedByName(pythonFileName)); 
		Assert.assertFalse(homePageObject.isFileLoadedByName(pythonFileName)); 
		
		log.info("Upload_File - Step 03: Click Btn Start");
		homePageObject.clickToStartButton();
		
		log.info("Upload_File - Step 04: Verify Link File Uploaded Successfully" + pythonFileName);
		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(pythonFileName));
		
		log.info("Upload_File - Step 05: Verify Image File Uploaded Successfully" + pythonFileName);
		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(pythonFileName));
	}
	
	@Test
	public void TC_01() {
		
		
		
		
	}
	
	@Test
	public void TC_02() {
		
		
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
		
		//driver.quit();
		
		// custom close browser để lúc nào cũng tắt browser driver đi ( dù có gặp bất kỳ vấn đề gì đi nữa ) 
		closeBrowserAndDriver();
		
//		if (driver != null) {
//			
//			driver.quit();
//		}
	
	}

}
