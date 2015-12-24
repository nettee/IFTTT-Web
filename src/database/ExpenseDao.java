package database;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public final class ExpenseDao {

	private static final Logger logger = Logger.getLogger(ExpenseDao.class);

	public static final int AMOUNT_FACTOR = 1;

	private ExpenseDao() {

	}

	public static void addExpense(Integer userTaskId, int amount) {

		String sql = "INSERT INTO expense(userTaskId, amount) VALUES(?, ?)";
		List<Object> params = Arrays.asList((Object) userTaskId, amount);
		DaoUtil.execute(sql, params);

		logger.info(String.format("addExpense: userTaskId=%d, amount=%d",
				userTaskId, amount));
	}

}
