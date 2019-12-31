package net.slipp.support;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import net.slipp.support.jdbc.ConnectionManager;

public class ConnectionManagerTest {
	@Test
	public void connection() {
		Connection con = ConnectionManager.getConnection();
		assertNotNull(con);
	}
}
