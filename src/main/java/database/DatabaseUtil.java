package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

class DatabaseUtil {

	private static final Logger logger = Logger.getLogger(DatabaseUtil.class);

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ifttt?autoDeserialize=true";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "123456";

	private static Connection connection;

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			logger.error("Cannot load driver " + DRIVER);
			System.exit(1);
		}
	}

	private DatabaseUtil() {
	}

	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
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
