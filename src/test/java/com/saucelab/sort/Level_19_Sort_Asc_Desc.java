package com.saucelab.sort;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import pageObjects.saucelab.LoginPageObject;
import pageObjects.saucelab.PageGeneratorManager;
import pageObjects.saucelab.ProductPageObject;

public class Level_19_Sort_Asc_Desc extends BaseTest {
	
	public WebDriver driver;
	
	
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		
		driver = getBrowserDriverOnlyOneUrl(browserName, url);
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		productPage = loginPage.clickToLoginButton();
		
			
	}
	
	@Test
	public void Sort_01_Name() {
		
		
		productPage.selectItemInProductSortDropdown("Name (A to Z)");
		productPage.sleepInSecond(3);
		verifyTrue(productPage.isProductNameSortByAscending());
		
		productPage.selectItemInProductSortDropdown("Name (Z to A)");
		verifyTrue(productPage.isProductNameSortByDescending());
		productPage.sleepInSecond(3);
	}
	
	@Test
	public void Sort_02_Price() {
		
		productPage.selectItemInProductSortDropdown("Price (low to high)");
		productPage.sleepInSecond(3);
		verifyTrue(productPage.isProductPriceSortbyAscending());
		
		
		productPage.selectItemInProductSortDropdown("Price (high to low)");
		productPage.sleepInSecond(3);
		verifyTrue(productPage.isProductPriceSortbyDescending());
	
	}
	
	
	
	@AfterClass
	public void afterClass() {
		
		driver.quit();
	}
	
	public LoginPageObject loginPage;
	public ProductPageObject productPage;

}
