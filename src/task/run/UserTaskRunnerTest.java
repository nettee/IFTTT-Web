package task.run;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UserTaskRunnerRunningTest.class, UserTaskRunnerStatusTest.class })
public class UserTaskRunnerTest {

}