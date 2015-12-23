package model.data;

import java.util.List;

import model.task.Task;
import database.MessageDao;
import database.UserDao;
import database.UserTaskDao;

/**
 * Javabean class
 * <p>
 * Properties:
 * <ul>
 * <li>id
 * <li>name
 * <li>password
 * <li>balance
 * </ul>
 * 
 */
public class User implements Bean {

	private Integer id;
	private String name;
	private String password;
	private int balance;

	public User() {
	}

	public User(Integer id, String name, String password, int balance) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.balance = balance;
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

	/**
	 * Used by JSP
	 * <p>
	 * <strong>NOTE:</strong> This is not a property
	 * <p>
	 * Set the attributes of this user according to id
	 * 
	 * @param id
	 *            the id of user
	 */
	public void setThisById(int id) {
		User tempUser = UserDao.getUserById(id);
		setThisFromUser(tempUser);
	}

	private void setThisFromUser(User that) {
		this.id = that.id;
		this.name = that.name;
		this.password = that.password;
		this.balance = that.balance;
	}

	public List<Message> getMessageList() {
		if (id == null) {
			throw new IllegalStateException("id not set yet");
		}
		return MessageDao.getMessageListByUserId(id);
	}

	public int getUnopenedMessageNumber() {
		if (id == null) {
			throw new IllegalStateException("id not set yet");
		}
		return MessageDao.getUnopenedMessageNumberByUserId(id);
	}
	
	public void setAllMessageOpened() {
		MessageDao.setAllMessageOpenedByUserId(id);
	}
	
	public void addTask(Task task) {
		UserTaskDao.addUserTask(id, task);
	}
	
	public List<UserTask> getTaskList() {
		return UserTaskDao.getUserTaskListByUserId(id);
	}
	
	public List<User> getUserList() {
		verifyAdmin(this.id);
		return UserDao.getUserList();
	}

	private static void verifyAdmin(int id) {
		final int adminId = 1;
		if (id != adminId) {
			throw new IllegalArgumentException(String.format("Illegal id %d", id));
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof User) {
			User that = (User) o;
			return id == that.id && name.equals(that.name)
					&& password.equals(that.password)
					&& balance == that.balance;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return String.format("User(id=%d, name=%s, password=%s, balance=%d)",
				id, name, password, balance);
	}

}
