package task.run;

import java.sql.Timestamp;

import model.task.Task;

import org.apache.log4j.Logger;

public class TaskRunner extends Thread {

	private static final Logger logger = Logger.getLogger(TaskRunner.class);

	public static final int ONCE = 0;
	public static final int REPEATED = 1;

	private final Task task;
	private final int mode;
	private Duration duration;

	private TaskRunner(Task task, int mode) {
		this.task = task;
		this.mode = mode;
	}

	public static TaskRunner getOnceRunner(Task userTask) {
		return new TaskRunner(userTask, ONCE);
	}

	public static TaskRunner getRepeatedRunner(Task userTask) {
		return new TaskRunner(userTask, REPEATED);
	}

	public Task getTask() {
		return task;
	}

	public int getMode() {
		return mode;
	}

	public boolean isOnce() {
		return mode == ONCE;
	}

	public boolean isRepeated() {
		return mode == REPEATED;
	}

	public Duration getDuration() {
		return duration;
	}

	@Override
	public void run() {

		long time0 = System.currentTimeMillis();
		work();
		long time1 = System.currentTimeMillis();

		duration = new Duration(time0, time1);

		logger.info(String.format("task used %d seconds", duration.getSeconds()));
	}

	private void work() {

		while (true) {

			if (task.THIS()) {
				logger.info(String.format("trigger effort: %s", task
						.getTrigger().toString()));
				task.THAT();
				logger.info(String.format("action done: %s", task.getAction()
						.toString()));
				return;
			}
		}
	}

	public static class Duration {

		public final long time0;
		public final long time1;

		Duration(long time0, long time1) {
			this.time0 = time0;
			this.time1 = time1;
		}

		public Timestamp getStartTime() {
			return new Timestamp(time0);
		}

		public Timestamp getEndTime() {
			return new Timestamp(time1);
		}

		public int getSeconds() {
			return (int) (1 + (time1 - time0) / 1000);
		}

	}

}
