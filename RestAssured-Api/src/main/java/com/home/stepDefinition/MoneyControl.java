package com.home.stepDefinition;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.grapecity.documents.excel.CsvSaveOptions;
import com.grapecity.documents.excel.IWorksheet;
import com.grapecity.documents.excel.Workbook;
import com.home.utility.WriteToFile;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class MoneyControl extends AbstractPageStepDef {

	WriteToFile wf = new WriteToFile();
	WebDriver driver = getDriver();

	String stockName;
	String PercChange;
	ArrayList<String> list = new ArrayList<String>();
	static HashMap<String, ArrayList<String>> stockData = new HashMap<String, ArrayList<String>>();

	ArrayList<String> loss = null;

	static HashMap<String, String> topLooser = new HashMap<String, String>();

	static ArrayList<String> Scrips = new ArrayList<String>();
	static ArrayList<String> scrips_Didnt_Load = new ArrayList<String>();

	@FindBy(xpath = "//a[@id='top_gain_lose2']")
	WebElement top_Looser;

	@FindBy(xpath = "//*[@class='bsr_table hist_tbl_hm']/table/tbody")
	WebElement WebTableOfCompanies;

	WebElement WebTableOfCompany;
	WebElement WebTableOfCompany2;

	@FindBy(xpath = "//*[@title='more']")
	WebElement moreCompanies;

	@FindBy(xpath = "//*[@id='wzrk-cancel']")
	WebElement cancel_Popup;

	WebElement indices_Name = null;
	String MarketCap;
	String MarketStockPrice;

	Logger logger = Logger.getLogger(MoneyControl.class);

	@Given("^I Load Money Control page$")
	public void i_Load_Money_Control() throws Throwable {
		try {

			driver.get("https://www.moneycontrol.com/stocks/marketstats/index.php");
			driver.findElement(By.xpath("//*[@id='wzrk-cancel']")).click();
			driver.manage().window().maximize();
			Thread.sleep(2000);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			driver.findElement(By.xpath("//a[@id='top_gain_lose2']")).click();

			driver.findElement(By.xpath("//a[@id='top_lose_nse']")).click();
			driver.findElement(By.xpath("//*[@id='toplose_nse']/div[2]/a")).click();

		}

		catch (Exception e) {

			System.out.println("Went into exception");

		}

	}

	//@Given("^I get the Company- five days Perfermance \"([^\"]*)\ and Loss Days \"([^\"]*)\"$")
	
	@Given("^I get the Company- five days Perfermance \"([^\"]*)\"  and Loss Days \"([^\"]*)\"$")
	public void i_get_the_Company_five_days_Perfermance_and_Loss_Days(String scripMCap, String lossDays)
			throws InterruptedException {

		// JavascriptExecutor js = (JavascriptExecutor) driver;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		Thread.sleep(1000);
		try {
			driver.findElement(
					By.xpath("//*[@class='statlf_title' and text()='NSE']//following::a[text()='" + scripMCap + "']"))
					.click();

		}

		catch (Exception e) {
			driver.findElement(By.xpath("//p[text()='NSE']//parent::div/ul/li/a[text()='" + scripMCap + "']")).click();

		}

		for (int k = 1; k < 2; k++) {

			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("window.scrollBy(0,1600)");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@title='more']")).click();

		}

		List<WebElement> rows1 = driver.findElements(
				By.xpath("//*[@id='mc_content']/section/section/div[1]/div[2]/div/div/div[2]/table/tbody/tr"));

		// List<WebElement> rows= WebTableOfCompany.findElements(By.tagName("tr"));

		int countofCompanies = rows1.size();
		System.out.println(" Total Count of the Loss for last 3 days comanies::-->" + countofCompanies);

		String celltext;
		String celltext1;
		String celltext2;
		String celltext3;
		String celltext4;
		String celltext5;

		for (int i = 0; i <= countofCompanies - 880; i++) {

			// System.out.println("Get the Text::-->" + rows1.get(i).getText());
			List<WebElement> columns = rows1.get(i).findElements(By.tagName("td"));

			celltext = columns.get(0).getText();
			int j = i + 1;

			List<WebElement> countOfPL = driver.findElements(
					By.xpath("//*[@id='mc_content']/section/section/div[1]/div[2]/div/div/div[2]/table/tbody/tr[" + j
							+ "]/td[15]/div"));

			if (countOfPL.size() == 5)

			{

				celltext1 = driver.findElement(
						By.xpath("//*[@id='mc_content']/section/section/div[1]/div[2]/div/div/div[2]/table/tbody/tr["
								+ j + "]/td[15]/div[1]"))
						.getAttribute("class");
				celltext2 = driver.findElement(
						By.xpath("//*[@id='mc_content']/section/section/div[1]/div[2]/div/div/div[2]/table/tbody/tr["
								+ j + "]/td[15]/div[2]"))
						.getAttribute("class");
				celltext3 = driver.findElement(
						By.xpath("//*[@id='mc_content']/section/section/div[1]/div[2]/div/div/div[2]/table/tbody/tr["
								+ j + "]/td[15]/div[3]"))
						.getAttribute("class");
				celltext4 = driver.findElement(
						By.xpath("//*[@id='mc_content']/section/section/div[1]/div[2]/div/div/div[2]/table/tbody/tr["
								+ j + "]/td[15]/div[4]"))
						.getAttribute("class");
				celltext5 = driver.findElement(
						By.xpath("//*[@id='mc_content']/section/section/div[1]/div[2]/div/div/div[2]/table/tbody/tr["
								+ j + "]/td[15]/div[5]"))
						.getAttribute("class");

				loss = new ArrayList<String>();

				switch (lossDays) {

				case ("2"):

				{

					if ((celltext5.equalsIgnoreCase("changea red")) && (celltext4.equalsIgnoreCase("changea red")))

					{

						loss.add(celltext1);
						loss.add(celltext2);
						loss.add(celltext3);
						loss.add(celltext4);
						loss.add(celltext5);

						stockData.put(celltext, loss);

						Thread.sleep(1000);

					}
				}

				case ("3"):

				{

					if ((celltext5.equalsIgnoreCase("changea red")) && (celltext4.equalsIgnoreCase("changea red"))
							&& (celltext3.equalsIgnoreCase("changea red")))

					{

						loss.add(celltext1);
						loss.add(celltext2);
						loss.add(celltext3);
						loss.add(celltext4);
						loss.add(celltext5);

						stockData.put(celltext, loss);

						Thread.sleep(1000);

					}
				}

				case ("4"):

				{
					if ((celltext5.equalsIgnoreCase("changea red")) && (celltext4.equalsIgnoreCase("changea red"))
							&& (celltext3.equalsIgnoreCase("changea red"))&& (celltext2.equalsIgnoreCase("changea red")))

					{

						loss.add(celltext1);
						loss.add(celltext2);
						loss.add(celltext3);
						loss.add(celltext4);
						loss.add(celltext5);

						stockData.put(celltext, loss);

						Thread.sleep(1000);

					}
				}

				case ("5"):

				{

					if ((celltext5.equalsIgnoreCase("changea red")) && (celltext4.equalsIgnoreCase("changea red"))
							&& (celltext3.equalsIgnoreCase("changea red")) && (celltext2.equalsIgnoreCase("changea red")
									&& (celltext1.equalsIgnoreCase("changea red"))))

					{

						loss.add(celltext1);
						loss.add(celltext2);
						loss.add(celltext3);
						loss.add(celltext4);
						loss.add(celltext5);

						stockData.put(celltext, loss);

						Thread.sleep(1000);

					}

				}

				}

			}

		}

		System.out.println("Loss Stocks are " + stockData.keySet() + "and Count is " + stockData.keySet().size());

	}

	@Given("^I get the Company - MarketCap \"([^\"]*)\" and Stock Price less then \"([^\"]*)\"$")
	public void i_get_the_Company_MarketCap_and_Stock_Price_less_then(String arg1, String currentStockPrice) throws Throwable {
		try {
			String Mcap = null;
			MarketCap = null;			
			String stockPrice= null;
			
			
			Iterator itr = stockData.keySet().iterator();

			while (itr.hasNext()) {

				String s = (String) itr.next();

				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,-1000)");

				driver.findElement(By.xpath(
						"//form[@name='form_topsearch']/input[@placeholder='Search Quotes, News, Mutual Fund NAVs']"))
						.clear();
				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//form[@name='form_topsearch']/input[@placeholder='Search Quotes, News, Mutual Fund NAVs']"))
						.click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//form[@name='form_topsearch']/input[@placeholder='Search Quotes, News, Mutual Fund NAVs']"))
						.sendKeys(s);
				Thread.sleep(2000);

				driver.findElement(By.xpath(
						"//form[@name='form_topsearch']/input[@placeholder='Search Quotes, News, Mutual Fund NAVs']"))
						.sendKeys(Keys.ENTER);

				Thread.sleep(3000);

				if (isElementPresent("//*[@id='stockName']/h1")) {

					try {

						Thread.sleep(1000);

						JavascriptExecutor js1 = (JavascriptExecutor) driver;
						js1.executeScript("window.scrollBy(0,1000)");

						Thread.sleep(1000);
						Mcap = driver.findElement(By.xpath("//td[@class='nsemktcap bsemktcap']")).getText();
						
					    stockPrice =  driver.findElement(By.xpath("//td[@class='nseHP bseHP']")).getText();
								
								

					} catch (Exception e) {

						System.out.println("GGGGGGGGGGGGG");

					}

				} else {

					System.out.println("Script didnt load");
					Mcap = "99";
					stockPrice = "0";
					scrips_Didnt_Load.add(s);
				}
				Thread.sleep(1000);

				if (Mcap.contains(",")) {

					Mcap = Mcap.replace(",", "");
					MarketCap = Mcap;
				} else {

					Mcap = Mcap;
				}
				
				if (stockPrice.contains(",")) {

					stockPrice = stockPrice.replace(",", "");
					MarketStockPrice = stockPrice;
				} else {

					stockPrice = stockPrice;
				}
				System.out.println("Mcap of the the stock::--" + s + "  is--> " + Mcap);
				System.out.println("Stock Price of the the stock::--" + s + "  is--> " + stockPrice);

				int marketCapital = Integer.valueOf(Mcap);
				int arg2 = Integer.valueOf(arg1);
				
				
				float cMarketPrice = Float.valueOf(stockPrice);
				float givenCMP = Integer.valueOf(currentStockPrice);

				if ((marketCapital >= arg2)&&(cMarketPrice <= givenCMP)) {

					System.out.println("Scrip Market Cap larger then::" + arg2
							+ "::are in Loss from Last 3 Trading Session are-->" + s);
					Scrips.add(s);

					topLooser.put(s, Mcap);

					JavascriptExecutor js1 = (JavascriptExecutor) driver;
					js1.executeScript("window.scrollBy(0,-2000)");

					driver.findElement(By.xpath(
							"//form[@name='form_topsearch']/input[@placeholder='Search Quotes, News, Mutual Fund NAVs']"))
							.clear();

				} else {

					Thread.sleep(1000);
					JavascriptExecutor js1 = (JavascriptExecutor) driver;
					js1.executeScript("window.scrollBy(0,-2000)");

					driver.findElement(By.xpath(
							"//form[@name='form_topsearch']/input[@placeholder='Search Quotes, News, Mutual Fund NAVs']"))
							.clear();

				}

			}

		}

		catch (Exception e) {

			System.out.println(e);

		}

	}

	public boolean isElementPresent(String locatorKey) {
		try {
			driver.findElement(By.xpath(locatorKey)).isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	@Given("^I write the Company- five days Perfermance")
	public void i_write_the_Company_five_days_Perfermance() throws Throwable {
		try {
			
			LocalDateTime localDateTime = LocalDateTime.now(); 
			 String path= "C:\\Users\\GPGiri\\git\\TestAutomationFramework-Giri\\RestAssured-Api\\Stock\\"+((localDateTime.toString())+".txt");

			FileWriter fw = new FileWriter(path);

			for (Map.Entry<String, String> entry : topLooser.entrySet()) {

				fw.write("Company is-->" + entry.getKey() + "--> Its Market Cap is :: " + entry.getValue() + "\n");

			}

			fw.write("======================================================" + "\n");
			fw.write("======================================================" + "\n");
			fw.write("======================================================" + "\n");
			fw.write("======================================================" + "\n");

			Iterator itr = scrips_Didnt_Load.iterator();

			while (itr.hasNext()) {

				String s = (String) itr.next();

				fw.write("Company is-->" + s + "--> scrips_Didnt_Load --Check Manually" + "\n");
			}

			fw.close();

			System.out.println("Success...");
		}

		catch (Exception e) {

			System.out.println(e);

		}
	}
}
