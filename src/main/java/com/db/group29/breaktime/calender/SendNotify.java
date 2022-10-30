package com.db.group29.breaktime.calender;

import java.security.Security;
import java.util.ArrayList;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.google.gson.Gson;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

public class SendNotify extends TimerTask{
	
	private static final String PUBLIC_KEY = "BGp3zIzafzIbSde57Tr2oLZexPRHEgaI4sDl-immxZsiPHFy6ZxKTfIUXjmJy4ID_a8VeJIryths80LUteldYHo";
    private static final String PRIVATE_KEY = "gBCj8x1-O9oJYO9ACV1ooUQ34koWZZu2jZqrGdQHpr8";
    private static String SUBJECT = "BreakTime";
    private String PAYLOAD;
 
    String endpoint;
    String username;
    ArrayList<Integer> interest;
    boolean executed = false;

    public void run2(String user, ArrayList<Integer> interest, String ep) {
		this.username = user;
		this.interest = interest;
		this.endpoint = ep;
		System.out.println(username + ":" + endpoint);
	}
    
	@Override
	public void run() {
		if (executed != false) {
			
			this.PAYLOAD = username + ":" + InterestCategory.getNotification(interest);
			System.out.println(this.PAYLOAD);
			Security.addProvider(new BouncyCastleProvider());
		    synchronized(this) {
		    	executed = true;
		    	this.sendNotif();
		    }
		}
	    
	}

	public String sendNotif() {
	     Security.addProvider(new BouncyCastleProvider());
	     	     	     
	     String sub = this.endpoint;   
	     
	     try {
	         PushService pushService = new PushService(PUBLIC_KEY, PRIVATE_KEY, SUBJECT);
	         Subscription subscription = new Gson().fromJson(sub, Subscription.class);
	         Notification notification = new Notification(subscription, this.PAYLOAD);
	         HttpResponse httpResponse = pushService.send(notification);
	         int statusCode = httpResponse.getStatusLine().getStatusCode();
	         return String.valueOf(statusCode);
	         
	     } catch (Exception e) {
	        return e.toString();
	     }
	     finally {
	    	 
	     }
    }
}
