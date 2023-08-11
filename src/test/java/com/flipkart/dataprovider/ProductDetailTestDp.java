package com.flipkart.dataprovider;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.flipkart.utility.CsvUtils;

public class ProductDetailTestDp {

	@DataProvider(name = "productDetail")
	public Object[][] searchTestData(Method m) {
		String csvDataPath = CsvUtils.getCsvFilePath("productConfig.csv");
		Map<String, String> csvData = CsvUtils.getCsvData(m.getName(), csvDataPath);
		return new Object[][] { { csvData } };
	}
}
