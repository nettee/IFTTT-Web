package database;

import java.util.List;

import model.Message;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MessageDaoTest {
	
	private static MessageDao messageDao = new MessageDao();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMessageById() {
		Message message = messageDao.getMessageById(1);
		System.out.println(message);
	}
	
	@Test
	public void testGetMessagesByUserId() {
		List<Message> messages = messageDao.getMessagesByUserId(4);
		for (Message message : messages) {
			System.out.println(message);
		}
	}

}
