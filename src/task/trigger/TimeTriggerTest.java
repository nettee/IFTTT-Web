package task.trigger;

import static org.junit.Assert.*;

import model.task.Task;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import task.action.HelloAction;
import task.run.UserTaskRunner;

public class TimeTriggerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testTimeTriggerIntInt() {
		new TimeTrigger(13, 45);
		new TimeTrigger(23, 59);
		new TimeTrigger(0, 0);
		new TimeTrigger(0, 1);
		new TimeTrigger(1, 0);
		try {
			new TimeTrigger(23, 60);
			fail("Exception is not thrown as expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			new TimeTrigger(24, 0);
			fail("Exception is not thrown as expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			new TimeTrigger(24, 60);
			fail("Exception is not thrown as expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			new TimeTrigger(-1, 1);
			fail("Exception is not thrown as expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			new TimeTrigger(1, -1);
			fail("Exception is not thrown as expected");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testToString() {
		TimeTrigger timeTrigger = new TimeTrigger(13, 45);
		System.out.println(timeTrigger);
		assertEquals(timeTrigger.toString(), "TimeTrigger{13:45:00}");
	}

	@Test
	public void test() {
		Task task = new Task("test");
		task.setTrigger(new TimeTrigger(14, 3));
		task.setAction(new HelloAction());
		UserTaskRunner runner = UserTaskRunner.getOnceRunner(task);
		runner.start();
		try {
			runner.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
