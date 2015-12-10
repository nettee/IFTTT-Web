package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class CommonDao {

	private final Connection connection;

	public CommonDao() {
		this.connection = DatabaseUtil.getConnection();
	}

	public final void apply(PreparedStatement pstmt, List<?> params)
			throws DatabaseException {
		try {
			Iterator<?> it = params.iterator();

			int index = 1;
			while (it.hasNext()) {

				Object obj = it.next();
				if (obj == null) {
					pstmt.setObject(index, "");
				} else {
					pstmt.setObject(index, obj);
				}

				index++;
			}
		} catch (SQLException ex) {
			throw new DatabaseException("can not apply parameter", ex);
		}
	}

	public final List<Map<String, Object>> convert(ResultSet rs)
			throws DatabaseException {

		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

		try {
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				Map<String, Object> recordMap = new HashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					String name = metaData.getColumnName(i);
					Object value = rs.getObject(i);
					recordMap.put(name, value);
				}

				results.add(recordMap);
			}
		} catch (SQLException e) {
			throw new DatabaseException(
					"can not convert result set to list of map", e);
		}
		return results;
	}

	public final List<Map<String, Object>> query(String sql, List<?> params)
			throws DatabaseException {
		List<Map<String, Object>> result = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql);
			this.apply(pstmt, params);
			rs = pstmt.executeQuery();
			result = convert(rs);
		} catch (SQLException e) {
			throw new DatabaseException("can not execute query", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					;
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					;
				}
			}
		}

		return result;
	}

	public final Map<String, Object> queryOneLine(String sql, List<?> params) {
		List<Map<String, Object>> results = query(sql, params);
		if (results.isEmpty()) {
			throw new DatabaseException("data not exist");
		} else if (results.size() > 1) {
			throw new DatabaseException("data not unique");
		}
		return results.get(0);
	}

	public final Object queryOneObject(String sql, List<?> params)
			throws DatabaseException {
		Map<String, Object> line = queryOneLine(sql, params);
		if (line.isEmpty()) {
			throw new DatabaseException("data not exist");
		} else {
			return line.values().toArray()[0];
		}
	}

	public final int execute(String sql, List<?> params)
			throws DatabaseException {
		int ret = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			this.apply(pstmt, params);
			ret = pstmt.executeUpdate();
		} catch (SQLException ex) {
			throw new DatabaseException("", ex);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					;
				}
			}
		}

		return ret;
	}

	public final void close() throws DatabaseException {
		if (connection == null) {
			throw new DatabaseException("can not close null connection");
		}
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException("Can not close common dao", e);
		}
	}

}
