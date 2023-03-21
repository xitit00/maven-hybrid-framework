package com.jquery;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
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


public class Level_15_ExtentV4 extends BaseTest {
	
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
		
	}
	
	@Test
	public void Upload_01_One_File_Per_Time (Method method) {
		
		
		homePageObject.uploadMultipleFiles(driver, pythonFileName);
		
		Assert.assertTrue(homePageObject.isFileLoadedByName(pythonFileName)); 
		
		// Test Failed to test attach screenshot
//		homePageObject.clickToStartButton();
		
		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(pythonFileName));
		
		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(pythonFileName));
		
	}
	
	@Test
	public void Upload_02_Multiple_File_Per_Time (Method method) {
		
		homePageObject.refreshCurrentPage(driver);
		
//		homePageObject.uploadMultipleFiles(driver, multipleFile);
		
		Assert.assertTrue(homePageObject.isFileLoadedByName(pythonFileName));
		
		Assert.assertTrue(homePageObject.isFileLoadedByName(cSharpFileName));
		

		Assert.assertTrue(homePageObject.isFileLoadedByName(javaFileName));
		
		Assert.assertTrue(homePageObject.isFileLoadedByName(rubyFileName));
		
		// Test Failed to test attach screenshot
//		homePageObject.clickToStartButton();
		
		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(pythonFileName));
		
		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(cSharpFileName));
		
		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(javaFileName));
		
		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(rubyFileName));
		
		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(pythonFileName));
		
		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(cSharpFileName));
		
		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(javaFileName));
		
		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(rubyFileName));
		
		
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
