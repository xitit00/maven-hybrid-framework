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


public class Level_12_Assert_Verify extends BaseTest {
	
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
	public void Upload_01_One_File_Per_Time () {
		
		// đừng thắc mắc tại sao ko implement uploadMultipleFiles ở homePage như thói quen , đơn giản là vì nó có hàm uploadFile gọi đc ở tất cả site app và kế thừa BasePage.
		// load 1 File lên rồi verify kiểu gì ? 
		homePageObject.uploadMultipleFiles(driver, pythonFileName);
		
		// verify tên file display là load thành công , nhưng đây mới chỉ là load file chưa phải là upload , vậy upload kiểu gì ?
		verifyTrue(homePageObject.isFileLoadedByName(pythonFileName)); 
		
		// click Btn start để upload , nhưng để ý là sẽ có multiple files cùng nút upload , vậy thì làm thế nào để click Btn Start để toàn bộ file upload 
		// = cách duyệt qua vòng lặp element lấy ra các nút upload và click 
		homePageObject.clickToStartButton();
		
		// Sau khi click Btn Start và upload thành công thì mình sẽ verify 2 cái
		// 1/ link file hình uploaded thành công 
		verifyTrue(homePageObject.isFileLinkUpLoadedByName(pythonFileName));
		
		// 2/ nếu là hình upload lên thì xem nó phải là cái hình hay ko , vì nếu là hình thì nó sẽ có chiều rộng cao ( dùng javascript để check natural width vs complete) 
		// lưu ý : 1/ là chỉ có thẻ img mới check đc , vậy phải trỏ đến thẻ /img thì mới check đc 
		//		   2/ Nhưng cũng có khả năng là hình nó bị vỡ ko upload lên đc thì khi chạy isImageLoaded => false 
		//		   3/ Cũng có thể chạy test thử ở console , nếu thành công => true , ngc lại => false
		verifyTrue(homePageObject.isFileImageUpLoadedByName(pythonFileName));
		
		
	}
	
	@Test
	public void Upload_02_Multiple_File_Per_Time () {
		
		// refresh lại trang ( bỏ đi hành động của Upload_01)
		homePageObject.refreshCurrentPage(driver);
		
		// đừng thắc mắc tại sao ko implement uploadMultipleFiles ở homePage như thói quen , đơn giản là vì nó có hàm uploadFile gọi đc ở tất cả site app và kế thừa BasePage.
		// ở multiple File thì có bao nhiêu file upload lên thì verify hết 
		// Step 01 - Load mutilple file
		homePageObject.uploadMultipleFiles(driver, multipleFile);
		
		// Step 02 - Verify multiple file loaded success
		verifyTrue(homePageObject.isFileLoadedByName(pythonFileName));
		verifyTrue(homePageObject.isFileLoadedByName(cSharpFileName));
		verifyTrue(homePageObject.isFileLoadedByName(javaFileName));
		verifyTrue(homePageObject.isFileLoadedByName(rubyFileName));
		
		// Step 03 - Click to start button
		homePageObject.clickToStartButton();
		
		// Step 04 - Verify multiple file link uploaded success
		verifyTrue(homePageObject.isFileLinkUpLoadedByName(pythonFileName));
		verifyTrue(homePageObject.isFileLinkUpLoadedByName(cSharpFileName));
		verifyTrue(homePageObject.isFileLinkUpLoadedByName(javaFileName));
		verifyTrue(homePageObject.isFileLinkUpLoadedByName(rubyFileName));
		
		// Step 05 - Verify multiple file image uploaded success
		verifyTrue(homePageObject.isFileImageUpLoadedByName(pythonFileName));
		verifyTrue(homePageObject.isFileImageUpLoadedByName(cSharpFileName));
		verifyTrue(homePageObject.isFileImageUpLoadedByName(javaFileName));
		verifyTrue(homePageObject.isFileImageUpLoadedByName(rubyFileName));
		
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
