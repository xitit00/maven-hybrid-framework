package pageUIs.jQuery.dataTable;

public class HomePageUI {

	public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String PAGINATION_PAGE_ACTIVED_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public static final String HEADER_TEXTBOX_BY_LABEL = "xpath=//div[@class='qgrd-header-text' and text()= '%s']/parent::div/following-sibling::input";
	public static final String TABLE_ROW = "xpath=//tbody";
	public static final String TOTAL_PAGINATION = "css=ul.qgrd-pagination-ul>li.qgrd-pagination-page";
	public static final String PAGINATION_PAGE_BY_INDEX = "xpath=//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page'][%s]";
	
	// Get all data 
	public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody/tr/td";
	
	// Get All Column Country , tương tự Female , Male , Total
	public static final String ALL_ROW_EACH_PAGE_COUNTRY = "xpath=//tbody/tr/td[@data-key='country']";
	
	// Index của cái cột mà mình cần enter/ click / select vào
	
	public static final String COLUMN_INDEX_BY_NAME = "xpath=//tr/td[text()='%s']/preceding-sibling::td"; 
	
	public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/input"; 
	
	public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/select";
	
	public static final String LOAD_BUTTON = "css=button#btnLoad";
	
	public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/input[@type='checkbox']"; 
	
	public static final String ICON_NAME_BY_ROW_NUMBER = "xpath=//tbody/tr[%s]/td/button[@title='%s']";
	
}
