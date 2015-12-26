package task.run;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.data.UserTask;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import database.UserTaskDao;

public class UserTaskRunnerTest {

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

	@Test
	public void testStatus_StartStop() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testStatus_StartPauseResumeStop() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.pause_();
		assertEquals(runner.getStatus(), UserTaskStatus.SUSPEND);
		runner.resume_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testStatus_StartPauseResumePauseResumeStop() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		for (int i = 0; i < 10; i++) {
			runner.pause_();
			assertEquals(runner.getStatus(), UserTaskStatus.SUSPEND);
			runner.resume_();
			assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		}
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testStatus_StartPauseStop() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.pause_();
		assertEquals(runner.getStatus(), UserTaskStatus.SUSPEND);
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testStatus_NonStartPause() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		try {
			runner.pause_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

	@Test
	public void testStatus_NonStartResume() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		try {
			runner.resume_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

	@Test
	public void testStatus_NonStartStop() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		try {
			runner.stop_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

	@Test
	public void testStatus_StartStart() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		try {
			runner.start_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

	@Test
	public void testStatus_StartResume() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		try {
			runner.resume_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

	@Test
	public void testStatus_StartPausePause() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.pause_();
		assertEquals(runner.getStatus(), UserTaskStatus.SUSPEND);
		try {
			runner.pause_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

	@Test
	public void testStatus_StartPauseStart() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.pause_();
		assertEquals(runner.getStatus(), UserTaskStatus.SUSPEND);
		try {
			runner.start_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}
	
	@Test
	public void testStatus_StartPauseResumeStart() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.pause_();
		assertEquals(runner.getStatus(), UserTaskStatus.SUSPEND);
		runner.resume_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		try {
			runner.start_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}
	
	@Test
	public void testStatus_StartPauseResumeResume() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.pause_();
		assertEquals(runner.getStatus(), UserTaskStatus.SUSPEND);
		runner.resume_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		try {
			runner.resume_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}
	
	@Test
	public void testStatus_StartStopStart() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
		try {
			runner.start_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

	@Test
	public void testStatus_StartStopPause() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
		try {
			runner.pause_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

	@Test
	public void testStatus_StartStopResume() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
		try {
			runner.resume_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

	@Test
	public void testStatus_StartStopStop() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(userTask, 20);
		assertEquals(runner.getStatus(), UserTaskStatus.NEW);
		runner.start_();
		assertEquals(runner.getStatus(), UserTaskStatus.RUNNING);
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
		try {
			runner.stop_();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

}
