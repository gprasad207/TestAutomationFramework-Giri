package com.home.stepDefinition;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.grapecity.documents.excel.CsvSaveOptions;
import com.grapecity.documents.excel.IWorksheet;
import com.grapecity.documents.excel.Workbook;
import com.home.utility.WriteToFile;

import cucumber.api.java.en.Given;

public class YahooFinance extends AbstractPageStepDef {

	WriteToFile wf = new WriteToFile();
	WebDriver driver = getDriver();

	String stockName;
	String PercChange;
	ArrayList<String> list = new ArrayList<String>();
	static HashMap<String, ArrayList<String>> stockData = new HashMap<String, ArrayList<String>>();
	
	//Object[][] data = new Object[1680][5];

	
	
	@FindBy(xpath = "//*[@placeholder ='Search Stocks, Currencies']")
	WebElement search_Stock_Input;
	@FindBy(xpath = "//*[@id ='search-button']")
	WebElement search_Button;

	Logger logger = Logger.getLogger(YahooFinance.class);

	@Given("^I Load Yahoo finance page$")
	public void i_Load_Yahoo_finance_page() throws Throwable {
		try {

			driver.get("https://in.investing.com/");
			driver.manage().window().maximize();

		}

		catch (Exception e) {

			System.out.println("Went into exception");

		}

	}

	@Given("^I Search Following Stocks \"([^\"]*)\"$")
	public void i_Search_Following_Stocks(String stock) throws Throwable {

		try {

			stockName = stock;
			driver.findElement(By.xpath("//*[@placeholder ='Search Stocks, Currencies']")).sendKeys(stock);

			Actions actions = new Actions(driver);

			actions.sendKeys(Keys.ENTER).build().perform();

			Thread.sleep(1000);

			driver.findElement(
					By.xpath("//*[@class = 'tr common-table-item']//following::span[text()='Share - NSE'][last()]"))
					.click();
			Thread.sleep(1000);
		}

		catch (Exception e) {

			System.out.println("Went into exception" + e.getMessage());

		}

	}

	@Given("^I Search go to the chart \"([^\"]*)\"$")
	public void i_Search_go_to_the_chart(String interval) throws Throwable {
		try {

			if (interval.equals("day")) {

				driver.findElement(By.xpath("//*[text()='Historical']")).click();

				// System.out.println("i clicked Historical button");

			} else {
				driver.findElement(By.xpath("//*[text()='Historical']")).click();

				// System.out.println("i clicked Historical button");

			}

			Thread.sleep(1000);
		}

		catch (Exception e) {

			// System.out.println("Historical is failing" + e.getStackTrace());
		}
	}

	@Given("^I get the data$")
	public void i_get_the_data() throws Throwable {

		try {

			driver.findElement(By.xpath("//*[text()='Daily']")).click();

			Thread.sleep(1000);

			/*
			 * String dailyPerceMove =
			 * driver.findElement(By.xpath("//*[text()='Chg. %']//following-sibling::dd"))
			 * .getText();
			 * 
			 * Thread.sleep(1000);
			 * 
			 * String averagePrice =
			 * driver.findElement(By.xpath("//*[text()='Average']//following-sibling::dd"))
			 * .getText(); Thread.sleep(1000);
			 */

			/*
			 * for (int i = 1; i <= 5; i++) {
			 * 
			 * String dailyMove = driver .findElement(By.
			 * xpath("//*[@class = 'common-table-item u-clickable'][i]/td[7]/span[1]"))
			 * .getText();
			 */

			List<WebElement> elements = driver
					.findElements(By.xpath("//*[@class = 'common-table-item u-clickable']/td[7]/span[1]"));
			System.out.println("Number of elements:" + elements.size());

			for (int i = 1; i < 6; i++) {
				
				String dailyMove = "Day_Move" + i;
				
				 dailyMove = elements.get(i).getText();
				//System.out.println("Values are:" + dailyMove);

				dailyMove = dailyMove.replaceFirst(".$", "");

				list.add(dailyMove);

				Thread.sleep(200);

			}

			stockData.put(stockName, list);

			for (Map.Entry<String, ArrayList<String>> entry : stockData.entrySet())
				logger.info("Stock Name = " + entry.getKey() + ", Daily Moves = " + entry.getValue());
			
				logger.info(" ");
				
				
				
				// Initialize workbook
				Workbook workbook = new Workbook();
				        
				// Fetch default worksheet
				IWorksheet worksheet = workbook.getWorksheets().get(0);
				Object data = new Object[][] { 				
				{ stockName, "NewYork", new GregorianCalendar(1968, 6, 8), "male", 80, 180 }
					
				};

				// Set data
				worksheet.getRange("A1:F5").setValue(data);
				worksheet.getRange("A:F").setColumnWidth(20);

				// Setting ColumnSeparator/ RowSeparator & CellOperator in CSVSaveOptions
				CsvSaveOptions saveOption = new CsvSaveOptions();
				saveOption.setColumnSeparator(",");
				saveOption.setRowSeparator("\r\n");
				saveOption.setCellSeparator('"');
				System.out.println("----I AM HERE");
				// Saving workbook to csv
				workbook.save("SaveCSVDelimiter.csv", saveOption);

			/*
			 * String highPriceStock =
			 * driver.findElement(By.xpath("//*[text()='Average']//following-sibling::dd"))
			 * .getText();
			 * 
			 * Thread.sleep(1000);
			 * 
			 * driver.findElement(By.xpath("//*[text()='Weekly']")).click();
			 * 
			 * Thread.sleep(1000);
			 * 
			 * String weeklyPerceMove =
			 * driver.findElement(By.xpath("//*[text()='Chg. %']//following-sibling::dd"))
			 * .getText(); Thread.sleep(1000);
			 * 
			 * logger.info("Details of Stock-->" + stockName + "**"+"Highest Price"+"-->" +
			 * highPriceStock + "**Daily Price-->" + dailyPerceMove+ "**Weekly" + "-->" +
			 * weeklyPerceMove);
			 * 
			 * logger.info("			   ");
			 * 
			 * System.out.println("Details of Stock-->" + stockName +
			 * "**"+"Highest Price"+"-->" + highPriceStock + "**Daily Price-->" +
			 * dailyPerceMove+ "**Weekly" + "-->" + weeklyPerceMove);
			 * 
			 * Thread.sleep(1000);
			 * 
			 * // wf.writtingFile(stockName, PerceMove);
			 * 
			 * logger.info("Stock 20 days:-" + stockName + "---->" + dailyPerceMove);
			 */

		}

		catch (Exception e) {

			System.out.println("trace is adddressd" + e.getStackTrace());
		}

	}

}
