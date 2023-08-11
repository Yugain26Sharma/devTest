package com.flipkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.utility.SeleniumOperations;
import com.flipkart.utility.SeleniumWaits;

public class HomePage {
	private WebDriver driver;
	private SeleniumOperations seOperations;
	private SeleniumWaits wait;

	@FindBy(xpath = "//input[contains(@title,'Search')]")
	private WebElement searchBox;

	@FindBy(xpath = "//button[text()='✕']")
	private WebElement loginPopUpCloseButton;

	@FindBy(css = "div[class='KK-o3G']")
	private WebElement cartQty;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		seOperations = new SeleniumOperations(driver);
		wait = new SeleniumWaits(driver);
		PageFactory.initElements(driver, this);
	}

	public void closeLoginPopUp() {
		seOperations.clickUsingAction(loginPopUpCloseButton, "login pop up close button");
	}

	public void searchProduct(String productName) {
		seOperations.sendKeys(searchBox, productName, "search box");
		searchBox.submit();
	}

	public int getCartQty() {
		wait.untillElementClickable(cartQty, 20, "cart icon");
		return Integer.parseInt(seOperations.getText(cartQty, "cart quantity"));

	}
}
