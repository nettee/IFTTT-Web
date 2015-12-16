package model.data;

import java.sql.Timestamp;

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
	private Timestamp publishTime;
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

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
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

	@Override
	public boolean equals(Object o) {
		if (o instanceof Message) {
			Message that = (Message) o;
			return id == that.id && userId == that.userId
					&& publishTime.equals(that.publishTime)
					&& digest.equals(that.digest)
					&& content.equals(that.content) && opened == that.opened;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return String.format("Message(id=%d, userId=%d, publishTime=%s, content=%s)", id, userId, publishTime, content);
	}

}
