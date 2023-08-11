package com.flipkart.setup;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

public class BrowserManager { 
	
	private String popUpBlockerChrome = "--disable-popup-blocking";
	
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		// set chrome specific options if required
		options.addArguments(popUpBlockerChrome);
		return options;
	}

	public FirefoxOptions getFireFoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		// set fireFox specific options if required
		return options;
	}

	public EdgeOptions getEdgeOptions() {
		EdgeOptions options = new EdgeOptions();
		// set edge specific options if required
		return options;
	}

	public InternetExplorerOptions getIEOptions() {
		InternetExplorerOptions options = new InternetExplorerOptions();
		// set ie specific options if required
		return options;
	}

	public SafariOptions getSafariptions() {
		SafariOptions options = new SafariOptions();
		// set safari specific options if required
		return options;
	}
}
