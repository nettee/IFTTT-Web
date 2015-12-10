package model;

import java.util.Calendar;

/**
 * Javabean class
 * <p>
 * Properties:
 * <ul>
 * <li>id
 * <li>userId
 * <li>publishTime
 * <li>digest
 * <li>content
 * <li>opened
 * </ul>
 */
public class Message implements Bean {

	private int id;
	private int userId;
	private Calendar publishTime;
	private String digest;
	private String content;
	private boolean opened;

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

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

}
