package task.run;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UserTaskRunnerRunningTest.class,
		UserTaskRunnerStatusTest.class, UserTaskRunnerControlRepeatedRunnerTest.class,
		UserTaskRunnerControlOnceRunnerTest.class })
public class UserTaskRunnerTest {

}
