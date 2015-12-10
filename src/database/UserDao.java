package database;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.User;

import org.apache.log4j.Logger;

public class UserDao extends CommonDao {

	private static final Logger logger = Logger.getLogger(UserDao.class);

	public boolean containsUser(String username) {
		String sql = "SELECT * FROM user WHERE name=?";
		List<String> params = Arrays.asList(username);
		List<Map<String, Object>> results = query(sql, params);
		boolean contains = !results.isEmpty();
		logger.info(String.format("containsUser: username=%s, %b", username, contains));
		return contains;
	}

	public void addUser(String username, String password) {
		String sql = "INSERT INTO user(name, password) VALUES(?, ?)";
		List<String> params = Arrays.asList(username, password);
		execute(sql, params);
		logger.info(String.format("addUser: username=%s, password=%s",
				username, password));
	}

	public boolean validPassword(String username, String password) {

		if (!containsUser(username)) {
			return false;
		}

		String sql = "SELECT password FROM user WHERE name=?";
		List<String> params = Arrays.asList(username);
		String password2 = (String) queryOne(sql, params);
		logger.info(String.format(
				"validPassword: username=%s, password=%s, original=%s",
				username, password, password2));

		return password.equals(password2);
	}
	
	public User getUserByName(String username) {
		
		String sql = "SELECT * FROM user WHERE name=?";
		List<String> params = Arrays.asList(username);
		List<Map<String, Object>> results = query(sql, params);
		
		return null;
	}

	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		// userDao.addUser("alice", "iloveyou");
		userDao.validPassword("alice", "123456");
		// userDao.addUser("bob", "123456789");
	}
}
