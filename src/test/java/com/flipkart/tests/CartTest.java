package com.flipkart.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.pages.CartDetailPage;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.ProductDetailPage;
import com.flipkart.pages.SearchResultPage;
import com.flipkart.setup.BrowserSetup;
import com.flipkart.utility.Assertion;
import com.flipkart.utility.JsonOperations;
import com.flipkart.utility.SeleniumOperations;
import com.jayway.jsonpath.JsonPath;

//@Listeners(TestListener.class)
public class CartTest {
	private WebDriver driver;
	private BrowserSetup browserSetup;
	private SeleniumOperations seOperations;

	private HomePage homePage;
	private SearchResultPage searchResultPage;
	private ProductDetailPage productDetailPage;
	private CartDetailPage cartDetailPage;

	@BeforeMethod
	public void setUp() {
		browserSetup = new BrowserSetup();
		driver = browserSetup.launchBrowser();
		seOperations = new SeleniumOperations(driver);

		homePage = new HomePage(driver);
		searchResultPage = new SearchResultPage(driver);
		productDetailPage = new ProductDetailPage(driver);
		cartDetailPage = new CartDetailPage(driver);

	}

	// Test Cart qunatity is incremented on adding product to cart
	@Test
	public void validateAddProductToCart() throws IOException {
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
		productDetailPage.addProductToCart();
		// validate the product count is incremented by 1
		Assertion.assertObjectEqual(1, cartDetailPage.getCartQty(), "Validate the cart qty is increased by 1");

	}

	@Test
	public void validateRemoveCartProduct() throws IOException {
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
		productDetailPage.addProductToCart();
		// validate the product count is incremented by 1
		Assertion.assertObjectEqual(1, cartDetailPage.getCartQty(), "Validate the cart qty is increased by 1");
		String qty = cartDetailPage.removeProductFromCart();

		Assertion.assertObjectEqual("Flipkart", qty, "Validate the cart qty is empty");
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		BrowserSetup.closeBrowser();
	}

}
