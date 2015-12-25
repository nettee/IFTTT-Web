package database;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExpenseDaoTest.class, MessageDaoTest.class, UserDaoTest.class,
		UserTaskDaoTest.class })
public class AllTests {

}
