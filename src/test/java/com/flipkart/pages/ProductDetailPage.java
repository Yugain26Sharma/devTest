package com.flipkart.pages;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.utility.SeleniumOperations;
import com.flipkart.utility.SeleniumWaits;

public class ProductDetailPage {
	private WebDriver driver;
	private SeleniumOperations seOperations;
	private SeleniumWaits wait;
	
	@FindBy(css = "svg[class='_1KOMV2']")
	private WebElement addToCart;
	
	@FindBy(xpath = "//div[@class='_3LWZlK']")
	private WebElement productRating;
	
	@FindBy(xpath = "//div[@class='_30jeq3 _16Jk6d']")
	private WebElement productPrice;
	
	//div[@class='_30jeq3 _16Jk6d']
	
	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
		seOperations = new SeleniumOperations(driver);
		wait = new SeleniumWaits(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void addProductToCart() {
		seOperations.clickUsingAction(addToCart, "add to cart button");
	}
	
	public Map<String, String> getProductDetails(){
		Map<String, String> productDetails = new LinkedHashMap<>();
		productDetails.put("productRating", seOperations.getText(productRating, "productRating"));
		productDetails.put("productPrice", seOperations.getText(productPrice, "productPrice").substring(1));
		return productDetails;
	}
	
}
