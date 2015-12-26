package model.data;

import org.apache.log4j.Logger;

import database.ExpenseDao;
import database.UserDao;
import database.UserTaskDao;

public class Expense {

	private static final Logger logger = Logger.getLogger(Expense.class);

	private static final int SIMPLE_AMOUNT = 5;
	private static final int AMOUNT_SECOND_FACTOR = 1;
	private final Integer id;
	private final Integer userTaskId;
	private final int amount;

	public Expense(Integer id, Integer userTaskId, int amount) {
		this.id = id;
		this.userTaskId = userTaskId;
		this.amount = amount;
	}

	public static Expense getSimpleExpense(Integer userTaskId) {
		return new Expense(null, userTaskId, SIMPLE_AMOUNT);
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

	/**
	 * Expense take effect
	 */
	public void effect() {
		
		ExpenseDao.addExpense(userTaskId, amount);
		UserTask userTask = UserTaskDao.getUserTaskById(userTaskId);
		
		User user = UserDao.getUserById(userTask.getUserId());
		
		int discountedAmount = discount(user, amount);
		UserDao.subtractBalance(userTask.getUserId(), discountedAmount);
		
		int points = convert2point(user, amount);
		UserDao.addScore(userTask.getUserId(), points);
		logger.info(String.format(
				"expense effected, amount discount from %d to %d, points=%d",
				amount, discountedAmount, points));
	}

	// returns the discounted amount	
	private int discount(User user, int amount) {
		int level = user.getLevel();
		double ratio = (100.0 - level * 2) / 100.0;
		int discountedAmount = (int) Math.round(amount * ratio);
		logger.info(String.format("user level %d, discount ratio %.0f%%", level, ratio * 100, discountedAmount));
		return discountedAmount;
	}
	
	// convert from amount to point
	private int convert2point(User user, int amount) {
		int level = user.getLevel();
		double ratio = 2.0 + level * 0.1;
		int point = (int) Math.round(amount * ratio);
		return point;
	}

	@Override
	public String toString() {
		return String.format("Expense[%d]{userTaskId=%d, amount=%d}", id,
				userTaskId, amount);
	}

}
