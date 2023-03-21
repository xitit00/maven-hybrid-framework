package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.wordpress.UserPostSearchPageUI;

public class UserPostSearchPO extends BasePage {

public WebDriver driver;
	
	public UserPostSearchPO(WebDriver driver) {
		
		this.driver = driver;
	}

	public boolean isNothingFoundMessageUndisplayed(String nothingFoundMessage) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, UserPostSearchPageUI.NOTHING_FOUND_MESSAGE, nothingFoundMessage);
		return isElementDisplayed(driver, UserPostSearchPageUI.NOTHING_FOUND_MESSAGE, nothingFoundMessage);
		
	}
}
