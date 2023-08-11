package com.flipkart.utility;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import com.flipkart.setup.PropertyManager;

public class CsvUtils {

	private static Map<String, Map<String, String>> allValues = new HashMap<String, Map<String, String>>();

	public static Map<String, String> getCsvData(String identifier, String filePath) {
		Map<String, Map<String, String>> testCases = readTestData(filePath);
		Map<String, String> data = testCases.get(identifier);
		return data;
	}

	private static Map<String, Map<String, String>> readTestData(String filePath) {
		ICsvMapReader reader;
		try {
			reader = new CsvMapReader(new FileReader(filePath), CsvPreference.STANDARD_PREFERENCE);
			final String[] headers = reader.getHeader(true);
			Map<String, String> fieldsInCurrentRow;

			while ((fieldsInCurrentRow = reader.read(headers)) != null) {
				readingHeaders(fieldsInCurrentRow);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return allValues;
	}

	private static void readingHeaders(Map<String, String> header) {
		String testCase = "";
		if (header.keySet() != null) {
			Set<String> keys = header.keySet();

			for (String key : keys) {
				if (key.contains("KeyName")) {
					testCase = header.get(key);
					allValues.put(testCase, header);
					break;
				}

			}
		}
	}
	
	//file name with extension
	public static String getCsvFilePath(String fileName) {
		return Paths.get(PropertyManager.getProperty("testDataPath") + fileName).toString();
	}

}
