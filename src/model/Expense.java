package model;

import java.util.Calendar;

public class Expense implements Bean {

	private int id;
	private int userTaskId;
	private Calendar startTime;
	private Calendar endTime;
	private int amount;

	public Expense() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserTaskId() {
		return userTaskId;
	}

	public void setUserTaskId(int userTaskId) {
		this.userTaskId = userTaskId;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
