//package com.jquery;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.Scanner;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import com.aventstack.extentreports.Status;
//
//import common.BaseTest;
//import pageObjects.jQuery.uploadFile.HomePageObject;
//import pageObjects.jQuery.uploadFile.PageGeneratorManager;
//import reportConfig.ExtentTestManagerV5;
//
//
//public class Level_15_ExtentV5_Screenshot extends BaseTest {
//	
//	private WebDriver driver;
//	private HomePageObject homePageObject;
//	
//	
//	// one File 
//	String cSharpFileName = "CSharp.png";
//	String javaFileName = "Java.png";
//	String pythonFileName = "Python.png";
//	String rubyFileName = "Ruby.png";
//	
//	// multiple File
//	String[] multipleFile = {cSharpFileName,javaFileName,pythonFileName,rubyFileName};
//	
//	
//	@Parameters({"browser","url"})
//	@BeforeClass
//	public void beforeClass(String browserName, String appurl) {
//
//		driver = getBrowserDriverOnlyOneUrl(browserName, appurl);
//		homePageObject = PageGeneratorManager.getHomePageObject(driver);
//		
//	}
//	
//	@Test
//	public void Upload_01_One_File_Per_Time (Method method) {
//		
//		ExtentTestManagerV5.startTest(method.getName(), "Upload_01_One_File_Per_Time");
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_File - Step 01: Load A File");
//
//		homePageObject.uploadMultipleFiles(driver, pythonFileName);
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_File - Step 02: Verify A File Loaded Successfully" + pythonFileName);
//		Assert.assertTrue(homePageObject.isFileLoadedByName(pythonFileName)); 
//		
//		// Test Failed to test attach screenshot
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_File - Step 03: Click Btn Start");
////		homePageObject.clickToStartButton();
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_File - Step 04: Verify Link File Uploaded Successfully" + pythonFileName);
//		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(pythonFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_File - Step 05: Verify Image File Uploaded Successfully" + pythonFileName);
//		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(pythonFileName));
//
//	}
//	
//	@Test
//	public void Upload_02_Multiple_File_Per_Time (Method method) {
//		
//		ExtentTestManagerV5.startTest(method.getName(), "Upload_02_Multiple_File_Per_Time");
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 01: Refresh Current Page '" + pythonFileName + "'");
//		homePageObject.refreshCurrentPage(driver);
//		
//		// Test Failed to test attach screenshot
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 02: Load Multiple File '" + multipleFile + "'");
////		homePageObject.uploadMultipleFiles(driver, multipleFile);
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 03: Verify Python File Loaded Successfully '" + pythonFileName + "'");
//		Assert.assertTrue(homePageObject.isFileLoadedByName(pythonFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 04: Verify CSharp File Loaded Successfully '" + cSharpFileName + "'");
//		Assert.assertTrue(homePageObject.isFileLoadedByName(cSharpFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 05: Verify Java File Loaded Successfully '" + javaFileName + "'");
//		Assert.assertTrue(homePageObject.isFileLoadedByName(javaFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 06: Verify Ruby File Loaded Successfully '" + rubyFileName + "'");
//		Assert.assertTrue(homePageObject.isFileLoadedByName(rubyFileName));
//		
//		// Test Failed to test attach screenshot
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 07: Click Btn Start");
////		homePageObject.clickToStartButton();
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 01: Verify Link File Uploaded Successfully '" + pythonFileName + "'");
//		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(pythonFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 02: Verify Link File Uploaded Successfully '" + cSharpFileName + "'");
//		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(cSharpFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 03: Verify Link File Uploaded Successfully '" + javaFileName + "'");
//		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(javaFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 04: Verify Link File Uploaded Successfully '" + rubyFileName + "'");
//		Assert.assertTrue(homePageObject.isFileLinkUpLoadedByName(rubyFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_Multiple_File - Step 05: Verify Image File Uploaded Successfully" + pythonFileName);
//		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(pythonFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_File - Step 07: Verify Image File Uploaded Successfully" + cSharpFileName);
//		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(cSharpFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_File - Step 08: Verify Image File Uploaded Successfully" + javaFileName);
//		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(javaFileName));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload_File - Step 09: Verify Image File Uploaded Successfully" + rubyFileName);
//		Assert.assertTrue(homePageObject.isFileImageUpLoadedByName(rubyFileName));
//	
//		
//	}
//	
//	public void sleepInSecond(long timeoutInSec){
//		
//		try {
//			
//			Thread.sleep(timeoutInSec * 1000);
//			
//		}catch (InterruptedException e) {
//			
//			e.printStackTrace();
//		}
//	}
//
//	@AfterClass
//	public void afterClass() {
//		
//		driver.quit();
//	}
//
//}
