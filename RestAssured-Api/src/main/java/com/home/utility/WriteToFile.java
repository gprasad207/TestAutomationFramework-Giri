package com.home.utility;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.home.utility.DataCreater;

public class WriteToFile {

    public void writtingFile(String st, String pe) throws IOException {

        List<String[]> csvData = createCsvDataSimple(st, pe);

        // default all fields are enclosed in double quotes
        // default separator is a comma
        try (CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\GPGiri\\git\\TestAutomationFramework-Giri\\RestAssured-Api\\DailystockMovementPerc.csv"))) {
            writer.writeAll(csvData);
        }

    }

    private static List<String[]> createCsvDataSimple(String st,String pe) {
    	
    	String datePctMov= DataCreater.dateCreater().concat("<-->").concat("%Change");
    	
        String[] header = { "Stock Name", datePctMov};
        String[] record1 = {st, pe};        

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);       

        return list;
    }

}