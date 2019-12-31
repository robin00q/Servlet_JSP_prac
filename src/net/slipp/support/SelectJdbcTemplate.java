package net.slipp.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class SelectJdbcTemplate {
	
	public Object executeQuery(String sql) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			setParameters(pstmt);
			
			rs = pstmt.executeQuery();
			
			return rowMap(rs);
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
	
	public abstract void setParameters(PreparedStatement pstmt) throws SQLException;

	public abstract Object rowMap(ResultSet rs) throws SQLException;

}
