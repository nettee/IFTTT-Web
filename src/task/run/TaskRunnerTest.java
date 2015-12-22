package task.run;

import model.task.Task;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import task.action.HelloAction;
import task.trigger.InstantTrigger;

public class TaskRunnerTest {

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
	public void test() {
		Task task = new Task();
		task.setTrigger(new InstantTrigger());
		task.setAction(new HelloAction());
		TaskRunner runner = TaskRunner.getOnceRunner(task);
		runner.start();
	}

}
