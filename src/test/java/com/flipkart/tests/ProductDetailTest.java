package com.flipkart.tests;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.dataprovider.ProductDetailTestDp;
import com.flipkart.pages.CartDetailPage;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.ProductDetailPage;
import com.flipkart.pages.SearchResultPage;
import com.flipkart.setup.BrowserSetup;
import com.flipkart.utility.Assertion;
import com.flipkart.utility.JsonOperations;
import com.flipkart.utility.SeleniumOperations;
import com.jayway.jsonpath.JsonPath;

public class ProductDetailTest {
	private WebDriver driver;
	private BrowserSetup browserSetup;
	private SeleniumOperations seOperations;

	private HomePage homePage;
	private SearchResultPage searchResultPage;
	private ProductDetailPage productDetailPage;

	@BeforeMethod(groups  = { "sanity" })
	public void setUp() {
		browserSetup = new BrowserSetup();
		driver = browserSetup.launchBrowser();
		seOperations = new SeleniumOperations(driver);

		homePage = new HomePage(driver);
		searchResultPage = new SearchResultPage(driver);
		productDetailPage = new ProductDetailPage(driver);
	}

	@Test(dataProvider = "productDetail", dataProviderClass = ProductDetailTestDp.class,groups  = { "sanity" })
	public void validateProductDetail(Map<String, String> testData) throws IOException {
		// fetch product deatils from json file
		String json = JsonOperations.readJsonFile("testDataPath", "products.json");
		String productCode = JsonPath.read(json, "$.regularProduct.product1");

	    homePage.closeLoginPopUp();
		homePage.searchProduct(productCode);
		searchResultPage.clickSearchResult(0);

		String currentWindowHandle = seOperations.getCurrentWindowHandle();
		// switch to the product detail page window
		for (String windowHandle : seOperations.getAllWindowHandles()) {
			if (!windowHandle.equals(currentWindowHandle)) {
				seOperations.switchToWindow(windowHandle);
				break;
			}
		}

		Map<String, String> actualProductDetail = productDetailPage.getProductDetails();

		Assertion.assertObjectEqual(testData.get("ProductRating"), actualProductDetail.get("productRating"),
				"Validate the product Rating");
		Assertion.assertObjectEqual(testData.get("ProductPrice"), actualProductDetail.get("productPrice"),
				"Validate the product Price");
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		BrowserSetup.closeBrowser();
	}

}
