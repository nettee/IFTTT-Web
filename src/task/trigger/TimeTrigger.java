package task.trigger;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import model.task.Trigger;

public class TimeTrigger implements Trigger {

	private static final long serialVersionUID = 1L;

	private final int hour;
	private final int minute;

	@Deprecated
	public TimeTrigger(Calendar time) {
		hour = time.get(Calendar.HOUR_OF_DAY);
		minute = time.get(Calendar.MINUTE);
	}

	public TimeTrigger(int hour, int minute) {
		if (hour < 0 || hour >= 24) {
			throw new IllegalArgumentException(String.format("illgal hour %d", hour));
		}
		if (minute < 0 || minute >= 60) {
			throw new IllegalArgumentException(String.format("illgal minute %d", minute));
		}
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public int getType() {
		return TIME;
	}

	@Override
	public boolean test() {
		Calendar now = new GregorianCalendar();
		Calendar settedTime = getSettedTime();
		return !now.before(settedTime);
	}
	
	private Calendar getSettedTime() {
		GregorianCalendar time = new GregorianCalendar();
		time.set(Calendar.HOUR_OF_DAY, hour);
		time.set(Calendar.MINUTE, minute);
		return time;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return hour;
	}

	@Override
	public Map<String, Object> getProperties() {
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("minute", minute);
				put("second", hour);
			}
		};
	}

	@Override
	public String toString() {
		return String.format("TimeTrigger{%02d:%02d:00}", hour, minute);
	}

}
