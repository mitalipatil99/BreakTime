/**
 * 
 */
package com.db.group29.breaktime.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.group29.breaktime.dto.Interests;
import com.db.group29.breaktime.dto.Users;
import com.db.group29.breaktime.service.BreakTimeService;
import com.db.group29.breaktime.service.BreakTimeServiceImplementation;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class APIController {
	
	private BreakTimeService service = new BreakTimeServiceImplementation();
	
	//API to return all the Interests list
	@GetMapping("/getAllInterests")
	public List<Interests> getAllInterests() {
		return service.getAllInterests();
	}

	//API to add user in Database
	@PostMapping("/subscribe")
	public Users subscribe(@RequestBody Users user) {
		return service.subscribe(user);
	}
	
	//API to Fetch user interest from Database
	@PostMapping("/login")
	public Users login(@RequestBody Users user) {
		return service.login(user);
	}
	//API to return all the users list
	@GetMapping("/getAllUsers")
	public List<Users> getAllUsers() {
		return service.getAllUsers();
	}
	
	//API to send notification to user
	@PostMapping("/send")
	public String send(@RequestBody Users user) {
		return service.send(user);
	}
	
	
	
	
	//API to update endpoint of user
	@PostMapping("/sendSubscription")
	public String updateEndpoint(@RequestBody Users user) {
		System.out.println("Endpoint updated:" + user.getEmailId());
		return service.updateEndpoint(user);
	}
}
