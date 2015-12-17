package database;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.data.Message;

import org.apache.log4j.Logger;

/**
 * Message Data Access and Operation
 * 
 * @see Message
 * 
 */
public final class MessageDao {

	private static final Logger logger = Logger.getLogger(MessageDao.class);

	public static int getMessageNumberByUserId(int userId) {
		String sql = "SELECT count(*) FROM message WHERE userId=?";
		return getSomeMessageNumberByUserId(sql, userId);
	}

	public static int getUnopenedMessageNumberByUserId(int userId) {
		String sql = "SELECT count(*) FROM message WHERE userId=? AND opened=FALSE";
		return getSomeMessageNumberByUserId(sql, userId);
	}

	private static int getSomeMessageNumberByUserId(String sql, int userId) {
		List<Integer> params = Arrays.asList(userId);
		Object result = DaoUtil.queryOneObject(sql, params);
		long number = (Long) result;
		return (int) number;
	}

	public static Message getMessageById(int id) {
		String sql = "SELECT * FROM message WHERE id=?";
		List<Integer> params = Arrays.asList(id);
		Map<String, Object> line = DaoUtil.queryOneLine(sql, params);
		return newMessageFromLine(line);
	}

	public static List<Message> getMessageListByUserId(int userId) {
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

	private static Message newMessageFromLine(Map<String, Object> line) {
		Integer id = (Integer) line.get("id");
		Integer userId = (Integer) line.get("userId");
		Timestamp publishTime = (Timestamp) line.get("publishTime");
		String digest = (String) line.get("digest");
		String content = (String) line.get("content");
		Boolean opened = (Boolean) line.get("opened");
		return new Message(id, userId, publishTime, digest, content, opened);
	}
	
	public static void setMessageOpenedByUserId(int userId) {
		// TODO
	}
	

	public static void sendMessageTo(Integer userId, String content) {
		addMessage(userId, content);
		logger.info(String.format("send message to user [%d]", userId));
	}

	public static void sendMessageToAll(String content) {
		List<Integer> userIds = new UserDao().getAllIds();
		for (int userId : userIds) {
			addMessage(userId, content);
		}
		logger.info(String.format("send message to user %s",
				Arrays.toString(userIds.toArray())));
	}

	private static void addMessage(Integer userId, String content) {
		String digest = content.length() <= 60 ? content : content.substring(0,
				40);
		String sql = "INSERT INTO message(userId, digest, content) VALUES(?, ?, ?)";
		List<Object> params = Arrays.asList((Object) userId, digest, content);
		DaoUtil.execute(sql, params);
		logger.info(String.format("addMessage: userId=%d, content=%s", userId,
				content));
	}

}
