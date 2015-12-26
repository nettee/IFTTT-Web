package task.run;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import model.data.UserTask;
import model.task.Task;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import task.action.HelloAction;
import task.trigger.TimeTrigger;

public class UserTaskRunnerControlOnceRunnerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("---------------------");
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

	private static UserTask getTimeUserTask() {
		Task timeTask = new Task("Time Task");
		Calendar calendar = new GregorianCalendar();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		timeTask.setTrigger(new TimeTrigger(hour, minute + 1));
		timeTask.setAction(new HelloAction());
		UserTask timeUserTask = new UserTask(null, 7, timeTask);
		return timeUserTask;
	}

	private static String timeToString() {
		Calendar time = new GregorianCalendar();
		return String.format("%02d:%02d:%02d", time.get(Calendar.HOUR_OF_DAY),
				time.get(Calendar.MINUTE), time.get(Calendar.SECOND));
	}

	@Test
	public void testControl_OnceRunner_Start_toEnd() {
		System.out.println("testControl_OnceRunner_Start_toEnd");
		UserTaskRunner runner = UserTaskRunner.getOnceRunner(getTimeUserTask());
		runner.start_();
		System.out.println("START at " + timeToString());
		runner.join_();
		System.out.println("END at " + timeToString());
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testControl_OnceRunner_StartStop() {
		System.out.println("testControl_OnceRunner_StartStop");
		UserTaskRunner runner = UserTaskRunner.getOnceRunner(getTimeUserTask());
		runner.start_();
		System.out.println("START at " + timeToString());
		sleepSecond(20);
		runner.stop_();
		System.out.println("STOP at " + timeToString());
		assertEquals(runner.getStatus(), UserTaskStatus.END);
		runner.join_();
		System.out.println("END at " + timeToString());
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testControl_OnceRunner_StartPauseStop() {
		System.out.println("testControl_OnceRunner_StartPauseStop");
		UserTaskRunner runner = UserTaskRunner.getOnceRunner(getTimeUserTask());
		runner.start_();
		System.out.println("START at " + timeToString());
		sleepSecond(20);
		runner.pause_();
		System.out.println("PAUSE at " + timeToString());
		sleepSecond(20);
		runner.stop_();
		System.out.println("STOP at " + timeToString());
		assertEquals(runner.getStatus(), UserTaskStatus.END);
		runner.join_();
		System.out.println("END at " + timeToString());
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testControl_OnceRunner_StartPauseResumeStop() {
		System.out.println("testControl_OnceRunner_StartPauseResumeStop");
		UserTaskRunner runner = UserTaskRunner.getOnceRunner(getTimeUserTask());
		runner.start_();
		System.out.println("START at " + timeToString());
		sleepSecond(20);
		runner.pause_();
		System.out.println("PAUSE at " + timeToString());
		sleepSecond(20);
		runner.resume_();
		System.out.println("RESUME at " + timeToString());
		sleepSecond(20);
		runner.stop_();
		System.out.println("STOP at " + timeToString());
		assertEquals(runner.getStatus(), UserTaskStatus.END);
		runner.join_();
		System.out.println("END at " + timeToString());
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}

	@Test
	public void testControl_OnceRunner_StartPauseResume_toEnd() {
		System.out.println("testControl_OnceRunner_StartPauseResume_toEnd");
		UserTaskRunner runner = UserTaskRunner.getOnceRunner(getTimeUserTask());
		runner.start_();
		System.out.println("START at " + timeToString());
		sleepSecond(20);
		runner.pause_();
		System.out.println("PAUSE at " + timeToString());
		sleepSecond(20);
		runner.resume_();
		System.out.println("RESUME at " + timeToString());
		sleepSecond(20);
		runner.join_();
		System.out.println("END at " + timeToString());
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}
	
	@Test
	public void testControl_OnceRunner_StartPauseResume_toEnd_outOfSettedTime() {
		System.out.println("testControl_OnceRunner_StartPauseResume_toEnd");
		UserTaskRunner runner = UserTaskRunner.getOnceRunner(getTimeUserTask());
		runner.start_();
		System.out.println("START at " + timeToString());
		sleepSecond(20);
		runner.pause_();
		System.out.println("PAUSE at " + timeToString());
		sleepSecond(100);
		runner.resume_();
		System.out.println("RESUME at " + timeToString());
		sleepSecond(20);
		runner.join_();
		System.out.println("END at " + timeToString());
		assertEquals(runner.getStatus(), UserTaskStatus.END);
	}
	

}
