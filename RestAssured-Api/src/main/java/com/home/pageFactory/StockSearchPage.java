package com.home.pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StockSearchPage {
	
	WebDriver driver;
	
	final static Logger logger = Logger.getLogger(StockSearchPage.class);
		
	@FindBy(xpath = "//*[@placeholder ='Search Stocks, Currencies']") WebElement search_Stock_Input;	
	@FindBy(xpath = "//*[@id ='search-button']" ) WebElement search_Button;
	
	
	public void search_Stock_Investment(WebDriver driver) throws Throwable
	{
		PageFactory.initElements(driver, this);
		
		
		
		
	}
	
	

}
