package com.db.group29.breaktime.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	
	public static Connection getConnection() {
		Properties properties = loadProperties();
		Connection connection = null;
		try {
			Class.forName(properties.getProperty("db.className"));
			connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.userName"), properties.getProperty("db.password"));
			return connection;
		}
		catch(ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static Properties loadProperties() {
		Properties properties = new Properties();
		
		try(InputStream inputstream = DBUtils.class.getClassLoader().getResourceAsStream("application.properties")){
			properties.load(inputstream);
			return properties;
		}
		catch(IOException e) {
			return null;
		}
	}
}
