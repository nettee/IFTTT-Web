package model.data;

import model.task.Task;

import org.apache.log4j.Logger;

import task.run.TaskRunner;

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

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public Task getTask() {
		return task;
	}

	public void startOnce() {
		logger.info(String.format("start[once] usertask, id=%d", id));

		Expense.getSimpleExpense(id).effect();
		TaskRunner.getOnceRunner(task).start();
	}
	
	public void startRepeated(int seconds) {
		logger.info(String.format("start[repeated] usertask, id=%d", id));
		Expense.getDurationExpense(id, seconds).effect();
		TaskRunner.getRepeatedRunner(task, seconds).start();
		
	}
	
	public void pause() {
		// TODO
	}
	
	public void stop() {
		// TODO
	}
	
	public void editTask(Task task) {
		// TODO
	}
	
	public void delete() {
		// TODO
	}
}
