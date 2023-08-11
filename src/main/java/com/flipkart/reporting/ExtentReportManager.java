package com.flipkart.reporting;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.flipkart.utility.DateUtils;
 
public class ExtentReportManager {
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	private final static String REPORT_NAME = "FlipkartTestResults";

	private static String reportDirectory = System.getProperty("user.dir") + "/reports/";

	public synchronized static ExtentReports getInstance() {
		if (extent == null) {
			extent = createInstance();
		}
		return extent;
	}

	public synchronized static ExtentTest createTest(String testName) {
		ExtentTest test = getInstance().createTest(testName);
		extentTest.set(test);
		return test;
	}

	public synchronized static ExtentTest getTest() {
		return extentTest.get();
	}

	public synchronized static void removeTest() {
		extentTest.remove();
	}

	private static ExtentReports createInstance() {

		String currentDate = DateUtils.getCurrentDateTime(DateUtils.format1);
		String executionStartDate = currentDate.split(":")[0];
		String executionStartTime = currentDate.split(":")[1];
		;

		String FileName = REPORT_NAME;
		// create directory
		new File(reportDirectory).mkdirs();

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
				reportDirectory + executionStartDate + "/" + FileName + executionStartTime + ".html");

		sparkReporter.config().setDocumentTitle("ExtentReport");
		sparkReporter.config().setReportName("Flipkart Test Automation");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		return extent;

	}
}
