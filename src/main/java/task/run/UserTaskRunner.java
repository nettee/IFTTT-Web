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
	protected final boolean debug;
	private UserTaskStatus status = UserTaskStatus.NEW;

	protected UserTaskRunner(UserTask userTask) {
		this(userTask, false);
	}

	protected UserTaskRunner(UserTask userTask, boolean debug) {
		this.userTask = userTask;
		this.debug = debug;
	}

	public static UserTaskRunner getOnceRunner(UserTask userTask) {
		return new OnceTaskRunner(userTask);
	}

	public static UserTaskRunner getRepeatedRunner(UserTask userTask,
			int seconds) {
		return new RepeatedTaskRunner(userTask, seconds);
	}

	public static class Debug {

		public static UserTaskRunner getOnceRunner(UserTask userTask) {
			return new OnceTaskRunner(userTask, true);
		}

		public static UserTaskRunner getRepeatedRunner(UserTask userTask,
				int seconds) {
			return new RepeatedTaskRunner(userTask, seconds, true);
		}

	}

	public Task getTask() {
		return userTask.getTask();
	}

	public UserTaskStatus getStatus() {
		return status;
	}
	
	protected void setStatus(UserTaskStatus status) {
		this.status = status;
	}

	public abstract int getMode();

	public boolean isOnce() {
		return getMode() == ONCE;
	}

	public boolean isRepeated() {
		return getMode() == REPEATED;
	}

	// FIXME a thread cannot be started twice
	public void start_() {
		checkStatus(UserTaskStatus.NEW);
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

	// only for test
	void join_() {
		try {
			this.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

	OnceTaskRunner(UserTask userTask) {
		super(userTask);
	}

	OnceTaskRunner(UserTask userTask, boolean debug) {
		super(userTask, debug);
	}

	@Override
	public int getMode() {
		return ONCE;
	}

	@Override
	protected void work() {
		logger.info("start work...");

		Task task = userTask.getTask();
		while (true) {

			// not tested
			UserTaskStatus st = getStatus();
			if (st.equals(UserTaskStatus.NEW)) {
				throw new IllegalStateException("Illegal runner status "
						+ st.name());
			} else if (st.equals(UserTaskStatus.RUNNING)) {
				;
			} else if (st.equals(UserTaskStatus.SUSPEND)) {
				continue;
			} else if (st.equals(UserTaskStatus.END)) {
				break;
			} else {
				throw new IllegalStateException();
			}

			if (task.THIS()) {
				if (debug) {
					logger.info(String.format("THIS satisfied: %s", task
							.getTrigger().toString()));
				}
				task.THAT();
				if (debug) {
					logger.info(String.format("THAT done: %s", task.getAction()
							.toString()));
				}

				setStatus(UserTaskStatus.END);
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

	RepeatedTaskRunner(UserTask userTask, int seconds) {
		super(userTask);
		this.seconds = seconds;
	}

	RepeatedTaskRunner(UserTask userTask, int seconds, boolean debug) {
		super(userTask, debug);
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
		Calendar endTime = new GregorianCalendar();
		endTime.add(Calendar.SECOND, seconds);

		logger.info(String.format(
				"starts at %02d:%02d:%02d, lasting %d seconds",
				startTime.get(Calendar.HOUR_OF_DAY),
				startTime.get(Calendar.MINUTE), startTime.get(Calendar.SECOND),
				seconds));

		Task task = userTask.getTask();
		try {
			while (new GregorianCalendar().before(endTime)) {

				UserTaskStatus st = getStatus();
				if (st.equals(UserTaskStatus.NEW)) {
					throw new IllegalStateException("Illegal runner status "
							+ st.name());
				} else if (st.equals(UserTaskStatus.RUNNING)) {
					Thread.sleep(1000);
				} else if (st.equals(UserTaskStatus.SUSPEND)) {
					Thread.sleep(1000);
					// delay one second to end
					endTime.add(Calendar.SECOND, 1);
					continue;
				} else if (st.equals(UserTaskStatus.END)) {
					endTime = new GregorianCalendar();
					continue;
				} else {
					throw new IllegalStateException();
				}

				if (task.THIS()) {
					if (debug) {
						logger.info(String.format("THIS satisfied: %s", task
								.getTrigger().toString()));
					}
					task.THAT();
					if (debug) {
						logger.info(String.format("THAT done: %s", task
								.getAction().toString()));
					}
				}
			}

			setStatus(UserTaskStatus.END);
			logger.info(String.format(
					"work done, from %02d:%02d:%02d to %02d:%02d:%02d",
					startTime.get(Calendar.HOUR_OF_DAY),
					startTime.get(Calendar.MINUTE),
					startTime.get(Calendar.SECOND),
					endTime.get(Calendar.HOUR_OF_DAY),
					endTime.get(Calendar.MINUTE), endTime.get(Calendar.SECOND)));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
