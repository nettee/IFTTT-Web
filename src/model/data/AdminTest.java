package model.data;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.UserDao;

public class AdminTest {

	private static Admin admin;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		User dog = UserDao.getUserByName("dog");
		admin = new Admin(dog);
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
	public void testGetUserList() {
		List<User> userList = admin.getUserList();
		for (User user : userList) {
			System.out.println(user);
		}
	}

	@Test
	public void testSendMessageTo() {
		User user = UserDao.getUserByName("nettis");
		int userId = user.getId();
		String subject = "Hello nettis";
		String content = "Long time no see!";
		admin.sendMessageTo(userId, subject, content);
	}

	@Test
	public void testSendMessageToAll() {
		String subject = "Late Greeting";
		String content = "Merry Christmas!";
		admin.sendMessageToAll(subject, content);
	}
	
	@Test
	public void testSetUserLevel() {
		User user = UserDao.getUserByName("jay");
		int userId = user.getId();
		admin.setUserLevel(userId, 20);
	}

}
