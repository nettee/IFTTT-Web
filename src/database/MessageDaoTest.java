package database;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import model.data.Message;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class MessageDaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("-------------------------");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMessageListByUserId() {
		List<Message> messages = MessageDao.getMessageListByUserId(1);
		for (Message message : messages) {
			System.out.println(message);
		}
	}

	@Test
	public void testGetMessageIdList() {
		List<Integer> idList = MessageDao.getMessageIdList();
		System.out.println(Arrays.toString(idList.toArray()));
	}

	@Test
	public void testGetMessageById() {
		List<Integer> idList = MessageDao.getMessageIdList();
		for (int id : idList) {
			Message message = MessageDao.getMessageById(id);
			System.out.println(message);
		}
	}

	@Test
	public void testGetMessageNumberByUserId() {
		List<Integer> userIdList = UserDao.getUserIdList();
		for (int userId : userIdList) {
			int number = MessageDao.getMessageNumberByUserId(userId);
			System.out.println(String.format("user[%d] has %d messages",
					userId, number));
		}
	}

	@Test
	public void testGetUnopenedMessageNumberByUserId() {
		List<Integer> userIdList = UserDao.getUserIdList();
		for (int userId : userIdList) {
			int number = MessageDao.getUnopenedMessageNumberByUserId(userId);
			System.out.println(String.format(
					"user[%d] has %d unopened messages", userId, number));
		}
	}

	@Ignore
	@Test
	public void testSetAllMessageOpenedByUserId_testSendMessageTo() {
		int userId = 7;

		MessageDao.setAllMessageOpenedByUserId(userId);
		int num1 = MessageDao.getUnopenedMessageNumberByUserId(userId);
		assertEquals(num1, 0);

		for (int i = 1; i <= 10; i++) {
			String subject = "test subject " + i;
			String content = "test content " + i;
			MessageDao.sendMessageTo(userId, subject, content);
			int num = MessageDao.getUnopenedMessageNumberByUserId(userId);
			assertEquals(num, i);
		}

		MessageDao.setAllMessageOpenedByUserId(userId);
		int num2 = MessageDao.getUnopenedMessageNumberByUserId(userId);
		assertEquals(num2, 0);

	}

	@Ignore
	@Test
	public void testSendMessageToAll() {
		String subject = "test message to all";
		String content = "Less is more than more";
		MessageDao.sendMessageToAll(subject, content);
	}

}
