package com.flipkart.setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUserSetup { 
	private static Properties properties;

	// load the property file having test user details
	static {
		try {
			FileInputStream fileStream = new FileInputStream(PropertyManager.getProperty("testUserFilePath"));
			properties = new Properties();
			properties.load(fileStream);
			fileStream.close();
		} catch (IOException e) {
			System.err.println("Failed to load the property file" + e.getMessage());
		}
	}

	// get the user id
	public static String getUserId() {
		return properties.getProperty("userId");
	}

	// get the user password
	public static String getUserPassword() {
		return properties.getProperty("userPassword");
	}
}
