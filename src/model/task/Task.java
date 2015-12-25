package model.task;

import java.io.Serializable;

import task.action.HelloAction;
import task.trigger.InstantTrigger;

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

	public static Task getDefaultTask() {
		Task task = new Task();
		task.setAction(new HelloAction());
		task.setTrigger(new InstantTrigger());
		return task;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean THIS() {
		return trigger.test();
	}

	public void THAT() {
		action.perform();
	}

	@Override
	public String toString() {
		return String.format("Task{\"%s\", %s, %s}", name, trigger.toString(),
				action.toString());
	}

}
