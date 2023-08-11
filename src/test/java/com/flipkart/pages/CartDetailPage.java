package com.flipkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.utility.SeleniumOperations;
import com.flipkart.utility.SeleniumWaits;

public class CartDetailPage {
	private WebDriver driver;
	private SeleniumOperations seOperations;
	private SeleniumWaits wait;


	@FindBy(css = "input[class='_253qQJ']")
	private WebElement cartQty;
	
	@FindBy(xpath = "//span[text()='Place Order']")
	private WebElement placeOrder;
	
	@FindBy(xpath = "//div[text()='Remove']")
	private WebElement removeLink;
	
	@FindBy(xpath = "//div[@class='td-FUv WDiNrH']//div[text()='Remove']")
	private WebElement removeButton;
	
	@FindBy(xpath = "//div[@class='_2FYYw1 _2_OGP3 _2T1qz2']")
	private WebElement flipKartQty;
	
	public CartDetailPage(WebDriver driver) {
		this.driver = driver;
		seOperations = new SeleniumOperations(driver);
		wait = new SeleniumWaits(driver);
		PageFactory.initElements(driver, this);
	}
	
	public int getCartQty() {
		seOperations.scollElementToView(cartQty, "qty");
		return Integer.parseInt(seOperations.getAttributeValue(cartQty, "value"));
	}
	
	public String removeProductFromCart() {
		seOperations.clickUsingAction(removeLink, "remove link");
		seOperations.clickUsingAction(removeButton, "remove button");
		return seOperations.getText(flipKartQty, "flipkart Text Qty");
		
	}
}
