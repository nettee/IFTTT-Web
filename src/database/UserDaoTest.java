package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDaoTest {

	private static UserDao userDao = new UserDao();
	private static User dog = new User();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dog.setId(1);
		dog.setName("dog");
		dog.setPassword("1");
		dog.setBalance(5000);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDao.close();
	}

	@Test
	public void testGetUserById() {
		User user = userDao.getUserById(dog.getId());
		assertEquals(user, dog);
	}

	@Test
	public void testGetUserByName() {
		User user = userDao.getUserByName(dog.getName());
		assertEquals(user, dog);
	}

	@Test
	public void testContainsUser() {
		assertTrue(userDao.containsUser(dog.getName()));
	}

	@Test
	public void testValidPassword() {
		assertTrue(userDao.validPassword(dog.getName(), dog.getPassword()));
	}

}
