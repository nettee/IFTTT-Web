package task.trigger;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import task.weibo.GetNewStatus;

import model.task.Trigger;

public class WeiboPostedTrigger implements Trigger {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(WeiboPostedTrigger.class);
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
		return WEIBO_POSTED;
	}

	@Override
	public boolean test() {
		String content=GetNewStatus.getNewStatus(code);
		logger.info("Latest Status: "+content+" test if contains"+pattern);
		if(content.contains(pattern)) return true;
		else return false;
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
