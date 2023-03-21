package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.nopCommerce.user.UserProductReviewPageUI;

public class UserProductReviewPageObject extends BasePage  {
	
	private WebDriver driver;

	public UserProductReviewPageObject(WebDriver driver) {
	
		this.driver = driver;
	}

	public void inputReviewTitle(String textValue) {
		
		sendkeyToElement(driver,UserProductReviewPageUI.REVIEW_TITLE_TEXTBOX, textValue);
	}

	public void inputReviewText(String textValue) {
		
		sendkeyToElement(driver,UserProductReviewPageUI.REVIEW_TEXT_TEXTBOX, textValue);
	}

	public void selectReviewRating() {
		
		waitForElementClickable(driver,UserProductReviewPageUI.REVIEW_RATING_RADIO_BUTTON);
		clickToElement(driver,UserProductReviewPageUI.REVIEW_RATING_RADIO_BUTTON);
	}

	public void clickReviewSubmit() {
	
		waitForElementClickable(driver,UserProductReviewPageUI.REVIEW_SUBMIT_BUTTON);
		clickToElement(driver,UserProductReviewPageUI.REVIEW_SUBMIT_BUTTON);
		
	}

	public String getAttributeReviewRating() {
	
		waitForElementVisible(driver, UserProductReviewPageUI.REVIEW_RATING);
		return getElementAttribute(driver, UserProductReviewPageUI.REVIEW_RATING, "style");
	}

	public String getTextReviewTitle() {

		waitForElementVisible(driver, UserProductReviewPageUI.REVIEW_TITLE);
		return getElementText(driver, UserProductReviewPageUI.REVIEW_TITLE);
	}

	public String getTextReviewText() {
		
		waitForElementVisible(driver, UserProductReviewPageUI.REVIEW_TEXT);
		return getElementText(driver, UserProductReviewPageUI.REVIEW_TEXT);
	}

	public boolean checkReviewMessageDisplay() {
	
		waitForElementVisible(driver, UserProductReviewPageUI.REVIEW_MESSAGE);
		return isElementDisplayed(driver,UserProductReviewPageUI.REVIEW_MESSAGE);
		
	}
	

}
