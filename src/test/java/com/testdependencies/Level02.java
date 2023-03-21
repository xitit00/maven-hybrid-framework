package com.testdependencies;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;

public class Level02 extends BaseTest {
	
	public WebDriver driver;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		
		driver = getBrowserDriverOnlyOneUrl(browserName, url);	
			
	}
	
	@Test
	public void TC_01() {
		
		
	}
	
	@Test
	public void TC_02() {
		
		
	}
	
	@Test
	public void TC_03() {
		
		
	}
	
	@Test
	public void TC_04() {
		
		
	}
	
	@Test
	public void TC_05() {
		
		
	}
	
	
	@AfterClass
	public void afterClass() {
		
		//driver.quit();
	}

}

