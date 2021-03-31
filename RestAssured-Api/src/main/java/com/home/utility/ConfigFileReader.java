package com.home.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
 
public  class ConfigFileReader {
	
	private static Properties properties;
	private static ConfigFileReader configFileReader;

    public ConfigFileReader() {
    	 
    	 
         BufferedReader reader;
      String propertyFilePath = "config//Configuration.properties";
         try {
             reader = new BufferedReader(new FileReader(propertyFilePath));
             properties = new Properties();
             try {
                 properties.load(reader);
                 reader.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
             throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
         } 
 }
 
    public static ConfigFileReader getInstance( ) {
     if(configFileReader == null) {
     configFileReader = new ConfigFileReader();
     }
        return configFileReader;
    }
 
    public String getBaseUrl() {
        String base_Url = properties.getProperty("baseUrl");
      
        if(base_Url != null) return base_Url;
        else throw new RuntimeException("base_Url not specified in the Configuration.properties file.");
    }
 
    public static String getReportConfigPath() {
        String report_Path = properties.getProperty("reportPath");
        if(report_Path != null) return report_Path;
        else throw new RuntimeException("reportPath not specified in the Configuration.properties file.");
    }
}
