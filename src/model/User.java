package model;

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
		throw new AssertionError("method not implemented");
	}

}
