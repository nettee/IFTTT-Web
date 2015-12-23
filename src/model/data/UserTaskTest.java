package model.data;

import model.task.Task;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import task.action.HelloAction;
import task.trigger.InstantTrigger;

public class UserTaskTest {

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
	public void testLaunch() {
		Task task = new Task();
		task.setTrigger(new InstantTrigger());
		task.setAction(new HelloAction());
		UserTask userTask = new UserTask(3, 1, task);
		userTask.launch();
	}

}
