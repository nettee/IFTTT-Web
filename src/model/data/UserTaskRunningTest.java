package model.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.UserTaskDao;

public class UserTaskRunningTest {

	private static UserTask userTask;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userTask = UserTaskDao.getUserTaskById(13);
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
	public void testStart() {
		userTask.startRepeated(20);
	}

}
