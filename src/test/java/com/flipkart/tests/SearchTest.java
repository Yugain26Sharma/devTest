package com.flipkart.tests;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.dataprovider.ProductDetailTestDp;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.SearchResultPage;
import com.flipkart.setup.BrowserSetup;
import com.flipkart.utility.Assertion;
import com.flipkart.utility.CsvUtils;
import com.flipkart.utility.JsonOperations;
import com.flipkart.utility.SeleniumOperations;
import com.jayway.jsonpath.JsonPath;

public class SearchTest {
	private WebDriver driver;
	private BrowserSetup browserSetup;
	private SeleniumOperations seOperations;

	private HomePage homePage;
	private SearchResultPage searchResultPage;

	@BeforeMethod(groups  = { "sanity" })
	public void setUp() {
		browserSetup = new BrowserSetup();
		driver = browserSetup.launchBrowser();
		seOperations = new SeleniumOperations(driver);

		homePage = new HomePage(driver);
		searchResultPage = new SearchResultPage(driver);
	}
	
	@Test(groups = { "sanity" })
	public void validateInvalidSearchResult() throws IOException {
		// fetch product deatils from json file
		String json = JsonOperations.readJsonFile("testDataPath", "products.json");
		String productCode = JsonPath.read(json, "$.invalidProduct.product1");

		homePage.closeLoginPopUp();
		homePage.searchProduct(productCode);

		Boolean invalidSearch = searchResultPage.inavlidSearchResult();

		Assertion.assertObjectEqual(true, invalidSearch, "Validate user get retry option for invalid search");
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		BrowserSetup.closeBrowser();
	}
}
