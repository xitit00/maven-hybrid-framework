package pageUIs.liveguru.admin;

public class AdminLoginPageUI {
	
	public static final String LOGIN_TEXTBOX = "xpath=//input[@name='login[%s]']";
	public static final String LOGIN_BUTTON = "xpath=//input[@title='Login']";
	public static final String MESSAGE_POPUP = "css=div#message-popup-window";
	public static final String CLOSE_BUTTON = "xpath=//span[text()='close']";
	public static final String TABLE_ROW = "xpath=//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]";
	
	public static final String COLUMN_TEXTBOX = "xpath=//input[@name='%s']";
	public static final String SEARCH_BUTTON = "xpath=//span[text()='Search']";
	
	public static final String COLUMN_CHECKBOX = "xpath=//td[contains(.,'%s')]/preceding-sibling::td/input";
	
	public static final String SELECTED_ITEM_DROPDOWN = "xpath=//span/label[text()='Actions']/following-sibling::select";
	
	public static final String SUBMIT_BUTTON = "xpath=//span[text()='Submit']";
	
	public static final String EMPTY_ROW = "xpath=//td[text()='No records found.']";
	
}
