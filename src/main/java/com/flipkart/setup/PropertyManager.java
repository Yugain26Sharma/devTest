package com.flipkart.setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager { 

	private static Properties properties;
	private static String propertyFilePath = "src/test/resources/PropertyFiles/config.properties";

	// load the property file
	static {
		try {
			FileInputStream fileStream = new FileInputStream(propertyFilePath);
			properties = new Properties();
			properties.load(fileStream);
			fileStream.close();
		} catch (IOException e) {
			System.err.println("Failed to load the property file" + e.getMessage());
		}
	}

	// get data from property file
	public static String getProperty(String keyName) {
		return properties.getProperty(keyName);
	}

}
