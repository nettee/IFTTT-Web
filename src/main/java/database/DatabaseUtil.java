package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

class DatabaseUtil {

	private static final Logger logger = Logger.getLogger(DatabaseUtil.class);

	private static Map<String, String> config = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
	{
		put("driver", "com.mysql.jdbc.Driver");
	    put("user","root");
	    put("password", "123456");
	    put("database", "ifttt");
	}};
	
	private static Connection connection;

	static {
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String driver = config.get("driver");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			logger.error("Cannot load driver " + driver);
			System.exit(1);
		}
	}

	private DatabaseUtil() {
	}

	public static Connection getConnection() {
		if (connection == null) {
			String user = config.get("user");
			String password = config.get("password");
			String database = config.get("database");
			String dburl = String.format("jdbc:mysql://localhost:3306/%s?autoDeserialize=true", database);
			try {
				connection = DriverManager.getConnection(dburl, user, password);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return connection;
	}

	public static void demo() {

		System.out.println("Connecting to database...");

		Connection connection = getConnection();

		System.out.println("creating statement...");
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String sql = "CREATE TABLE table1(tid INT NOT NULL);";
			statement.execute(sql); // create clause cannot use executeQuery
		} catch (SQLException e) {
			logger.error("execute error", e);
			return;
		}

		System.out.println("Done.");
	}

}
