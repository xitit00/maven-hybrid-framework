//package reportConfig;
//
//import java.io.File;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import common.BaseTest;
//import common.GlobalConstants;
//import io.qameta.allure.Attachment;
//
//public class AllureTestListener implements ITestListener {
//
//	private static String getTestMethodName(ITestResult iTestResult) {
//		return iTestResult.getMethod().getConstructorOrMethod().getName();
//	}
//
//	// Screenshot attachments for Allure
//	@Attachment(value = "Screenshot of {0}", type = "image/png")
//	public static byte[] saveScreenshotPNG(String testName, WebDriver driver) {
//		return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//	}
//
//	// Text attachments for Allure
//	@Attachment(value = "Text attachment of {0}", type = "text/plain")
//	public static String saveTextLog(String message) {
//		return message;
//	}
//
//	// HTML attachments for Allure
//	@Attachment(value = "{0}", type = "text/html")
//	public static String attachHtml(String html) {
//		return html;
//	}
//
//	@Override
//	public void onTestFailure(ITestResult iTestResult) {
//		Object testClass = iTestResult.getInstance();
//		WebDriver driver = ((BaseTest) testClass).getDriverInstance();
//		saveScreenshotPNG(iTestResult.getName(), driver);
//		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
//	}
//	
//	@Override
//	// đã từng muốn gọi delete All File Json ở Allure-results trong onStart nhưng k đc 
//	public void onStart(ITestContext iTestContext) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void onTestSkipped(ITestResult iTestResult) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void onFinish(ITestContext arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onTestStart(ITestResult arg0) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void onTestSuccess(ITestResult arg0) {
//		// TODO Auto-generated method stub
//	}
//	
////	public void deleteAllFileInFolder() {
////		try {
////			
////			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
////			File file = new File(pathFolderDownload);
////			File[] listOfFiles = file.listFiles();
////			for (int i = 0; i < listOfFiles.length; i++) {
////				if (listOfFiles[i].isFile()) {
////					System.out.println(listOfFiles[i].getName());
////					new File(listOfFiles[i].toString()).delete();
////				}
////			}
////		} catch (Exception e) {
////			System.out.print(e.getMessage());
////		}
////	}
//
//
//}
