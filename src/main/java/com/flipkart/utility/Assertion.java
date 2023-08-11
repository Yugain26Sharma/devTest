package com.flipkart.utility;

import org.testng.Assert;

import com.flipkart.reporting.TestListener;

public class Assertion { 

	public static void assertObjectEqual(Object expected, Object actual, String message) {
		if (expected == null ? actual == null : expected.equals(actual)) {
			TestListener.addComment("Pass", message + "passed");
		} else {
			TestListener.addComment("Fail",
					message + "failed" + "Expected value is --> " + expected + ".Actual value is --> " + actual);
			Assert.fail();
		}
	}

	public static void assertObjectNotEqual(Object expected, Object actual, String message) {
		if (!(expected == null ? actual == null : expected.equals(actual))) {
			TestListener.addComment("Pass", message + "passed");
		} else {
			TestListener.addComment("Fail",
					message + "failed" + "Expected value is --> " + expected + ".Actual value is --> " + actual);
			Assert.fail();
		}
	}

}
