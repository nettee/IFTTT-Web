package model;

import database.UserDao;

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
	 * set the attributes of this user according to id
	 * @param id the id of user
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
	public String toString() {
		return String.format("User(id=%d, name=%s, password=%s, balance=%d)", id, name, password, balance);
	}

}
