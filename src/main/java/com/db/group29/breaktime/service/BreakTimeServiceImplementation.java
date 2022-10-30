/**
 * 
 */
package com.db.group29.breaktime.service;

import java.util.List;

import com.db.group29.breaktime.dto.Interests;
import com.db.group29.breaktime.dto.Users;
import com.db.group29.breaktime.repository.BreakTimeImplementationRepository;
import com.db.group29.breaktime.repository.BreakTimeRepository;

public class BreakTimeServiceImplementation implements BreakTimeService {

	private BreakTimeRepository breaktimerepository = new BreakTimeImplementationRepository();
	
	//API to return all the Interests list
	@Override
	public List<Interests> getAllInterests() {
		return breaktimerepository.getAllInterests();
	}

	//API to add user details in Database
	public Users subscribe(Users user) {
		return breaktimerepository.subscribe(user);
	};
		
	//API to fetch user details from Database
	@Override
	public Users login(Users user) {
		return breaktimerepository.login(user);
	}

	//API to update endpoint of user
	@Override
	public String updateEndpoint(Users user) {
		return breaktimerepository.updateEndpoint(user);
	}

	//API to return userlists
	@Override
	public List<Users> getAllUsers() {
		
		return breaktimerepository.getAllUsers();
	}

	//API to send notification
	@Override
	public String send(Users user) {
		
		return breaktimerepository.send(user);
	}
}
