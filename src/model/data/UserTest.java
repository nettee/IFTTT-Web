package model.data;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	
	private static User user = new User();

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
	public void testSetThisById() {
		user.setThisById(4);
		System.out.println(user);
	}

	@Test
	public void testGetMessageList() {
		user.setThisById(4);
		List<Message> messageList = user.getMessageList();
		for (Message message : messageList) {
			System.out.println(message);
		}
	}

}
