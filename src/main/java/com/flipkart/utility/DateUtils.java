package com.flipkart.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	// Date Time formats
	public static final String format1 = "dd-MM-yyyy:HH-mm-ss";

	public static String getCurrentDateTime(String format) {
		Date currentDate = new Date(System.currentTimeMillis());
		return formatDateTime(format, currentDate);
	}

	public static String formatDateTime(String format, Date date) {
		DateFormat formatBuilder = new SimpleDateFormat(format);
		return formatBuilder.format(date);
	}
}
