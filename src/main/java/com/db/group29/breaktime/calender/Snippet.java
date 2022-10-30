package com.db.group29.breaktime.calender;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.math.BigDecimal;

import com.db.group29.breaktime.databaseconnection.DatabaseConnection;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

public class Snippet {

	
	public static void CalenderEvents() {
		InterestCategory.setInterest();
		
		
		//Database connection
		try {
		    Reader reader = Files.newBufferedReader(Paths.get("./src/main/resources/Event.json"));
		    JsonObject parser = (JsonObject) Jsoner.deserialize(reader);		
		    JsonArray obj = (JsonArray) parser.get("list");
		    
		    
		    DatabaseConnection dc = new DatabaseConnection();
		    
		    
		    Timer timer = new Timer();
		    
		    for(Object i:obj) {
		    	JsonObject k = (JsonObject) i;
		    	String endtime = (String) k.get("endtime");
		    	String user = (String) k.get("user");
		    	BigDecimal bd = (BigDecimal) k.get("id");
		    	ArrayList<Integer> interestIdList = dc.getUserInterest(bd.intValue());
		    	String userEndpoint = dc.getEndpoint(bd.intValue()); 
		    	
			    DateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date date1 = (Date) dateFormatter1.parse(endtime);
			    SendNotify x = new SendNotify();
			    timer.schedule(x, date1);   
			    x.run2(user, interestIdList, userEndpoint);
		    }
		    reader.close();	
		    
		} catch (Exception ex) {
		    ex.printStackTrace();
		}			
	}
}

