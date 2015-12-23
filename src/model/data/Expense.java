package model.data;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import database.ExpenseDao;
import database.UserDao;
import database.UserTaskDao;

import task.run.TaskRunner;

public class Expense {

	private static final Logger logger = Logger.getLogger(Expense.class);

	public static final int AMOUNT_FACTOR = 1;

	private final Integer id;
	private final Integer userTaskId;
	private final Timestamp startTime;
	private final Timestamp endTime;
	private final int amount;

	public Expense(Integer id, Integer userTaskId, TaskRunner.Duration duration) {
		this.id = id;
		this.userTaskId = userTaskId;
		this.startTime = duration.getStartTime();
		this.endTime = duration.getEndTime();
		this.amount = duration.getSeconds() * AMOUNT_FACTOR;
	}

	public Integer getId() {
		return id;
	}

	public Integer getUserTaskId() {
		return userTaskId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public int getAmount() {
		return amount;
	}

	public void effect() {
		ExpenseDao.addExpense(userTaskId, startTime, endTime, amount);
		int userId = UserTaskDao.getUserIdById(userTaskId);
		UserDao.payExpense(userId, amount);
		logger.info("expense effected");
	}

}
