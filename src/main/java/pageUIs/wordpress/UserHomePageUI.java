package pageUIs.wordpress;

public class UserHomePageUI {
	
	public static final String POST_TITLE_TEXT = "xpath=//article//a[text()='%s']";
	public static final String POST_CURRENT_DATE_TEXT_BY_POST_TITLE = "xpath=//article//a[text()='%s']/ancestor::header//time[text()='%s']";
	public static final String POST_AUTHOR_TEXT_BY_POST_TITLE = "xpath=//article//a[text()='%s']/ancestor::header//span[@class='author vcard']/a[text()='%s']";
	public static final String POST_BODY_TEXT_BY_POST_TITLE = "xpath=//article//a[text()='%s']/ancestor::header/following-sibling::div/p[text()='%s']";
	public static final String SEARCH_TEXTBOX = "xpath=//section[@class='widget widget_search']/form[@role='search']//input[@name='s']";
	public static final String SEARCH_BUTTON = "xpath=//section[@class='widget widget_search']/form[@role='search']//input[@value='Search']";

	
}
