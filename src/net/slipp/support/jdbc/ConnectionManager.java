package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.slipp.user.UserDAO;

public class ConnectionManager {
	final static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	public static Connection getConnection() {	
		String url = "jdbc:mysql://localhost:3306/slipp_dev?serverTimezone=Asia/Seoul";
		String id = "root";
		String pw = "qpqp1010P";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, id, pw);
		} catch (Exception e){
			logger.debug("{}" + e.getMessage());
			
			return null;
		}
	}
}
