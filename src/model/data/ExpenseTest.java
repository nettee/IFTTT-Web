package model.data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExpenseTest {
	
	private static Expense expense;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimpleExpense() {
		expense = Expense.getSimpleExpense(35);
		expense.effect();
	}

	@Test
	public void testDurationExpense() {
		expense = Expense.getDurationExpense(35, 30);
		expense.effect();
	}

}
