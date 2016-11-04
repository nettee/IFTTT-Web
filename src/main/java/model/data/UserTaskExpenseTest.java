package model.data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.UserDao;
import database.UserTaskDao;

public class UserTaskExpenseTest {

	private static UserTask userTask;
	private static User user1;
	private static User user2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userTask = UserTaskDao.getUserTaskById(13);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("--------------------------");
		user1 = UserDao.getUserById(userTask.getUserId());
	}

	@After
	public void tearDown() throws Exception {
		user2 = UserDao.getUserById(userTask.getUserId());
		System.out.println(String.format("balance diff: %d, score diff: %d",
				user2.getBalance() - user1.getBalance(), user2.getScore()
						- user1.getScore()));
		user1 = null;
		user2 = null;
	}

	@Test
	public void testStartOnce() {
		userTask.startOnce();
		userTask.joinRunner();
	}
	
	@Test
	public void testStartRepeated() {
		userTask.startRepeated(20);
		userTask.joinRunner();
	}

}
