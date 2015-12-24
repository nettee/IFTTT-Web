package task.run;

import model.task.Task;

import org.apache.log4j.Logger;

public abstract class TaskRunner extends Thread {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(TaskRunner.class);

	public static final int ONCE = 0;
	public static final int REPEATED = 1;

	protected final Task task;

	protected TaskRunner(Task task) {
		this.task = task;
	}

	public static TaskRunner getOnceRunner(Task task) {
		return new OnceTaskRunner(task);
	}

	public static TaskRunner getRepeatedRunner(Task task, int seconds) {
		return new RepeatedTaskRunner(task, seconds);
	}

	public Task getTask() {
		return task;
	}

	public abstract int getMode();

	public boolean isOnce() {
		return getMode() == ONCE;
	}

	public boolean isRepeated() {
		return getMode() == REPEATED;
	}

	@Override
	public void run() {
		work();

	}

	protected abstract void work();

	private static class OnceTaskRunner extends TaskRunner {

		public OnceTaskRunner(Task task) {
			super(task);
		}

		@Override
		public int getMode() {
			return ONCE;
		}

		@Override
		protected void work() {
			while (true) {
				if (task.THIS()) {
					task.THAT();
					return;
				}
			}
		}

	}

	private static class RepeatedTaskRunner extends TaskRunner {

		private Integer seconds;

		public RepeatedTaskRunner(Task task, int seconds) {
			super(task);
			this.seconds = seconds;
		}

		@Override
		public int getMode() {
			return REPEATED;
		}

		@Override
		protected void work() {
			try {
				for (int i = 0; i < seconds; i++) {
					Thread.sleep(1000);
				}
				if (task.THIS()) {
					task.THAT();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
