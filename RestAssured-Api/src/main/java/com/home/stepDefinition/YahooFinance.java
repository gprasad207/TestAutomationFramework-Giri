package com.home.stepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class YahooFinance extends AbstractPageStepDef {
	WebDriver driver= getDriver();
	
	String stockName;
	String PercChange;
	
	@FindBy(xpath = "//*[@placeholder ='Search Stocks, Currencies']") WebElement search_Stock_Input;	
	@FindBy(xpath = "//*[@id ='search-button']" ) WebElement search_Button;
	
	Logger logger = Logger.getLogger(YahooFinance.class);
	
	@Given("^I Load Yahoo finance page$")
	public void i_Load_Yahoo_finance_page() throws Throwable {		
		try {
			
			driver.get("https://in.investing.com/");		
			driver.manage().window().maximize();
			
			/*
			 * List<WebElement> listOfElements = driver.findElements(By.xpath(//*[@role="tablist"]));
			 * 
			 * System.out.println(listOfElements.iterator().next().getText());
			 */
			
		}
		
		catch(Exception e){
			
			System.out.println("Went into exception");			
			
		}	
	    
	}
		
	@Given("^I Search Following Stocks \"([^\"]*)\"$")
	public void i_Search_Following_Stocks(String stock) throws Throwable {
		
	try {
		
		stockName =stock;
		driver.findElement(By.xpath("//*[@placeholder ='Search Stocks, Currencies']")).sendKeys(stock);		
		
		  Actions actions = new Actions(driver);
		  
		  actions.sendKeys(Keys.ENTER).build().perform();
		 
		  Thread.sleep(800);
		 
		//String s = driver.findElement(By.xpath("//*[@class = 'tr common-table-item']//following::span[text()='Share - NSE'][last()]")).getText();
			
		//System.out.println("---------->"+s );
		driver.findElement(By.xpath("//*[@class = 'tr common-table-item']//following::span[text()='Share - NSE'][last()]")).click();
		 Thread.sleep(1000);
		}
		
		catch(Exception e){
			
			System.out.println("Went into exception"+ e.getMessage());	
		
			
		}		    
	
	}
	
	@Given("^I Search go to the chart \"([^\"]*)\"$")
	public void i_Search_go_to_the_chart(String interval ) throws Throwable {
		try {
			
			driver.findElement(By.xpath("//*[text()='Historical']")).click();
			
			
			Thread.sleep(1000);
			
			if (interval.equals("day")) {
				driver.findElement(By.xpath("//*[text()='Daily']")).click();

			} 
			
			// driver.findElement(By.xpath("//*[text()='Daily']")).click();
			   
			/*
			 * String Change=
			 * driver.findElement(By.xpath("//*[text()='Chg. %']")).getText();
			 * System.out.println("++++++++++>" + Change);
			 */
			  
			  String s=  driver.findElement(By.xpath("//*[text()='Chg. %']//following-sibling::dd")).getText();
			//  System.out.println("Percentage change of the Stock 20 days:-"+ stockName +"IS---->"+ s );
			  
			  logger.info("Percentage change of the Stock 20 days:-"+ stockName +"Is---->"+ s );
			
			
			Thread.sleep(1000);
		}
		   
		   catch(Exception e) 
		   {
			   
			   System.out.println("trace is adddressd"+e.getStackTrace());
		   }
	}


}
