package task.run;

import java.util.Calendar;
import java.util.GregorianCalendar;

import model.data.UserTask;
import model.task.Task;

import org.apache.log4j.Logger;

public abstract class UserTaskRunner extends Thread {

	private static final Logger logger = Logger.getLogger(UserTaskRunner.class);

	public static final int ONCE = 0;
	public static final int REPEATED = 1;

	protected final UserTask userTask;
	private UserTaskStatus status = UserTaskStatus.END;

	protected UserTaskRunner(UserTask userTask) {
		this.userTask = userTask;
	}

	public static UserTaskRunner getOnceRunner(UserTask userTask) {
		return new OnceTaskRunner(userTask);
	}

	public static UserTaskRunner getRepeatedRunner(UserTask userTask, int seconds) {
		return new RepeatedTaskRunner(userTask, seconds);
	}

	public Task getTask() {
		return userTask.getTask();
	}

	public UserTaskStatus getStatus() {
		return status;
	}

	public abstract int getMode();

	public boolean isOnce() {
		return getMode() == ONCE;
	}

	public boolean isRepeated() {
		return getMode() == REPEATED;
	}

	public void start_() {
		checkStatus(UserTaskStatus.END);
		logger.info(String.format("runner start: %s", userTask.toString()));
		
		status = UserTaskStatus.RUNNING;
		start();
	}

	public void pause_() {
		checkStatus(UserTaskStatus.RUNNING);
		logger.info(String.format("runner pause: %s", userTask.toString()));

		status = UserTaskStatus.SUSPEND;
	}

	public void resume_() {
		checkStatus(UserTaskStatus.SUSPEND);
		logger.info(String.format("runner resume: %s", userTask.toString()));

		status = UserTaskStatus.RUNNING;
	}

	public void stop_() {
		checkStatus(UserTaskStatus.RUNNING, UserTaskStatus.SUSPEND);
		logger.info(String.format("runner stop: %s", userTask.toString()));

		status = UserTaskStatus.END;
	}

	private void checkStatus(UserTaskStatus expectedStatus) {
		if (!status.equals(expectedStatus)) {
			throw new IllegalStateException(String.format(
					"wrong status %s, %s expected", status.name(),
					expectedStatus.name()));
		}
	}

	private void checkStatus(UserTaskStatus expectedStatus1,
			UserTaskStatus expectedStatus2) {
		if (!(status.equals(expectedStatus1) || status.equals(expectedStatus2))) {
			throw new IllegalStateException(String.format(
					"wrong status %s, (%s or %s) expected", status.name(),
					expectedStatus1.name(), expectedStatus2.name()));
		}
	}

	@Override
	public void run() {
		work();

	}

	protected abstract void work();

}

class OnceTaskRunner extends UserTaskRunner {

	private static final Logger logger = Logger.getLogger(OnceTaskRunner.class);

	public OnceTaskRunner(UserTask userTask) {
		super(userTask);
	}

	@Override
	public int getMode() {
		return ONCE;
	}

	@Override
	protected void work() {
		logger.info("start work...");
		while (true) {
			if (userTask.getTask().THIS()) {
				logger.info("THIS satisfied");
				userTask.getTask().THAT();
				logger.info("THAT done");
				logger.info("work done");
				return;
			}
		}
	}

}

class RepeatedTaskRunner extends UserTaskRunner {

	private static final Logger logger = Logger
			.getLogger(RepeatedTaskRunner.class);

	private Integer seconds;

	public RepeatedTaskRunner(UserTask userTask, int seconds) {
		super(userTask);
		this.seconds = seconds;
	}

	@Override
	public int getMode() {
		return REPEATED;
	}

	@Override
	protected void work() {
		logger.info("start work...");
		Calendar startTime = new GregorianCalendar();
		Calendar time = new GregorianCalendar();
		time.add(Calendar.SECOND, seconds);
		Calendar endTime = time;
		logger.info(String.format(
				"starts at %02d:%02d:%02d, ends at %02d:%02d:%02d",
				startTime.get(Calendar.HOUR_OF_DAY),
				startTime.get(Calendar.MINUTE), startTime.get(Calendar.SECOND),
				endTime.get(Calendar.HOUR_OF_DAY),
				endTime.get(Calendar.MINUTE), endTime.get(Calendar.SECOND)));

		try {
			while (new GregorianCalendar().before(endTime)) {
				Thread.sleep(1000);
				if (userTask.getTask().THIS()) {
					logger.info("THIS satisfied");
					userTask.getTask().THAT();
					logger.info("THAT done");
				}
			}
			logger.info("work done");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
