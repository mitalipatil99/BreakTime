/**
 * 
 */
package com.db.group29.breaktime.service;

import java.util.List;

import com.db.group29.breaktime.dto.Interests;
import com.db.group29.breaktime.dto.Users;

public interface BreakTimeService {

	//API to return all the Interests list
	//public List<String> getAllInterests();
	public List<Interests> getAllInterests();
	
	//API to add user details in Database
	public Users subscribe(Users user);
	
	//API to add user details from Database
	public Users login(Users user);
	
	//API to update endpoint of user
	public String updateEndpoint(Users user);

	//API to return all users
	public List<Users> getAllUsers();

	//API to send notification 
	public String send(Users user); 
}
