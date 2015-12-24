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
	private Map<Integer, TaskRunner> map = new HashMap<Integer, TaskRunner>();

	public TaskRunner getRunner(UserTask userTask) {
		int userTaskId = userTask.getId();
		return getRunner(userTaskId);
	}

	TaskRunner getRunner(Integer userTaskId) {
		return map.get(userTaskId);
	}

	public void putRunner(UserTask userTask, TaskRunner runner) {
		int userTaskId = userTask.getId();
		putRunner(userTaskId, runner);
	}

	void putRunner(Integer userTaskId, TaskRunner runner) {
		map.put(userTaskId, runner);
	}

}
