package database;

import java.util.Arrays;
import java.util.List;

import model.data.UserTask;
import model.task.Task;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import task.action.HelloAction;
import task.trigger.TimeTrigger;

public class UserTaskDaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("-------------------------");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUserTaskListByUserId() {
		int userId = 1;
		List<UserTask> userTaskList = UserTaskDao
				.getUserTaskListByUserId(userId);
		for (UserTask userTask : userTaskList) {
			System.out.println(userTask);
		}
	}

	@Test
	public void testGetUserTaskNumberByUserId() {
		List<Integer> userIdList = UserDao.getUserIdList();
		for (int userId : userIdList) {
			int number = UserTaskDao.getUserTaskNumberByUserId(userId);
			System.out.println(String.format("user[%d] has %d usertasks",
					userId, number));
		}
	}

	@Test
	public void testGetUserTaskIdList() {
		List<Integer> idList = UserTaskDao.getUserTaskIdList();
		System.out.println(Arrays.toString(idList.toArray()));
	}

	@Test
	public void testGetUserTaskById() {
		List<Integer> idList = UserTaskDao.getUserTaskIdList();
		for (int id : idList) {
			UserTask userTask = UserTaskDao.getUserTaskById(id);
			System.out.println(userTask);
		}
	}

	@Ignore
	@Test
	public void testAddUserTask() {
		Task task = Task.getDefaultTask();
		List<Integer> userIdList = UserDao.getUserIdList();
		for (int userId : userIdList) {
			if (userId % 2 == 0) {
				UserTaskDao.addUserTask(userId, task);
			}
		}
	}

	@Ignore
	@Test
	public void testEditUserTaskTaskById() {
		Task task = new Task("new test task");
		task.setTrigger(new TimeTrigger(13, 45));
		task.setAction(new HelloAction());

		for (int id = 50; id <= 72; id++) {
			UserTaskDao.editUserTaskTaskById(id, task);
		}
		
		for (int id = 50; id <= 72; id++) {
			UserTask userTask = UserTaskDao.getUserTaskById(id);
			System.out.println(userTask);
		}
	}
	
	@Ignore
	@Test
	public void testDeleteUserTaskById() {
		for (int id = 50; id <= 72; id++) {
			UserTaskDao.deleteUserTaskById(id);
		}
	}

}
