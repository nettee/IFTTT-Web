package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.data.UserTask;
import model.task.Task;

import org.apache.log4j.Logger;

public final class UserTaskDao {

	private static final Logger logger = Logger.getLogger(UserTaskDao.class);
	
	private UserTaskDao() {
		
	}

	// TODO test
	public static int getUserTaskNumberByUserId(int userId) {
		String sql = "SELECT count(*) FROM usertask WHERE userId=?";
		List<Integer> params = Arrays.asList(userId);
		Object result = DaoUtil.queryOneObject(sql, params);
		long number = (Long) result;
		return (int) number;
	}

	// TODO test
	public static UserTask getUserTaskById(int id) {
		String sql = "SELECT * FROM usertask WHERE id=?";
		List<Integer> params = Arrays.asList(id);
		Map<String, Object> line = DaoUtil.queryOneLine(sql, params);
		return newUserTaskFromLine(line);
	}

	// TODO test
	public static int getUserIdById(int id) {
		String sql = "SELECT userId FROM usertask WHERE id=?";
		List<Integer> params = Arrays.asList(id);
		Object result = DaoUtil.queryOneObject(sql, params);
		int userId = (Integer) result;
		logger.info(String.format("getUserIdById: id=%d, returns %d", id,
				userId));
		return userId;
	}

	public static List<UserTask> getUserTaskListByUserId(int userId) {
		String sql = "SELECT * FROM usertask WHERE userId=?";
		List<Integer> params = Arrays.asList(userId);
		List<Map<String, Object>> lines = DaoUtil.query(sql, params);

		List<UserTask> userTaskList = new ArrayList<UserTask>();
		for (Map<String, Object> line : lines) {
			UserTask userTask = newUserTaskFromLine(line);
			userTaskList.add(userTask);
		}
		logger.info(String.format("getUserTaskListByUserId: userId=%d", userId));
		return userTaskList;
	}

	private static UserTask newUserTaskFromLine(Map<String, Object> line) {
		Integer id = (Integer) line.get("id");
		Integer userId = (Integer) line.get("userId");
		Task task = (Task) line.get("task");

		return new UserTask(id, userId, task);
	}

	public static void addUserTask(Integer userId, Task task) {
		if (task == null) {
			throw new NullPointerException("task == null");
		}
		String sql = "INSERT INTO usertask(userId, task) VALUES(?, ?)";
		List<Object> params = Arrays.asList((Object) userId, (Object) task);
		DaoUtil.execute(sql, params);
		logger.info(String.format("add usertask: userId=%d, %s", userId,
				task.toString()));
	}

	public static void editUserTaskTaskById(Integer id, Task task) {
		if (task == null) {
			throw new NullPointerException("task == null");
		}
		String sql = "UPDATE usertask SET task=? WHERE id=?";
		List<Object> params = Arrays.asList((Object) task, (Object) id);
		DaoUtil.execute(sql, params);
		logger.info(String.format("edit usertask's task: id=%d, task=%s", id,
				task.toString()));
	}

	public static void deleteUserTaskById(Integer id) {
		String sql = "DELETE FROM usertask WHERE id=?";
		List<Integer> params = Arrays.asList(id);
		DaoUtil.execute(sql, params);
		logger.info(String.format("delete usertask: id=%d", id));
	}
}
