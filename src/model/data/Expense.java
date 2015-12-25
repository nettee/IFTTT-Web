package model.data;

import org.apache.log4j.Logger;

import database.ExpenseDao;
import database.UserDao;
import database.UserTaskDao;

public class Expense {

	private static final Logger logger = Logger.getLogger(Expense.class);

	private static final int AMOUNT_SECOND_FACTOR = 1;
	private static final int SCORE_AMOUNT_FACTOR = 1;

	private final Integer id;
	private final Integer userTaskId;
	private final int amount;

	private Expense(Integer id, Integer userTaskId, int amount) {
		this.id = id;
		this.userTaskId = userTaskId;
		this.amount = amount;
	}

	public static Expense getSimpleExpense(Integer userTaskId) {
		return new Expense(null, userTaskId, 5);
	}

	public static Expense getDurationExpense(Integer userTaskId, int seconds) {
		return new Expense(null, userTaskId, seconds * AMOUNT_SECOND_FACTOR);
	}

	public Integer getId() {
		return id;
	}

	public Integer getUserTaskId() {
		return userTaskId;
	}

	public int getAmount() {
		return amount;
	}

	public void effect() {
		ExpenseDao.addExpense(userTaskId, amount);
		// TODO test
		UserTask userTask = UserTaskDao.getUserTaskById(userTaskId);
		int userId = userTask.getId();
		UserDao.payExpense(userId, amount);
		UserDao.addScore(userId, amount * SCORE_AMOUNT_FACTOR);
		logger.info("expense effected");
	}

}
