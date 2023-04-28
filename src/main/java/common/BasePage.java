package common;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.UserHomePO;
import pageUIs.jQuery.uploadFile.BasePageJQueryUI;
import pageUIs.nopCommerce.user.BasePageNopCommerceUI;

public class BasePage {
	
	
	// hàm constructor
	
	public static BasePage getBasePageObject() {
		
		BasePage b = new BasePage();
		return b;
	}

	// các hàm dùng chung để tương tác với pageObjects
	// common class
	// Thật ra có thể khởi tạo cái web driver bên ngoài để dùng trong các hàm bên dưới thông qua hàm constructor , nhưng ko nên 
	// vì class này là common class , những class khác dùng lại BasePage sẽ có nhiệm vụ truyền webdriver vào là sẽ dùng đc.
	// => Tham số WebDriver là bắt buộc 
	
	
	// Web Browser
	public void openPageUrl(WebDriver driver, String pageUrl) {
		
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		
		return driver.getCurrentUrl();
		
	}
	
	public String getPageSourceCode(WebDriver driver) {
		
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		
		driver.navigate().refresh();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		
		for (Cookie cookie : cookies) {
			
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		
		return driver.manage().getCookies();
	}
	
	//alert
	// wait alert is Presence to accept , dismiss , sendkey ...
	
	public Alert waitForAlertPresence(WebDriver driver) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		// return alert
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		
		waitForAlertPresence(driver).accept();
		
	}
	
	public void cancelAlert(WebDriver driver) {
		
		waitForAlertPresence(driver).dismiss();
		
	}
	
	public String getAlertText(WebDriver driver) {
		
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendKeyToAlert(WebDriver driver,String textValue) {
		
		waitForAlertPresence(driver).sendKeys(textValue);
		
	}
	
	//window , tab
	
	public void switchToWindowByID(WebDriver driver,String pageID) {

		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows);
		
		for (String window : allWindows) {

			if (!window.equals(pageID)) {

				driver.switchTo().window(window);
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver,String expectedPageTitle) {

		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows);
		
		for (String window : allWindows) {
			
			
			driver.switchTo().window(window);
		
			String actualTitle = driver.getTitle().trim();
			System.out.println(actualTitle);
			if (actualTitle.equals(expectedPageTitle)) {

				break;
			}
		}
	}
	
	public void closeAllTabWithoutParent(WebDriver driver,String parentPageID) {
		
	 	Set<String> allWindowsID = driver.getWindowHandles();
	 	
	 	for (String windowID : allWindowsID) {
			
	 		if (!windowID.equals(parentPageID)) {
	 			
	 			driver.switchTo().window(windowID);
	 			driver.close();
	 			
	 		}
		}
	 	
	 	driver.switchTo().window(parentPageID);
	}
	
	//Web Element
	
	//Xpath Locator
//	private By getByXpath(String xpathLocator) {
//		
//		return By.xpath(xpathLocator);
//	}
	
	// Dynamic Locator
	// locator Type: id= , css= , xpath= , name= , class=
	// locator Type: ID= , CSS= , XPATH= , NAME= , CLASS=
	// locator Type: Id= , Css= , Xpath= , Name= , Class=
	public By getByLocator(String locatorType) {
		
		By byLocator = null;
		
		// buộc dùng startWith để chắc chắn ở những kí tự đầu ứng với locator bên trên
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			
			String strId = locatorType.substring(3);
			byLocator = By.id(strId);
			
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class")) {
			
			String strClass = locatorType.substring(6);
			byLocator = By.className(strClass);
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
			
			String strName = locatorType.substring(5);
			byLocator = By.name(strName);
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			
			String strCss = locatorType.substring(4);
			byLocator = By.cssSelector(strCss);
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			
			String strXpath = locatorType.substring(6);
			byLocator = By.xpath(strXpath);
			
		} else {
			
			throw new RuntimeException("Locator type is not supported");
		}
		
		return byLocator;
	}
	
	// Dynamic_Xpath , Rest Parameters
	// Nếu như truyền vào locator type là xpath = đúng 
	// Truyền vào locator type # xpath = sai 
	public String getDynamicXpath(String locatorType, String... restValues) {
		
		System.out.println("Locator Type Before = " + locatorType);
		// check là xpath thì dùng rest params truyền vào , còn ko thì thôi bỏ qua
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			
			locatorType = String.format(locatorType, (Object[])restValues);
		}
		
