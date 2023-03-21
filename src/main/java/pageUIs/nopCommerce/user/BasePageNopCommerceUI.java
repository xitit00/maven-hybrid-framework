package pageUIs.nopCommerce.user;

public class BasePageNopCommerceUI {
	
	public static final String ADDRESSES_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
	public static final String REWARD_POINT_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Reward points']";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='My product reviews']";
	public static final String LOGOUT_LINK_AT_USER = "css=a[class='ico-logout']";
	public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
	
	//Pattern_Object 
	public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA = "xpath=//div[contains(@class,'%s')]//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_CHECK_BOX_BY_LABEL = "xpath=//label[contains(.,'%s')]/following-sibling::input";
}
