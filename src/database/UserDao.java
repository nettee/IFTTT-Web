package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.data.User;

import org.apache.log4j.Logger;

/**
 * User Data Access and Operation
 * 
 * @see User
 * 
 */
public final class UserDao {

	private static final Logger logger = Logger.getLogger(UserDao.class);

	private UserDao() {

	}

	// TODO test
	public static List<User> getUserList() {
		String sql = "SELECT * FROM user";
		List<Map<String, Object>> lines = DaoUtil.query(sql, Collections.EMPTY_LIST);
		
		List<User> userList = new ArrayList<User>();
		for (Map<String, Object> line : lines) {
			User user = newUserFromLine(line);
			userList.add(user);
		}
		logger.info("get user list");
		return userList;
	}

	public static User getUserById(int id) {
		String sql = "SELECT * FROM user WHERE id=?";
		List<Integer> params = Arrays.asList(id);
		Map<String, Object> line = DaoUtil.queryOneLine(sql, params);
		logger.info(String.format("get user by id=%d", id));
		return newUserFromLine(line);
	}

	public static User getUserByName(String name) {
		String sql = "SELECT * FROM user WHERE name=?";
		List<String> params = Arrays.asList(name);
		Map<String, Object> line = DaoUtil.queryOneLine(sql, params);
		logger.info(String.format("get user by name=%s", name));
		return newUserFromLine(line);
	}

	private static User newUserFromLine(Map<String, Object> line) {
		Integer id = (Integer) line.get("id");
		String name = (String) line.get("name");
		String password = (String) line.get("password");
		Integer balance = (Integer) line.get("balance");
		return new User(id, name, password, balance);
	}

	public static List<Integer> getAllIds() {
		String sql = "SELECT id FROM user";
		List<String> params = Collections.emptyList();
		List<Map<String, Object>> lines = DaoUtil.query(sql, params);
		List<Integer> idList = new ArrayList<Integer>();
		for (Map<String, Object> line : lines) {
			Integer id = (Integer) line.get("id");
			idList.add(id);
		}
		return idList;
	}

	public static boolean existsUser(String username) {
		String sql = "SELECT * FROM user WHERE name=?";
		List<String> params = Arrays.asList(username);
		List<Map<String, Object>> results = DaoUtil.query(sql, params);
		boolean contains = !results.isEmpty();
		logger.info(String.format("containsUser: username=%s, %b", username,
				contains));
		return contains;
	}

	public static void addUser(String username, String password) {
		String sql = "INSERT INTO user(name, password) VALUES(?, ?)";
		List<String> params = Arrays.asList(username, password);
		DaoUtil.execute(sql, params);
		logger.info(String.format("addUser: username=%s, password=%s",
				username, password));
	}

	public static boolean validPassword(String username, String password) {

		if (!existsUser(username)) {
			return false;
		}

		String sql = "SELECT password FROM user WHERE name=?";
		List<String> params = Arrays.asList(username);
		String password2 = (String) DaoUtil.queryOneObject(sql, params);
		logger.info(String.format(
				"validPassword: username=%s, password=%s, original=%s",
				username, password, password2));

		return password.equals(password2);
	}

	// TODO test
	public static void payExpense(int userId, int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException();
		}
		String sql = "UPDATE user SET balance=balance-? WHERE id=?";
		List<Integer> params = Arrays.asList(amount, userId);
		DaoUtil.execute(sql, params);
		logger.info(String.format("payExpense: userId=%d, amount=%d", userId,
				amount));
	}

}
