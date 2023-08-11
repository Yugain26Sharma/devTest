package com.flipkart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.utility.SeleniumOperations;
import com.flipkart.utility.SeleniumWaits;

public class SearchResultPage {
	private WebDriver driver;
	private SeleniumOperations seOperations;
	private SeleniumWaits wait;

	@FindBy(css = "div[class='_1AtVbE col-12-12'] a[rel='noopener noreferrer']")
	private List<WebElement> searchResults;
	
	@FindBy(xpath = "//span[text()='RETRY']")
	private WebElement invalidSearch;
	
	
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		seOperations = new SeleniumOperations(driver);
		wait = new SeleniumWaits(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickSearchResult(int index) {
		if (index >= 0 && index < searchResults.size()) {
			WebElement searchResult = searchResults.get(index);
			wait.untillElementClickable(searchResult, 20, "first search result");
			seOperations.clickUsingAction(searchResult, "first search result");
		} else {
			throw new IllegalArgumentException("The index provided is invalid: " + index);
		}
	}
	
	public Boolean inavlidSearchResult() {
		if(invalidSearch.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public void fetchSearchResultDisplayed() {
		
	}

}
