package model.data;

import database.UserDao;

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

	private int id;
	private String name;
	private String password;
	private int balance;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
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
		UserDao dao = new UserDao();
		User tempUser = dao.getUserById(id);
		setThisFromUser(tempUser);
	}

	private void setThisFromUser(User that) {
		setId(that.id);
		setName(that.name);
		setPassword(that.password);
		setBalance(that.balance);
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
