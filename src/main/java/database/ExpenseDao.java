package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.data.Expense;

import org.apache.log4j.Logger;

/**
 * Expense Data Access and Operation
 * 
 * @see Expense
 * 
 */
public final class ExpenseDao {

	private static final Logger logger = Logger.getLogger(ExpenseDao.class);

	private ExpenseDao() {

	}

	// ============== read daos ================

	public static List<Expense> getExpenseListByUserTaskId(int userTaskId) {
		String sql = "SELECT * FROM expense WHERE userTaskId=? ORDER BY id";
		List<Integer> params = Arrays.asList(userTaskId);
		List<Map<String, Object>> lines = DaoUtil.query(sql, params);

		List<Expense> expenseList = new ArrayList<Expense>();
		for (Map<String, Object> line : lines) {
			Expense expense = newExpenseFromLine(line);
			expenseList.add(expense);
		}
		logger.info(String.format(
				"get expense list by userTaskId=%d - %d expenses in total",
				userTaskId, expenseList.size()));
		return expenseList;
	}

	public static List<Integer> getExpenseIdList() {
		String sql = "SELECT id FROM expense";
		List<String> params = Collections.emptyList();
		List<Map<String, Object>> lines = DaoUtil.query(sql, params);
		List<Integer> idList = new ArrayList<Integer>();
		for (Map<String, Object> line : lines) {
			Integer id = (Integer) line.get("id");
			idList.add(id);
		}
		logger.info(String.format(
				"get expense id list - %d expense ids in total", idList.size()));
		return idList;
	}

	public static Expense getExpenseById(int id) {
		String sql = "SELECT * FROM expense WHERE id=?";
		List<Integer> params = Arrays.asList(id);
		Map<String, Object> line = DaoUtil.queryOneLine(sql, params);
		Expense expense = newExpenseFromLine(line);
		logger.info(String.format("get expense by id=%d - %s", id,
				expense.toString()));
		return expense;
	}

	private static Expense newExpenseFromLine(Map<String, Object> line) {
		Integer id = (Integer) line.get("id");
		Integer userTaskId = (Integer) line.get("userTaskId");
		Integer amount = (Integer) line.get("amount");
		return new Expense(id, userTaskId, amount);
	}

	// ============== write daos ================

	public static void addExpense(Integer userTaskId, int amount) {

		String sql = "INSERT INTO expense(userTaskId, amount) VALUES(?, ?)";
		List<Object> params = Arrays.asList((Object) userTaskId, amount);
		DaoUtil.execute(sql, params);

		logger.info(String.format("addExpense: userTaskId=%d, amount=%d",
				userTaskId, amount));
	}

}
