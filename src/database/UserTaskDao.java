package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.data.UserTask;
import model.task.Action;
import model.task.Task;
import model.task.Trigger;

import org.apache.log4j.Logger;

public class UserTaskDao {

	private static final Logger logger = Logger.getLogger(UserTaskDao.class);

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

	public static List<UserTask> getUserTaskListByUserId(int userId) {
		String sql = "SELECT * FROM usertask WHERE userId=?";
		List<Integer> params = Arrays.asList(userId);
		List<Map<String, Object>> lines = DaoUtil.query(sql, params);

		List<UserTask> userTaskList = new ArrayList<UserTask>();
		for (Map<String, Object> line : lines) {
			UserTask userTask = newUserTaskFromLine(line);
			userTaskList.add(userTask);
		}
		return userTaskList;
	}

	private static UserTask newUserTaskFromLine(Map<String, Object> line) {
		Integer id = (Integer) line.get("id");
		Integer userId = (Integer) line.get("userId");
		Trigger trigger = (Trigger) line.get("trigger_");
		Action action = (Action) line.get("action_");

		logger.debug(String.format("id=%d, userId=%d, trigger=%s, action=%s",
				id, userId, trigger, action));
		return new UserTask(id, userId, trigger, action);
	}

	public static void addUserTask(Integer userId, Task task) {
		Trigger trigger = task.getTrigger();
		Action action = task.getAction();
		String sql = "INSERT INTO usertask(userId, trigger_, action_) VALUES(?, ?, ?)";
		List<Object> params = Arrays.asList((Object) userId, trigger, action);
		DaoUtil.execute(sql, params);
		logger.info(String.format("new usertask: userId=%d, %s, %s", userId,
				trigger.toString(), action.toString()));
	}

}
