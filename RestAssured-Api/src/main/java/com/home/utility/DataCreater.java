package com.home.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataCreater {

	public static String dateCreater() {
		
		
		//List<String> Li = new ArrayList<String>(); 
				
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		
		String exl_Date= dateFormat.format(date);
		
		return exl_Date;
		
		}

}
