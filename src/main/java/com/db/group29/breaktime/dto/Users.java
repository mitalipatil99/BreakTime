/**
 * 
 */
package com.db.group29.breaktime.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Users {
	
	private int userId;
	private String userName;
	private String emailId;
	private String password;
	private String endpoint;
	private List<Interests> interests;
}
