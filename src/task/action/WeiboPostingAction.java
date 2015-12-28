package task.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import task.weibo.UpdateStatus;

import model.task.Action;

public class WeiboPostingAction implements Action {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(WeiboPostingAction.class);
	private final String user;
	private final String code;
	private final String content;

	public WeiboPostingAction(String user, String code, String content) {
		this.user = user;
		this.code = code;
		this.content = content;
	}

	@Override
	public int getType() {
		return WEIBO_POSTING;
	}

	@Override
	public void perform() {
		logger.info("Post Weibo Status"+content);
		UpdateStatus.postStatus(code, content);
	}

	@Override
	public Map<String, Object> getProperties() {
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("user", user);
				put("content", content);
			}
		};
	}

	@Override
	public String toString() {
		return String.format("WeiboPushingAction{user=%s, content=%s",
				user, content);
	}

}
