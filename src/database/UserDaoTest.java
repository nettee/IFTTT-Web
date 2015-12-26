package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import model.data.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class UserDaoTest {

	private static User dog = new User();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dog.setThisById(1);
		System.out.println("-------------------------");
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetUserList() {
		List<User> userList = UserDao.getUserList();
		for (User user : userList) {
			System.out.println(user);
		}
	}

	@Test
	public void testGetUserIdList() {
		List<Integer> idList = UserDao.getUserIdList();
		System.out.println(Arrays.toString(idList.toArray()));
	}

	@Test
	public void testGetUserById() {
		User user = UserDao.getUserById(dog.getId());
		assertEquals(user, dog);
	}

	@Test
	public void testGetUserByName() {
		User user = UserDao.getUserByName(dog.getName());
		assertEquals(user, dog);
	}

	@Test
	public void testExistsUser() {
		assertTrue(UserDao.existsUser(dog.getName()));
	}

	@Test
	public void testConfirmPassword() {
		assertTrue(UserDao.confirmPassword(dog.getName(), dog.getPassword()));
	}

	@Ignore
	@Test
	public void testAddUser() {
		for (int i = 0; i < 10; i++) {
			String username = "ÄÄß¸" + i + "ºÅ";
			UserDao.addUser(username, "duluth");
		}
	}

	@Ignore
	@Test
	public void testSubtractBalance() {
		for (int i = 0; i < 10; i++) {
			String username = "ÄÄß¸" + i + "ºÅ";
			User user = UserDao.getUserByName(username);
			UserDao.subtractBalance(user.getId(), (i + 1) * 100);
		}
	}
	
	@Test
	public void testAddBalance() {
		User jay = UserDao.getUserByName("jay");
		UserDao.addBalance(jay.getId(), 1000);
	}

	@Ignore
	@Test
	public void testAddScore() {
		for (int i = 0; i < 10; i++) {
			String username = "ÄÄß¸" + i + "ºÅ";
			User user = UserDao.getUserByName(username);
			UserDao.addScore(user.getId(), (i + 1) * 100);
		}
	}

}
