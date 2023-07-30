package com.home.runner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.cucumber.listener.Reporter;

import java.io.File;
import com.home.utility.ConfigFileReader;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="features"
        ,glue= {"com.home.stepDefinition"},
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/ApiExecution.html",
        		"rerun:target/rerun.txt",	
        		 "json:target/cucumber-reports/CucumberReport.json",
    			"junit:target/cucumber-reports/Cucumber.xml",
    			"pretty", "html:target/cucumber-reports"}, 
        		monochrome = true,
        		tags = {"@Invest"}
                )

public class APIMyTestMy { 
	
	public static void writeExtentReport() {
		
		Reporter.loadXMLConfig(new File(ConfigFileReader.getReportConfigPath()));		
		
	}
	

}


