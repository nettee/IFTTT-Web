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
		return get(userTask.getId());
	}

	private UserTaskRunner get(Integer userTaskId) {
		return map.get(userTaskId);
	}

	public void putUserTask(UserTask userTask, UserTaskRunner runner) {
		put(userTask.getId(), runner);
	}

	private void put(Integer userTaskId, UserTaskRunner runner) {
		map.put(userTaskId, runner);
	}
	
	public void removeUserTask(UserTask userTask) {
		remove(userTask.getId());
	}
	
	private void remove(Integer userTaskId) {
		map.remove(userTaskId);
	}

}
