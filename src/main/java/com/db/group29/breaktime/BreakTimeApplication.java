package com.db.group29.breaktime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.db.group29.breaktime.calender.Snippet;  

@SpringBootApplication
public class BreakTimeApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(BreakTimeApplication.class, args);
		
		Snippet.CalenderEvents();
	}	
}


	
		
	


