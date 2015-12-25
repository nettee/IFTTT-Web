package model.data;

import model.task.Task;

import org.apache.log4j.Logger;

import task.run.RunnerBoard;
import task.run.TaskRunner;
import database.UserTaskDao;

public class UserTask {

	private static final Logger logger = Logger.getLogger(UserTask.class);

	private final Integer id;
	private final Integer userId;
	private final Task task;

	public UserTask(Integer id, Integer userId, Task task) {
		this.id = id;
		this.userId = userId;
		this.task = task;
	}

	public static UserTask getUserTask(int id) {
		return UserTaskDao.getUserTaskById(id);
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public Task getTask() {
		return task;
	}

	@Override
	public String toString() {
		return String.format("UserTask[%d]{userId=%d, %s}", id, userId,
				task.toString());
	}

	public void startOnce() {
		logger.info(String.format("start(once) usertask[%d]...", id));

		Expense.getSimpleExpense(id).effect();

		TaskRunner runner = TaskRunner.getOnceRunner(task);
		RunnerBoard.getInstance().putRunner(this, runner);
		runner.start();

		logger.info(String.format("usertask[%d] started(once)", id));
	}

	public void startRepeated(int seconds) {
		logger.info(String.format("start(repeated) usertask[%d]...", id));

		Expense.getDurationExpense(id, seconds).effect();

		TaskRunner runner = TaskRunner.getRepeatedRunner(task, seconds);
		RunnerBoard.getInstance().putRunner(this, runner);
		// FIXME a usertask cannot be started more than once
		runner.start();

		logger.info(String.format("usertask[%d] started(repeated)", id));
	}

	public void pause() {
		logger.info(String.format("pause usertask[%d]...", id));

		TaskRunner runner = getRunner();
		// TODO

		logger.info(String.format("usertask[%d] paused", id));
	}

	public void stop() {
		logger.info(String.format("stop usertask[%d]...", id));

		TaskRunner runner = getRunner();
		// TODO

		logger.info(String.format("usertask[%d] stopped", id));
	}

	private TaskRunner getRunner() {
		TaskRunner runner = RunnerBoard.getInstance().getRunner(this);
		if (runner == null) {
			throw new IllegalStateException(String.format(
					"usertask[%d] does not exist", id));
		}
		return runner;
	}

	public void editTask(Task task) {
		if (task == null) {
			throw new NullPointerException("task == null");
		}
		logger.info(String.format("edit usertask[%d]...", id));

		UserTaskDao.editUserTaskTaskById(id, task);

		logger.info(String.format("usertask[%d] edited to %s", id,
				task.toString()));

	}

	public void delete() {
		logger.info(String.format("delete usertask[%d]...", id));

		UserTaskDao.deleteUserTaskById(id);

		logger.info(String.format("usertask[%d] deleted", id));
	}

}
