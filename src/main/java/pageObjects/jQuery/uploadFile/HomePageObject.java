package pageObjects.jQuery.uploadFile;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BasePage;
import io.qameta.allure.Step;
import pageUIs.jQuery.uploadFile.HomePageUI;


public class HomePageObject extends BasePage {
	
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		
		this.driver = driver;
		
	}

	// import io.qameta.allure.Step
	// Add Step TC instead of log(...) 
	@Step("Verify File Is Loaded By Name {0}")
	public boolean isFileLoadedByName(String fileName) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, HomePageUI.FILE_NAME_LOADED, fileName);
		return isElementDisplayed(driver, HomePageUI.FILE_NAME_LOADED, fileName);
		
	}
	
	@Step("Verify File Link Up Is Loaded By Name {0}")
	public boolean isFileLinkUpLoadedByName(String fileName) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
		return isElementDisplayed(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
		
	}

	@Step("Click to Start Button")
	public void clickToStartButton() {
		// TODO Auto-generated method stub
		
		// cách 1 : lấy ra total size , duyệt qua vòng lặp for ... i , lấy ra index để biết vị trí nút Start nằm ở dòng nào 
//		int startButtonNumber = getElementSize(driver, HomePageUI.START_BUTTON);
//		
//		for (int i = 0; i < startButtonNumber; i++) {
//			// wait , click , sleep 
//		}
		
		// cách 2 : lấy ra mảng elment = cách getListElements , duyệt qua mảng elements để wait , click Btn luôn cho nhanh
		List<WebElement> startButtons = getListWebElement(driver, HomePageUI.START_BUTTON);
		
		for (WebElement startBtn : startButtons) {
			
			startBtn.click();
			sleepInSecond(2);
		}
		
		
	}

	@Step("Verify File Img Is UpLoaded By Name {0}")
	public boolean isFileImageUpLoadedByName(String fileName) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_IMG, fileName);
		return isImageLoaded(driver, HomePageUI.FILE_NAME_UPLOADED_IMG, fileName);
	}	
	
}
