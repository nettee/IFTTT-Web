package task.trigger;

import java.util.HashMap;
import java.util.Map;

import model.task.Trigger;

public class WeiboPushedTrigger implements Trigger {

	private static final long serialVersionUID = 1L;

	private String username;
	private String pattern;

	public WeiboPushedTrigger(String username, String pattern) {
		this.username = username;
		this.pattern = pattern;
	}

	@Override
	public int getType() {
		return WEIBO_PUSHED;
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
				put("username", username);
				put("pattern", pattern);
			}
		};
	}

}
