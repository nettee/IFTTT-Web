package model.data;

import org.apache.log4j.Logger;

import task.run.TaskRunner;
import database.ExpenseDao;
import database.UserDao;
import database.UserTaskDao;

public class Expense {

	private static final Logger logger = Logger.getLogger(Expense.class);

	private static final int AMOUNT_FACTOR = 1;

	private final Integer id;
	private final Integer userTaskId;
	private final int mode;
	private final int amount;

	private Expense(Integer id, Integer userTaskId, int mode, int amount) {
		this.id = id;
		this.userTaskId = userTaskId;
		this.mode = mode;
		this.amount = amount;
	}

	public static Expense getSimpleExpense(Integer userTaskId) {
		return new Expense(null, userTaskId, TaskRunner.ONCE, 5);
	}
	
	public static Expense getDurationExpense(Integer userTaskId, int seconds) {
		return new Expense(null, userTaskId, TaskRunner.REPEATED, seconds
				* AMOUNT_FACTOR);
	}

	public Integer getId() {
		return id;
	}

	public Integer getUserTaskId() {
		return userTaskId;
	}

	public int getMode() {
		return mode;
	}

	public int getAmount() {
		return amount;
	}

	public void effect() {
		ExpenseDao.addExpense(userTaskId, mode, amount);
		int userId = UserTaskDao.getUserIdById(userTaskId);
		UserDao.payExpense(userId, amount);
		logger.info("expense effected");
	}

}
