package reportConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import common.GlobalConstants;
import common.BaseTest;

public class ReportNGListener implements ITestListener {
	
	// quản lý việc liên quan đến config của từng loại report

	// ITestResult đại diện cho Test Case
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("---------- " + result.getName() + " FAILED test ----------");
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		// tạo 1 testClass
		Object testClass = result.getInstance();
		// lấy driver đã tạo từ BaseTest thông qua testClass đã tạo
		WebDriver webDriver = ((BaseTest) testClass).getDriverInstance();

		// lấy ra screenPath nhét vào Report
		String screenshotPath = captureScreenshotBase64(webDriver, result.getName());
		Reporter.getCurrentTestResult();
		// Img FILE
		//Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///" + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
		
		// Img BASE 64
		Reporter.log("<br><a target=\"_blank\" href=\"data:image/png;base64," + screenshotPath + "\">" + "<img src=\"data:image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");

		Reporter.setCurrentTestResult(null);
		
	}
	
	
	// take a screen shot FILE IMG
	public String captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			//String screenPath = System.getProperty("user.dir") + "\\ReportNGScreenShots\\" + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
			String screenPath = GlobalConstants.REPORTNG_SCREENSHOT + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
			FileUtils.copyFile(source, new File(screenPath));
			return screenPath;
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
			return e.getMessage();
		}
	}
	
	// take a screen shot BASE 64 IMG
	public String captureScreenshotBase64(WebDriver driver, String screenshotName) {
		
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	// ITestContext đại diện cho Test Class
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
}
