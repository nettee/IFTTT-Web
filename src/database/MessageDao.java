package database;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.Message;

import org.apache.log4j.Logger;

/**
 * Message Data Access and Operation
 * 
 * @see Message
 * 
 */
public class MessageDao {

	private static final Logger logger = Logger.getLogger(MessageDao.class);

	public Message getMessageById(int id) {
		String sql = "SELECT * FROM message WHERE id=?";
		List<Integer> params = Arrays.asList(id);
		Map<String, Object> line = DaoUtil.queryOneLine(sql, params);
		return newMessageFromLine(line);
	}

	public List<Message> getMessagesByUserId(int userId) {
		String sql = "SELECT * FROM message WHERE userId=?";
		List<Integer> params = Arrays.asList(userId);
		List<Map<String, Object>> lines = DaoUtil.query(sql, params);
		List<Message> messageList = new ArrayList<Message>();
		for (Map<String, Object> line : lines) {
			Message message = newMessageFromLine(line);
			messageList.add(message);
		}
		return messageList;
	}

	private Message newMessageFromLine(Map<String, Object> line) {
		Message message = new Message();
		message.setId((Integer) line.get("id"));
		message.setUserId((Integer) line.get("userId"));
		message.setPublishTime((Timestamp) line.get("publishTime"));
		message.setDigest((String) line.get("digest"));
		message.setContent((String) line.get("content"));
		message.setOpened((Boolean) line.get("opened"));
		return message;
	}

	private void addMessage(Integer userId, String content) {
		String digest = content.length() <= 60 ? content : content.substring(0,
				40);
		String sql = "INSERT INTO message(userId, digest, content) VALUES(?, ?, ?)";
		List<Object> params = Arrays.asList((Object) userId, digest, content);
		DaoUtil.execute(sql, params);
		logger.info(String.format("addMessage: userId=%d, content=%s", userId,
				content));
	}

	public void sendMessageTo(Integer userId, String content) {
		addMessage(userId, content);
		logger.info(String.format("send message to user [%d]", userId));
	}

	public void sendMessageToAll(String content) {
		List<Integer> userIds = new UserDao().getAllIds();
		for (int userId : userIds) {
			addMessage(userId, content);
		}
		logger.info(String.format("send message to user %s",
				Arrays.toString(userIds.toArray())));
	}

	public static void main(String[] args) {
		MessageDao messageDao = new MessageDao();
		messageDao.addMessage(4, "hello world");
	}

}
