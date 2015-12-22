package model.data;

import model.task.Task;

public class UserTask {

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

}
