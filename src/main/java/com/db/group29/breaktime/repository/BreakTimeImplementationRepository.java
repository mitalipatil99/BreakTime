/**
 * 
 */
package com.db.group29.breaktime.repository;

import java.security.Security;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.db.group29.breaktime.calender.InterestCategory;
import com.db.group29.breaktime.databaseconnection.DatabaseConnection;
import com.db.group29.breaktime.dto.Interests;
import com.db.group29.breaktime.dto.Users;
import com.db.group29.breaktime.utils.DBUtils;
import com.google.gson.Gson;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

public class BreakTimeImplementationRepository implements BreakTimeRepository {
	
	private Users userVariable;
	
	//API to return all the Interests list
	@Override
	public List<Interests> getAllInterests(){
		
		List<Interests> interestList = new ArrayList<Interests>();
		
		String query = "select * from interests";
		Connection connection = DBUtils.getConnection();

		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		
		try {
			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(query);
			rs = preparedstatement.executeQuery();
			
			while(rs.next()){
				
				Interests interest = new Interests();
				interest.setInterestid(rs.getInt(1));
				interest.setInteresttitle(rs.getString(2));
				interest.setInteresturl(rs.getString(3));
						
				interestList.add(interest);
			}

			return interestList;
		} 
		catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
				return null;
			}
			catch(SQLException exc) {
				exc.printStackTrace();
				return null;
			}
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			DBUtils.closeConnection(connection);
		}
	}

	
	//API to add user details in Database
	public Users subscribe(Users user) {
		String insertStatement = "insert into users (username, emailid, password) values(?,?,?)";
		
		Connection connection = DBUtils.getConnection();
		PreparedStatement preparedstatement = null;
		
		try {
			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(insertStatement,Statement.RETURN_GENERATED_KEYS);
			
			preparedstatement.setString(1, user.getUserName());
			preparedstatement.setString(2, user.getEmailId());
			preparedstatement.setString(3, user.getPassword());
			
			int result = preparedstatement.executeUpdate();
			ResultSet rs = preparedstatement.getGeneratedKeys();
			rs.next();
			
			if(result > 0) {
				connection.commit();
			}
			else {
				return null;
			}
			
			for(int i = 0; i < user.getInterests().size(); i++){	
				
				insertStatement = "insert into keyst (userid, interestid) values(?,?)";
				connection.setAutoCommit(false);
				preparedstatement = connection.prepareStatement(insertStatement);
				preparedstatement.setInt(1, rs.getInt(1));
				preparedstatement.setInt(2, user.getInterests().get(i).getInterestid());
				result = preparedstatement.executeUpdate();
				
			}
			connection.commit();
			
			String query = "select u.emailid, u.username, i.interestid, i.interesttitle, i.imgurl, u.userid from users u join "
					+ "(select k.userid, i.interestid, i.interesttitle, i.imgurl from keyst k join interests i "
					+ "on k.interestid = i.interestid) as i "
					+ "on u.userid = i.userid and u.emailid = ? "; 
			
			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1,user.getEmailId());
			rs = preparedstatement.executeQuery();

			rs.next();	
			this.userVariable = new Users();
			Interests interest = new Interests();
			
			this.userVariable.setEmailId(rs.getString(1));
			this.userVariable.setUserName(rs.getString(2));
			this.userVariable.setUserId(rs.getInt(6));
			
			ArrayList<Interests> interestList = new ArrayList<Interests>();
			
			//InterestObject
			interest.setInterestid(rs.getInt(3));
			interest.setInteresttitle(rs.getString(4));
			interest.setInteresturl(rs.getString(5));
			
			interestList.add(interest);	
			while(rs.next()){
				
				Interests interest_2 = new Interests();
				interest_2.setInterestid(rs.getInt(3));
				interest_2.setInteresttitle(rs.getString(4));
				interest_2.setInteresturl(rs.getString(5));
				interestList.add(interest_2);
			}
			
			this.userVariable.setInterests(interestList);
			return this.userVariable;
		} 
		catch (SQLException e) {
			
			try {
				connection.rollback();
				e.printStackTrace();
				return null;
			}
			catch(SQLException exc) {
				exc.printStackTrace();
				return null;
			}
			
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			DBUtils.closeConnection(connection);
		}
	}
	
	
	//API to fetch user details from Database
	@Override
	public Users login(Users user) {
		
		String query = "select u.emailid, u.UserName, i.interestid, i.interesttitle, i.imgurl,u.userid from users u join "
				+ "(select k.userid, i.interestid, i.interesttitle, i.imgurl from keyst k join interests i "
				+ "on k.interestid = i.interestid) as i "
				+ "on u.userid = i.userid and u.emailid = ? and password = ?"; 
		Connection connection = DBUtils.getConnection();
		
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		
		try {
			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1,user.getEmailId());
			preparedstatement.setString(2,user.getPassword());
			rs = preparedstatement.executeQuery();
			rs.next();	
			if (rs.getRow()>0) {
						
				this.userVariable = new Users();
				Interests interest = new Interests();
				
				ArrayList<Interests> interestList = new ArrayList<Interests>();
					
				this.userVariable.setEmailId(rs.getString(1));
				this.userVariable.setUserName(rs.getString(2));
				this.userVariable.setUserId(rs.getInt(6));
				
				//InterestObject
				interest.setInterestid(rs.getInt(3));
				interest.setInteresttitle(rs.getString(4));
				interest.setInteresturl(rs.getString(5));
				
				interestList.add(interest);
				
				while(rs.next()){
					
					Interests interest_2 = new Interests();
					
					interest_2.setInterestid(rs.getInt(3));
					interest_2.setInteresttitle(rs.getString(4));
					interest_2.setInteresturl(rs.getString(5));
					interestList.add(interest_2);
				}
				this.userVariable.setInterests(interestList);
			}
			else {
				this.userVariable = new Users();
				this.userVariable.setUserId(-1);
			}
			return this.userVariable;
		} 
		catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
				return null;
			}
			catch(SQLException exc) {
				exc.printStackTrace();
				return null;
			}
			
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			DBUtils.closeConnection(connection);
		}
	}

	//API to update endpoint of user
	@Override
	public String updateEndpoint(Users user) {
		
		String updateStatement = "update users set endpoint = ? where emailid = ?";
		Connection connection = DBUtils.getConnection();
		
		PreparedStatement preparedstatement = null;
		
		try {
			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(updateStatement);
			
			preparedstatement.setString(1, user.getEndpoint());
			preparedstatement.setString(2, user.getEmailId());
			
			int result = preparedstatement.executeUpdate();
			
			if(result > 0) {
				connection.commit();
				return "Successfully update "+ result + " rows";
			}
			else {
				return "Operation failed to update data";
			}
		} 
		catch (SQLException e) {
			
			try {
				connection.rollback();
				e.printStackTrace();
				return "Operation Failed, SQLException Occured!";
			}
			catch(SQLException exc) {
				exc.printStackTrace();
				return "Operation Failed, SQLException Occured!";
			}
			
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			return "Operation Failed, NULLPointerException Occured!";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Operation Failed, Exception Occured!";
		}
		finally {
			DBUtils.closeConnection(connection);
		}
	}


	@Override
	public List<Users> getAllUsers() {
		List<Users> userList = new ArrayList<Users>();
		
		String query = "select * from users";
		Connection connection = DBUtils.getConnection();

		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		
		try {
			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(query);
			rs = preparedstatement.executeQuery();
			
			while(rs.next()){
				
				Users user = new Users();
				user.setUserId(rs.getInt(1));
			    user.setUserName(rs.getString(2));
			    user.setEmailId(rs.getString(3));
			    user.setPassword(rs.getString(4));
			    user.setEndpoint(rs.getString(5));
			
						
				userList.add(user);
			}

			return userList;
		} 
		catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
				return null;
			}
			catch(SQLException exc) {
				exc.printStackTrace();
				return null;
			}
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			DBUtils.closeConnection(connection);
		}
	
		
	}
	private static final String PUBLIC_KEY = "BGp3zIzafzIbSde57Tr2oLZexPRHEgaI4sDl-immxZsiPHFy6ZxKTfIUXjmJy4ID_a8VeJIryths80LUteldYHo";
    private static final String PRIVATE_KEY = "gBCj8x1-O9oJYO9ACV1ooUQ34koWZZu2jZqrGdQHpr8";
    private static String SUBJECT = "BreakTime";
    private String PAYLOAD;

	@Override
	public String send(Users user) {
		
		DatabaseConnection dc = new DatabaseConnection();
		
		ArrayList<Integer> interestIdList = dc.getUserInterest(user.getUserId());
		
		String ep=dc.getEndpoint(user.getUserId());
		System.out.println(ep);
		
		this.PAYLOAD = dc.getUserName(user.getUserId())+":"+InterestCategory.getNotification(interestIdList);
		
		Security.addProvider(new BouncyCastleProvider());

		System.out.println(this.PAYLOAD);
		 try {
	         PushService pushService = new PushService(PUBLIC_KEY, PRIVATE_KEY, SUBJECT);
	         Subscription subscription = new Gson().fromJson(ep, Subscription.class);
	         Notification notification = new Notification(subscription, this.PAYLOAD);
	         HttpResponse httpResponse = pushService.send(notification);
	         int statusCode = httpResponse.getStatusLine().getStatusCode();
	         return String.valueOf(statusCode);
	         
	     } catch (Exception e) {
	        return e.toString();
	     }
       
	
		
	
	}
}