		for (String value : restValues) {
		
			System.out.println("Values map to locator = " + value);
		}
		
		System.out.println("Locator Type After = " + locatorType);
		return locatorType;
	}
	
	public WebElement getWebElement(WebDriver driver, String locatorType) {
		
		return driver.findElement(getByLocator(locatorType));
	} 
	
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		
		return driver.findElements(getByLocator(locatorType));
	}
	
	public void clickToElement(WebDriver driver, String locatorType) {
	
		// only run IE
		if (driver.toString().contains("internet explorer")) {
			
			clickToElementByJS(driver, locatorType);
			sleepInSecond(3);
		}
		else {
			
			getWebElement(driver, locatorType).click();
		}
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String... restValues) {
		
		locatorType = getDynamicXpath(locatorType, restValues);
		// only run IE
		if (driver.toString().contains("internet explorer")) {
			
			clickToElementByJS(driver, locatorType, restValues);
			sleepInSecond(3);
		}
		else {
	
			getWebElement(driver, locatorType).click();
		}
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		
		// khai báo biến gán nếu như dùng nhiều lần , thay vì gọi lại getWebElement() clear rồi sendkey.
		WebElement element = getWebElement(driver, locatorType);
		
		element.clear();
		element.sendKeys(textValue);
		
	}
	
	public void clearValueInElementByDeleteKey(WebDriver driver, String locatorType) {
		
		
		WebElement element = getWebElement(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.COMMAND,"a",Keys.DELETE));
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue , String... restValues) {
		
		// khai báo biến gán nếu như dùng nhiều lần , thay vì gọi lại getWebElement() clear rồi sendkey.
		
		locatorType = getDynamicXpath(locatorType, restValues);
		
		WebElement element = getWebElement(driver, locatorType);
		
		element.clear();
		element.sendKeys(textValue);
		
	}
	
	// Default Dropdown
	// cấu trúc : select[@name='DateOfBirthMonth']
	public void selectItemByValueInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... restValues) {
		
		locatorType = getDynamicXpath(locatorType, restValues);
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByValue(textItem);
	}
	
	public void selectItemByValueInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByValue(textItem);
	}
	
	public void selectItemByTextInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... restValues) {
		
		locatorType = getDynamicXpath(locatorType, restValues);
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemByTextInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType,  String... restValues) {
		
		locatorType = getDynamicXpath(locatorType, restValues);
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locatorType , String... restValues) {
		
		locatorType = getDynamicXpath(locatorType, restValues);
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	
	
	// Custom Dropdown
	// cấu trúc : div
	public void selectElementInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedElement) {

		//c1 
//		getWebElement(driver, parentXpath).click();
//		sleepInSecond(1);
		
		//c2
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(parentLocator))).click();

		List<WebElement> allElements = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		System.out.println("All Item is " + allElements.size());

		for (WebElement element : allElements) {

			System.out.println("Item text: " + element.getText());

			if (element.getText().trim().equals(expectedElement)) {

				if (element.isDisplayed()) {

					element.click();

				} else {

					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
					sleepInSecond(1);
					element.click();

				}

				break;

			}
		}
	}
	
	public void sleepInSecond(long timeoutInSec) {

		try {

			Thread.sleep(timeoutInSec * 1000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	// ít dùng
	public String getElementAttribute(WebDriver driver , String locatorType, String attributeName) {
		
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver , String locatorType, String attributeName, String... restValues ) {
		
		return getWebElement(driver, getDynamicXpath(locatorType, restValues)).getAttribute(attributeName);
	}
	
	public String getElementText(WebDriver driver , String locatorType) {
		
		return getWebElement(driver, locatorType).getText();
	}
	
	public String getElementText(WebDriver driver , String locatorType, String... restValues) {
		
		return getWebElement(driver, getDynamicXpath(locatorType, restValues)).getText();
	}
	
	public String getElementCssValue(WebDriver driver , String locatorType, String propertyName) {
		
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver , String locatorType) {
		
		return  getListWebElement(driver, locatorType).size();
	}
	
	public int getElementSize(WebDriver driver , String locatorType, String... restValues) {
		
		locatorType = getDynamicXpath(locatorType, restValues);
		return  getListWebElement(driver, locatorType).size();
	}
	
	// check , uncheck -> checkbox , radio checkbox
	
	public void checkToDefaultCheckboxRadio(WebDriver driver , String locatorType) {
		
		WebElement element = getWebElement(driver, locatorType);
		
		if (!element.isSelected()) {
			
			element.click();
		}
	}
	
	public void checkToDefaultCheckboxRadio(WebDriver driver , String locatorType, String... restValues) {
		
		locatorType = getDynamicXpath(locatorType, restValues);
		WebElement element = getWebElement(driver, locatorType);
		
		if (!element.isSelected()) {
			
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckboxRadio(WebDriver driver , String locatorType) {
		
		WebElement element = getWebElement(driver, locatorType);
		
		if (element.isSelected()) {
			
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckboxRadio(WebDriver driver , String locatorType, String... restValues) {
		
		locatorType = getDynamicXpath(locatorType, restValues);
		WebElement element = getWebElement(driver, locatorType);
		
		if (element.isSelected()) {
			
			element.click();
		}
	}
	
	// Displayed  , Enabled , Selected
	
	// cách này dùng đc chỉ cho mỗi TH2 : element not displayed but in DOM , ko check đc TH3  
	
//	public boolean isElementUnDisplayed(WebDriver driver , String locatorType, String... restValues) {
//		
//		boolean status = true;
//		
//		if (getWebElement(driver, getDynamicXpath(locatorType, restValues)).isDisplayed()) {
//			
//			status = false;
//		}
//		
//		return status;
//	}
//	
//	public boolean isElementUnDisplayed(WebDriver driver , String locatorType) { 
//		
//		boolean status = true; 
//		
//		if (getWebElement(driver, locatorType).isDisplayed()) {
//			
//			status = false;
//		}
//		
//		return status;
//	}
	
	public boolean isElementDisplayed(WebDriver driver , String locatorType) {
		
		try {
			// Tìm thấy element: 
			// Case 1 - Displayed - trả về true 
			// Case 2 - Undisplayed - trả về false 
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (NoSuchElementException e) {
		
			// Ko tìm thấy element 
			// Case 3 - Undisplayed - Nó sẽ trả về exception khi nãy : 
			// NoSuchElementException: no such element: Unable to locate element ...  - trả về false
			
			e.printStackTrace();
			return false;
		}
	}
	
	// cách này dùng đc cả cho TH2 vs TH3 
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		
		System.out.println("Start time = " + new Date().toString());
		
		// Gán 5s để find element NOT in DOM 
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locator);
		
		// Nếu như mình gán = 5s apply cho tất cả các step về sau đó: findElement / findElements thì sẽ ko đủ time page load và UI render
		// => gán lại = 30s find element in DOM 
		overrideGlobalTimeout(driver, longTimeout);
		
		if (elements.size() == 0) {
			
			System.out.println("Case 3 - Element ko có trong DOM");
			System.out.println("End time = " + new Date().toString());
			return true; 
		}
		else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			
			System.out.println("Case 2 - Element có trong DOM nhưng ko visible/ displayed");
			System.out.println("End time = " + new Date().toString());
			return true; 
			
		}
		else {
			
			System.out.println("Case 1 - Element có trong DOM và display");
			return false;
		}
	}
		
		public boolean isElementUndisplayed(WebDriver driver, String locator, String... restValues) {
			
			System.out.println("Start time = " + new Date().toString());
			
			// Gán 5s để find element NOT in DOM 
			overrideGlobalTimeout(driver, shortTimeout);
			
			String locatorType = getDynamicXpath(locator, restValues);
			List<WebElement> elements = getListWebElement(driver, locatorType);
			
			// Nếu như mình gán = 5s apply cho tất cả các step về sau đó: findElement / findElements thì sẽ ko đủ time page load và UI render
			// => gán lại = 30s find element in DOM 
			overrideGlobalTimeout(driver, longTimeout);
			
			if (elements.size() == 0) {
				
				System.out.println("Case 3 - Element ko có trong DOM");
				System.out.println("End time = " + new Date().toString());
				return true; 
			}
			else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
				
				System.out.println("Case 2 - Element có trong DOM nhưng ko visible/ displayed");
				System.out.println("End time = " + new Date().toString());
				return true; 
				
			}
			else {
				
				System.out.println("Case 1 - Element có trong DOM và display");
				return false;
			}
		
	}
	
	// override lại time out implicit wait theo ý mình 
	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementDisplayed(WebDriver driver , String locatorType, String... restValues) {
		
		return getWebElement(driver, getDynamicXpath(locatorType, restValues)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver , String locatorType) {
		
		return getWebElement(driver, locatorType).isEnabled();
	}
	
	public boolean isElementEnabled(WebDriver driver , String locatorType, String... restValues) {
		
		return getWebElement(driver, getDynamicXpath(locatorType, restValues)).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver , String locatorType) {
		
		return getWebElement(driver, locatorType).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver , String locatorType, String... restValues) {
		
		return getWebElement(driver, getDynamicXpath(locatorType, restValues)).isSelected();
	}
	
	// Frame , Iframe
	
	public void switchToFrameIframe(WebDriver driver , String locatorType ) {
		
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		
		driver.switchTo().defaultContent();
	}
	
	// User action
	
	public void hoverMouseToElement(WebDriver driver , String locatorType ) {
		
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	public void pressKeyToElement(WebDriver driver , String locatorType , Keys key) {
		
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver , String locatorType , Keys key , String...restValues) {
		
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, restValues)), key).perform();
		
	}
	
	//Upload đợi học trong phần Framework tiếp theo 
	// Ko xài File.separator 
//	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
//	
//		String filePath = "";
//		
//		if (GlobalConstants.OS_NAME.startsWith("Window")) {
//			
//			// Window 
//			filePath = System.getProperty("user.dir") + "\\uploadFiles\\";
//		}
//		else {
//			
//			// Mac , Linux
//			
//			filePath = System.getProperty("user.dir") + "/uploadFiles/";
//		}
//		
//		String fullFileName = "";
//		for (String file : fileNames) {
//			
//			fullFileName = fullFileName + filePath + file + "\n";
//			
//		}
//		fullFileName = fullFileName.trim();
//		getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);
//	
//	}
	
	// Xài File.separator
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		
		//  Đường dẫn thư mục uploadFile: Windows / MAC / Linux
		String filePath = GlobalConstants.UPLOAD_FILE;
		
		// Đường dẫn của tất cả các file (fileNames)
		// 1 file : Java.png
		// nhiều file : String[] fileNames = {"CSharp.png","Java.png","Python.png"}
		String fullFileName = "";
		for (String file : fileNames) {
			
			// "" + /Users/anhnguyen/Documents/Selenium/03-Hybrid Automation Framework/uploadFiles/ + Java.png + "\n"
			fullFileName = fullFileName + filePath + file + "\n";
			
		}
		// dùng trim để xoá kí tự khoảng trắng / tab / xuống dòng của 1 chuỗi , ở đây là "\n" 
		// => cuối cùng lấy ra 1 chuỗi để sendKeys 1 file , ở đây là fullFileName 
		fullFileName = fullFileName.trim();
		
		getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);
	
	}
	
	//JsExecutor

	public String getInnerText(WebDriver driver) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public String getDomainPage(WebDriver driver) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.domain;");
	}

	public String getURL(WebDriver driver) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.URL;");
	}

	public String getTitle(WebDriver driver) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.title;");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void scrollToBottomPage(WebDriver driver) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void hightlightElement(WebDriver driver, String locatorType) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");

		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);

		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void hightlightElement(WebDriver driver, String locatorType , String...restValues) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		locatorType = getDynamicXpath(locatorType, restValues);

		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");

		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);

		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void clickToElementByJS(WebDriver driver, String locatorType, String...restValues) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		locatorType = getDynamicXpath(locatorType, restValues);
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void scrollToElementOnTop(WebDriver driver, String locatorType) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void scrollToElementOnTop(WebDriver driver, String locatorType, String...restValues) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		locatorType = getDynamicXpath(locatorType, restValues);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void scrollToElementOnDown(WebDriver driver, String locatorType) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locatorType));
	}

	public void scrollToElementOnDown(WebDriver driver, String locatorType, String...restValues) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		locatorType = getDynamicXpath(locatorType, restValues);
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locatorType));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locatorType, String value) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locatorType));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locatorType, String value, String...restValues) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		locatorType = getDynamicXpath(locatorType, restValues);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locatorType));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove, String...restValues) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		locatorType = getDynamicXpath(locatorType, restValues);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType, String...restValues) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		locatorType = getDynamicXpath(locatorType, restValues);
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isPageLoadedSuccess(WebDriver driver) {

		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {

				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active == 0);");

			}

		};

		return explicitWait.until(jQueryLoad);

	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		}
		return false;
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType, String...restValues) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		locatorType = getDynamicXpath(locatorType, restValues);
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		}
		return false;
	}

	
	// Explicit Wait
	
	public void waitForElementVisible(WebDriver driver, String typeLocator) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(typeLocator)));
		
	}
	
	public void waitForElementVisible(WebDriver driver, String typeLocator, String... restValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(typeLocator, restValues))));
		
	}
	
	public void waitForAllElementVisible(WebDriver driver, String typeLocator) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(typeLocator)));
		
	}
	
	public void waitForAllElementVisible(WebDriver driver, String typeLocator, String... restValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(typeLocator, restValues))));
		
	}
	
	public void waitForElementInVisible(WebDriver driver, String typeLocator) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(typeLocator)));
		
	}
	
	/* 
	 *  Wait for element undisplayed in DOM or not in DOM and override implicit timeout 
	 *  Kết hợp giữa implicit và explicit ở bài Wait để findElement , xem lại bài Wait để hiểu rõ thêm
	 */
	public void waitForElementUndisplayed(WebDriver driver, String typeLocator) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,shortTimeout);
		overrideGlobalTimeout(driver, shortTimeout);
		System.out.println("Start time for wait undisplayed =" + new Date().toString());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(typeLocator)));
		overrideGlobalTimeout(driver, longTimeout);
		System.out.println("End time for wait undisplayed =" + new Date().toString());
	}
	
	public void waitForElementInVisible(WebDriver driver, String typeLocator, String... restValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(typeLocator, restValues))));
		
	}
	
	public void waitForAllElementInVisible(WebDriver driver, String typeLocator) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElement(driver, typeLocator)));
	
	}
	
	public void waitForAllElementInVisible(WebDriver driver, String typeLocator, String... restValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElement(driver, getDynamicXpath(typeLocator, restValues))));
	
	}
	
	public void waitForElementClickable(WebDriver driver, String typeLocator) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(typeLocator)));
		
	}
	
	public void waitForElementClickable(WebDriver driver, String typeLocator, String... restValues) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(typeLocator, restValues))));
		
	}
	
	// ít dùng
	public void waitForElementPresence(WebDriver driver, String typeLocator) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(typeLocator)));
		
	}
	
	public void waitForAllElementPresence(WebDriver driver, String typeLocator) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(typeLocator)));
		
	}
	
	// Tối ưu ở bài học Level_07_Switch_Page / Switch Page UI
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		
		waitForElementClickable(driver, BasePageNopCommerceUI.ADDRESSES_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ADDRESSES_LINK);
		
		UserAddressPageObject a = PageGeneratorManager.getUserAddressPage(driver);
		return a;
		
	}
	
	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		
		waitForElementClickable(driver, BasePageNopCommerceUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageNopCommerceUI.REWARD_POINT_LINK);
		
		UserRewardPointPageObject r = PageGeneratorManager.getUserRewardPointPage(driver);
		return r;
		
	}
	
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		
		waitForElementClickable(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		
		UserMyProductReviewPageObject m = PageGeneratorManager.getUserMyProductReviewPage(driver);
		return m;
		
	}
	
	// Tối ưu ở bài học Level_09_Dynamic_Locator / Dynamic Xpath , gom tất cả hàm đã viết ở Level_07_Switch_Page lại thành 1 hàm
	// cách này dùng cho ít page: 10 - 20 pages
	public BasePage openPagesAtMyAccountByName(WebDriver driver, String areaName , String pageName) {
		
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, areaName, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, areaName, pageName);
		
		switch (pageName) {
		
		case "Addresses":
			
			return PageGeneratorManager.getUserAddressPage(driver);
			
		case "My product reviews":
			
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
			
		case "Reward points":

			return PageGeneratorManager.getUserRewardPointPage(driver);	

		default:
			
			throw new RuntimeException("Invalid page name at My Account area.");
			
		}
	}
	
	// cách này dùng cho nhiều page , vd : 15 - 20 page trở lên 
	// Pattern Object
	public void openPagesAtMyAccountByPageName(WebDriver driver, String areaName , String pageName) {
		
		waitForElementClickable(driver,BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, areaName, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, areaName, pageName);
	}
	
	// Level_08_Switch_Role 
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		
		waitForElementClickable(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_USER);
		
		UserHomePageObject u = PageGeneratorManager.getUserHomePage();
		u.setDriver(driver);
		return u;
	}
	
	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		
		waitForElementClickable(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_ADMIN);
		
		AdminLoginPageObject a = PageGeneratorManager.getAdminLoginPage(driver);
		return a;
	}
	
	// Wordpress 
	
	public UserHomePO openEndUserSite(WebDriver driver, String endUserUrl) {
		
		openPageUrl(driver, endUserUrl);
		return pageObjects.wordpress.PageGeneratorManager.getUserHomePO(driver);
	}
	
	public AdminDashboardPO openAdminSite(WebDriver driver, String adminUrl) {
		
		openPageUrl(driver, adminUrl);
		return pageObjects.wordpress.PageGeneratorManager.getAdminDashboardPO(driver);
	}
	
	//Level_05_Page_Factory
	// Học cho biết pageFactory , phỏng vấn có khả năng gặp
	// override method waitForElement , click , isDisplay , getText , sendkey ...
	
	public void waitForElementClickableByElement(WebDriver driver, WebElement element) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public void clickToElementByElement(WebElement element) {
		
		// k cần findElement vì FindBy đã giúp mình findElement r.
		element.click();
	}
	
	public void waitForElementVisibleByElement(WebDriver driver, WebElement element) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public boolean isElementDisplayedByElement(WebElement element) {
		
		return element.isDisplayed();
	}
	
	
	public String getElementTextByElement(WebElement element) {
		
		return element.getText();
	}
	
	public void sendkeyToElementByElement(WebElement element, String textValue) {
		
		element.clear();
		element.sendKeys(textValue);
		
	}
	
	private long longTimeout = GlobalConstants.LONG_TIME_OUT;
	private long shortTimeout = GlobalConstants.SHORT_TIME_OUT;
	
	// Level_18_Pattern_Object 
	// Java doc : quét tên method -> source -> generate element comment -> ra phần comment chi tiết màu xanh
	/**
	 * Enter to dynamic TEXTBOX by ID 
	 * @author anhnguyen
	 * @param driver
	 * @param textboxID
	 * @param value
	 */
	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}
	
	/** 
	 * Click to dynamic BUTTON by Text
	 * @author anhnguyen
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT,buttonText);
	}
	
	
	/**
	 * Select item in DROPDOWN by Name
	 * @author anhnguyen
	 * @param driver
	 * @param dropdownAttributeName
	 * @param itemValue
	 */
	public void selectToDropdownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
		
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
		selectItemByValueInDefaultDropdown(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
	}
	
	/**
	 * Click dynamic RADIO BUTTON by  Label
	 * @author anhnguyen
	 * @param driver
	 * @param radioButtonLabelName
	 */
	public void clickToRadioButtonByLabel(WebDriver driver, String radioButtonLabelName) {
		
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
		checkToDefaultCheckboxRadio(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
	}
	
	
	/**
	 * Click dynamic CHECK BOX by  Label
	 * @author anhnguyen
	 * @param driver
	 * @param checkBoxLabelName
	 */
	public void clickToCheckBoxByLabel(WebDriver driver, String checkBoxLabelName) {
		
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CHECK_BOX_BY_LABEL, checkBoxLabelName);
		checkToDefaultCheckboxRadio(driver, BasePageNopCommerceUI.DYNAMIC_CHECK_BOX_BY_LABEL, checkBoxLabelName);
	}
	
	public String getTextboxAttributeValueByID(WebDriver driver, String textboxID) {
		
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}
}
