package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

class DatabaseUtil {

	private static final Logger logger = Logger.getLogger(DatabaseUtil.class);

	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String DB_URL = "jdbc:sqlite:/home/william/.ifttt-web/data.db";
//	private static final String DB_USER = "checkyh";
//	private static final String DB_PASSWORD = "123456";

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
				connection = DriverManager.getConnection(DB_URL);
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
