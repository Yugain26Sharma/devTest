package com.flipkart.utility;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.flipkart.reporting.TestListener;

public class SeleniumOperations {

	private WebDriver driver;

	public SeleniumOperations(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		String title = null;
		try {
			title = driver.getTitle();
			TestListener.addComment("Pass", "User fetched the page title succesfully");
		} catch (Exception e) {
			TestListener.addComment("Fail", "User failed to fetched the page title");
			Assert.fail(e.getMessage());
		}
		return title;
	}

	public void sendKeys(WebElement element, String text, String fieldName) {
		try {
			element.clear();
			element.sendKeys(text);
			TestListener.addComment("Pass", "User sent text--> " + text + " to field -->" + fieldName);
		} catch (Exception e) {
			TestListener.addComment("Fail", "User failed to sent text--> " + text + " to field -->" + fieldName);
			Assert.fail(e.getMessage());
		}
	}

	public void clickUsingAction(WebElement element, String fieldName) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();
			TestListener.addComment("Pass", "User clicked on field -->" + fieldName);
		} catch (Exception e) {
			TestListener.addComment("Fail", "User failed to click on field -->" + fieldName);
			Assert.fail(e.getMessage());
		}
	}

	public String getCurrentWindowHandle() {
		String windowHandle = null;
		try {
			windowHandle = driver.getWindowHandle();
		} catch (Exception e) {
			TestListener.addComment("Fail", "User failed to fetch window handle");
			Assert.fail(e.getMessage());
		}
		return windowHandle;
	}

	public Set<String> getAllWindowHandles() {
		Set<String> windowHandles = null;
		try {
			windowHandles = driver.getWindowHandles();
		} catch (Exception e) {
			TestListener.addComment("Fail", "User failed to fetch window handles");
			Assert.fail(e.getMessage());
		}
		return windowHandles;
	}

	public void switchToWindow(String windowName) {
		try {
			driver.switchTo().window(windowName);
		} catch (Exception e) {
			TestListener.addComment("Fail", "User failed to switch to window --> " + windowName);
			Assert.fail(e.getMessage());
		}
	}

	public String getAttributeValue(WebElement element, String attributeName) {
		String attributeValue = null;
		try {
			attributeValue = element.getAttribute(attributeName);
		} catch (Exception e) {
			TestListener.addComment("Fail", "User failed to fetch attribute value");
			Assert.fail(e.getMessage());
		}
		return attributeValue;
	}

	public String getText(WebElement element, String fieldName) {
		String text = null;
		try {
			text = element.getText();
			TestListener.addComment("Pass", "User fetched tex value --> " + text + "for field-->" + fieldName);
		} catch (Exception e) {
			TestListener.addComment("Fail", "User fetched tex value for field-->" + fieldName);
			Assert.fail(e.getMessage());
		}
		return text;
	}

	public void scollElementToView(WebElement element, String fieldName) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			TestListener.addComment("Pass", "Scrolled to field --> " + fieldName);
		} catch (Exception e) {
			TestListener.addComment("Fail", "Not able to scroll to field-->" + fieldName);
			Assert.fail(e.getMessage());
		}
	}
}
