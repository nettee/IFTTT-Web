package database;

import java.util.Arrays;
import java.util.List;

import model.data.Expense;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ExpenseDaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("-------------------------");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetExpenseListByUserId() {
		int userTaskId = 3;
		List<Expense> expenseList = ExpenseDao
				.getExpenseListByUserTaskId(userTaskId);
		for (Expense expense : expenseList) {
			System.out.println(expense);
		}
	}

	@Test
	public void testGetExpenseIdList() {
		List<Integer> idList = ExpenseDao.getExpenseIdList();
		System.out.println(Arrays.toString(idList.toArray()));
	}

	@Test
	public void testGetExpenseById() {
		List<Integer> idList = ExpenseDao.getExpenseIdList();
		for (int id : idList) {
			Expense expense = ExpenseDao.getExpenseById(id);
			System.out.println(expense);
		}
	}

	@Ignore
	@Test
	public void testAddExpense() {
		for (int i = 15; i < 20; i++) {
			ExpenseDao.addExpense(13, i);
		}
	}

}
