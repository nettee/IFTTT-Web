package model.task;

import java.io.Serializable;

public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private Trigger trigger;
	private Action action;

	public Task() {
		this("Default Task");
	}

	public Task(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return String.format("Task{%s, %s}", trigger.toString(),
				action.toString());
	}

}
