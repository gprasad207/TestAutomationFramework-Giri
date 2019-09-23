package com.home.runner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
        features ="features"
        ,glue= {"stepDefinition"},
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report2.html",
        		"rerun:target/rerun.txt",	
        		 "json:target/cucumber-reports/Cucumber2.json",
    			"junit:target/cucumber-reports/Cucumber.xml",
    			"pretty", "html:target/cucumber-reports"}, 
        		monochrome = true,
        		tags = {"@Facebook"}
                )

public class APIMyTestMy {
	

}


