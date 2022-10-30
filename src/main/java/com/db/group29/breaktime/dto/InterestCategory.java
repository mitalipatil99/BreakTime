///**
// * 
// */
//package com.db.group29.breaktime.dto;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class InterestCategory {
//
//	ArrayList<ArrayList<String>> interests = new ArrayList<ArrayList<String>>();
//	
//	public InterestCategory(){
//		
//		ArrayList<String> arrlist = new ArrayList<String>(10);
//		
//		arrlist.add("Want to pause and reflect for a moment?");	
//		arrlist.add(" Hey you’ve been working long and hard, time to take a break! :)\r\n"
//				+ "     Would you like to begin with 10 deep breaths?\r\n"
//				+ "");	
//		arrlist.add("Rather than thinking of meditation as another thing to do, think of it as one time in the day,   when you don’t have anything to do at all ");	
//		arrlist.add("enjoy the feeling of pausing to balance and catch your breath");
//		arrlist.add("Busy day? Take two minutes to unwind, you won’t regret it!");	
//		arrlist.add("Time to close your eyes and pause for a moment, breathe and reflect on your day so far");
//		interests.add(arrlist);
//
//		ArrayList<String> arrlist1 = new ArrayList<String>(10);
//		arrlist1.add("Hey you, pause for a moment, would you like to take your mind off of work for some time? \r\n"
//				+ "  You could go for a walk whilst listening to music ! or you could go for a jog to create some mental space\r\n"
//				+ "");	
//		arrlist1.add("Be mindful of your mind and health! Take some time to move around and rejuvenate!");	
//		arrlist1.add("Let's keep the day going by adding more fuel to it! reminding you to exercise just a little for now! Perhaps, 10 pushups?  15 situps or a one minute plank can invigorate you ");
//		arrlist1.add("Want to stay active yet take a break? Go for a run! ");
//		interests.add(arrlist1);
//
//		// Music
//		ArrayList<String> arrlist2 = new ArrayList<String>(10);
//		arrlist2.add("Your afternoon lift is here! UPLIFT your mood with your favourite Spotify playlist");	
//		arrlist2.add("Bored of the office humdrum, try listening to your favourite podcast right now");	
//		arrlist2.add("It's true listening to music boosts your mood! Perhaps you can try it right away");	
//		interests.add(arrlist2);
//		
//		// Food 
//		ArrayList<String> arrlist3 = new ArrayList<String>(10);
//		arrlist3.add("You are almost done for the day! Take a break , have a kit kat ");	
//		arrlist3.add("Treat yourself to a tea break!");	
//		interests.add(arrlist3);
//		
//		// Art and craft
//		ArrayList<String> arrlist4 = new ArrayList<String>(10);
//		arrlist4.add("Tap into your inner artist by getting creative with your notepad! Doodle, sketch, make planes or swans, amuse yourself ");	
//		arrlist4.add("Still keen to reduce stress and increase focus? Let your creative energy flow through the artist within you, try doing something fun! ");	
//		interests.add(arrlist4);
//		
//	}
//	
//	public String getNotification(int userID , int[] UserInterestIDs) {
//		Random x = new Random();
//		int y,y1;
//		y = x.nextInt(UserInterestIDs.length);
//		y1 = x.nextInt(interests.get(UserInterestIDs[y]).size());
//		
//		return interests.get(UserInterestIDs[y]).get(y1);
//	}
//}
