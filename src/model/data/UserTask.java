package model.data;

import model.task.Task;

import org.apache.log4j.Logger;

import task.run.TaskRunner;
import task.run.TaskRunner.Duration;

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

	public void launch() {
		logger.info(String.format("launch usertask, id=%d, userId=%d", id,
				userId));

		TaskRunner runner = TaskRunner.getOnceRunner(task);
		runner.start();
		try {
			runner.join();
			Duration duration = runner.getDuration();
			logger.info(String.format("start time: %s, end time: %s",
					duration.getStartTime(), duration.getEndTime()));
			Expense expense = new Expense(null, id, duration);
			expense.effect();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
