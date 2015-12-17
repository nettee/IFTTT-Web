package task.trigger;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import model.task.Trigger;

public class TimeTrigger implements Trigger {

	private static final long serialVersionUID = 1L;

	private final Calendar settedTime;

	public TimeTrigger(Calendar time) {
		settedTime = time;
	}

	@Override
	public boolean test() {
		GregorianCalendar currentTime = new GregorianCalendar();
		return currentTime.after(settedTime);
	}

	public Calendar getSettedTime() {
		return settedTime;
	}

	@Override
	public String toString() {
		int year = settedTime.get(Calendar.YEAR);
		int month = settedTime.get(Calendar.MONTH);
		int day = settedTime.get(Calendar.DAY_OF_MONTH);
		int hour = settedTime.get(Calendar.HOUR_OF_DAY);
		int minute = settedTime.get(Calendar.MINUTE);
		int second = settedTime.get(Calendar.SECOND);
		return String.format("TimeTrigger(%4d-%2d-%2d %2d:%2d:%2d)", year,
				month, day, hour, minute, second);
	}

	@Override
	public Map<String, Object> getProperties() {
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("setted time", settedTime);
			}
		};
	}

}
