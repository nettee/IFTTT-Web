package model.data;

import model.task.Task;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.UserTaskDao;

import task.action.HelloAction;
import task.trigger.InstantTrigger;

public class UserTaskTest {
	
	private static UserTask userTask;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Task task = new Task();
		task.setTrigger(new InstantTrigger());
		task.setAction(new HelloAction());
		userTask = new UserTask(3, 1, task);
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
	public void testStartOnce() {
		userTask.startOnce();
	}
	
	@Test
	public void testEditTask() {
		Task task = new Task();
		task.setTrigger(new InstantTrigger());
		task.setAction(new HelloAction());
		userTask.editTask(task);
	}
	
	@Test
	public void testDelete() {
		UserTask userTask2 = UserTaskDao.getUserTaskById(2);
		userTask2.delete();
	}

}
