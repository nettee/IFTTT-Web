package model.data;

import java.util.List;

import model.task.Task;
import database.MessageDao;
import database.UserDao;
import database.UserTaskDao;

public class User {

	protected Integer id;
	protected String name;
	protected String password;
	protected int balance;
	protected int score;

	public User() {
	}

	public static User getAdmin() {
		return UserDao.getUserById(1);
	}

	public User(Integer id, String name, String password, int balance, int score) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public int getBalance() {
		return balance;
	}

	public int getScore() {
		return score;
	}

	public void setThisById(int id) {
		User tempUser = UserDao.getUserById(id);
		setThisFromUser(tempUser);
	}

	protected void setThisFromUser(User that) {
		this.id = that.id;
		this.name = that.name;
		this.password = that.password;
		this.balance = that.balance;
		this.score = that.score;
	}

	private void checkId() {
		if (id == null) {
			throw new IllegalStateException("id not set yet");
		}
	}

	public List<Message> getMessageList() {
		checkId();
		return MessageDao.getMessageListByUserId(id);
	}

	public int getUnopenedMessageNumber() {
		checkId();
		return MessageDao.getUnopenedMessageNumberByUserId(id);
	}

	public void setAllMessageOpened() {
		checkId();
		MessageDao.setAllMessageOpenedByUserId(id);
	}

	public List<UserTask> getTaskList() {
		checkId();
		return UserTaskDao.getUserTaskListByUserId(id);
	}

	public void addTask(Task task) {
		checkId();
		UserTaskDao.addUserTask(id, task);
	}

	public void recharge(int amount) {
		UserDao.addBalance(id, amount);
	}

	@Override
	public String toString() {
		return String.format(
				"User(id=%d, name=%s, password=%s, balance=%d, score=%d)", id,
				name, password, balance, score);
	}

}
