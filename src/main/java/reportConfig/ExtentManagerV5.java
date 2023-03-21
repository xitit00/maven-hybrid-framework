package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import common.GlobalConstants;

public class ExtentManagerV5 {
	public static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.PROJECT_PATH + "/ExtentReportV5/ExtentReport.html");
		reporter.config().setReportName("JQuery Upload HTML");
		reporter.config().setDocumentTitle("JQuery Upload HTML");
		reporter.config().setTimelineEnabled(true);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTheme(Theme.STANDARD);

		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Company", "Automation FC");
		extentReports.setSystemInfo("Project", "JQuery Upload");
		extentReports.setSystemInfo("Team", "Auto QA");
		extentReports.setSystemInfo("JDK version", GlobalConstants.JAVA_VERSION);
		return extentReports;
	}
}
