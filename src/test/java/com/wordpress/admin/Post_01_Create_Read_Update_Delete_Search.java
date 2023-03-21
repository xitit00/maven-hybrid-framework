package com.wordpress.admin;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;
import pageObjects.wordpress.UserPostSearchPO;

enum Type {
	
	TITLE,
	AUTHOR,
	BODY,
	DATE
}

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {
	
	private WebDriver driver;

	private String adminUsername = "automationfc";
	private String adminPassword = "automationfc2022";
	private String postTitleValue = "Live Coding Title" + generateFakeNumber();
	private String postBodyValue =  "Live Coding Body" + generateFakeNumber();
	String editPostTitle = "Edit Coding Title" + generateFakeNumber();
	String editPostBody = "Edit Coding Body" + generateFakeNumber();
	private String authorName =  "automationfc";
	private String adminUrl, endUserUrl;
	private String currentDay = getCurrentDay();
	
	// khai báo biến viewPostUrl (copy url) để dùng đc cho nhiều TC 
	String searchPostUrl = ""; 
	
	@Parameters({"browser","urlAdmin","urlUser"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String urlUser) {
		
		// c1 - dùng get , set để khởi tạo class và set Driver
//		adminLoginPO = AdminLoginPO.getAdminLoginPageObject();
//		adminLoginPO.setDriver(driver);
		
		//c2 - new = cách này cũng đc , nhưng ko che dấu khởi tạo
		//adminLoginPO = new AdminLoginPO(driver);
		
		this.endUserUrl = urlUser;
		this.adminUrl = adminUrl;

		log.info("Pre-Condition - Step 01: Open browser and admin Url");
		driver = getBrowserDriverOnlyOneUrl(browserName, adminUrl);	
		//c3 - che dấu khởi tạo với PageGeneratorManager
		adminLoginPage = PageGeneratorManager.getAdminLoginPageObject(driver);
		
		log.info("Pre-Condition - Step 02: Enter to Username textbox with value: " + adminUsername);
		adminLoginPage.enterToUsernameTextbox(adminUsername);
		
		
		log.info("Pre-Condition - Step 03:  Enter to Username textbox with value: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);
		
		log.info("Pre-Condition - Step 04: Click to Login button");
		// c1 chuyển page = cách khởi tạo sẵn từ PageGeneratorManager và gán ( tốn 2 dòng code )
		//adminLoginPage.clickToLoginButton();
		//adminDashboardPage = PageGeneratorManager.getAdminDashboardPO(driver);
		
		// c2 chuyển page = cách che giấu khởi tạo từ PageGeneratorManager ở class TC , mình sẽ giấu việc khởi tạo ở trong 
		// clickToLoginButton() , với việc gôp clickLogin vs chuyển page trong 1 lần ( chỉ tốn 1 dòng code )
		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}

	@Test
	public void Post_01_Create_New_Post() {
		
		// Sau khi login xong -> mình sẽ create new post
		log.info("Create Post - Step 01: Click to 'Posts' Menu Link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
	
		//copy link để mở lại url ( nếu ko có bất kỳ công cụ gì để mở lại url , vd: button , link text , ...)
		log.info("Create Post - Step 02: Get 'Search Posts' page Url");
		searchPostUrl = adminPostSearchPage.getSearchPostPageUrl(driver);
	
		log.info("Create Post - Step 03: Click to 'Add New' button");
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();
		
		log.info("Create Post - Step 04: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(postTitleValue);
		
		log.info("Create Post - Step 05: Enter to body");
		adminPostAddNewPage.enterToAddNewPostBody(postBodyValue);
		
		log.info("Create Post - Step 06: Click to 'Publish' button");
		adminPostAddNewPage.clickToPublishButton();
		
		log.info("Create Post - Step 07: Click to 'Pre Publish' button");
		adminPostAddNewPage.clickToPrePublishButton();
		
		log.info("Create Post - Step 08: Verify 'Post published' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));

	}
	
	@Test
	public void Post_02_Search_And_View_Post() {

		// Sau khi Create post success -> mình sẽ chuyển qua search post
		// Open searchPostUrl
		log.info("Search Post - Step 01: Open 'Search Post' page");
		// mở search post page , trả về trang post search và gán lại luôn từ trang create post 
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Search Post - Step 02: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitleValue);
		
		log.info("Search Post - Step 03: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Search Post - Step 04: Verify Search table contains '" + postTitleValue + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", postTitleValue));
	
		log.info("Search Post - Step 05: Verify Search table contains '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));
		
		// *** lưu ý : việc chuyển site khi chuyển từ admin sang user , mình sẽ ko khai báo việc chuyển 
		// ở riêng 1 trang nào hết vd: Search Post , vì sao -> vì mình muốn từ tất cả các page ( user , product , post, ... )
		// tại admin của những trang đó mà muốn chuyển qua user = cách khai báo hàm chuyển site tại BasePage ( vd : Level_09_Dynamic_Locator ) 
	
		log.info("Search Post - Step 06: Open End User site And Move To User Home Page");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Search Post - Step 07: Verify Post info displayed at Home page");
		//verify title , body , authorName , DateTime
		//riêng Date Time thì mình phải setting trên admin về UTC +7 , nên làm riêng 1 TC cho phần này 
		
		verifyTrue(userHomePage.isPostTitleDisplayed(postTitleValue));
		verifyTrue(userHomePage.isPostInfoDisplayed(postTitleValue,postBodyValue,"BODY"));
		verifyTrue(userHomePage.isPostInfoDisplayed(postTitleValue,authorName,"AUTHOR"));
		verifyTrue(userHomePage.isPostInfoDisplayed(postTitleValue,currentDay,"DATE"));
		
		log.info("Search Post - Step 08: Click to Post title and Move to User Post Detail Page");
		userPostDetailPage = userHomePage.clickToPostTitle(postTitleValue);
		
		log.info("Search Post - Step 09: Verify Post info displayed at Post detail page");
		verifyTrue(userPostDetailPage.isPostTitleDisplayed(postTitleValue));
		verifyTrue(userPostDetailPage.isPostInfoDisplayed(postTitleValue, postBodyValue, "BODY"));
		verifyTrue(userPostDetailPage.isPostInfoDisplayed(postTitleValue,authorName,"AUTHOR"));
		verifyTrue(userPostDetailPage.isPostInfoDisplayed(postTitleValue,currentDay,"DATE"));
		
	}
	
	@Test
	public void Post_03_Edit_Post() {
		
		log.info("Edit Post - Step 01: Open Admin Site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver,this.adminUrl);
		
		log.info("Edit Post - Step 02: Click to 'Posts' Menu Link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Edit Post - Step 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitleValue);
		
		log.info("Edit Post - Step 04: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Edit Post - Step 05: Click to Post title link and navigate to Edit Post page");
		adminPostAddNewPage = adminPostSearchPage.clickToPostTitleLink(postTitleValue);
		
		log.info("Edit Post - Step 06: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(editPostTitle);
		
		log.info("Edit Post - Step 07: Enter to post body");
		adminPostAddNewPage.enterToAddNewPostBody(editPostBody);
		
		log.info("Edit Post - Step 08: Click to 'Update' button");
		adminPostAddNewPage.clickToPublishButton();
	
		log.info("Edit Post - Step 09: Verify 'Post updated' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostUpdatedMessageDisplayed("Post updated."));
		
		log.info("Edit Post - Step 10: Open 'Search Post' page");
		// mở search post page , trả về trang post search và gán lại luôn từ trang create post 
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Edit Post - Step 11: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Edit Post - Step 12: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Edit Post - Step 13: Verify Search table contains '" + editPostTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", editPostTitle));
	
		log.info("Edit Post - Step 14: Verify Search table contains '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));
	
		log.info("Edit Post - Step 15: Open End User site And Move To User Home Page");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Edit Post - Step 16: Verify Post info displayed at Home page");
		verifyTrue(userHomePage.isPostTitleDisplayed(editPostTitle));
		verifyTrue(userHomePage.isPostInfoDisplayed(editPostTitle,editPostBody,"BODY"));
		verifyTrue(userHomePage.isPostInfoDisplayed(editPostTitle,authorName,"AUTHOR"));
		verifyTrue(userHomePage.isPostInfoDisplayed(editPostTitle,currentDay,"DATE"));
		
		log.info("Edit Post - Step 17: Click to Post title and Move to User Post Detail Page");
		userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);
		
		log.info("Edit Post - Step 18: Verify Post info displayed at Post detail page");
		verifyTrue(userPostDetailPage.isPostTitleDisplayed(editPostTitle));
		verifyTrue(userPostDetailPage.isPostInfoDisplayed(editPostTitle, editPostBody, "BODY"));
		verifyTrue(userPostDetailPage.isPostInfoDisplayed(editPostTitle,authorName,"AUTHOR"));
		verifyTrue(userPostDetailPage.isPostInfoDisplayed(editPostTitle,currentDay,"DATE"));
		
	}
	
	@Test
	public void Post_05_Delete_Post() {
		
		log.info("Delete Post - Step 01: Open Admin Site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver,this.adminUrl);
		
		log.info("Delete Post - Step 02: Click to 'Posts' Menu Link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Delete Post - Step 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete Post - Step 04: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Delete Post - Step 05: Select Post detail checkbox");
		adminPostSearchPage.selectPostDetailCheckboxByTitle(editPostTitle);
		
		log.info("Delete Post - Step 06: Select 'Move To Trash' item in dropdown");
		adminPostSearchPage.selectItemInActionDropdown("Move to Trash");
		
		log.info("Delete Post - Step 07: Click to 'Apply' button");
		adminPostSearchPage.clickToApplyButton();
		
		//1 post moved to the Trash.
		log.info("Delete Post - Step 08: Verify '1 post moved to the Trash.' message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moved to the Trash."));
		
		log.info("Delete Post - Step 09: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete Post - Step 10: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Delete Post - Step 11: Verify 'No posts found.' message is displayed");
		verifyTrue(adminPostSearchPage.isNoPostsFoundMessageDisplayed("No posts found."));

		log.info("Delete Post - Step 12: Open End User site And Move To User Home Page");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Delete Post - Step 13: Verify Post title undisplayed at Home page");
		verifyTrue(userHomePage.isPostTitleUndisplayed(editPostTitle));
		
		log.info("Delete Post - Step 14: Enter to Search textbox");
		userHomePage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete Post - Step 15: Click to 'Search' button");
		userPostSearchPage = userHomePage.clickToSearchButton();
		
		log.info("Delete Post - Step 16: Verify 'Nothing Found' message is displayed");
		verifyTrue(userPostSearchPage.isNothingFoundMessageUndisplayed("Nothing Found"));

	}
	
	public void sleepInSecond(long timeoutInSec){
		
		try {
			
			Thread.sleep(timeoutInSec * 1000);
			
		}catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		
		closeBrowserAndDriver();
	}
	
	AdminLoginPO adminLoginPage; 
	AdminDashboardPO adminDashboardPage;
	AdminPostSearchPO adminPostSearchPage;
	AdminPostAddNewPO adminPostAddNewPage;
	UserHomePO userHomePage;
	UserPostDetailPO userPostDetailPage;
	UserPostSearchPO userPostSearchPage;
}
