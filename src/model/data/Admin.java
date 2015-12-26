package model.data;

import java.util.List;

import database.MessageDao;
import database.UserDao;

public class Admin extends User {

	private static final int ADMIN_ID = 1;

	public Admin(User user) {
		super();
		setThisFromUser(user);
		verifyAdmin(id);
	}

	private static void verifyAdmin(int id) {
		if (id != ADMIN_ID) {
			throw new IllegalArgumentException(String.format("Illegal id %d, not admin",
					id));
		}
	}

	public List<User> getUserList() {
		return UserDao.getUserList();
	}

	public void sendMessageToAll(String subject, String content) {
		MessageDao.sendMessageToAll(subject, content);
	}

}
