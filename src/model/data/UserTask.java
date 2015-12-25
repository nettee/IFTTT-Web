package model.data;

import model.task.Task;

import org.apache.log4j.Logger;

import task.run.RunnerBoard;
import task.run.UserTaskRunner;
import task.run.UserTaskStatus;
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

	public UserTaskStatus getStatus() {
		return getRunner().getStatus();
	}

	@Override
	public String toString() {
		return String.format("UserTask[%d]{userId=%d, %s}", id, userId,
				task.toString());
	}

	public void startOnce() {
		Expense.getSimpleExpense(id).effect();

		UserTaskRunner runner = UserTaskRunner.getOnceRunner(task);
		RunnerBoard.getInstance().putRunner(this, runner);
		runner.start_();
	}

	public void startRepeated(int seconds) {
		Expense.getDurationExpense(id, seconds).effect();

		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(task, seconds);
		RunnerBoard.getInstance().putRunner(this, runner);
		runner.start_();
	}

	public void pause() {
		UserTaskRunner runner = getRunner();
		runner.pause_();
	}

	public void resume() {
		UserTaskRunner runner = getRunner();
		runner.resume_();
	}

	public void stop() {
		UserTaskRunner runner = getRunner();
		runner.stop_();
	}

	private UserTaskRunner getRunner() {
		UserTaskRunner runner = RunnerBoard.getInstance().getRunner(this);
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
		logger.info(String.format("edit usertask[%d] to %s...", id,
				task.toString()));

		UserTaskDao.editUserTaskTaskById(id, task);
	}

	public void delete() {
		logger.info(String.format("delete usertask[%d]...", id));

		UserTaskDao.deleteUserTaskById(id);
	}

}
