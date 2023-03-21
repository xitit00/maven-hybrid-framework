package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.wordpress.AdminPostAddNewPageUI;

public class AdminPostAddNewPO extends BasePage {
	
	public WebDriver driver;
	
	public AdminPostAddNewPO(WebDriver driver) {
		
		this.driver = driver;
	}

	public void enterToAddNewPostTitle(String postTitleValue) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX);
		sendkeyToElement(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX, postTitleValue);
	}

	public void enterToAddNewPostBody(String postBodyValue) {
		// TODO Auto-generated method stub
		
		//click
		waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.BODY_BUTTON);
		
		
		waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_DOCUMENT);
		
		//clear before sendkey
		clearValueInElementByDeleteKey(driver, AdminPostAddNewPageUI.BODY_DOCUMENT);
		
		//sendkey 
		sendkeyToElement(driver, AdminPostAddNewPageUI.BODY_DOCUMENT, postBodyValue);
		
	}

	public void clickToPublishButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
		clickToElement(driver,  AdminPostAddNewPageUI.PUBLISH_BUTTON);
		
	}

	public boolean isPostPublishMessageDisplayed(String postPublishedMessage) {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISHED_MESSAGE, postPublishedMessage);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.PUBLISHED_MESSAGE, postPublishedMessage);
		
	}
	
	public AdminPostSearchPO openSearchPostPageUrl(String searchPostUrl) {
		
		openPageUrl(driver, searchPostUrl);
		return PageGeneratorManager.getAdminPostSearchPO(driver);
		
	}

	public void clickToPrePublishButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
		clickToElement(driver,  AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
	}

	public boolean isPostUpdatedMessageDisplayed(String postUpdatedMessage) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISHED_MESSAGE, postUpdatedMessage);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.PUBLISHED_MESSAGE, postUpdatedMessage);
	}

}
