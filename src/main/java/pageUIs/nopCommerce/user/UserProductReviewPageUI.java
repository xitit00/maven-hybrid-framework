package pageUIs.nopCommerce.user;

public class UserProductReviewPageUI {
	
	public static final String REVIEW_TITLE_TEXTBOX = "xpath=//input[@class='review-title']";
	public static final String REVIEW_TEXT_TEXTBOX = "xpath=//textarea[@class='review-text']";
	public static final String REVIEW_RATING_RADIO_BUTTON = "xpath=//input[@aria-label='Good']";
	public static final String REVIEW_SUBMIT_BUTTON = "xpath=//button[text()='Submit review']";
	
	public static final String REVIEW_MESSAGE = "xpath=//div[contains(text(),'Product review is successfully added.')]";
	public static final String REVIEW_TITLE = "xpath=//div[@class= 'review-title']/strong";
	public static final String REVIEW_TEXT = "xpath=//div[@class= 'review-text']";
	//strong[text()='order 123']/parent::div/following-sibling::div/div[@class='rating']/div
	public static final String REVIEW_RATING = "xpath=//div[@class='product-review-box']/div[@class='rating']/div";
}
