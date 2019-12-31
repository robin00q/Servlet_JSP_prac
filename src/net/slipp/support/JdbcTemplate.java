package net.slipp.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.slipp.user.User;
import net.slipp.user.UserDAO;

public abstract class JdbcTemplate {
	
	final static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	public Connection getConnection() {	
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
	
	public abstract void setParameters(PreparedStatement pstmt) throws SQLException;

	public void executeUpdate(String sql) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = getConnection().prepareStatement(sql);
			setParameters(pstmt);
			
			pstmt.executeUpdate();
		} finally {
			if(pstmt != null) {
				pstmt.close();				
			}
			
			if(conn != null) {
				conn.close();				
			}
		}
	}
}
