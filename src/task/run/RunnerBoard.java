package task.run;

import java.util.HashMap;
import java.util.Map;

import model.data.UserTask;

public class RunnerBoard {

	private static RunnerBoard instance = null;

	private RunnerBoard() {
	}

	// singleton pattern
	public static RunnerBoard getInstance() {
		if (instance == null) {
			instance = new RunnerBoard();
		}
		return instance;
	}

	// stores map from usertask id to task-runner
	private Map<Integer, UserTaskRunner> map = new HashMap<Integer, UserTaskRunner>();

	public UserTaskRunner getRunner(UserTask userTask) {
		int userTaskId = userTask.getId();
		return getRunner(userTaskId);
	}

	UserTaskRunner getRunner(Integer userTaskId) {
		return map.get(userTaskId);
	}

	public void putRunner(UserTask userTask, UserTaskRunner runner) {
		int userTaskId = userTask.getId();
		putRunner(userTaskId, runner);
	}

	void putRunner(Integer userTaskId, UserTaskRunner runner) {
		map.put(userTaskId, runner);
	}

}
