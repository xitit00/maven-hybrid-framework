package common;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	//các hàm dùng cho tầng testcases , vd : User_01_
	private String projectPath = System.getProperty("user.dir");
	// thay vì dùng 
	//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
	//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
	//System.setProperty("webdriver.msedge.driver", projectPath + "/browserDriver/msedgedriver");
	// => dùng webdrivermanager thay thế 
	private WebDriver driverBaseTest;


	@BeforeSuite
	public void initBeforeSuite() {

		deleteAllureReport();
	}

	// gán final cho log ở Base Test thì sang class khác ko khởi tạo lại , chỉ gọi lại thôi
	protected final Log log;

	//khởi tạo constructor cho cái class hiện tại với Log
	protected BaseTest() {

		// LogFactory nhận vào 1 cái class hiện tại getClass()
		log = LogFactory.getLog(getClass());
	}

	// vì driver đc tạo khi TC gọi vào BaseTest , cho nên tạo 1 hàm trả về WebDriver cho việc take screenShot 
	public WebDriver getDriverInstance() {

		return this.driverBaseTest;
	}

	//Multiple browser
	//	protected WebDriver getBrowserDriver(String browserName, String environment) {
	//		
	//		
	//		switch (browserName) {
	//		
	//		case "firefox":
	//			
	//			WebDriverManager.firefoxdriver().setup();
	//			driverBaseTest = new FirefoxDriver();
	//		
	//		break;
	//		
	//		case "chrome":
	//			
	//			WebDriverManager.chromedriver().setup();
	//			driverBaseTest = new ChromeDriver();
	//			
	//		break;
	//		
	//		case "edge":
	//			
	//			WebDriverManager.edgedriver().setup();
	//			driverBaseTest = new EdgeDriver();
	//			
	//		break;
	//		
	//		case "h_firefox":
	//			
	//			WebDriverManager.firefoxdriver().setup();
	//			FirefoxOptions options = new FirefoxOptions();
	//			options.addArguments("--headless");
	//			options.addArguments("window-size=1920x1080");
	//			driverBaseTest = new FirefoxDriver(options);
	//		
	//		break;
	//	
	//		case "h_chrome":
	//			
	//			WebDriverManager.chromedriver().setup();
	//			ChromeOptions options1 = new ChromeOptions();
	//			options1.addArguments("-headless");
	//			options1.addArguments("window-size=1920x1080");
	//			driverBaseTest = new ChromeDriver(options1);
	//			
	//		break;
	//		
	//		// trừ đi 5,6 version chrome driver sẽ ra đc version cốc cốc
	//		case "coccoc":
	//			
	//			// truyền version vào là sẽ tải đc version như mong muốn để run Cốc cốc , vd : 91.0.4472.101
	//			WebDriverManager.chromedriver().driverVersion("").setup();
	//			ChromeOptions options2 = new ChromeOptions();
	//			options2.setBinary("/Applications/Cốc Cốc.app");
	//			driverBaseTest = new ChromeDriver(options2);
	//			
	//		break;
	//		
	//		// ko ổn định 
	//		case "safari":
	//			
	//			driverBaseTest = new SafariDriver();
	//			driverBaseTest.manage().window().maximize();
	//		
	//		break;
	//		
	//		// opera kéo driver vào đổi từ text edit -> .exe 
	//		case "opera":
	//			
	//			WebDriverManager.operadriver().setup();
	//			driverBaseTest = new OperaDriver();
	//		
	//		break;
	//		
	//		// ko ổn định , ít dùng , dùng trên window và từ window 11 đã khai tử bỏ 
	//		case "ie":
	//			
	//			WebDriverManager.iedriver().arch32();
	//			driverBaseTest = new InternetExplorerDriver();	
	//		break;
	//
	//		default:
	//			
	//			new RuntimeException("Browser name invalid");
	//			break;
	//		}
	//		
	//		// Set timeout tim element
	//		driverBaseTest.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	//		
	//		// open URL 
	//		
	//		driverBaseTest.get(getEnvironmentUrl(environment));
	//		
	//		return driverBaseTest;
	//	}

	// GlobalConstants
	//	private String getEnvironmentUrl(String environment) {
	//		
	//		String url = "";
	//		
	//		switch (environment) {
	//		case "DEV":
	//			
	//			url = GlobalConstants.DEV_PORTAL_PAGE_URL;
	//			break;
	//			
	//		case "STG":
	//			
	//			url = GlobalConstants.STG_PORTAL_PAGE_URL;
	//			break;
	//		
	//		case "TEST":
	//			
	//			url = GlobalConstants.TEST_PORTAL_PAGE_URL;
	//			break;
	//			
	//		case "PRO":
	//			
	//			url = GlobalConstants.PRO_PORTAL_PAGE_URL;
	//			break;
	//
	//		default:
	//			break;
	//		}
	//		
	//		return url;
	//	}

	//Enum 
	protected WebDriver getBrowserDriver(String browserName, String environmentName) {

		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

		switch (browserList) {

		case FIREFOX:


			WebDriverManager.firefoxdriver().setup();
			driverBaseTest = new FirefoxDriver();

			break;

		case CHROME:

			WebDriverManager.chromedriver().setup();
			driverBaseTest = new ChromeDriver();

			break;

		case EDGE:

			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();

			break;

		case H_FIREFOX:

			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driverBaseTest = new FirefoxDriver(options);

			break;

		case H_CHROME:

			WebDriverManager.chromedriver().setup();
			ChromeOptions options1 = new ChromeOptions();
			options1.addArguments("-headless");
			options1.addArguments("window-size=1920x1080");
			driverBaseTest = new ChromeDriver(options1);

			break;

			// trừ đi 5,6 version chrome driver sẽ ra đc version cốc cốc
		case COCCOC:

			// truyền version vào là sẽ tải đc version như mong muốn để run Cốc cốc , vd : 91.0.4472.101
			WebDriverManager.chromedriver().driverVersion("").setup();
			ChromeOptions options2 = new ChromeOptions();
			if (GlobalConstants.OS_NAME.startsWith("Mac")) {

				//MAC
				options2.setBinary("/Applications/Cốc Cốc.app");
			}
			else {

				//Windows
				options2.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			}
			driverBaseTest = new ChromeDriver(options2);

			break;

			// ko ổn định 
		case SAFARI:

			driverBaseTest = new SafariDriver();


			break;

			// opera kéo driver vào đổi từ text edit -> .exe 
		case OPERA:

			WebDriverManager.operadriver().setup();
			driverBaseTest = new OperaDriver();

			break;

			// ko ổn định , ít dùng , dùng trên window và từ window 11 đã khai tử bỏ 
		case IE:

			WebDriverManager.iedriver().arch32();
			driverBaseTest = new InternetExplorerDriver();	
			break;

		default:

			throw new RuntimeException("Browser name invalid");

		}

		// Set timeout tim element 
		// Mình chỉ cần set implicit wait 1 lần để sét time out cho tất cả element trong class TC.
		driverBaseTest.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// open URL 
		
		System.out.println(environmentName);
		driverBaseTest.get(getEnvironmentUrl(environmentName));

		//driverBaseTest.manage().window().setSize(new Dimension(414, 896));
		driverBaseTest.manage().window().maximize();

		return driverBaseTest;
	}

	//Enum 
	private String getEnvironmentUrl(String environment) {

		// xài cấu trúc If/else 
//		String url = "";
//
//		EnvironmentList enList = EnvironmentList.valueOf(environment);
//		if (enList == EnvironmentList.DEV) {
//
//			url = GlobalConstants.DEV_PORTAL_PAGE_URL;
//		}
//		else if (enList == EnvironmentList.STG) {
//
//			url = GlobalConstants.STG_PORTAL_PAGE_URL;
//		}
//		else if (enList == EnvironmentList.TESTING) {
//
//			url = GlobalConstants.TEST_PORTAL_PAGE_URL;
//		}
//		else {
//
//			url = GlobalConstants.PRO_PORTAL_PAGE_URL;
//		}
//
//		return url;

		// xài cấu trúc switch/case 

		String envUrl = null;

		EnvironmentList envi = EnvironmentList.valueOf(environment);

		switch (envi) {
		case DEV:

			envUrl = GlobalConstants.DEV_PORTAL_PAGE_URL;
			break;
		case TESTING:

			envUrl = GlobalConstants.TEST_PORTAL_PAGE_URL;
			break;
		case STG:

			envUrl = GlobalConstants.STG_PORTAL_PAGE_URL;
			break;
			
		case PRE_PRD:

			envUrl = GlobalConstants.PRE_PRD_PORTAL_PAGE_URL;
			break;
		case PRD:

			envUrl = GlobalConstants.PRO_PORTAL_PAGE_URL;
			break;
			
		default:
			envUrl = null;
			break;
		}

		return envUrl;
	}

	// Ở bài Level_10_Jquery_DataTable , mình sẽ tạm thời bỏ qua phần environment ( STG , DEV, .. sẽ học ở Multiple Enviroment sau ) 
	// => tạo 1 cái getBrowserDriver mới chỉ chứa browserName , appURL

	protected WebDriver getBrowserDriverOnlyOneUrl(String browserName, String appUrl) {

		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

		switch (browserList) {

		case FIREFOX:

			//			WebDriverManager.firefoxdriver().setup();
			//			FirefoxOptions firefoxSSL = new FirefoxOptions();
			//			// sét certificate SSL = true là hết lỗi "Your connection is not private"
			//			firefoxSSL.setAcceptInsecureCerts(true);
			WebDriverManager.firefoxdriver().setup();

			// Disable browser driver log
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + "\\FirefoxLog.txt");

			FirefoxOptions options = new FirefoxOptions();
			// localize language
			options.addPreference("intl.accept_languages", "vi-vn, vi, en-us, en");

			// disable notifications popup
			options.addArguments("--disable-notifications");

			//disable geolocation popup
			options.addArguments("--disable-geolocation");

			// auto save / download in firefox later


			driverBaseTest = new FirefoxDriver(options);

			break;

		case CHROME:

			//			WebDriverManager.chromedriver().setup();
			//			ChromeOptions chromeSSL = new ChromeOptions();
			//			// sét certificate SSL = true là hết lỗi "Your connection is not private"
			//			chromeSSL.setAcceptInsecureCerts(true);
			WebDriverManager.chromedriver().setup();

			// Disable browser diver log 
			System.setProperty("webdriver.chrome.args","--disable-loggin");
			System.setProperty("webdriver.chrome.silenOutput", "true");

			ChromeOptions chromeOptions = new ChromeOptions();

			// localize language
			chromeOptions.addArguments("--lang=vi");

			// disable notifications popup
			chromeOptions.addArguments("--disable-notifications");

			//disable geolocation popup
			chromeOptions.addArguments("--disable-geolocation");

			// automation info bar (Chrome latest)
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			// disable Save password chrome
			Map<String, Object> prefs = new HashMap<String,Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);


			// auto save / download in chrome 
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FILE);

			// run file prefs "disable Save password" and "auto save/download"
			chromeOptions.setExperimentalOption("prefs", prefs);


			driverBaseTest = new ChromeDriver(chromeOptions);

			break;

		case EDGE:

			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();

			break;

		case H_FIREFOX:

			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions hOptions = new FirefoxOptions();
			hOptions.addArguments("--headless");
			hOptions.addArguments("window-size=1920x1080");
			driverBaseTest = new FirefoxDriver(hOptions);

			break;

		case H_CHROME:

			WebDriverManager.chromedriver().setup();
			ChromeOptions options1 = new ChromeOptions();
			options1.addArguments("-headless");
			options1.addArguments("window-size=1920x1080");
			driverBaseTest = new ChromeDriver(options1);

			break;

			// trừ đi 5,6 version chrome driver sẽ ra đc version cốc cốc
		case COCCOC:

			// truyền version vào là sẽ tải đc version như mong muốn để run Cốc cốc , vd : 91.0.4472.101
			WebDriverManager.chromedriver().driverVersion("").setup();
			ChromeOptions options2 = new ChromeOptions();
			if (GlobalConstants.OS_NAME.startsWith("Mac")) {

				//MAC
				options2.setBinary("/Applications/Cốc Cốc.app");
			}
			else {

				//Windows
				options2.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			}
			driverBaseTest = new ChromeDriver(options2);

			break;

			// ko ổn định 
		case SAFARI:

			// khi setting safari driver cho MAC , mình có enable lên thì ko cần dòng setup() nữa nhé , thêm vào thì hơi dư
			//WebDriverManager.safaridriver().setup();
			driverBaseTest = new SafariDriver();
			break;

			// opera kéo driver vào đổi từ text edit -> .exe 
		case OPERA:

			WebDriverManager.operadriver().setup();
			driverBaseTest = new OperaDriver();

			break;

			// ko ổn định , ít dùng , dùng trên window và từ window 11 đã khai tử bỏ 
		case IE:

			WebDriverManager.iedriver().arch32();
			driverBaseTest = new InternetExplorerDriver();	
			break;

		default:

			throw new RuntimeException("Browser name invalid");

		}

		// Set timeout tim element
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIME_OUT, TimeUnit.SECONDS);

		// open URL 
		System.out.println("URL: " + appUrl);
		driverBaseTest.get(appUrl);


		driverBaseTest.manage().window().maximize();

		return driverBaseTest;
	}

	protected int generateFakeNumber() {

		Random random = new Random();
		return random.nextInt(99999);
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {

			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {

			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {

			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public void deleteAllureReport() {
		try {

			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-results";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driverBaseTest.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driverBaseTest != null) {

				// IE có 1 nhược điểm là cái TC của class trc đó mà có login vào r thì sau khi quit browser r -> chạy 1 TC của 
				// class khác ( khác thread ) nhưng IE vẫn giữ lại phiên đăng nhập của lần chạy cho Class trc 
				// -> dùng deleteAllCookies 
				driverBaseTest.manage().deleteAllCookies();
				driverBaseTest.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected String getCurrentDate() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int day = nowUTC.getDayOfMonth();
		String dayValue = "";
		// kiểm tra ngày < 10 thì thêm "0" vào cho đúng format 
		if (day < 10) {
			dayValue = "0" + day;
		}
		else {

			dayValue = String.valueOf(day);
		}
		return dayValue;
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		int month = now.getMonthOfYear();
		String monthValue = "";
		// kiểm tra tháng < 10 thì thêm "0" vào cho đúng format 
		if (month < 10) {
			monthValue = "0" + month;
		}
		else {

			monthValue = String.valueOf(month);
		}
		return monthValue;
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		return String.valueOf(now.getYear());
	}

	protected String getCurrentDay() {
		// tuỳ theo format của dự án mà mình sẽ return cho đúng (vd : mm/dd/yyyy hoặc dd/MM/yyyy) nha
		return getCurrentDate() + "/" + getCurrentMonth() + "/" + getCurrentYear();
	}

	// show Browsers Log , khi đã login vào Home hoặc chuyển trang thì gọi hàm này ra 
	protected void showBrowserConsoleLogs(WebDriver driver) {

		if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {

			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging:logList) {

				if (logging.getLevel().toString().toLowerCase().contains("error")) {

					log.info("---" + logging.getLevel().toString() + "--- \n" + logging.getMessage());
				}
			}
		}
	}
}
