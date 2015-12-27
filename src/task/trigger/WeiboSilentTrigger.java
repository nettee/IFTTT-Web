package task.trigger;

import java.util.HashMap;
import java.util.Map;

import model.task.Trigger;

public class WeiboSilentTrigger implements Trigger {

	private static final long serialVersionUID = 1L;

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
		this.code =code;
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public int getType() {
		return WEIBO_SILENT;
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getProperties() {
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				// TODO
			}
		};
	}

}
