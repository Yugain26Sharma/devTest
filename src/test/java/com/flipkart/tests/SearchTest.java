package com.flipkart.tests;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

	
	@Test(groups = { "sanity" })
	public void validateInvalidSearchResult1() throws IOException {
	Assert.assertEquals(false, true);	
	}
	
	@Test(groups = { "sanity" })
	public void validateInvalidSearchResult2() throws IOException {
		Assert.assertEquals(false, false);	
	}
	
	@Test(groups = { "sanity" })
	public void validateInvalidSearchResult3() throws IOException {
		Assert.assertEquals(1, 1);	
	}


}
