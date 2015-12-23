package task.run;

import org.apache.log4j.Logger;

import model.task.Task;

public class TaskRunner extends Thread {

	private static final Logger logger = Logger.getLogger(TaskRunner.class);

	public static final int ONCE = 0;
	public static final int REPEATED = 1;

	private final Task task;
	private final int mode;

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

	@Override
	public void run() {

		long time0 = System.currentTimeMillis();
		work();
		long time1 = System.currentTimeMillis();
		
		int second = (int) (time1 - time0 + 999) / 1000;

		logger.info(String.format("task used %d seconds", second));
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

}
