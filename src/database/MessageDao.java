package database;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	private MessageDao() {

	}

	// ============== read daos ================

	public static List<Message> getMessageListByUserId(int userId) {
		String sql = "SELECT * FROM message WHERE userId=? ORDER BY publishTime DESC";
		List<Integer> params = Arrays.asList(userId);
		List<Map<String, Object>> lines = DaoUtil.query(sql, params);

		List<Message> messageList = new ArrayList<Message>();
		for (Map<String, Object> line : lines) {
			Message message = newMessageFromLine(line);
			messageList.add(message);
		}
		logger.info(String.format(
				"get message list by userId=%d - %d messages in total", userId,
				messageList.size()));
		return messageList;
	}

	public static List<Integer> getMessageIdList() {
		String sql = "SELECT id FROM message";
		List<String> params = Collections.emptyList();
		List<Map<String, Object>> lines = DaoUtil.query(sql, params);
		List<Integer> idList = new ArrayList<Integer>();
		for (Map<String, Object> line : lines) {
			Integer id = (Integer) line.get("id");
			idList.add(id);
		}
		logger.info(String.format(
				"get message id list - %d message ids in total", idList.size()));
		return idList;
	}

	public static Message getMessageById(int id) {
		String sql = "SELECT * FROM message WHERE id=?";
		List<Integer> params = Arrays.asList(id);
		Map<String, Object> line = DaoUtil.queryOneLine(sql, params);
		Message message = newMessageFromLine(line);
		logger.info(String.format("get message by id=%d - %s", id,
				message.toString()));
		return message;
	}

	private static Message newMessageFromLine(Map<String, Object> line) {
		Integer id = (Integer) line.get("id");
		Integer userId = (Integer) line.get("userId");
		Timestamp publishTime = (Timestamp) line.get("publishTime");
		String subject = (String) line.get("subject");
		String content = (String) line.get("content");
		Boolean opened = (Boolean) line.get("opened");
		return new Message(id, userId, publishTime, subject, content, opened);
	}

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

	// ============== write daos ================

	public static void setAllMessageOpenedByUserId(int userId) {
		String sql = "UPDATE message SET opened=TRUE WHERE userId=?";
		List<Integer> params = Arrays.asList(userId);
		DaoUtil.execute(sql, params);
		logger.info(String.format("set all message of user[%d] opened", userId));
	}

	public static void sendMessageTo(Integer userId, String subject,
			String content) {
		addMessage(userId, subject, content);
		logger.info(String.format(
				"send message to user[%d]: subject=\"%s\", content=\"%s\"",
				userId, subject, content));
	}

	public static void sendMessageToAll(String subject, String content) {
		List<Integer> userIds = UserDao.getUserIdList();
		for (int userId : userIds) {
			addMessage(userId, subject, content);
		}
		logger.info(String.format(
				"send message to user%s: subject=\"%s\", content=\"%s\"",
				Arrays.toString(userIds.toArray()), subject, content));
	}

	private static void addMessage(Integer userId, String subject,
			String content) {
		String sql = "INSERT INTO message(userId, subject, content) VALUES(?, ?, ?)";
		List<Object> params = Arrays.asList((Object) userId, subject, content);
		DaoUtil.execute(sql, params);
	}

}
