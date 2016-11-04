package task.trigger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import task.weibo.GetNewStatus;

import model.task.Trigger;

public class WeiboSilentTrigger implements Trigger {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(WeiboSilentTrigger.class);
	private final String code;
	private final int hour;
	private final int minute;

	public WeiboSilentTrigger(String code, int hour, int minute) {
		if (hour < 0 || hour >= 24) {
			throw new IllegalArgumentException(String.format("illgal hour %d",
					hour));
		}
		if (minute < 0 || minute >= 60) {
			throw new IllegalArgumentException(String.format(
					"illgal minute %d", minute));
		}
		this.code = code;
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public int getType() {
		return WEIBO_SILENT;
	}

	@Override
	public boolean test() {
		Date post_time = GetNewStatus.getNewStatus_Date(code);
		Date current_time = new Date();
		logger.info("current time: " + current_time + " post_time: "
				+ post_time + " means "
				+ (current_time.getTime() - post_time.getTime()) / 60000
				+ " min");
		if ((current_time.getTime() - post_time.getTime()) / 60000 > hour * 60
				+ minute)
			return true;
		return false;
	}

	@Override
	public Map<String, Object> getProperties() {
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("hour", hour);
				put("minute", minute);
			}
		};
	}

	@Override
	public String toString() {
		return String.format("WeiboSilentTrigger{hour=%s, minute=%s}", hour,
				minute);
	}
}
