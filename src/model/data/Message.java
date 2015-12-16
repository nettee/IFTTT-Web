package model.data;

import java.sql.Timestamp;

public class Message {

	private final int id;
	private final Integer userId;
	private final Timestamp publishTime;
	private final String digest;
	private final String content;
	private final boolean opened;

	public Message(Integer id, Integer userId, Timestamp publishTime,
			String digest, String content, boolean opened) {
		this.id = id;
		this.userId = userId;
		this.publishTime = publishTime;
		this.digest = digest;
		this.content = content;
		this.opened = opened;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public String getDigest() {
		return digest;
	}

	public String getContent() {
		return content;
	}

	public boolean isOpened() {
		return opened;
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
		return String
				.format("Message(id=%d, userId=%d, publishTime=%s, content=%s, opened=%b)",
						id, userId, publishTime, content, opened);
	}

}
