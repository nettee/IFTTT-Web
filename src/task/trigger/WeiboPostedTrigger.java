package task.trigger;

import java.util.HashMap;
import java.util.Map;

import model.task.Trigger;

public class WeiboPostedTrigger implements Trigger {

	private static final long serialVersionUID = 1L;
	
	private final String user;
	private final String code;
	private final String pattern;

	public WeiboPostedTrigger(String user, String code, String pattern) {
		this.user = user;
		this.code = code;
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
				put("user", user);
				put("pattern", pattern);
			}
		};
	}

	@Override
	public String toString() {
		return String.format("WeiboPushedTrigger{user=%s, pattern=%s}", user,
				pattern);
	}

}
