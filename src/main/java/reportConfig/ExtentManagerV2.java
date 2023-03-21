//package reportConfig;
//package reportConfig;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//
//
//public class ExtentManagerV2 {
//	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
//	private static ExtentReports extent = ExtentManagerV2.getReporter();
//	
//	public synchronized static ExtentReports getReporter() {
//		if (extent == null) {
//			// ExtentReportV2 : thư mục chứa , ExtentScreenshot.html cái html chứa hình nhé
//			// extent = new ExtentReports(GlobalConstants.EXTENT_PATH + "/ExtentReportV2.html", true);
//			extent = new ExtentReports(System.getProperty("user.dir") + "/ExtentReportV2/ExtentScreenshot.html", true);
//		}
//		return extent;
//	}
//
//	public static synchronized ExtentTest getTest() {
//		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
//	}
//
//	public static synchronized void endTest() {
//		extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
//	}
//
//	public static synchronized ExtentTest startTest(String testName, String desc) {
//		ExtentTest test = extent.startTest(testName, desc);
//		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
//		return test;
//	}
//}
