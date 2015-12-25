package model.data;

import java.util.List;

import model.task.Task;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import task.action.HelloAction;
import task.trigger.InstantTrigger;
import task.trigger.TimeTrigger;

public class UserTest {

	private static User user;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		user = new User();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddTask() {
		user.setThisById(1);
		for (int i = 5; i <= 7; i++) {
			String name = String.format("ºùÂ«ÍÞ%dºÅ", i);
			Task task = new Task(name);
			task.setTrigger(new InstantTrigger());
			task.setAction(new HelloAction());
			user.addTask(task);
		}
	}
	
	@Test
	public void testTaskRunningChain() {
		user.setThisById(1);
		
		UserTask userTask = user.getTaskList().get(1);
		Task task = userTask.getTask();
		task.setTrigger(new TimeTrigger(15, 23));
		userTask.editTask(task);
		
		UserTask ut = UserTask.getUserTask(12);
		ut.startOnce();
		
	}

	@Test
	public void testSetThisById() {
		user.setThisById(4);
		System.out.println(user);
	}

	@Test
	public void testGetMessageList() {
		user.setThisById(4);
		List<Message> messageList = user.getMessageList();
		for (Message message : messageList) {
			System.out.println(message);
		}
	}

}
