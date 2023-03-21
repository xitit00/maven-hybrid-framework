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
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;



public class Level_10_DataTable_DataGrid extends BaseTest {
	
	private WebDriver driver;
	private HomePageObject homePageObject;
	List<String> actualAllCountryValueAllPage;
	List<String> expectedAllCountryValueAllPage = new ArrayList<String>();
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appurl) {

		driver = getBrowserDriverOnlyOneUrl(browserName, appurl);
	
		homePageObject = PageGeneratorManager.getHomePageObject(driver);
	}
	
	//Handle_DataTable_DataGrid_Part1
	
	//@Test
	public void Table_01_Paging() {
		
		// case này dùng để phân trang , nếu như trong TH có data table 
		// click vào các số trang mong muốn , sleep vài giây để load trang ( Action )
		// check page number hiện tại đã click là actived , dựa vào thay đổi element để ( verify )
		
		homePageObject.openPagingByPageNumber("10");
		Assert.assertTrue(homePageObject.isPageNumberActived("10"));
		sleepInSecond(3);
		
		homePageObject.openPagingByPageNumber("20");
		Assert.assertTrue(homePageObject.isPageNumberActived("20"));
		sleepInSecond(3);
		
		homePageObject.openPagingByPageNumber("7");
		Assert.assertTrue(homePageObject.isPageNumberActived("7"));
		sleepInSecond(3);
		
	}
	
	//@Test
	public void Table_02_Enter_To_Header() {
		
		// sau khi chạy xong Table_01 action và verify việc phân trang 
		// 
		// ở Table_02 ta sẽ check việc search = cách input text and enter xem có hiệu quả ko nhé , nhớ refresh lại page 1
		
		//homePageObject.refreshCurrentPage(driver);
		
		homePageObject.enterToHeaderTextboxByLabel("Country","Argentina");
		homePageObject.enterToHeaderTextboxByLabel("Females","338282");
		homePageObject.enterToHeaderTextboxByLabel("Males","349238");
		homePageObject.enterToHeaderTextboxByLabel("Total","687522");
		sleepInSecond(2);
		
		homePageObject.enterToHeaderTextboxByLabel("Country","Afghanistan");
		homePageObject.enterToHeaderTextboxByLabel("Females","384187");
		homePageObject.enterToHeaderTextboxByLabel("Males","407124");
		homePageObject.enterToHeaderTextboxByLabel("Total","791312");
		sleepInSecond(2);
		
		// search key "zzz" and verify no data
		homePageObject.enterToHeaderTextboxByLabel("Country","zzz");
		verifyTrue(homePageObject.isElementRowUnDisplayed());
		sleepInSecond(2);
	}
	
	//@Test
	public void Table_03_Get_All_Row_Each_Page() {
		
		// Đọc dữ liệu file country.txt ra 
		// Lưu vào 1 list expectedAllCountryValueAllPage
		
		File file = new File("/Users/anhnguyen/Documents/Selenium/03-Hybrid Automation Framework/countryData/country.txt");
	
		try {
			var scan = new Scanner(file);
			while (scan.hasNextLine()) {
				expectedAllCountryValueAllPage.add(scan.nextLine());
			  }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		actualAllCountryValueAllPage = homePageObject.getValueEachRowAtAllPage();
		System.out.print("Expected size : " + expectedAllCountryValueAllPage.size());
		System.out.print("Actual size : " + actualAllCountryValueAllPage.size());
		
		Assert.assertEquals(actualAllCountryValueAllPage, expectedAllCountryValueAllPage);
	}
	
	//Handle_DataTable_DataGrid_Part2
	@Test
	public void Table_04_Action_At_Any_Row() {
		
		// khi chạy TC số 4 cho việc click icon thì đóng @Test ở TC_03 vs TC_02
		
		// click btnLoadDemo
		homePageObject.clickToLoadButton();
		sleepInSecond(2);
		
		// dựa vào index column , row -> cell ( cái mình sẽ thao tác )
		// Column name: ( dùng tên column để phân biệt column mình muốn nhập ) - tham số 1 -> lấy ra đc column index
		// row number: tại row nào , ex : nhập vào textbox tại dòng 1 , 5 hoặc 7 - tham số 2
		// value để nhập liệu - tham số 3
		homePageObject.enterToTextBoxByColumnNameAtRowNumber("Album","2","Michael 97");
		sleepInSecond(2);
		
		homePageObject.enterToTextBoxByColumnNameAtRowNumber("Artist","4","Micheal Jackson");
		sleepInSecond(2);
		
		homePageObject.enterToTextBoxByColumnNameAtRowNumber("Year","3","1997");
		sleepInSecond(2);
		
		homePageObject.enterToTextBoxByColumnNameAtRowNumber("Price","1","500");
		sleepInSecond(2);
		
		// select dropdown 
		
		homePageObject.selectDropDownByColumnNameAtRowNumber("Origin","5","Japan");
		sleepInSecond(2);
		String actualText = homePageObject.getActualText("Origin","5");
		System.out.println("Actual text: " + actualText);
		verifyEquals(actualText , "Japan");
		
		// checkbox 
		
		homePageObject.checkToCheckBoxByColumnNameAtRowNumber("With Poster?","3");
		homePageObject.checkToCheckBoxByColumnNameAtRowNumber("With Poster?","5");
		
		verifyTrue(homePageObject.isCheckBoxSelected("With Poster?","3"));
		verifyTrue(homePageObject.isCheckBoxSelected("With Poster?","5"));
		
		homePageObject.uncheckToCheckBoxByColumnNameAtRowNumber("With Poster?","1");
		homePageObject.uncheckToCheckBoxByColumnNameAtRowNumber("With Poster?","2");
		homePageObject.uncheckToCheckBoxByColumnNameAtRowNumber("With Poster?","4");
		
		verifyFalse(homePageObject.isCheckBoxNotSelected("With Poster?","1"));
		verifyFalse(homePageObject.isCheckBoxNotSelected("With Poster?","2"));
		verifyFalse(homePageObject.isCheckBoxNotSelected("With Poster?","4"));
		
	}
	
	//@Test
	public void Table_05_Action_At_Any_Row_II () {
		
		// khi chạy TC số 5 cho việc click icon thì đóng @Test ở TC_04
		
		homePageObject.clickToLoadButton();
		sleepInSecond(2);
		
		// icon click 
		homePageObject.clickToIconByRowNumber("1","Remove Current Row");
		homePageObject.sleepInSecond(2);
		
		homePageObject.clickToIconByRowNumber("1","Insert Row Above");
		homePageObject.sleepInSecond(2);
		
		homePageObject.clickToIconByRowNumber("3","Move Up");
		homePageObject.sleepInSecond(2);
		
		homePageObject.clickToIconByRowNumber("2","Move Down");
		homePageObject.sleepInSecond(2);
		
		homePageObject.clickToIconByRowNumber("5","Remove Current Row");
		homePageObject.sleepInSecond(2);
		
		homePageObject.clickToIconByRowNumber("4","Remove Current Row");
		homePageObject.sleepInSecond(2);
		
		homePageObject.clickToIconByRowNumber("3","Remove Current Row");
		homePageObject.sleepInSecond(2);
		
		homePageObject.clickToIconByRowNumber("2","Remove Current Row");
		homePageObject.sleepInSecond(2);
		
		homePageObject.clickToIconByRowNumber("1","Remove Current Row");
		homePageObject.sleepInSecond(2);
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
