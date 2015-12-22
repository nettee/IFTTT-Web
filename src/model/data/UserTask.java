package model.data;

import model.task.Action;
import model.task.Trigger;

public class UserTask {

	private final Integer id;
	private final Integer userId;
	private final Trigger trigger;
	private final Action action;

	public UserTask(Integer id, Integer userId, Trigger trigger, Action action) {
		this.id = id;
		this.userId = userId;
		this.trigger = trigger;
		this.action = action;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public Action getAction() {
		return action;
	}

}
