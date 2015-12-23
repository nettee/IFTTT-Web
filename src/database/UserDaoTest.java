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
import org.junit.Test;

public class UserDaoTest {

	private static User dog = new User(1, "dog", "1", 5000);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
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
	public void testGetAllIds() {
		List<Integer> allIds = UserDao.getAllIds();
		System.out.println(Arrays.toString(allIds.toArray()));
	}

	@Test
	public void testExistsUser() {
		assertTrue(UserDao.existsUser(dog.getName()));
	}

	@Test
	public void testValidPassword() {
		assertTrue(UserDao.validPassword(dog.getName(), dog.getPassword()));
	}

}
