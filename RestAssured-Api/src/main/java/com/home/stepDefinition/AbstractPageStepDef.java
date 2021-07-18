package com.home.stepDefinition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class AbstractPageStepDef {
	
	protected static ChromeDriver driver ;
	
	protected WebDriver getDriver() {
		
		if (driver == null) {
			
			WebDriverManager.chromedriver().setup();		
			
			ChromeOptions options = new ChromeOptions();
			
				options.addArguments("window-size=1960x1080");			
				options.setExperimentalOption("useAutomationExtension", false);			
				//options.addArguments("disable-popup-blocking");
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				/*
				 * options.addArguments("--disable-notifications");
				 * options.addArguments("disable-infobars");
				 * options.addArguments("disable-popup-blocking");
				 * options.addArguments("--disable-infobars");
				 * options.addArguments("--disable-web-security");
				 * options.addArguments("--allow-running-insecure-content");
				 * options.addArguments("--test-type");
				 * options.addArguments("--start-maximized");
				 */
			 
			
			driver = new ChromeDriver(options);
		}
		
		
		return driver;

	}
}