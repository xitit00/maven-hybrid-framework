package pageObjects.jQuery.dataTable;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BasePage;
import pageUIs.jQuery.dataTable.HomePageUI;

public class HomePageObject extends BasePage {
	
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	public void openPagingByPageNumber(String pageNumber) {
		
		waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}

	public boolean isPageNumberActived(String pageNumber) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
		
		return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
	}

	public void enterToHeaderTextboxByLabel(String headerLabel, String value) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, headerLabel);
		sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, value, headerLabel);
		pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);
	}

	public List<String> getValueEachRowAtAllPage() {
		// TODO Auto-generated method stub
		
		// Lấy ra toàn bộ trang
		int totalSize = getElementSize(driver, HomePageUI.TOTAL_PAGINATION);
		
		List<String> allRowValuesAllPage = new ArrayList<String>();
		
		// Duyệt qua toàn bộ trang
		for (int i = 1; i <= totalSize; i++) {
			
			// click vào từng trang
			clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_INDEX, String.valueOf(i));
			sleepInSecond(1);
			
			List<WebElement> allRowValueEachPage = getListWebElement(driver, HomePageUI.ALL_ROW_EACH_PAGE);
			
			// Duyệt qua tất cả row của mỗi page
			for (WebElement eachRow : allRowValueEachPage) {
				
				// get text của tất cả row của 1 category thì ko cần if/else và add vào ArrayList 
				//allRowValuesAllPage.add(eachRow.getText());
				
				// get text của tất cả row ( all data ) nhưng bỏ đi cột đầu tiên của mỗi dòng ? 
				// vì sao ? vì cột đầu tiên ko có text , mà 4 cột còn lại thì có text , kiểm tra eachRow.getText khác rỗng thì add vào
				
				if (eachRow.getText().length() > 0) {
					
					allRowValuesAllPage.add(eachRow.getText());
				}
				
				// lấy index của each Row ra
//				int index = allRowValueEachPage.indexOf(eachRow);
				
				// so sánh chỉ add LAST INDEX của mỗi page
//				if (index == allRowValueEachPage.size() - 1) {
//					
//					allRowValuesAllPage.add(eachRow.getText());
//				}
				
				// so sánh chỉ add FIRST INDEX của mỗi page
//				if (index == 0) {
//					
//					allRowValuesAllPage.add(eachRow.getText());
//				}
				
			}
			
		}
		
	
		
		for (String value : allRowValuesAllPage) {
			
			
			//System.out.println("-----");
			System.out.println(value);
		
		}
		
		return allRowValuesAllPage;
		
	}
	
	public void sleepInSecond(long timeoutInSec){

		try {

			Thread.sleep(timeoutInSec * 1000);

		}catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void enterToTextBoxByColumnNameAtRowNumber(String columnName, String rowNumber, String value) {
		// column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		// sendkey vào dòng nào
		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowNumber, String.valueOf(columnIndex));
		
		
	}

	public void selectDropDownByColumnNameAtRowNumber(String columnName, String rowNumber, String textValue) {
		
		// column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		// select dropdown tại item nào
		waitForElementClickable(driver,HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX , rowNumber, String.valueOf(columnIndex));
		selectItemByTextInDefaultDropdown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, textValue, rowNumber, String.valueOf(columnIndex));
	}

	public void clickToLoadButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.LOAD_BUTTON);
		clickToElement(driver, HomePageUI.LOAD_BUTTON);
		
	}

	public void checkToCheckBoxByColumnNameAtRowNumber(String columnName, String rowNumber) {
		// TODO Auto-generated method stub
		// column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		// checkbox 
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		checkToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
	}

	public void uncheckToCheckBoxByColumnNameAtRowNumber(String columnName, String rowNumber) {
		// TODO Auto-generated method stub
		// column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		// checkbox 
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		uncheckToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		
	}

	public void clickToIconByRowNumber( String rowNumber, String titleValue) {
		// TODO Auto-generated method stub
		
		//action click
		waitForElementClickable(driver, HomePageUI.ICON_NAME_BY_ROW_NUMBER, rowNumber , titleValue);
		clickToElement(driver, HomePageUI.ICON_NAME_BY_ROW_NUMBER, rowNumber , titleValue);
	}

	public boolean isElementRowUnDisplayed() {
		// TODO Auto-generated method stub
		
		return isElementUndisplayed(driver, HomePageUI.TABLE_ROW);

	}

	public String getActualText(String columnName, String rowNumber) {
		// TODO Auto-generated method stub
		
		// column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		// get Item Selected 
		return getSelectedItemDefaultDropdown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		
	}

	public boolean isCheckBoxSelected(String columnName, String rowNumber) {
		// TODO Auto-generated method stub
		
		// column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		// checkbox selected 
		return isElementSelected(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		
	}

	public boolean isCheckBoxNotSelected(String columnName, String rowNumber) {
		// TODO Auto-generated method stub
		
		// column index dựa vào tên cột
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		// checkbox not selected
		return isElementSelected(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
	}


	
	
}
