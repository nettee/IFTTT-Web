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
	
	// ============== read daos ================

	public static List<User> getUserList() {
		String sql = "SELECT * FROM user";
		List<Map<String, Object>> lines = DaoUtil.query(sql,
				Collections.EMPTY_LIST);

		List<User> userList = new ArrayList<User>();
		for (Map<String, Object> line : lines) {
			User user = newUserFromLine(line);
			userList.add(user);
		}
		logger.info(String.format("get user list - %d users in total",
				userList.size()));
		return userList;
	}

	public static List<Integer> getUserIdList() {
		String sql = "SELECT id FROM user ORDER BY id";
		List<String> params = Collections.emptyList();
		List<Map<String, Object>> lines = DaoUtil.query(sql, params);
		List<Integer> idList = new ArrayList<Integer>();
		for (Map<String, Object> line : lines) {
			Integer id = (Integer) line.get("id");
			idList.add(id);
		}
		logger.info(String.format("get user id list - %d user ids in total",
				idList.size()));
		return idList;
	}

	public static User getUserById(int id) {
		String sql = "SELECT * FROM user WHERE id=?";
		List<Integer> params = Arrays.asList(id);
		Map<String, Object> line = DaoUtil.queryOneLine(sql, params);
		User user = newUserFromLine(line);
		logger.info(String.format("get user by id=%d - %s", id, user.toString()));
		return user;
	}

	public static User getUserByName(String name) {
		String sql = "SELECT * FROM user WHERE name=?";
		List<String> params = Arrays.asList(name);
		Map<String, Object> line = DaoUtil.queryOneLine(sql, params);
		User user = newUserFromLine(line);
		logger.info(String.format("get user by name=%s - %s", name,
				user.toString()));
		return user;
	}

	private static User newUserFromLine(Map<String, Object> line) {
		Integer id = (Integer) line.get("id");
		String name = (String) line.get("name");
		String password = (String) line.get("password");
		int balance = (Integer) line.get("balance");
		int score = (Integer) line.get("score");
		return new User(id, name, password, balance, score);
	}

	/**
	 * Tests whether there exists user of specified name
	 * 
	 * @return <tt>true</tt> if there exists user of specified name
	 */
	public static boolean existsUser(String username) {
		String sql = "SELECT * FROM user WHERE name=?";
		List<String> params = Arrays.asList(username);
		List<Map<String, Object>> results = DaoUtil.query(sql, params);
		boolean exists = !results.isEmpty();
		logger.info(String.format("exists user: username=%s - %s", username,
				(exists ? "Yes" : "No")));
		return exists;
	}

	/**
	 * Tests whether password confirms to username
	 * 
	 * @return <tt>true</tt> if password confirms to username
	 */
	public static boolean confirmPassword(String username, String password) {

		if (!existsUser(username)) {
			return false;
		}

		String sql = "SELECT password FROM user WHERE name=?";
		List<String> params = Arrays.asList(username);
		String password2 = (String) DaoUtil.queryOneObject(sql, params);
		boolean confirms = password.equals(password2);

		logger.info(String.format(
				"confirm password of user %s - '%s' with '%s' - %s", username,
				password, password2, (confirms ? "Yes" : "No")));
		return confirms;
	}
	
	// ============== write daos ================

	public static void addUser(String username, String password) {
		String sql = "INSERT INTO user(name, password) VALUES(?, ?)";
		List<String> params = Arrays.asList(username, password);
		DaoUtil.execute(sql, params);
		logger.info(String.format("add user: username=%s, password=%s",
				username, password));
	}

	public static void subtractBalance(int userId, int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount <= 0");
		}
		String sql = "UPDATE user SET balance=balance-? WHERE id=?";
		List<Integer> params = Arrays.asList(amount, userId);
		DaoUtil.execute(sql, params);
		logger.info(String.format("payExpense: userId=%d, amount=%d", userId,
				amount));
	}
	
	public static void addBalance(int userId, int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount <= 0");
		}
		String sql = "UPDATE user SET balance=balance+? WHERE id=?";
		List<Integer> params = Arrays.asList(amount, userId);
		DaoUtil.execute(sql, params);
		logger.info(String.format("addBalance: userId=%d, amount=%d", userId,
				amount));
	}

	public static void addScore(int userId, int points) {
		if (points <= 0) {
			throw new IllegalArgumentException("points <= 0");
		}
		String sql = "UPDATE user SET score=score+? WHERE id=?";
		List<Integer> params = Arrays.asList(points, userId);
		DaoUtil.execute(sql, params);
		logger.info(String.format("addScore: userId=%d, points=%d", userId,
				points));
	}

}
