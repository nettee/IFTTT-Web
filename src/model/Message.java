package model;

import java.util.Calendar;

public class Message implements Bean {

	private int id;
	private int userId;
	private Calendar publishTime;
	private String content;

	public Message() {

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

	public Calendar getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Calendar publishTime) {
		this.publishTime = publishTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
