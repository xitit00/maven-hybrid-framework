package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.wordpress.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage {
	
	public WebDriver driver;
	
	public AdminPostSearchPO(WebDriver driver) {
		
		this.driver = driver;
	}

	public AdminPostAddNewPO clickToAddNewButton() {
		// TODO Auto-generated method stub
		
		AdminPostAddNewPO adminPostAddNewPage =  PageGeneratorManager.getAdminPostAddNewPO(driver);
		waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		return adminPostAddNewPage;
		
	}

	public String getSearchPostPageUrl(WebDriver driver) {
		
		return getPageUrl(driver);
	}

	public void enterToSearchTextbox(String postTitleValue) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX, postTitleValue);
		
	}

	public void clickToSearchPostButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
		
	}

	public boolean isPostSearchTableDisplayed(String headerID, String postTitleValue) {
		// TODO Auto-generated method stub
		
		// tìm index cột 
		int headerIndex = getElementSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_ID, headerID) + 1;
		
		// lấy data từ cột vs verify data của cột đó
		waitForElementVisible(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex) , postTitleValue);
		return isElementDisplayed(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex) , postTitleValue);
		
	}

	public AdminPostAddNewPO clickToPostTitleLink(String postTitleValue) {
		// TODO Auto-generated method stub

		waitForElementClickable(driver, AdminPostSearchPageUI.ROW_TITLE_DETAIL_BY_NAME,postTitleValue);
		clickToElement(driver, AdminPostSearchPageUI.ROW_TITLE_DETAIL_BY_NAME,postTitleValue);
		
		return PageGeneratorManager.getAdminPostAddNewPO(driver);

	}

	public void selectPostDetailCheckboxByTitle(String editPostTitle) {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, AdminPostSearchPageUI.ROW_CHECKBOX_BY_TITLE_NAME, editPostTitle);
		checkToDefaultCheckboxRadio(driver, AdminPostSearchPageUI.ROW_CHECKBOX_BY_TITLE_NAME, editPostTitle);
		
	}

	public void selectItemInActionDropdown(String dropDownItem) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostSearchPageUI.ACTION_DROPDOWN);
		selectItemByTextInDefaultDropdown(driver, AdminPostSearchPageUI.ACTION_DROPDOWN, dropDownItem);
	}

	public void clickToApplyButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostSearchPageUI.APPLY_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.APPLY_BUTTON);
	}

	public boolean isMoveToTrashMessageDisplayed(String moveToTrashMessage) {
		// TODO Auto-generated method stub
		
		waitForElementVisible(driver, AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE, moveToTrashMessage);
		return isElementDisplayed(driver, AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE, moveToTrashMessage);
		
	}

	public boolean isNoPostsFoundMessageDisplayed(String noPostFoundMessage) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminPostSearchPageUI.NO_POST_FOUND_MESSAGE, noPostFoundMessage);
		return isElementDisplayed(driver, AdminPostSearchPageUI.NO_POST_FOUND_MESSAGE, noPostFoundMessage);
	}

}
