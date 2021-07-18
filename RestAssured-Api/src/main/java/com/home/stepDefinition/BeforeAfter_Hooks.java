package com.home.stepDefinition;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BeforeAfter_Hooks extends AbstractPageStepDef{
	

	public void openChromeAndLaunchTheApplication() throws Throwable
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://in.finance.yahoo.com");
		driver.manage().deleteAllCookies();
	}
	

}
