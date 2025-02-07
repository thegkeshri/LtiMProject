package com.ltim.product.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnConfig {
	 public static Connection getConnection() {
	        Connection connection = null;
	        try (InputStream input = DatabaseConnConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
	            Properties prop = new Properties();
	            if (input == null) {
	                System.out.println("Sorry, unable to find db.properties");
	                return null;
	            }
	            prop.load(input);

	            String url = prop.getProperty("spring.datasource.url");
	            String username = prop.getProperty("spring.datasource.username");
	            String password = prop.getProperty("spring.datasource.password");

	            connection = DriverManager.getConnection(url, username, password);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return connection;
	    }
}
