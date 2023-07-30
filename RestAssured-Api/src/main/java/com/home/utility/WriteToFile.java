package com.home.utility;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.home.utility.DataCreater;

public class WriteToFile {

	// public void writtingFile() throws IOException {

	public static void main(String args[]) throws IOException {
		String S1 = "1,0,1,0,1,0,0,1";
		Map<String, Integer> m = new HashMap<String, Integer>();
		
	char c[] = S1.toCharArray();
	
	List<char[]> asList = Arrays.asList(c);
	
	//System.out.println("---------->"+ asList.iterator().hasNext());;
	
	Iterator itr = asList.iterator();
	
		while (itr.hasNext()) 
		
		{
			System.out.println(itr.next().toString());
			
			
		}
		//
		
		
		
	}

	}



