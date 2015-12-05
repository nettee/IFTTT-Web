package model;

public class UserTask implements Bean {

	private int id;
	private int userId;
	private int triggerId;
	private String triggerArg;
	private int actionId;
	private String actionArg;

	public UserTask() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(int triggerId) {
		this.triggerId = triggerId;
	}

	public String getTriggerArg() {
		return triggerArg;
	}

	public void setTriggerArg(String triggerArg) {
		this.triggerArg = triggerArg;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public String getActionArg() {
		return actionArg;
	}

	public void setActionArg(String actionArg) {
		this.actionArg = actionArg;
	}

}
