package task.run;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

}

class OnceTaskRunner extends TaskRunner {

	private static final Logger logger = Logger.getLogger(OnceTaskRunner.class);

	public OnceTaskRunner(Task task) {
		super(task);
	}

	@Override
	public int getMode() {
		return ONCE;
	}

	@Override
	protected void work() {
		logger.info("start work...");
		while (true) {
			if (task.THIS()) {
				logger.info("THIS satisfied");
				task.THAT();
				logger.info("THAT done");
				logger.info("work done");
				return;
			}
		}
	}

}

class RepeatedTaskRunner extends TaskRunner {

	private static final Logger logger = Logger
			.getLogger(RepeatedTaskRunner.class);

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
				if (task.THIS()) {
					logger.info("THIS satisfied");
					task.THAT();
					logger.info("THAT done");
				}
			}
			logger.info("work done");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
