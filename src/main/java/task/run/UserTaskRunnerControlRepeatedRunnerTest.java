package task.run;

import static org.junit.Assert.assertEquals;
import model.data.UserTask;
import model.task.Task;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTaskRunnerControlRepeatedRunnerTest {

	private static UserTask defaultUserTask;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		defaultUserTask = new UserTask(null, 7, Task.getDefaultTask());
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

	private void sleepSecond(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testControl_RepeatedRunner_Start_toEnd() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(
				defaultUserTask, 10);
		runner.start_();
		runner.join_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testControl_RepeatedRunner_StartStop() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(
				defaultUserTask, 20);
		runner.start_();
		sleepSecond(5);
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
		runner.join_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testControl_RepeatedRunner_StartPauseStop() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(
				defaultUserTask, 20);
		runner.start_();
		sleepSecond(3);
		runner.pause_();
		sleepSecond(3);
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
		runner.join_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testControl_RepeatedRunner_StartPauseResumeStop() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(
				defaultUserTask, 20);
		runner.start_();
		sleepSecond(3);
		runner.pause_();
		sleepSecond(3);
		runner.resume_();
		sleepSecond(3);
		runner.stop_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
		runner.join_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testControl_RepeatedRunner_StartPauseResume_toEnd() {
		UserTaskRunner runner = UserTaskRunner.getRepeatedRunner(
				defaultUserTask, 12);
		runner.start_();
		sleepSecond(3);
		runner.pause_();
		sleepSecond(3);
		runner.resume_();
		sleepSecond(3);
		runner.join_();
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}


}
