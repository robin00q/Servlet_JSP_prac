package net.slipp.support.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {
	T rowMap(ResultSet rs) throws SQLException;
}
