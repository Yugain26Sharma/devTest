package com.flipkart.setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.flipkart.reporting.TestListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup extends BrowserManager {

	private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<WebDriver>();

	public WebDriver launchBrowser() {
		WebDriver driver;
		String browserName = PropertyManager.getProperty("browser").toLowerCase();
		int globalWait = Integer.parseInt(PropertyManager.getProperty("implicitWait"));
		String applicationUrl = PropertyManager.getProperty("url");

		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = getChromeOptions();
			driver = new ChromeDriver(chromeOptions);
			break; 

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = getFireFoxOptions();
			driver = new FirefoxDriver(firefoxOptions);

		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = getEdgeOptions();
			driver = new EdgeDriver(edgeOptions);

		case "ie":
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions ieOptions = getIEOptions();
			driver = new InternetExplorerDriver(ieOptions);

		case "safari":
			WebDriverManager.safaridriver().setup();
			SafariOptions safariOptions = getSafariptions();
			driver = new SafariDriver(safariOptions);

		default:
			throw new IllegalArgumentException(String.join(":", "Invalid browser provided ", browserName));
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(globalWait, TimeUnit.SECONDS);
		webDriverThreadLocal.set(driver);
		navigateToUrl(applicationUrl);
		return getDriver();
	}

	public static WebDriver getDriver() {
		return webDriverThreadLocal.get();
	}

	public static void closeBrowser() { 
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			webDriverThreadLocal.remove();
		}
	}

	public void navigateToUrl(String url) {
		WebDriver driver = getDriver();
		driver.get(url);
	}

}
