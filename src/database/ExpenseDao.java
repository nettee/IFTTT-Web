package database;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public final class ExpenseDao {

	private static final Logger logger = Logger.getLogger(ExpenseDao.class);

	public static final int AMOUNT_FACTOR = 1;

	private ExpenseDao() {

	}

	public static void addExpense(Integer userTaskId, 
		 Timestamp startTime, Timestamp endTime, int amount) {

		String sql = "INSERT INTO expense(userTaskId, startTime, endTime, amount) VALUES(?, ?, ?, ?)";
		List<Object> params = Arrays.asList((Object) userTaskId, startTime,
				endTime, amount);
		DaoUtil.execute(sql, params);

		logger.info(String
				.format("addExpense: userTaskId=%d, startTime=%s, endTime=%s, amount=%d",
						userTaskId, startTime, endTime, amount));
	}

}
