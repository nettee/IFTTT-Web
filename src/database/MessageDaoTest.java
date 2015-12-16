package database;

import java.util.List;

import model.data.Message;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMessageById() {
		Message message = MessageDao.getMessageById(1);
		System.out.println(message);
	}
	
	@Test
	public void testGetMessageNumberByUserId() {
		int number = MessageDao.getMessageNumberByUserId(4);
		System.out.println(number);
	}
	
	@Test
	public void testGetUnopenedMessageNumberByUserId() {
		int number = MessageDao.getUnopenedMessageNumberByUserId(4);
		System.out.println(number);
	}
	
	@Test
	public void testGetMessageListByUserId() {
		List<Message> messages = MessageDao.getMessageListByUserId(4);
		for (Message message : messages) {
			System.out.println(message);
		}
	}

}
