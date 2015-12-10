package database;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.User;

import org.apache.log4j.Logger;

public class UserDao extends CommonDao {

	private static final Logger logger = Logger.getLogger(UserDao.class);
	
	public User getUserById(int id) {
		String sql = "SELECT * FROM user WHERE id=?";
		List<Integer> params = Arrays.asList(id);
		List<Map<String, Object>> results = query(sql, params);
		if (results.isEmpty()) {
			throw new IllegalStateException(String.format(
					"user id '%d' does not exist", id));
		} else if (results.size() > 1) {
			throw new IllegalStateException(String.format(
					"user id '%d' is not unique", id));
		}
		Map<String, Object> line = results.get(0);
		return getUserFromLine(line);
	}

	public User getUserByName(String name) {
		String sql = "SELECT * FROM user WHERE name=?";
		List<String> params = Arrays.asList(name);
		List<Map<String, Object>> results = query(sql, params);
		if (results.isEmpty()) {
			throw new IllegalStateException(String.format(
					"username '%s' does not exist", name));
		} else if (results.size() > 1) {
			throw new IllegalStateException(String.format(
					"username '%s' is not unique", name));
		}
		Map<String, Object> line = results.get(0);
		return getUserFromLine(line);
	}
	
	private User getUserFromLine(Map<String, Object> line) {
		User user = new User();
		user.setId((Integer) line.get("id"));
		user.setName((String) line.get("name"));
		user.setPassword((String) line.get("password"));
		user.setBalance((Integer) line.get("balance"));
		return user;
	}

	public boolean containsUser(String username) {
		String sql = "SELECT * FROM user WHERE name=?";
		List<String> params = Arrays.asList(username);
		List<Map<String, Object>> results = query(sql, params);
		boolean contains = !results.isEmpty();
		logger.info(String.format("containsUser: username=%s, %b", username,
				contains));
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

	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		User user = userDao.getUserByName("dog");
		System.out.println(user);
		User user2 = userDao.getUserById(1);
		System.out.println(user2);
		User user3 = new User();
		user3.setThisById(1);
		System.out.println(user3);
	}
}
