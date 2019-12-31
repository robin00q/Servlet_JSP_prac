package net.slipp.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.slipp.support.JdbcTemplate;

public class UserDAO {

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
	


	public void addUser(User user) throws SQLException {
		JdbcTemplate template = new JdbcTemplate() {

			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
		};
		
		String sql = "insert into USERS values(?,?,?,?)";
		
		template.executeUpdate(sql);
	}
	
	public void removeUser(String userId) throws SQLException {
		JdbcTemplate template = new JdbcTemplate() {
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}	
		};
		
		String sql = "delete from USERS where userId = ?";
		template.executeUpdate(sql);
	}
	
	public void updateUser(User user) throws SQLException {
		JdbcTemplate template = new JdbcTemplate() {
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}
		};
		
		String sql = "update USERS set password = ?, name = ?, email = ? where userId = ?";
		template.executeUpdate(sql);
	}
	
	public User findByUserId(String userId) throws SQLException {
		String sql = "select * from USERS where userId = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1,  userId);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			
			return new User(
					rs.getString("userId"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("email"));
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	
}
