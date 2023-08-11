 package com.flipkart.reporting;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.flipkart.setup.BrowserSetup;

public class TestListener implements ITestListener {
	private ExtentReports extent = ExtentReportManager.getInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override 
	public synchronized void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
		BrowserSetup.closeBrowser();
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL, MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
		extentTest.get().fail(result.getThrowable());
		String path = takeScreenshot(result.getMethod().getMethodName());
		extentTest.get().fail("<b>font color=red" + "Failure Screenshot" + "</font></b>",
				MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
		BrowserSetup.closeBrowser();
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, MarkupHelper.createLabel("Test Skipped", ExtentColor.YELLOW));
		extentTest.get().skip(result.getThrowable());
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
		extentTest.remove();
	}

	public static void addComment(String status, String message) {
		switch (status.toUpperCase()) {
		case "PASS":
			extentTest.get().log(Status.PASS, message);
			break;

		case "FAIL":
			extentTest.get().log(Status.FAIL, message);
			break;

		case "INFO":
			extentTest.get().log(Status.INFO, message);
			break;
		}
	}
	
	public static String takeScreenshot(String methodName) {
		TakesScreenshot screenShot = (TakesScreenshot)BrowserSetup.getDriver();
		return screenShot.getScreenshotAs(OutputType.BASE64);
	}

}
