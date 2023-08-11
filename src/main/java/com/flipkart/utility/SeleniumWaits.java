package com.flipkart.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.flipkart.reporting.TestListener;

public class SeleniumWaits {
	private WebDriver driver;

	public SeleniumWaits(WebDriver driver) {
		this.driver = driver; 
	}

	public void untillElementVisible(WebElement element, int duration, String elementName) {
		try {
			new WebDriverWait(driver, duration).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			TestListener.addComment("Fail", elementName + " --> not visible after wait of -->" + duration + " seconds");
			Assert.fail(e.getMessage());
		}
	}

	public void untillElementClickable(WebElement element, int duration, String elementName) {
		try {
			new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			TestListener.addComment("Fail",
					elementName + " --> not clickable after wait of -->" + duration + " seconds");
			Assert.fail(e.getMessage());
		}
	}
}
