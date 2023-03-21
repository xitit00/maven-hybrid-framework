package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.wordpress.UserHomePageUI;
import pageUIs.wordpress.UserPostDetailPageUI;

public class UserPostDetailPO extends BasePage {
	
public WebDriver driver;
	
	public UserPostDetailPO(WebDriver driver) {
		
		this.driver = driver;
	}


	public boolean isPostTitleDisplayed(String postTitleValue) {
		
		waitForElementVisible(driver, UserPostDetailPageUI.POST_TITLE_TEXT, postTitleValue);
		return isElementDisplayed(driver, UserPostDetailPageUI.POST_TITLE_TEXT, postTitleValue);
	}


	public boolean isPostInfoDisplayed(String postTitle, String postInfo, String key) {
		
		Boolean b = true;
		

		if (key == "AUTHOR") {
			
			waitForElementVisible(driver, UserPostDetailPageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, postInfo);
			b = isElementDisplayed(driver, UserPostDetailPageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, postInfo);
		}
		else if (key == "BODY") {
			
			waitForElementVisible(driver, UserPostDetailPageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postInfo);
			b = isElementDisplayed(driver, UserPostDetailPageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postInfo);
		}
		else {
			
			waitForElementVisible(driver, UserPostDetailPageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, postInfo);
			b = isElementDisplayed(driver, UserPostDetailPageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, postInfo);
		}
		
			
		return b;
	}

}
