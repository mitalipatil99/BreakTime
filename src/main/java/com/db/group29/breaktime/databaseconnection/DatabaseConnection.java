/**
 * 
 */
package com.db.group29.breaktime.databaseconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.group29.breaktime.utils.DBUtils;

public class DatabaseConnection {

	//Get User Endpoint
	public String getEndpoint(int userid) {
		String query = "select endpoint from users where userid = ?";
		Connection connection = DBUtils.getConnection();

		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		
		try {
			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1,userid);
			rs = preparedstatement.executeQuery();
			if(rs.next()){	
				return rs.getString(1);			
			}
			
			return null;
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

	//Get use interest IDs
	public ArrayList<Integer> getUserInterest(int userid) {
		
		ArrayList<Integer> interestList = new ArrayList<Integer>();
		
		String query = "select interestid from keyst where userid = ?";
		Connection connection = DBUtils.getConnection();

		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		
		try {
			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1,userid);
			rs = preparedstatement.executeQuery();
			//rs.next();
		
			while(rs.next()){	
				interestList.add(rs.getInt(1));		
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
	public String getUserName(int userid) {
		String query = "select username from users where userid = ?";
		Connection connection = DBUtils.getConnection();

		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		
		try {
			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1,userid);
			rs = preparedstatement.executeQuery();
//			rs.next();
			if(rs.next()){	
				return rs.getString(1);			
			}
			
			return null;
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
}
