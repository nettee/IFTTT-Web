package task.run;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.data.UserTask;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import database.UserTaskDao;

public class UserTaskRunnerRunningTest {

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
	public void testGetOnceRunner() {
		UserTaskRunner runner = UserTaskRunner.getOnceRunner(userTask);
		assertTrue(runner.isOnce());
		assertFalse(runner.isRepeated());
	}

	@Test
	public void testGetRepeatedRunner() {
		int seconds = 20;
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask,
				seconds);
		assertFalse(runner.isOnce());
		assertTrue(runner.isRepeated());
	}

	@Ignore
	@Test
	public void testOnceRun() {
		UserTaskRunner runner = UserTaskRunner.Debug.getOnceRunner(userTask);
		runner.start_();
		runner.join_();
	}

	@Ignore
	@Test
	public void testRepeatedRun() {
		UserTaskRunner runner = UserTaskRunner.Debug.getRepeatedRunner(
				userTask, 20);
		runner.start_();
		runner.join_();
	}

}
